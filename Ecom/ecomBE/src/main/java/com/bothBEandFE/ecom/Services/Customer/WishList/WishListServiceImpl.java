package com.bothBEandFE.ecom.Services.Customer.WishList;

import com.bothBEandFE.ecom.Dto.WishListDto;
import com.bothBEandFE.ecom.Entity.Product;
import com.bothBEandFE.ecom.Entity.User;
import com.bothBEandFE.ecom.Entity.WishList;
import com.bothBEandFE.ecom.Repository.ProductRepository;
import com.bothBEandFE.ecom.Repository.UserRepository;
import com.bothBEandFE.ecom.Repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishListRepository wishListRepository;

    public WishListDto addProductToWishList(WishListDto wishListDto) {
        Optional<Product> optionalProduct = productRepository.findById(wishListDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()) {

            boolean exists = wishListRepository.existsByUserIdAndProductId(
                    wishListDto.getUserId(), wishListDto.getProductId()
            );

            if (exists) {
                throw new IllegalArgumentException("Product already exists in the wishlist.");
            }
            WishList wishList = new WishList();

            wishList.setProduct(optionalProduct.get());
            wishList.setUser(optionalUser.get());

            return wishListRepository.save(wishList).getWishListDto();
        }
        return null;
    }

    public List<WishListDto> getWishListByUserId(Long userId) {
        return wishListRepository.findAllByUserId(userId).stream().
                map(WishList::getWishListDto).collect(Collectors.toList());
    }

}
