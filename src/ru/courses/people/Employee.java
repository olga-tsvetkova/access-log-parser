package ru.courses.people;

public class Employee {
    private String name; //хранит имя сотрудника
    private Department department;//указывает на объект типа Department, в котором сотрудник работает

    public Employee(String name, Department department) {
        this.name = name;
        setDepartment(department); // Проверяем условие принадлежности начальника своему отделу
    }

    public void setDepartment(Department department) {
       if (department != null && department.getHead() == this) { // Если сотрудник становится начальником своего отдела
                department.setHead(this);
            }
    this.department = department;
    }


    @Override
    public String toString() {
        if (isHead()) {
            return name + " начальник отдела " + department.getName();
        } else {
            return name + " работает в отделе " + department.getName() +
                    ", начальник которого " + department.getHead().name;
        }
    }

    public boolean isHead() {
        return department != null && department.getHead() == this;
    }

}


