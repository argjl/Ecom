package com.bothBEandFE.ecom.Services.Admin.Category;

import java.util.List;

import com.bothBEandFE.ecom.Dto.CategoryDto;
import com.bothBEandFE.ecom.Entity.Category;

public interface CategoryService {

	Category createCategory(CategoryDto categoryDto);

	List<Category> getAllCategories();
}
