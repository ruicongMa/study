package com.mark.openclosed;

/**
 * @author Mark
 * @date 2020/4/21 21:39
 */
public class Test {

    public static void main(String[] args) {
        ICourse javaCourse = new JavaCourse(1, "Java架构师课程", 100d);
        System.out.println("课程id:" + javaCourse.getId()
                + "\n课程名称：" + javaCourse.getName()
                + "\n课程价格：" + javaCourse.getPrice());

        System.out.println("============================");

        /**
         * 现在我们要给Java架构师课程做活动，价格优惠。如果修改JavaCourse中的getPrice()方法，
         * 则会存在一定的风险，可能影响其他地方的调用结构。我们如何在不修改原有代码前提下，实现价格优惠这个功能呢？
         * 现在我们再写一个处理优惠逻辑的类，JavaDiscountCourse类（思考一下为什么要叫JavaDiscountCourse,而不叫DiscountCourse）
         */
        JavaDiscountCourse javaDiscountCourse = new JavaDiscountCourse(1, "Java架构师课程", 100d);
        System.out.println("课程id:" + javaDiscountCourse.getId()
                + "\n课程名称：" + javaDiscountCourse.getName()
                + "\n课程原价格：" + javaDiscountCourse.getOriginPrice()
                + "\n课程优惠后的价格：" + javaDiscountCourse.getPrice());

    }
}
