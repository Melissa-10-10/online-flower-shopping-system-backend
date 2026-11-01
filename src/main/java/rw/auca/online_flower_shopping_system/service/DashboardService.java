package rw.auca.online_flower_shopping_system.service;



import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rw.auca.online_flower_shopping_system.domain.FlowerOrderStatus;
import rw.auca.online_flower_shopping_system.repository.CustomerRepository;
import rw.auca.online_flower_shopping_system.repository.DeliveryProofRepository;
import rw.auca.online_flower_shopping_system.repository.FlowerOrderRepository;
import rw.auca.online_flower_shopping_system.repository.FloristRepository;
import rw.auca.online_flower_shopping_system.repository.OutletRepository;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final FloristRepository floristRepository;
    private final OutletRepository outletRepository;
    private final FlowerOrderRepository flowerOrderRepository;
    private final DeliveryProofRepository deliveryProofRepository;

    public DashboardService(CustomerRepository customerRepository,
                            FloristRepository floristRepository,
                            OutletRepository outletRepository,
                            FlowerOrderRepository flowerOrderRepository,
                            DeliveryProofRepository deliveryProofRepository) {
        this.customerRepository = customerRepository;
        this.floristRepository = floristRepository;
        this.outletRepository = outletRepository;
        this.flowerOrderRepository = flowerOrderRepository;
        this.deliveryProofRepository = deliveryProofRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("customers", customerRepository.count());
        summary.put("florists", floristRepository.count());
        summary.put("freeOutlets", (long) outletRepository.findFreeOutlets().size());
        summary.put("ordersToday", flowerOrderRepository.countByOrderDate(LocalDate.now()));
        summary.put("ordered",
                (long) flowerOrderRepository.findByStatusOrderByOrderDateAsc(FlowerOrderStatus.ORDERED).size());
        summary.put("deliveryProofs", deliveryProofRepository.count());
        return summary;
    }
}