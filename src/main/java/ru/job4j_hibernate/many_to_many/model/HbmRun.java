package ru.job4j_hibernate.many_to_many.model;

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

            Book book1 = Book.of("Алиса в стране чудес");
            Book book2 = Book.of("Дюна");
            Book book3 = Book.of("Я - робот");

            Author author1 = Author.of("Артур Кларк");
            author1.getBooks().add(book1);
            author1.getBooks().add(book2);

            Author author2 = Author.of("Айзек Азимов");
            author2.getBooks().add(book2);
            author2.getBooks().add(book3);

            session.persist(author1);
            session.persist(author2);

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
