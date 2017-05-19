/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author jajip
 */
@Entity
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reporte;
    @Column(nullable = false, length = 200)
    private String texto;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    

    public Long getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(Long id_reporte) {
        this.id_reporte = id_reporte;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }

    public UsuarioRegistrado getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }
    @Column(nullable = false, length = 20)
    private String tipo;
    
    @ManyToOne
    @JoinColumn(nullable=false)
    private Evento evento;
    @ManyToOne
    @JoinColumn(nullable=false)
    private Valoracion valoracion;
    @ManyToOne
    @JoinColumn(nullable=false)
    private UsuarioRegistrado usuarioRegistrado;
    
    

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTexto() {
        return texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id_reporte;
    }

    public void setId(Long id) {
        this.id_reporte = id;
    }

    public Reporte(String tx, Date fech, String tipo, Evento ev, Valoracion val, UsuarioRegistrado us){
        setTexto(tx);
        setFecha(fech);
        setTipo(tipo);
        setEvento(ev);
        setValoracion(val);
        setUsuarioRegistrado(us);
    }
    
    public Reporte(String tx,String tipo){
       setTipo(tipo);
        setTexto(tx);
    }
    
    public Reporte(){
        
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_reporte != null ? id_reporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.id_reporte == null && other.id_reporte != null) || (this.id_reporte != null && !this.id_reporte.equals(other.id_reporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectogrupo.Reporte[ id=" + id_reporte + " ]";
    }

}
