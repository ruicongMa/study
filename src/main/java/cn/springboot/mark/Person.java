package cn.springboot.mark;

import lombok.Builder;

/**
 * @author Mark
 * @create 2017/6/5
 */
@Builder
public class Person {

    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public Person() {
    // }

    protected void say() {
        System.out.println("protected void say");
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public class Ha {

        public void say() {
            Person.this.say();
        }
    }
}
