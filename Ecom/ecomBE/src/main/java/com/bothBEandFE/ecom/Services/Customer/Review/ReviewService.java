package com.bothBEandFE.ecom.Services.Customer.Review;

import com.bothBEandFE.ecom.Dto.OrderedProductResponseDto;
import com.bothBEandFE.ecom.Dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductResponseDto getOrderedProductDetailsByOrderId(Long orderId);

    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
