package rw.auca.online_flower_shopping_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import rw.auca.online_flower_shopping_system.domain.Customer;
import rw.auca.online_flower_shopping_system.repository.CustomerRepository;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String saveCustomer(Customer customer){
        customerRepository.save(customer);
        return "Customer saved successfully";
    }
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Optional<Customer> getById(UUID id){
        return customerRepository.findById(id);
    
    }
    public String updateCustomer(UUID id, Customer request){
        Customer customer =  customerRepository.findById(id).orElse(null);
        if (customer == null){
            return "The customer with that id does not exist";

        }
        customer.setfirstName(request.getfirstName());
        customer.setlastName(request.getlastName());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customerRepository.save(customer);
        return "Customer updated successfully!";
    
    }

    public String deleteCustomer(UUID id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null){
            return "the customer with that id does not exist";

        }
        customerRepository.delete(customer);
        return "Customer deleted successfully";
    }

    }


