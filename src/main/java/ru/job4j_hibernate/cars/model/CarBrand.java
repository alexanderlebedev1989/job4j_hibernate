package ru.job4j_hibernate.cars.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cars_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> cars = new ArrayList<>();

    public static CarBrand of(String name) {
        CarBrand carBrand = new CarBrand();
        carBrand.name = name;
        return carBrand;
    }

    public void addCar(CarModel model) {
        cars.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand carBrand = (CarBrand) o;
        return id == carBrand.id &&
                Objects.equals(name, carBrand.name) &&
                Objects.equals(cars, carBrand.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cars);
    }
}
