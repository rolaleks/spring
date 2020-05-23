package ru.geekbrains.hibernate.persist;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_product")
public class UserProduct {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.DATE)
    private Date purchase_date;

    @Column(name = "purchase_cost")
    private double purchase_cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getPurchaseDate() {
        return purchase_date;
    }

    public void setPurchaseDate(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public double getPurchaseCost() {
        return purchase_cost;
    }

    public void setPurchaseCost(double purchase_cost) {
        this.purchase_cost = purchase_cost;
    }
}
