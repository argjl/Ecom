package com.bothBEandFE.ecom.Dto;

import com.bothBEandFE.ecom.Entity.CartItems;
import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String userName;

    private String couponName;

    private List<CartItemsDto> cartItems;


}
