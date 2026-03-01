import java.lang.reflect.Field;

public class Nullifier {
    public static void setAllReferenceFieldsToNull(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return;
        }
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // Пропускаем статические поля — они принадлежат классу, а не объекту
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                // Разрешаем доступ к полю (включая private, protected и т.д.)
                field.setAccessible(true);
                // Проверяем, что тип поля — ссылочный (не примитив)
                if (!field.getType().isPrimitive()) {
                    field.set(obj, null);
                }
            }
            // Переходим к родительскому классу для обработки унаследованных полей
            clazz = clazz.getSuperclass();
        }
    }
}
