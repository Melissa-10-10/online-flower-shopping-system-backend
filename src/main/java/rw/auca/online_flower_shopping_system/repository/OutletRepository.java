package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.auca.online_flower_shopping_system.domain.Outlet;

public interface OutletRepository extends JpaRepository<Outlet, UUID> {
    @Query("""
    select o
    from Outlet o
    where not exists (
        select f.id from Florist f where f.outlet = o
    )
    order by o.outletNumber
    """)
List<Outlet> findFreeOutlets();
Optional<Outlet> findByOutletNumber(int outletNumber);

}