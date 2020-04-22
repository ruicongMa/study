package com.mark.dependenceinversion;

/**
 * @author Mark
 * @date 2020/4/22 9:01
 */
public class PythonCourse implements ICourse {

    @Override
    public void study() {
        System.out.println("Mark在学习Python课程");
    }
}
