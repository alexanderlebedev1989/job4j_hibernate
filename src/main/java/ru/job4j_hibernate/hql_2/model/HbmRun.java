package ru.job4j_hibernate.hql_2.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import javax.persistence.Query;


public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate candidate1 = Candidate.of("Ivan", 1, 30000);
            Candidate candidate2 = Candidate.of("Bob", 2, 40000);
            Candidate candidate3 = Candidate.of("Anna", 3, 50000);

            session.save(candidate1);
            session.save(candidate2);
            session.save(candidate3);

            Query query = session.createQuery("FROM Candidate ");
            for (Object can : query.getResultList()) {
                System.out.println(can);
            }

            Query query1 = session.createQuery("FROM Candidate c WHERE c.id = :cId");
            query1.setParameter("cId", 4);
            System.out.println(query1.getSingleResult());

            Query query2 = session.createQuery("FROM Candidate c WHERE c.name = :cName");
            query2.setParameter("cName", "Anna");
            System.out.println(query2.getSingleResult());

            session.createQuery("INSERT INTO Candidate (name, experience, salary) " +
                    "SELECT CONCAT(c.name, 'NEW'), c.experience + 1, c.salary + 5000 " +
                    "FROM Candidate c WHERE c.id = :cId")
                    .setParameter("cId", 1)
                    .executeUpdate();

            session.createQuery("DELETE FROM Candidate WHERE id = :cId")
                    .setParameter("cId", 3)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
