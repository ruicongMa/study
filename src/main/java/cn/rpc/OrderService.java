package cn.rpc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark
 * @date 2020/11/1 17:43
 */
public class OrderService implements IOrderService {
    @Override
    public List<String> getOrderList() {
        List<String> list = new ArrayList<>();
        list.add("zoe");
        list.add("moe");
        return list;
    }

    @Override
    public String getOrderById(String orderId) {
        return "moe love zoe";
    }
}
