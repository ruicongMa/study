package cn.springboot.mark;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Mark
 * @date 2022/7/16 9:24
 */
@Component
@Data
public class Animal {

    private String name;
    private int age;
}
