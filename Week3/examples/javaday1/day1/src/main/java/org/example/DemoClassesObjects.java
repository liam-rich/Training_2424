package org.example;

import java.util.Objects;

class DemoClassesObjects {

    static class Student {
        private static int nextId = 1;
        private static int totalStudents =0;

        private final int id;
        private String name;

        static {
            System.out.println(" [static block] Student class loaded");
        }

        Student(String name){
            this.id = nextId++;
            this.name = name;
            totalStudents++;
        }

        public static int getTotalStudents() {
            return totalStudents;
        }

        public static void setTotalStudents(int totalStudents) {
            Student.totalStudents = totalStudents;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {

        Student a = new Student("Asha");
        Student b = new Student("Ben");
        System.out.println(a);
        System.out.println(b);

    }

}
