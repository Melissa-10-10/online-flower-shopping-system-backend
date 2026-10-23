package rw.auca.online_flower_shopping_system.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;


@Entity
@Table(name = "outlet")
public class Outlet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "outlet_number", unique = true)
    private int outletNumber;

    @Column(name = "name")
    private String name;

    public UUID getId() {
        return id;
    }

    public int getOutletNumber() {
        return outletNumber;
    }

    public void setOutletNumber(int outletNumber) {
        this.outletNumber = outletNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

