package rw.auca.online_flower_shopping_system.controller;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

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
import rw.auca.online_flower_shopping_system.domain.Customer;
import rw.auca.online_flower_shopping_system.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping("/save")

    public ResponseEntity<String>saveCustomer(@RequestBody Customer customer){
        String result = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok(customerService.getAll());

}
@GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        return ResponseEntity.ok(customerService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable UUID id, @RequestBody Customer request){
        String result = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID id){
        String result = customerService.deleteCustomer(id);
        return ResponseEntity.ok(result);
    }

}