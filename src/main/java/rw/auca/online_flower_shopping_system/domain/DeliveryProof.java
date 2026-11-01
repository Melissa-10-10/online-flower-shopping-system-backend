package rw.auca.online_flower_shopping_system.domain;



import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_proof")
public class DeliveryProof {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "received_by")
    private String receivedBy;

    @Column(name = "comments")
    private String comments;

    @Column(name = "rating")
    private int rating;

    @Column(name = "confirmed_date")
    private LocalDate confirmedDate;

    @ManyToOne
    @JoinColumn(name = "flower_order_id")
    private FlowerOrder flowerOrder;

    public UUID getId() {
        return id;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(LocalDate confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    public FlowerOrder getFlowerOrder() {
        return flowerOrder;
    }

    public void setFlowerOrder(FlowerOrder flowerOrder) {
        this.flowerOrder = flowerOrder;
    }
}
