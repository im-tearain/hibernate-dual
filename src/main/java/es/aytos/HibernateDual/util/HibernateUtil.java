package es.aytos.HibernateDual.util;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {

    private HibernateUtil() {
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Se ha producido un error construyendo la factoría de sesiones: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}