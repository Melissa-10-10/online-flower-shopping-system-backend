package rw.auca.online_flower_shopping_system.service;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.stereotype.Service;
import rw.auca.online_flower_shopping_system.domain.FlowerOrder;
import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;
import rw.auca.online_flower_shopping_system.repository.FlowerOrderRepository;

@Service
public class FlowerOrderService {
    private final FlowerOrderRepository flowerOrderRepository;

    public FlowerOrderService(FlowerOrderRepository flowerOrderRepository){
        this.flowerOrderRepository = flowerOrderRepository;
    }

    public String saveFlowerOrder(FlowerOrder flowerOrder) {
    if (flowerOrder.getOrderDate().isBefore(LocalDate.now())) {
        return "Flower order date cannot be in the past";
    }
    if (flowerOrderRepository.existsByFloristIdAndOrderDateAndStatusNot(
            flowerOrder.getFlorist().getId(),
            flowerOrder.getOrderDate(),
            FlowerOrderStatus.REJECTED)) {
        return "Florist already has an order on that date";
    }
    flowerOrder.setStatus(FlowerOrderStatus.ORDERED);
    flowerOrderRepository.save(flowerOrder);
    return "Flower order saved successfully";
}
public String updateStatus(UUID id, FlowerOrderStatus newStatus) {
    FlowerOrder flowerOrder = flowerOrderRepository.findById(id).orElse(null);
    if (flowerOrder == null) {
        return "The flower order with that id does not exist";
    }

    FlowerOrderStatus current = flowerOrder.getStatus();
    boolean allowed = false;

    if (current == FlowerOrderStatus.ORDERED) {
        allowed = newStatus == FlowerOrderStatus.PREPARING
            || newStatus == FlowerOrderStatus.REJECTED;
    } else if (current == FlowerOrderStatus.PREPARING) {
        allowed = newStatus == FlowerOrderStatus.DELIVERED
            || newStatus == FlowerOrderStatus.REJECTED;
    }

    if (!allowed) {
        return "Cannot change status from " + current + " to " + newStatus;
    }

    flowerOrder.setStatus(newStatus);
    flowerOrderRepository.save(flowerOrder);
    return "Flower order status updated successfully";
}
    public List<FlowerOrder> getBetween(LocalDate start, LocalDate end) {
    return flowerOrderRepository.findByOrderDateBetween(start, end);
}

    public List<FlowerOrder> getAll() {

        return flowerOrderRepository.findAll();

    }
    public List<FlowerOrder> getByStatus(FlowerOrderStatus status) {
    return flowerOrderRepository.findByStatusOrderByOrderDateAsc(status);
}

    public Optional<FlowerOrder> getById(UUID id){
        return flowerOrderRepository.findById(id);

    }
    public String updateFlowerOrder(UUID id, FlowerOrder request){
        FlowerOrder flowerOrder = flowerOrderRepository.findById(id).orElse(null);
        if (flowerOrder == null){
            return "order with that id does not exist";

        }

        flowerOrder.setOrderDate(request.getOrderDate());
        flowerOrder.setBouquetName(request.getBouquetName());
        flowerOrder.setDeliveryAddress(request.getDeliveryAddress());
        flowerOrderRepository.save(flowerOrder);
        return "flower order saved successfully";
    
    }

    public String deleteFlowerOrder(UUID id) {
        FlowerOrder flowerOrder = flowerOrderRepository.findById(id).orElse(null);
        if (flowerOrder == null) {
            return "order with that id does not exist";
        }
        flowerOrderRepository.delete(flowerOrder);
        return "Flower order deleted successfully";
    }

}