package ru.alvioneurope.sample.mapper;

import ru.alvioneurope.sample.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    List<Order> getOrderHistory();
    void storeOrder(Map<String, Object> params);
    List<Order> getOrdersStatus();
    void clearOldOrdersStatus(String orders);
}
