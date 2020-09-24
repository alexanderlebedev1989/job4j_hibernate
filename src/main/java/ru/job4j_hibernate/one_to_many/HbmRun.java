package ru.job4j_hibernate.one_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j_hibernate.one_to_many.model.CarBrand;
import ru.job4j_hibernate.one_to_many.model.CarModel;
import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
        List<CarBrand> carBrands;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            carBrands = session.createQuery("from CarBrand").list();
            for (CarBrand car : carBrands) {
                for (CarModel carModel : car.getCars()) {
                    System.out.println(carModel);
                }
            }

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
