package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University  {
    String name;
    int age;

    private List<Student> students = new ArrayList<>();
    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double grade) {
        for (Student student : students) {
            if(student.getAverageGrade()==grade){
                return student;
            }
        }

        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentWithMaxAverage = students.get(0);
        for (Student student : students) {
            if(student.getAverageGrade()>studentWithMaxAverage.getAverageGrade())
                studentWithMaxAverage = student;
        }

        return studentWithMaxAverage;
    }

    public Student getStudentWithMinAverageGrade() {
        Student studentWithMinAverageGrade = students.get(0);
        for (Student student : students) {
            if(student.getAverageGrade()<studentWithMinAverageGrade.getAverageGrade())
                studentWithMinAverageGrade = student;
        }

        return studentWithMinAverageGrade;

    }
    public void expel(Student student) {
        students.remove(student);
        }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}