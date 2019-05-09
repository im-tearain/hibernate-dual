package es.aytos.HibernateDual.repositorio;

import org.hibernate.*;

import es.aytos.HibernateDual.modelo.*;
import es.aytos.HibernateDual.util.*;

public class RepositorioDireccion {

    public static Direccion getDireccion(Integer idDireccion) {
        try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {

            sesion.beginTransaction();

            return (Direccion)sesion.createQuery("from Direccion " + "where idDireccion = :identificador")
                    .setParameter("identificador", idDireccion).uniqueResult();
        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando la dirección: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
