package es.aytos.HibernateDual;

import java.util.*;

import es.aytos.HibernateDual.modelo.*;
import es.aytos.HibernateDual.repositorio.*;

public class Pruebas {

    public static void main(String[] args) {
        int idPersona = crearPersona();
        // actualizarPersona(idPersona, "99999999L");
        // consultarPersona(idPersona);
        eliminarPersona(idPersona);
    }

    public static Integer crearPersona() {
        // 1. Construyo la persona
        final Persona persona = new Persona();
        persona.setNombre("Joaquín");
        persona.setApellidos("Álvarez Álvarez");
        persona.setNif("16578945L");
        persona.setEstadoCivil(EstadoCivil.CASADO);
        persona.setFechaNacimiento(new Date());

        // 2. Construyo las direcciones
        Direccion direccion1 = new Direccion();
        direccion1.setCodigoPostal(41400L);
        direccion1.setDireccion("Avenida Blas Infante Nº 2");
        direccion1.setProvincia(Provincia.SEVILLA);

        Direccion direccion2 = new Direccion();
        direccion2.setCodigoPostal(41420L);
        direccion2.setDireccion("Calle Alberti");
        direccion2.setProvincia(Provincia.GRANADA);

        // 3. Aficiones
        Aficion aficion1 = new Aficion();
        aficion1.setIdAficion(1);
        aficion1.setDescripcion("Deportes");

        // 4. Usuario
        Usuario usuario = new Usuario();
        usuario.setContrasenia("123456");
        usuario.setEmail("joaquin.alvarez@aytos.es");

        return RepositorioPersona.crearPersona(persona, usuario, Arrays.asList(direccion1, direccion2),
                Arrays.asList(aficion1));
    }

    public static Persona consultarPersona(final Integer idPersona) {
        final Persona persona = RepositorioPersona.getPersona(idPersona);

        System.out.println("Nuevo NIF: " + persona.getNif());
        // System.out.println(persona.getApellidos());
        // System.out.println(persona.getEstadoCivil());
        // persona.getAficiones().stream().forEach(a -> System.out.println(a.getDescripcion()));
        // persona.getDirecciones().stream().forEach(d -> System.out.println(d.getCodigoPostal()));

        return persona;
    }

    public static void eliminarPersona(final Integer idPersona) {
        RepositorioPersona.eliminarPersona(idPersona);
    }

    public static void actualizarPersona(final Integer idPersona, final String nif) {
        RepositorioPersona.actualizarPersona(idPersona, nif);
    }
}