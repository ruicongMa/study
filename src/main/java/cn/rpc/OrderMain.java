package cn.rpc;

/**
 * @author Mark
 * @date 2020/11/1 17:45
 */
public class OrderMain {

    public static void main(String[] args) throws Exception {
        PublishService publishService = new PublishService();
        publishService.publish(new OrderService(), 8080);
    }
}
