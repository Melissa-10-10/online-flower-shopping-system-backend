package rw.auca.online_flower_shopping_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import rw.auca.online_flower_shopping_system.domain.FlowerOrder;
import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;
import rw.auca.online_flower_shopping_system.service.FlowerOrderService;

@RestController
@RequestMapping("/api/flower-orders")
public class FlowerOrderController {

    private final FlowerOrderService flowerOrderService;

    public FlowerOrderController(FlowerOrderService flowerOrderService) {
        this.flowerOrderService = flowerOrderService;
    }

    @PostMapping("/save")
public ResponseEntity<String> saveFlowerOrder(@RequestBody FlowerOrder flowerOrder) {
    String result = flowerOrderService.saveFlowerOrder(flowerOrder);
    if (result.equals("Flower order date cannot be in the past")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    if (result.equals("Florist already has an order on that date")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
}
@PatchMapping("/{id}/status")
public ResponseEntity<String> updateStatus(@PathVariable UUID id,
                                        @RequestParam FlowerOrderStatus status) {
    String result = flowerOrderService.updateStatus(id, status);
    if (result.equals("The flower order with that id does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    if (result.startsWith("Cannot change status")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    return ResponseEntity.ok(result);
}

    @GetMapping("/all")
    public ResponseEntity<List<FlowerOrder>> getAll() {
        return ResponseEntity.ok(flowerOrderService.getAll());
    }
    @GetMapping("/between")
public ResponseEntity<List<FlowerOrder>> getBetween(@RequestParam String start,
                                                    @RequestParam String end) {
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    return ResponseEntity.ok(flowerOrderService.getBetween(startDate, endDate));
}
    @GetMapping("/by-status")
    public ResponseEntity<List<FlowerOrder>> getByStatus(@RequestParam FlowerOrderStatus status) {
    return ResponseEntity.ok(flowerOrderService.getByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<FlowerOrder> result = flowerOrderService.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The flower order with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlowerOrder(@PathVariable UUID id, @RequestBody FlowerOrder request) {
        String result = flowerOrderService.updateFlowerOrder(id, request);
        if (result.equals("The flower order with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlowerOrder(@PathVariable UUID id) {
        String result = flowerOrderService.deleteFlowerOrder(id);
        if (result.equals("The flower order with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
