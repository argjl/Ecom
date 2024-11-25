package com.bothBEandFE.ecom.Repository;

import com.bothBEandFE.ecom.Entity.Order;
import com.bothBEandFE.ecom.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);

    @Query(value = "SELECT * FROM orders WHERE LEFT(tracking_id, 16) = UNHEX(REPLACE(:trackingId, '-', ''))", nativeQuery = true)
    Optional<Order> findByTrackingId(@Param("trackingId") String trackingId);

    List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByOrderStatus(OrderStatus status);
}
