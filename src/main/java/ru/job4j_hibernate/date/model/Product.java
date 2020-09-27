package ru.job4j_hibernate.date.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String producer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public static Product of(String name, String producer) {
        Product p = new Product();
        p.name = name;
        p.producer = producer;
        p.created = new Date(System.currentTimeMillis());
        return p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}