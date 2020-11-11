package cn.rpc;

import java.util.List;

/**
 * @author Mark
 * @date 2020/11/1 17:57
 */
public class UserMain {

    public static void main(String[] args) {
        RPCProxy rpcProxy = new RPCProxy("localhost", 8080);
        IOrderService orderService = rpcProxy.getProxy(IOrderService.class);
        List<String> orderList = orderService.getOrderList();
        System.out.println(orderList);
        String moe = orderService.getOrderById("moe");
        System.out.println(moe);
    }
}
