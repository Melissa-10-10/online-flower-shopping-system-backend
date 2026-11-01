package rw.auca.online_flower_shopping_system.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import rw.auca.online_flower_shopping_system.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}