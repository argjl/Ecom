package com.bothBEandFE.ecom.Controller;

import com.bothBEandFE.ecom.Dto.OrderDto;
import com.bothBEandFE.ecom.Services.Customer.Cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class TrackingController {

    @Autowired
    private CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId) {
        OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
        System.out.println("searchOrderByTrackingId Controller tracking Id from Request"+trackingId);
        if (orderDto == null) {
            System.out.println("searchOrderByTrackingId Controller"+orderDto);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDto);
    }
}
