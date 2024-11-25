package com.bothBEandFE.ecom.Controller.Admin;

import com.bothBEandFE.ecom.Dto.AnalyticResponse;
import com.bothBEandFE.ecom.Dto.OrderDto;
import com.bothBEandFE.ecom.Services.Admin.AdminOrder.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrder(){
        return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
    }

    @GetMapping("/orders/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){
        System.out.println("Entered the ChangeOrderStatus");
        OrderDto orderDto=adminOrderService.changeOrderStatus(orderId,status);
        if(orderDto==null){
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticResponse> calculateAnalytics(){
        return ResponseEntity.ok(adminOrderService.calculateAnalytics());
    }
}
