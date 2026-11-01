package rw.auca.online_flower_shopping_system.service;



import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.online_flower_shopping_system.domain.DeliveryProof;
import rw.auca.online_flower_shopping_system.domain.FlowerOrder;
import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;
import rw.auca.online_flower_shopping_system.repository.DeliveryProofRepository;
import rw.auca.online_flower_shopping_system.repository.FlowerOrderRepository;

@Service
public class DeliveryProofService {

    private final DeliveryProofRepository deliveryProofRepository;
    private final FlowerOrderRepository flowerOrderRepository;

    public DeliveryProofService(DeliveryProofRepository deliveryProofRepository,
                                FlowerOrderRepository flowerOrderRepository) {
        this.deliveryProofRepository = deliveryProofRepository;
        this.flowerOrderRepository = flowerOrderRepository;
    }

    // E1
    public String saveDeliveryProof(UUID flowerOrderId, DeliveryProof proof) {
        FlowerOrder order = flowerOrderRepository.findById(flowerOrderId).orElse(null);
        if (order == null) {
            return "The flower order with that id does not exist";
        }
        if (order.getStatus() != FlowerOrderStatus.DELIVERED) {
            return "Delivery proofs are only for DELIVERED flower orders";
        }
        proof.setFlowerOrder(order);
        proof.setConfirmedDate(LocalDate.now());
        deliveryProofRepository.save(proof);
        return "Delivery proof saved successfully";
    }

    // E2
    public List<DeliveryProof> getByCustomerPhone(String phone) {
        return deliveryProofRepository.findProofsByCustomerPhone(phone);
    }

    // E3
    public String deleteDeliveryProof(UUID id) {
        DeliveryProof proof = deliveryProofRepository.findById(id).orElse(null);
        if (proof == null) {
            return "The delivery proof with that id does not exist";
        }
        deliveryProofRepository.delete(proof);
        return "Delivery proof deleted successfully";
    }
}