package com.bothBEandFE.ecom.Services.Customer;

import com.bothBEandFE.ecom.Dto.ProductDetailDto;
import com.bothBEandFE.ecom.Dto.ProductDto;
import com.bothBEandFE.ecom.Entity.FAQ;
import com.bothBEandFE.ecom.Entity.Product;
import com.bothBEandFE.ecom.Entity.Review;
import com.bothBEandFE.ecom.Repository.FAQRepository;
import com.bothBEandFE.ecom.Repository.ProductRepository;
import com.bothBEandFE.ecom.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustmerProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductsByTitle(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailDto getProductDetailById(Long productId){
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            List<FAQ> faqList=faqRepository.findAllByProductId(productId);
            List<Review> reviewList=reviewRepository.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();
            productDetailDto.setProductDto(optionalProduct.get().getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailDto;
        }

        return null;
    }
}
