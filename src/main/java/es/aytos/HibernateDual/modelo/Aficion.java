package es.aytos.HibernateDual.modelo;

import javax.persistence.*;

@Entity
public class Aficion {

    @Id
    private Integer idAficion;

    private String descripcion;

    public Integer getIdAficion() {
        return idAficion;
    }

    public void setIdAficion(Integer idAficion) {
        this.idAficion = idAficion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
