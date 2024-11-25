package com.bothBEandFE.ecom.Controller.Customer;

import com.bothBEandFE.ecom.Dto.OrderedProductResponseDto;
import com.bothBEandFE.ecom.Dto.ReviewDto;
import com.bothBEandFE.ecom.Services.Customer.Review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductDetailsByUserId(@PathVariable Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductDetailsByOrderId(orderId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
        if (reviewDto1 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
    }
}

