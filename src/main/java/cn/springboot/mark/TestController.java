package cn.springboot.mark;

import cn.springboot.mark.conf.PersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 * @author Mark
 * @create 2017/6/5
 */
@SpringBootApplication
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private PersonConfig personConfig;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/")
    public String index() {
        System.out.println(personConfig);
        return "Hello Spring Boot " + getLocalHost();
    }

    @RequestMapping("/sendEvent")
    public Object sendEvent() {
        applicationContext.publishEvent((new ApplicationEvent("moe") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        }));
        return "ok";
    }

    /**
     * mac 启动spring-boot 特别慢：http://ningg.top/tool-personal-intellij-idea-for-mac-optimize/
     * mac 启动tomcat报错：http://www.bubuko.com/infodetail-1865308.html
     * 查看本机的HostName
     *
     * @return
     */
    private String getLocalHost() {
        String host = null;
        try {
            host = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (Exception e) {
            throw new RuntimeException("Error. Failed to retrive crunchifyHost:" + e);
        }
        return host;
    }

    @RequestMapping("/{id}")
    public Person view(@PathVariable("id") Long id) {
        Person person = new Person(id, "mark");
        person.setId(id);
        return person;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestController.class, args);
    }

}
