package es.aytos.HibernateDual.modelo;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Integer idUsuario;

    private String email;

    @Column(name = "password")
    private String contrasenia;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
