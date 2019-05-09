package es.aytos.HibernateDual.modelo;

import javax.persistence.*;

@Entity
public class Direccion {

    @Id
    @GeneratedValue
    private Integer idDireccion; // PK

    private String direccion;

    private Long codigoPostal;

    @Enumerated
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Long codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
