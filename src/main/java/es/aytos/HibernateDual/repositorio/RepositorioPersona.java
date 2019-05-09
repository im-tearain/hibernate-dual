package es.aytos.HibernateDual.repositorio;

import java.util.*;

import org.hibernate.*;

import es.aytos.HibernateDual.modelo.*;
import es.aytos.HibernateDual.util.*;

public class RepositorioPersona {

    public static Integer crearPersona(final Persona persona, final Usuario usuario, final List<Direccion> direcciones,
            final List<Aficion> aficiones) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {

            sesion.beginTransaction();

            final Integer personaBBDD = (Integer)sesion.save(persona);
            sesion.flush();

            persona.setIdPersona(personaBBDD);

            persona.setUsuario(usuario);
            usuario.setPersona(persona);

            sesion.flush();

            persona.setDirecciones(direcciones);
            direcciones.stream().forEach(d -> d.setPersona(persona));

            sesion.flush();

            persona.setAficiones(aficiones);

            sesion.flush();

            sesion.getTransaction().commit();

            return personaBBDD;

        } catch (Exception e) {
            System.out.println("Se ha producido un error creando una persona: " + e.getMessage());
            sesion.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static List<Persona> consultarPersonas() {
        try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {

            sesion.beginTransaction();

            final List<Persona> resultados = sesion.createQuery("from Persona").list();

            return resultados;

        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando las personas: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Persona getPersona(final Integer idPersona) {
        try (Session sesion = HibernateUtil.getSessionFactory().getCurrentSession()) {

            sesion.beginTransaction();

            return (Persona)sesion.createQuery("from Persona where idPersona = :idPersona")
                    .setParameter("idPersona", idPersona).uniqueResult();

        } catch (Exception e) {
            System.out.println("Se ha producido un error consultando la persona: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void eliminarPersona(final Integer idPersona) {
        final Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            sesion.beginTransaction();

            sesion.createQuery("delete from Usuario where idPersona = :idPersona").setParameter("idPersona", idPersona)
                    .executeUpdate();
            sesion.createQuery("delete from Direccion where idPersona = :idPersona")
                    .setParameter("idPersona", idPersona).executeUpdate();
            sesion.createQuery("delete from Persona where idPersona = :idPersona").setParameter("idPersona", idPersona)
                    .executeUpdate();

            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Se ha producido un error eliminando una persona: " + e.getMessage());
            sesion.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            sesion.close();
        }
    }

    public static void actualizarPersona(final Integer idPersona, final String nif) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();

        try {

            sesion.beginTransaction();

            final Persona persona = (Persona)sesion.createQuery("from Persona where idPersona = :identificador")
                    .setParameter("identificador", idPersona).uniqueResult();

            persona.setNif(nif);
            sesion.flush();

            // Actualizar persona
            // sesion.createQuery("Update Persona set nif = :nif " + "where idPersona = :idPersona")
            // .setParameter("nif", nif).setParameter("idPersona", idPersona).executeUpdate();

            sesion.getTransaction().commit();

        } catch (Exception e) {
            sesion.getTransaction().rollback();

            System.out.println("Se ha producido un error actualizando la persona: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            sesion.close();
        }

    }

}
