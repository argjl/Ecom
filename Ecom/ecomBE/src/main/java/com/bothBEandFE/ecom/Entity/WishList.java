package com.bothBEandFE.ecom.Entity;

import com.bothBEandFE.ecom.Dto.WishListDto;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public WishListDto getWishListDto(){
        WishListDto wishListDto= new WishListDto();

        wishListDto.setId(id);
        wishListDto.setProductId(product.getId());
        wishListDto.setPrice(product.getPrice());
        wishListDto.setReturnedImg(product.getImg());
        wishListDto.setProductName(product.getName());
        wishListDto.setProductDescription(product.getDescription());
        wishListDto.setUserId(user.getId());

        return wishListDto;
    }

}
