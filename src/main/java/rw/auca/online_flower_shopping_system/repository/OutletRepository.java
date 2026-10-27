package rw.auca.online_flower_shopping_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.online_flower_shopping_system.domain.Outlet;

public interface OutletRepository extends JpaRepository<Outlet, UUID> {

}