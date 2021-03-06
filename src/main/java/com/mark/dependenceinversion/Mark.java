package com.mark.dependenceinversion;

/**
 * @author Mark
 * @date 2020/4/22 8:55
 */
public class Mark {

    public Mark() {

    }

    public void studyJavaCourse() {
        System.out.println("Mark在学习Java课程");
    }

    public void studyPythonCourse() {
        System.out.println("Mark在学习Python课程");
    }

    public void studyAICourse() {
        System.out.println("Mark在学习AI课程");
    }

    public void study(ICourse iCourse) {
        iCourse.study();
    }

    private ICourse iCourse;

    //方式二通过构造器注入
    public Mark(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    //方式三通过Setter注入
    public void setICourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study() {
        iCourse.study();
    }
}
