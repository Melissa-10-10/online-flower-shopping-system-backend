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

import rw.auca.online_flower_shopping_system.domain.Outlet;
import rw.auca.online_flower_shopping_system.service.OutletService;

@RestController
@RequestMapping("/api/outlets")
public class OutletController {

    private final OutletService outletService;

    public OutletController(OutletService outletService) {
        this.outletService = outletService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveOutlet(@RequestBody Outlet outlet) {
        String result = outletService.saveOutlet(outlet);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Outlet>> getAll() {
        return ResponseEntity.ok(outletService.getAll());
    }
    @GetMapping("/free")
public ResponseEntity<List<Outlet>> getFreeOutlets() {
    return ResponseEntity.ok(outletService.getFreeOutlets());
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Outlet> result = outletService.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The outlet with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOutlet(@PathVariable UUID id, @RequestBody Outlet request) {
        String result = outletService.updateOutlet(id, request);
        if (result.equals("The outlet with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOutlet(@PathVariable UUID id) {
        String result = outletService.deleteOutlet(id);
        if (result.equals("The outlet with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}
