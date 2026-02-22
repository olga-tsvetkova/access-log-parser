package ru.courses.people;

public class Department {
    private final String name;//название отдела
    private Employee head;    //ссылка на сотрудника-руководителя

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //получать начальников отделов
    public Employee getHead() {
        return head;
    }

    //устанавливать начальников отделов
    public void setHead(Employee employee) {
        if (employee != null && !employee.isHead()) {
            employee.setDepartment(this); // Устанавливаем связь обратно
        }
        this.head = employee;
    }



    }


