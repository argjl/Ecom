package com.bothBEandFE.ecom.Services.Customer.WishList;

import com.bothBEandFE.ecom.Dto.WishListDto;

import java.util.List;

public interface WishListService {

    WishListDto addProductToWishList(WishListDto wishListDto);

    List<WishListDto> getWishListByUserId(Long userId);
}
