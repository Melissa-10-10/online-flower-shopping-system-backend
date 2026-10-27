package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.auca.online_flower_shopping_system.domain.FlowerOrder;

public interface FlowerOrderRepository extends JpaRepository<FlowerOrder, UUID>{

}