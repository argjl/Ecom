package com.bothBEandFE.ecom.Entity;

import com.bothBEandFE.ecom.Dto.CartItemsDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    @JsonBackReference
    private Order order;

public CartItemsDto getCartItemsDto(){
    CartItemsDto cartItemsDto= new CartItemsDto();
    cartItemsDto.setId(id);
    cartItemsDto.setPrice(price);
    cartItemsDto.setProductId(product.getId());
    cartItemsDto.setQuantity(quantity);
    cartItemsDto.setUserId(user.getId());
    cartItemsDto.setProductName(product.getName());
    cartItemsDto.setReturnedImg(product.getImg());

    return cartItemsDto;
}

}
