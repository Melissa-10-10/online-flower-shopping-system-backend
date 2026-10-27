package rw.auca.online_flower_shopping_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import rw.auca.online_flower_shopping_system.domain.FlowerType;
import rw.auca.online_flower_shopping_system.repository.FlowerTypeRepository;

@Service
public class FlowerTypeService {
    private final FlowerTypeRepository flowerTypeRepository;

    public FlowerTypeService(FlowerTypeRepository flowerTypeRepository){
        this.flowerTypeRepository = flowerTypeRepository;

    }

    public String saveFlowerType(FlowerType flowerType){
        flowerTypeRepository.save(flowerType);
        return "flower type saved successfully";

    }
    public List<FlowerType> getAll(){
        return flowerTypeRepository.findAll();

    }

    public Optional<FlowerType> getById(UUID id){
        return flowerTypeRepository.findById(id);

    }

    public String updateFlowerType(UUID id, FlowerType request){
        FlowerType flowerType = flowerTypeRepository.findById(id).orElse(null);
        if (flowerType == null){
            return "The flower type with that id does not exist";
        }
        flowerType.setName(request.getName());
        flowerTypeRepository.save(flowerType);
        return "FlowerType updated successfully";
    }
    public String deleteFlowerType(UUID id){
    

    
        FlowerType flowerType = flowerTypeRepository.findById(id).orElse(null);
        if (flowerType == null){
            return "The flower type with that id does nor exist";

        }
        flowerTypeRepository.delete(flowerType);
        return "Flower type deleted successfully";

    }





}