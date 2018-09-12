package org.popova.lesson.sweater.domain;

import javax.persistence.*;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ElementCollection
    @CollectionTable(name = "Quantitys",
    joinColumns = @JoinColumn(name = "bag_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "count")
    private Map<Product, Integer> quantity = new HashMap<Product, Integer>();

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

    public Map<Product, Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(Map<Product, Integer> quantity) {
        this.quantity = quantity;
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
