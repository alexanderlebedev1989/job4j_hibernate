package ru.job4j_hibernate.hql_2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int experience;
    private int salary;

    public static Candidate of(String name, int experience, int salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                experience == candidate.experience &&
                salary == candidate.salary &&
                Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary);
    }

    @Override
    public String toString() {
        return String.format("Candidate: id=%s, name=%s, experience=%s, salary=%s", id, name, experience, salary);
    }
}
