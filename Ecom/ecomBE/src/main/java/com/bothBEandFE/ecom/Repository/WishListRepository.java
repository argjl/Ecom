package com.bothBEandFE.ecom.Repository;

import com.bothBEandFE.ecom.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);

    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
