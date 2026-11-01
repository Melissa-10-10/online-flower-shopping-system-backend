package rw.auca.online_flower_shopping_system.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.online_flower_shopping_system.domain.DeliveryProof;
import rw.auca.online_flower_shopping_system.service.DeliveryProofService;

@RestController
@RequestMapping("/api/delivery-proofs")
public class DeliveryProofController {

    private final DeliveryProofService deliveryProofService;

    public DeliveryProofController(DeliveryProofService deliveryProofService) {
        this.deliveryProofService = deliveryProofService;
    }

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> saveDeliveryProof(@RequestParam UUID flowerOrderId,
                                                    @RequestBody DeliveryProof proof) {
        String result = deliveryProofService.saveDeliveryProof(flowerOrderId, proof);
        if (result.endsWith("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        if (result.startsWith("Delivery proofs are only")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // E2
    @GetMapping("/customer/{phone}")
    public ResponseEntity<List<DeliveryProof>> getByCustomerPhone(@PathVariable String phone) {
        return ResponseEntity.ok(deliveryProofService.getByCustomerPhone(phone));
    }

    // E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDeliveryProof(@PathVariable UUID id) {
        String result = deliveryProofService.deleteDeliveryProof(id);
        if (result.endsWith("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
