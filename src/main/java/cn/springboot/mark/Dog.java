package cn.springboot.mark;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Mark
 * @date 2022/7/16 9:25
 */
@Data
@Component
public class Dog {

    private String sex;

    private Animal animal;

    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println(this);
    }

    public Dog(Animal animal, ApplicationContext applicationContext) {
        this.animal = animal;
        this.applicationContext = applicationContext;
    }
}
