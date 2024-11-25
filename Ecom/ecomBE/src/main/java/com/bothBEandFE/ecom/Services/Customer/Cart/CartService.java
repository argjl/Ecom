package com.bothBEandFE.ecom.Services.Customer.Cart;

import com.bothBEandFE.ecom.Dto.AddProductInCartDto;
import com.bothBEandFE.ecom.Dto.OrderDto;
import com.bothBEandFE.ecom.Dto.PlaceOrderDto;
import com.bothBEandFE.ecom.Entity.CartItems;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

//    boolean deleteCartItems(Long id);

    OrderDto applyCoupon(Long userId, String code);

    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlacedOrders(Long userId);

    OrderDto searchOrderByTrackingId(UUID trackingId);
}
