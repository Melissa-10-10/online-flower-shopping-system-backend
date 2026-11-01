package rw.auca.online_flower_shopping_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.online_flower_shopping_system.domain.Florist;
import rw.auca.online_flower_shopping_system.service.FloristService;

@RestController
@RequestMapping("/api/florists")
public class FloristController {

    private final FloristService floristService;

    public FloristController(FloristService floristService) {
        this.floristService = floristService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFlorist(@RequestBody Florist florist) {
        String result = floristService.saveFlorist(florist);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Florist>> getAll() {
        return ResponseEntity.ok(floristService.getAll());
    }
    @GetMapping("/by-flower-type")
public ResponseEntity<List<Florist>> getByFlowerType(@RequestParam String name) {
    return ResponseEntity.ok(floristService.getByFlowerType(name));
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Florist> result = floristService.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The florist with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }
    @PatchMapping("/{floristId}/outlet")
public ResponseEntity<String> changeOutlet(@PathVariable UUID floristId,
                                        @RequestParam int outletNumber) {
    String result = floristService.changeOutlet(floristId, outletNumber);
    if (result.endsWith("does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    if (result.equals("That outlet is taken by another florist")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
    return ResponseEntity.ok(result);
}

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFlorist(@PathVariable UUID id, @RequestBody Florist request) {
        String result = floristService.updateFlorist(id, request);
        if (result.equals("The florist with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlorist(@PathVariable UUID id) {
        String result = floristService.deleteFlorist(id);
        if (result.equals("The florist with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
