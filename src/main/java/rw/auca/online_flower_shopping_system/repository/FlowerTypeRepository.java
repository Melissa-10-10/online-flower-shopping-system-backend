package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.auca.online_flower_shopping_system.domain.FlowerType;

public interface FlowerTypeRepository extends JpaRepository<FlowerType, UUID>{
    @Query("select ft from FlowerType ft where ft.florists is empty")
List<FlowerType> findUnusedFlowerTypes();

}