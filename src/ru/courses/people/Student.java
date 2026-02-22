package ru.courses.people;

public class Student {
    private String name;
    private int[] grades;


    public Student(String name, int[] grades) {
        this.name = name;
        for (int i = 0; i < grades.length; i++) {
            if (grades[i] < 2 || grades[i] > 5) {
                throw new IllegalArgumentException("Оценка должна быть между 2 и 5");
            }
        }
        this.grades = grades.clone();
    }

    public Student(String name) {
        this.name = name;
    }

    public int[] getGrades() {
        return grades.clone();
    }

    public void addGrade(int grade) {
        // проверка значения оценки на допустимость
        if (grade < 2 || grade> 5)  throw new IllegalArgumentException("Оценка должна быть между 2 и 5");
        // создание нового массива с размером на 1 больше текущего массива оценок
        int[] newGrades = new int[grades.length + 1];
        // копирование элементов в новый массив с добавлением новой оценки в конец массива
        System.arraycopy(grades, 0, newGrades, 0, grades.length);
        newGrades[newGrades.length - 1] = grade;
        // присваивание нового массива полю grades
        this.grades = newGrades;
    }


    @Override
    public String toString() {
        String buf = "";
        for (int i = 0; i < grades.length; i++) {
            buf += grades[i] + ",";
        }
        buf = buf.substring(0, buf.length() - 1);
        return name + ":["  + buf +"]";
    }



    }


