package cn.springboot.mark.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Mark
 * @date 2021/7/25 21:36
 */
@ConfigurationProperties(prefix = "person")
@Component
// @PropertySource("classpath:conf/person.properties")
public class PersonConfig {

    private String name;
    private int age;

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


    @Override
    public String toString() {
        return "PersonConfig{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
