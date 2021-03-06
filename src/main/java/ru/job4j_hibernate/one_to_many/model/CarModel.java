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

    @ManyToOne
    @JoinColumn(name = "carBrand_id")
    private CarBrand carBrand;

    public static CarModel of(String name, CarBrand carBrand) {
        CarModel carModel = new CarModel();
        carModel.name = name;
        carModel.carBrand = carBrand;
        return carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return id == carModel.id &&
                Objects.equals(name, carModel.name) &&
                Objects.equals(carBrand, carModel.carBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, carBrand);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carBrand=" + carBrand +
                '}';
    }
}
