package ru.geekbrains.hibernate;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.hibernate.persist.Product;
import ru.geekbrains.hibernate.persist.User;
import ru.geekbrains.hibernate.persist.UserProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lesson {

    public static void main(String[] args) {

        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        //generateTestData(em);

        Product p1 = em.find(Product.class, 2L);
        Product p2 = em.find(Product.class, 5L);
        Product p3 = em.find(Product.class, 9L);

        User u1 = em.find(User.class, 1L);
        User u2 = em.find(User.class, 3L);

        //Покупка первым покупателем
        ArrayList<Product> products1= new ArrayList<>();
        products1.add(p1);
        products1.add(p3);
        buy(em, products1, u1);

        //Покупка вторым покупателем
        ArrayList<Product> products2= new ArrayList<>();
        products2.add(p2);
        products2.add(p3);
        buy(em, products2, u1);

        //Посмотерть что купил клиент №1
        u1.getProducts().forEach(System.out::println);

        //Посмотерть продукты №3
        p3.getUsers().forEach(System.out::println);

        //Удаление сущностей
        em.getTransaction().begin();

        Product p = em.find(Product.class, 10L);
        User u = em.find(User.class, 10L);
        em.remove(p);
        em.remove(u);
        em.getTransaction().commit();


    }

    private static void generateTestData(EntityManager em) {

        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Product p = new Product("Product_" + i, i * 100);
            User u = new User("User_" + i);
            em.persist(p);
            em.persist(u);
        }
        em.getTransaction().commit();
    }

    private static void buy(EntityManager em, List<Product> products, User u) {

        em.getTransaction().begin();
        Date date = new Date();
        for (Product product : products) {
            UserProduct up = new UserProduct();
            up.setProduct(product);
            up.setUser(u);
            up.setPurchaseDate(date);
            up.setPurchaseCost(product.getCost());
            em.persist(up);
        }
        em.getTransaction().commit();
    }
}
