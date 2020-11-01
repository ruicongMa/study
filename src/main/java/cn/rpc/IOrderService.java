package cn.rpc;

import java.util.List;

/**
 * @author Mark
 * @date 2020/11/1 17:41
 */
public interface IOrderService {

    List<String> getOrderList();

    String getOrderById(String orderId);
}
