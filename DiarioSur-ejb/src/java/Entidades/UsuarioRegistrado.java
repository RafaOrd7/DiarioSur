/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author pablo
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipoUsuario", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("UsuarioRegistrado")

public class UsuarioRegistrado implements Serializable {
   
    private static long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idUser;
    @Column(nullable  = false, length=50)
    private String nombre;
    @Column(nullable  = false, length=50)
    private String apellidos;
    @Column(nullable  = false, length=50, unique=true)
    private String dni;
   
   @Column(nullable  = false, length=50, unique=true)
    private String email;
    @Column(nullable  = true, length=250)
    private String preferencias;
    @Column(nullable  = true)
    private byte multimedia;
    @Column(nullable  = true, length=50)
    private String password;
    @Column(nullable  = false, length=100)
    private String historialEventos;
    @Column(nullable  = false, length=100)
    private boolean borrado;

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    public List<Reporte> getReporte() {
        return reporte;
    }

    public void setReporte(List<Reporte> reporte) {
        this.reporte = reporte;
    }

    public List<Valoracion> getValoracion() {
        return valoracion;
    }

    public void setValoracion(List<Valoracion> valoracion) {
        this.valoracion = valoracion;
    }

    public List<Notificacion> getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(List<Notificacion> notificacion) {
        this.notificacion = notificacion;
    }

    public List<Evento> getMegusta() {
        return megusta;
    }

    public void setMegusta(List<Evento> megusta) {
        this.megusta = megusta;
    }
    
    /////////////////RELACIONES////////////////
    
    //usuario publica evento
    @OneToMany (mappedBy="usuarioRegistrado")
    @JoinColumn(nullable=true)
    private List<Evento> evento;
    //usuario genera reporte
    @OneToMany (mappedBy="usuarioRegistrado")
    private List<Reporte> reporte;
    //escribe valoracion
    @OneToMany(mappedBy="usuarioRegistrado")
    private List<Valoracion> valoracion;
    //recibe notificacion
    @OneToMany(mappedBy="usuarioRegistrado")
    private List<Notificacion> notificacion;
    //me gusta
    @ManyToMany
    @JoinTable(name="me_gusta",joinColumns=@JoinColumn(name="user_fk"),
             inverseJoinColumns=@JoinColumn(name="me_gusta_fk"))
    private List<Evento> megusta;
    
    
    
    /////////////// CONSTRUCTORES ////////////
    public UsuarioRegistrado () {
        
    }
    
    public UsuarioRegistrado (String idUser, String nombre, String apellidos, String dni, String email, String password) {
        this.idUser=idUser;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.dni=dni;
        this.email=email;
        this.password=password;
    }
    
    
    //////////////// MÉTODOS ///////////////
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUser() != null ? getIdUser().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRegistrado)) {
            return false;
        }
        UsuarioRegistrado other = (UsuarioRegistrado) object;
        if ((this.getIdUser() == null && other.getIdUser() != null) || (this.getIdUser() != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsuarioRegistrado[ id=" + getIdUser() + " ]";
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the preferencias
     */
    public String getPreferencias() {
        return preferencias;
    }

    /**
     * @param preferencias the preferencias to set
     */
    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    /**
     * @return the multimedia
     */
    public byte getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(byte multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the historialEventos
     */
    public String getHistorialEventos() {
        return historialEventos;
    }

    /**
     * @param historialEventos the historialEventos to set
     */
    public void setHistorialEventos(String historialEventos) {
        this.historialEventos = historialEventos;
    }
    
}
