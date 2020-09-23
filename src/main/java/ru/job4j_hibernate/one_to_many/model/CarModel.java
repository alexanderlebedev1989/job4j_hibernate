package ru.job4j_hibernate.one_to_many.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars_model")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static CarModel of(String name) {
        CarModel carModel = new CarModel();
        carModel.name = name;
        return carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return id == carModel.id &&
                Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
