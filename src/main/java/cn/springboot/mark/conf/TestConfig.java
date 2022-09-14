package cn.springboot.mark.conf;

import cn.springboot.mark.Person;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Mark
 * @date 2021/7/27 10:11
 */
// @Configuration
@EnableConfigurationProperties(PersonConfig.class)
public class TestConfig {

    public TestConfig(PersonConfig personConfig) {
        System.out.println(personConfig);
    }

    @Bean
    public Person person(PersonConfig personConfig) {
        Person person = new Person(Long.valueOf(personConfig.getAge()), personConfig.getName());
        return person;
    }
}
