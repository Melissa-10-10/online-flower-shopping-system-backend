package rw.auca.online_flower_shopping_system.repository;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.online_flower_shopping_system.domain.DeliveryProof;

public interface DeliveryProofRepository extends JpaRepository<DeliveryProof, UUID> {

    @Query("""
        select d
        from DeliveryProof d
        where d.flowerOrder.customer.phone = :phone
        order by d.confirmedDate desc
        """)
    List<DeliveryProof> findProofsByCustomerPhone(@Param("phone") String phone);

    boolean existsByFlowerOrderId(UUID flowerOrderId);
}
