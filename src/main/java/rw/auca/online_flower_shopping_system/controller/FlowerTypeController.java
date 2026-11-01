package rw.auca.online_flower_shopping_system.controller;

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
import org.springframework.web.bind.annotation.RestController;

import rw.auca.online_flower_shopping_system.domain.FlowerType;
import rw.auca.online_flower_shopping_system.service.FlowerTypeService;

@RestController
@RequestMapping("/api/flower-types")
public class FlowerTypeController {

    private final FlowerTypeService flowerTypeService;

    public FlowerTypeController(FlowerTypeService flowerTypeService) {
        this.flowerTypeService = flowerTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFlowerType(@RequestBody FlowerType flowerType) {
        String result = flowerTypeService.saveFlowerType(flowerType);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlowerType>> getAll() {
        return ResponseEntity.ok(flowerTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<FlowerType> result = flowerTypeService.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The flower type with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }
    @GetMapping("/unused")
public ResponseEntity<List<FlowerType>> getUnused() {
    return ResponseEntity.ok(flowerTypeService.getUnused());
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlowerType(@PathVariable UUID id, @RequestBody FlowerType request) {
        String result = flowerTypeService.updateFlowerType(id, request);
        if (result.equals("The flower type with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlowerType(@PathVariable UUID id) {
        String result = flowerTypeService.deleteFlowerType(id);
        if (result.equals("The flower type with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
