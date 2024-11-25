package com.bothBEandFE.ecom.Services.Admin.Coupon;

import com.bothBEandFE.ecom.Entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
