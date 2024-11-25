package com.bothBEandFE.ecom.Services.Admin.AdminProduct;

import java.io.IOException;
import java.util.List;

import com.bothBEandFE.ecom.Dto.ProductDto;

public interface AdminProductService {
	
	ProductDto addProduct(ProductDto productDto) throws IOException;
	
	List<ProductDto> getAllProducts();

	List<ProductDto> getAllProductsByName(String name);

	boolean deleteProduct(Long id);

	ProductDto getProductById(Long productId);

	ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}
