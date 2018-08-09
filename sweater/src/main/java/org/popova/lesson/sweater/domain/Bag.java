package org.popova.lesson.sweater.domain;

import javax.persistence.*;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "products_buy",
            joinColumns = { @JoinColumn(name = "bag_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    private List<Product> productsForBuy;


    public Bag() {
    }

    public Bag(User user) {
        this.author = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Product> getProductsForBuy() {
        return productsForBuy;
    }

    public void setProductsForBuy(List<Product> productsForBuy) {
        this.productsForBuy = productsForBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(id, bag.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
