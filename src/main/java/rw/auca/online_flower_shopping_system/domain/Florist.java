package rw.auca.online_flower_shopping_system.domain;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;






@Entity
@Table(name = "florist")
public class Florist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;
    
    @Column(name = "first-name")    
    private String firstName;

    @Column(name = "last-name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    // many florists can work at one outlet
    @ManyToOne
    @JoinColumn(name = "outlet_id")
    private Outlet outlet;

    // florist owns the jointable florist_flower_type
    @ManyToMany
    @JoinTable(
        name = "florist_flower_type",
        joinColumns = @JoinColumn(name = "florist_id"),
        inverseJoinColumns = @JoinColumn(name = "flower_type_id")
    )
    private List<FlowerType> flowerTypes = new ArrayList<>();

    public UUID getId(){
        return id;
    }
    public  String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    public List<FlowerType> getFlowerTypes() {
        return flowerTypes;
    }

    public void setFlowerTypes(List<FlowerType> flowerTypes) {
        this.flowerTypes = flowerTypes;
    }
}




        


