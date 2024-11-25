package com.bothBEandFE.ecom.Services.Customer;

import com.bothBEandFE.ecom.Dto.ProductDetailDto;
import com.bothBEandFE.ecom.Dto.ProductDto;
import com.bothBEandFE.ecom.Entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public interface CustmerProductService {

    List<ProductDto> getAllProducts() ;

    List<ProductDto> searchProductsByTitle(String name) ;

    ProductDetailDto getProductDetailById(Long productId);
}
