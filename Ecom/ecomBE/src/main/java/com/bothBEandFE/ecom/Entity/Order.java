package com.bothBEandFE.ecom.Entity;

import com.bothBEandFE.ecom.Dto.OrderDto;
import com.bothBEandFE.ecom.Enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    @JsonManagedReference
    private List<CartItems> cartItems;

    public OrderDto getOrderDto(){
        OrderDto orderDto=new OrderDto();

        orderDto.setId(id);
        orderDto.setAddress(address);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setTrackingId(trackingId);
        orderDto.setAmount(amount);
        orderDto.setDate(date);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setUserName(user.getName());

        if(coupon!=null){
            orderDto.setCouponName(coupon.getName());
        }
        return orderDto;
    }


}
