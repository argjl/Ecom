package com.bothBEandFE.ecom.Services.Admin.Coupon;

import com.bothBEandFE.ecom.Dto.OrderDto;
import com.bothBEandFE.ecom.Entity.Coupon;
import com.bothBEandFE.ecom.Exceptions.ValidationException;
import com.bothBEandFE.ecom.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon Code Already Exists.");
        }
        else{
            return couponRepository.save(coupon);
        }
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }



}
