package rw.auca.online_flower_shopping_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rw.auca.online_flower_shopping_system.domain.FlowerType;
import rw.auca.online_flower_shopping_system.domain.Florist;
import rw.auca.online_flower_shopping_system.domain.Outlet;
import rw.auca.online_flower_shopping_system.repository.FlowerTypeRepository;
import rw.auca.online_flower_shopping_system.repository.FloristRepository;
import rw.auca.online_flower_shopping_system.repository.OutletRepository;

@Service
public class FloristService {

    private final FloristRepository floristRepository;
    private final OutletRepository outletRepository;
    private final FlowerTypeRepository flowerTypeRepository;

    public FloristService(FloristRepository floristRepository,
                        OutletRepository outletRepository,
                        FlowerTypeRepository flowerTypeRepository) {
        this.floristRepository = floristRepository;
        this.outletRepository = outletRepository;
        this.flowerTypeRepository = flowerTypeRepository;
    }

    public String saveFlorist(Florist florist) {
        floristRepository.save(florist);
        return "Florist saved successfully";
    }

    public List<Florist> getAll() {
        return floristRepository.findAll();
    }

    public List<Florist> getByFlowerType(String name) {
        return floristRepository.findByFlowerTypeName(name);
    }

    public Optional<Florist> getById(UUID id) {
        return floristRepository.findById(id);
    }

    public String updateFlorist(UUID id, Florist request) {
        Florist florist = floristRepository.findById(id).orElse(null);
        if (florist == null) {
            return "The florist with that id does not exist";
        }
        florist.setFirstName(request.getFirstName());
        florist.setLastName(request.getLastName());
        florist.setPhone(request.getPhone());
        floristRepository.save(florist);
        return "Florist updated successfully";
    }

    public String deleteFlorist(UUID id) {
        Florist florist = floristRepository.findById(id).orElse(null);
        if (florist == null) {
            return "The florist with that id does not exist";
        }
        floristRepository.delete(florist);
        return "Florist deleted successfully";
    }

    // D3: add a flower type to a florist
    @Transactional
    public String addFlowerType(UUID floristId, UUID flowerTypeId) {
        Florist florist = floristRepository.findById(floristId).orElse(null);
        if (florist == null) {
            return "The florist with that id does not exist";
        }
        FlowerType flowerType = flowerTypeRepository.findById(flowerTypeId).orElse(null);
        if (flowerType == null) {
            return "The flower type with that id does not exist";
        }
        for (FlowerType existing : florist.getFlowerTypes()) {
            if (existing.getId().equals(flowerTypeId)) {
                return "The florist already has that flower type";
            }
        }
        florist.getFlowerTypes().add(flowerType);
        floristRepository.save(florist);
        return "Flower type added to the florist";
    }

    // D4: change the outlet of a florist
    public String changeOutlet(UUID floristId, int outletNumber) {
        Florist florist = floristRepository.findById(floristId).orElse(null);
        if (florist == null) {
            return "The florist with that id does not exist";
        }
        Outlet outlet = outletRepository.findByOutletNumber(outletNumber).orElse(null);
        if (outlet == null) {
            return "The outlet with that number does not exist";
        }
        if (floristRepository.existsByOutletIdAndIdNot(outlet.getId(), floristId)) {
            return "That outlet is taken by another florist";
        }
        florist.setOutlet(outlet);
        floristRepository.save(florist);
        return "Outlet changed successfully";
    }
}