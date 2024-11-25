package com.bothBEandFE.ecom.Controller.Customer;

import com.bothBEandFE.ecom.Dto.WishListDto;
import com.bothBEandFE.ecom.Services.Customer.WishList.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping("/wishList")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto){
        try {
            WishListDto postedWishListDto = wishListService.addProductToWishList(wishListDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(postedWishListDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong");
        }
    }

    @GetMapping("/wishList/{userId}")
    public ResponseEntity<List<WishListDto>> getWishListByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishListService.getWishListByUserId(userId));
    }

}
