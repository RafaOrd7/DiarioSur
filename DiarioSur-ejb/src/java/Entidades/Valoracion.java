/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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

/**
 *
 * @author jajip
 */
@Entity
public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idValoracion;
    
    @Column(nullable = false)
    private Integer calificacion;
    @Column(nullable = false, length = 200)
    private String comentario;
    private byte multimedia;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaPublicacion;
    
    @ManyToOne
    @JoinColumn(nullable  = false)
    private UsuarioRegistrado usuarioRegistrado;
    @ManyToOne
    @JoinColumn(nullable=false)
    private Evento evento;
    @OneToMany (mappedBy="valoracion")
    private List<Reporte> reportes;
    
    
    public Valoracion(Integer c, String co, Date fecha, UsuarioRegistrado u,Evento e){
        calificacion=c;
        comentario=co;
        fechaPublicacion=fecha;
        usuarioRegistrado=u;
        evento=e;
    }
    
    
    public Valoracion(){
       
    }
    
    public Evento getEvento(){
        return evento;
    }
    
    public void setEvento(Evento e){
        evento=e;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIdValoracion() {
        return idValoracion;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public byte getMultimedia() {
        return multimedia;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setIdValoracion(Long idValoracion) {
        this.idValoracion = idValoracion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setMultimedia(byte multimedia) {
        this.multimedia = multimedia;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    

    public Long getId() {
        return idValoracion;
    }

    public void setId(Long id) {
        this.idValoracion = id;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValoracion != null ? idValoracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valoracion)) {
            return false;
        }
        Valoracion other = (Valoracion) object;
        if ((this.idValoracion == null && other.idValoracion != null) || (this.idValoracion != null && !this.idValoracion.equals(other.idValoracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectogrupo.Valoracion[ id=" + idValoracion + " ]";
    }
    
   
    
}


