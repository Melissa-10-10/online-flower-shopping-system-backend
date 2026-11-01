package rw.auca.online_flower_shopping_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.online_flower_shopping_system.domain.Customer;
import rw.auca.online_flower_shopping_system.domain.FlowerOrder;
import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;

public interface FlowerOrderRepository extends JpaRepository<FlowerOrder, UUID> {

    List<FlowerOrder> findByStatusOrderByOrderDateAsc(FlowerOrderStatus status);

    List<FlowerOrder> findByOrderDateBetween(LocalDate start, LocalDate end);

    @Query("""
        select distinct o.customer
        from FlowerOrder o
        where o.florist.id = :floristId
        """)
    List<Customer> findCustomersByFloristId(@Param("floristId") UUID floristId);

    boolean existsByFloristIdAndOrderDateAndStatusNot(UUID floristId, LocalDate orderDate, FlowerOrderStatus status);
}