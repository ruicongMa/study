package com.mark.dependenceinversion;

/**
 * @author Mark
 * @date 2020/4/22 8:57
 */
public class DependenceInversionTest {

    public static void main(String[] args) {
        Mark mark = new Mark();
        mark.studyJavaCourse();
        mark.studyPythonCourse();
        /**
         * 现在Mark还想学习AI人工智能的课程，这个时候，业务扩展，我们的代码要从底层到高层（调用层）一次修改代码。
         * 在Mark类中增加studyAICourse()的方法，在高层也要追加代用，如此一来，系统发布以后，实际上是非常不稳定的，
         * 在修改代码的同时也会带来意想不到的风险。
         */
        mark.studyAICourse();

        System.out.println("==================");

        //方式一
        mark.study(new JavaCourse());
        mark.study(new PythonCourse());

        System.out.println("==================");

        //方式二：通过构造器注入
        mark = new Mark(new JavaCourse());
        mark.study();
        mark = new Mark(new PythonCourse());
        mark.study();

        System.out.println("==================");

        //方式三：通过Setter方式注入
        mark.setICourse(new JavaCourse());
        mark.study();
        mark.setICourse(new PythonCourse());
        mark.study();

    }
}
