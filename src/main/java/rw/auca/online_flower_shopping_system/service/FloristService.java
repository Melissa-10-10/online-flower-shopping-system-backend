package rw.auca.online_flower_shopping_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import rw.auca.online_flower_shopping_system.domain.Florist;
import rw.auca.online_flower_shopping_system.repository.FloristRepository;

@Service
public class FloristService {
    private final FloristRepository floristRepository;

    public FloristService(FloristRepository floristRepository) {
        this.floristRepository = floristRepository;
    }

    public String saveFlorist(Florist florist){
        floristRepository.save(florist);
        return "Florist saves successfully";
    }

    public List<Florist> getAll(){
        return floristRepository.findAll();
    }

    public Optional<Florist> getById(UUID id){
        return floristRepository.findById(id);
    }

    public String updateFlorist(UUID id, Florist request) {
        Florist florist = floristRepository.findById(id).orElse(null);
        if (florist == null){
            return "the Florist with this id does not exist";

        }
        florist.setFirstName(request.getFirstName());
        florist.setLastName(request.getLastName());
        florist.setPhone(request.getPhone());
        floristRepository.save(florist);
        return "Florist updated successfully";
    }

    public String deleteFlorist(UUID id){
        Florist florist = floristRepository.findById(id).orElse(null);
        if (florist == null){
            return "the Florist with this id does not exist";

        }
        floristRepository.delete(florist);
        return "Florist deleted successfully";
    }



    }

