package org.popova.lesson.sweater.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please, enter the tag")
    @Length(max = 255, message = "Tag too long (more than 255)")
    private String title;

    @NotBlank(message = "Please, enter the tag")
    @Length(max = 255, message = "Tag too long (more than 255)")
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;


    //----
    @NotBlank(message = "Please, enter the price")
    @Length(max = 20, message = "Price too long (more than 20 signs)")
    private String price;


    @Length(max = 255, message = "Tag too long (more than 255)")
    private String material;


    @Length(max = 255, message = "Tag too long (more than 255)")
    private String country;

    @Length(max = 2048, message = "Tag too long (more than 2048)")
    private String description;
    //----


    private String filename;

    public Product() {
    }

    public Product( String title, String tag, String price, String material, String country, String description, User author) {
        this.title = title;
        this.tag = tag;
        this.author = author;
        this.price = price;
        this.material = material;
        this.country = country;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
