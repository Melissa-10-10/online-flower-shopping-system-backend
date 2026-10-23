package rw.auca.online_flower_shopping_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "flower_type")
public class FlowerType{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

     // Florist owns this relationship (it has the join table).
    // @JsonIgnore stops the JSON loop: a flower type does not show its florists.
    @ManyToMany(mappedBy = "flowerTypes")
    @JsonIgnore
    private List<Florist> florists = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Florist> getFlorists() {
        return florists;
    }

    public void setFlorists(List<Florist> florists) {
        this.florists = florists;
    }

    
}