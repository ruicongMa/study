package cn.springboot.mark;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author Mark
 * @date 2022/5/7 20:15
 */
@Service
public class EventService implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        if (source != null && "moe".equals(source.toString())) {
            System.out.println("EventService 接受到了一个事件" + event);
        }
    }
}
