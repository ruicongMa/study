import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author Mark
 * @date 2021/8/9 10:40
 */
public class TestReactive {

    @Test
    public void test01() {
        Consumer<String> consumer = System.out::println;

        consumer.accept("abc");

        Consumer<Person> personConsumer = Person::say;
        personConsumer.accept(new Person("zhangsan", 18));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private String name;
        private Integer age;

        public static void say(Person person) {
            System.out.println(person);
        }
    }

}
