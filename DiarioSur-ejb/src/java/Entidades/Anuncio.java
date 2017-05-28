/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Garri
 */
@Entity
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_anuncio;
    @Column(nullable = false, length = 100)
    @Temporal(value = TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(nullable = false, length = 100)
    @Temporal(value = TemporalType.DATE)
    private Date fechaExpiracion;
    @Column(nullable = true, length = 1000)
    private File multimedia;
    @Column(nullable = false, length = 50)
    private String empresa;
    @Column(nullable = false, length = 10)
    private String dimensiones;
    @Column(nullable = false, length = 10)
    private String prioridad;
    @Column(nullable = false, length = 100)
    private String tags;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Administrador administrador;

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    @OneToMany(mappedBy = "anuncio")
    private List<Evento> evento;

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getId_anuncio() {
        return id_anuncio;
    }

    public void setId_anuncio(Long id) {
        this.id_anuncio = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_anuncio != null ? id_anuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id_anuncio fields are not set
        if (!(object instanceof Anuncio)) {
            return false;
        }
        Anuncio other = (Anuncio) object;
        if ((this.id_anuncio == null && other.id_anuncio != null) || (this.id_anuncio != null && !this.id_anuncio.equals(other.id_anuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diariosur.Anuncio[ id=" + id_anuncio + " ]";
    }

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String FechaPublicacionString() {
        DateFormat formato = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formato.format(fechaPublicacion);
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the fechaExpiracion
     */
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String FechaExpiracionString() {
        DateFormat formato = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formato.format(fechaExpiracion);
    }

    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    /**
     * @return the multimedia
     */
    public File getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(File multimedia) {
        this.multimedia = multimedia;
    }

    public Anuncio(Date fP, Date fE, File m, String em, String dim, String pri, String tag, Administrador ad, List<Evento> ev) {
        fechaPublicacion = fP;
        fechaExpiracion = fE;
        multimedia = m;
        empresa = em;
        dimensiones = dim;
        prioridad = pri;
        tags = tag;
        administrador = ad;
        evento = ev;

    }

    public Anuncio() {

    }

}
