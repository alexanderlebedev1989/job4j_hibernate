package ru.job4j_hibernate.hql.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            Student one = Student.of("Alex", 21, "Moscow");
//            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
//            Student three = Student.of("Nikita", 25, "Kaliningrad");
//
//            session.save(one);
//            session.save(two);
//            session.save(three);

//            session.createQuery(
//                    "UPDATE Student s SET s.age = :newAge, s.city = :newCity WHERE s.id = :fId")
//                    .setParameter("newAge", 22)
//                    .setParameter("newCity", "London")
//                    .setParameter("fId", 1)
//                    .executeUpdate();
//
//            Query query = session.createQuery("FROM Student s WHERE s.id = :sId");
//            query.setParameter("sId", 1);
//            System.out.println(query.getSingleResult());

//            session.createQuery("DELETE FROM Student WHERE id = :sId")
//                    .setParameter("sId", 1)
//                    .executeUpdate();
            session.createQuery(
                    "INSERT INTO Student (name, age, city) " +
                            "SELECT CONCAT(s.name, 'Petr'), s.age + 5, s.city " +
                            "FROM Student s WHERE s.id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();



            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
