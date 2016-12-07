package ru.alvioneurope.sample.service;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvioneurope.sample.dto.CreateOrderRequest;
import ru.alvioneurope.sample.dto.CreateOrderResponse;
import ru.alvioneurope.sample.mapper.OrderMapper;
import ru.alvioneurope.sample.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lkhruschev on 06.12.2016.
 * lkhruschev@alvioneurope.ru
 * Skype: levasfx
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public CreateOrderResponse storeOrder(CreateOrderRequest orderRequest) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("string", orderRequest.getString());
        params.put("startTime", orderRequest.getStartTime());
        params.put("orderId", null);
        orderMapper.storeOrder(params);
        return new CreateOrderResponse((Long) params.get("orderId"));
    }

    public List<Order> getOrderHistory() {
        return orderMapper.getOrderHistory();
    }

    public List<Order> getOrdersStatus() {
        List<Order> orders = orderMapper.getOrdersStatus();
        if (!orders.isEmpty()) {
            List<Long> longs = FluentIterable.from(orders).transform(new Function<Order, Long>() {
                public Long apply(Order input) {
                    return input.getId();
                }
            }).toList();
            String orderIds = Joiner.on(",").join(longs);
            orderMapper.clearOldOrdersStatus(orderIds);
        }
        return orders;
    }

}
