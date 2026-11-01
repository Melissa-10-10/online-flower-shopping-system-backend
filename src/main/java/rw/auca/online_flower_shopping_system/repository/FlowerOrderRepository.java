package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.auca.online_flower_shopping_system.domain.FlowerOrder;
import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;

public interface FlowerOrderRepository extends JpaRepository<FlowerOrder, UUID>{
    List<FlowerOrder> findByStatusOrderByOrderDateAsc(FlowerOrderStatus status);
    List<FlowerOrder> findByOrderDateBetween(LocalDate start, LocalDate end);

}