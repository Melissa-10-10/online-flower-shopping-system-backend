package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.auca.online_flower_shopping_system.domain.Florist;

public interface FloristRepository extends JpaRepository<Florist, UUID>{
    @Query("""
    select distinct f
    from Florist f
    join f.flowerTypes ft
    where lower(ft.name) = lower(:name)
    """)
List<Florist> findByFlowerTypeName(@Param("name") String name);

}