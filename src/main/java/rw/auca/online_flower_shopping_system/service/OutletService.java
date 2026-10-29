package rw.auca.online_flower_shopping_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import rw.auca.online_flower_shopping_system.domain.Outlet;
import rw.auca.online_flower_shopping_system.repository.OutletRepository;

@Service
public class OutletService{
    private final OutletRepository outletRepository;

    public OutletService(OutletRepository outletRepository){
        this.outletRepository = outletRepository;

    }
    public String saveOutlet(Outlet outlet) {
        outletRepository.save(outlet);
        return "outlet saved successfully";

    }
    public List<Outlet> getAll(){
        return outletRepository.findAll();
    }
    public Optional<Outlet> getById(UUID id){
        return outletRepository.findById(id);
    }

    public String deleteOutlet(UUID id){
        Outlet outlet = outletRepository.findById(id).orElse(null);
        if (outlet ==null){
            return "The outlet with that id does not exist";

        }
        outletRepository.delete(outlet);
        return "Outlet deleted successfully";


    }
    public String updateOutlet(UUID id, Outlet request){
        Outlet outlet = outletRepository.findById(id).orElse(null);
        if (outlet == null){
            return "The outlet with that id does not exist";
        }
        outlet.setOutletNumber(request.getOutletNumber());
        outlet.setName(request.getName());
        outletRepository.save(outlet);
        return "Outlet updated successfully";
    }
    }
