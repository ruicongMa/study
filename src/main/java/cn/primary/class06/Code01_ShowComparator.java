package cn.primary.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Mark
 * @date 2021/6/22 下午4:28
 */
public class Code01_ShowComparator {

    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static class IdComparator implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.id, s2.id);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("张三", 5, 27);
        Student s2 = new Student("李四", 1, 17);
        Student s3 = new Student("王五", 4, 29);
        Student s4 = new Student("赵六", 3, 9);
        Student s5 = new Student("左七", 2, 34);
        PriorityQueue<Student> heap = new PriorityQueue<>(new IdComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
