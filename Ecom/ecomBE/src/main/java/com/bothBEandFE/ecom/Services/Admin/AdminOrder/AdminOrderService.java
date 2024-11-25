package com.bothBEandFE.ecom.Services.Admin.AdminOrder;

import com.bothBEandFE.ecom.Dto.AnalyticResponse;
import com.bothBEandFE.ecom.Dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticResponse calculateAnalytics();
}
