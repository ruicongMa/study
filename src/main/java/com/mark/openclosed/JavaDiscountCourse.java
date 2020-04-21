package com.mark.openclosed;

/**
 * Java架构课程优惠类
 * @author Mark
 * @date 2020/4/21 21:37
 */
public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    //课程原价格
    public Double getOriginPrice() {
        return super.getPrice();
    }

    //课程优惠价
    @Override
    public Double getPrice() {
        return super.getPrice() * 0.6;
    }
}
