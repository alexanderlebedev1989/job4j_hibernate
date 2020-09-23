package ru.job4j_hibernate.one_to_many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j_hibernate.one_to_many.model.CarBrand;
import ru.job4j_hibernate.one_to_many.model.CarModel;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            CarModel model1 = CarModel.of("ВАЗ-2106");
            CarModel model2 = CarModel.of("ВАЗ-2107");
            CarModel model3 = CarModel.of("ВАЗ-2108");

            session.save(model1);
            session.save(model2);
            session.save(model3);

            CarBrand brand = CarBrand.of("Жигули");

            brand.addCar(session.load(CarModel.class, 1));
            brand.addCar(session.load(CarModel.class, 2));
            brand.addCar(session.load(CarModel.class, 3));
            session.save(brand);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
