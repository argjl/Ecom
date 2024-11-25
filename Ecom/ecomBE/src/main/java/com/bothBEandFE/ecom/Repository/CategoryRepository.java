package com.bothBEandFE.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bothBEandFE.ecom.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
