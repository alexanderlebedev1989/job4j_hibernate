package ru.job4j_hibernate.category.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "humans")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String info;

    private Calendar created;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        this.cities.add(city);
    }

    public static Human of(String info) {
        Human human = new Human();
        human.info = info;
        human.created = Calendar.getInstance();
        return human;
    }

    public String getInfo() {
        return info;
    }

    public Calendar getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }
}
