/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosur;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author VersusPC
 */
@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id_evento;
    @Column(nullable= false)
    private String nombre;
    @Column(nullable= false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable= false)
    private String tipo;
    @Column(nullable= false)
    private Float precio;
    @Column(nullable= false)
    private String tags;
    @Column(name="descripción")
    private String descripcion;
    @Column(nullable= false)
    private Boolean verificado;
    @Column(name="geolocalización")
    private String geolocalizacion;
    @Column(nullable= false)
    private Boolean borrado;
    @OneToMany (mappedBy="evento")
    private List<Valoracion> valoraciones;   
    @OneToMany (mappedBy="evento")
    private List<Reporte> reportes;   
    @ManyToOne
    @JoinColumn(nullable= false)
    private UsuarioRegistrado usuarioRegistrado;
    @ManyToOne
    @JoinColumn(nullable= false)
    private Anuncio anuncio;
    // añadido por Mike
    @ManyToMany(mappedBy = "megusta")
    private List<UsuarioRegistrado> user_megusta;
    // fin añadido por Mike
    
    public Evento(){
        
    }

    public Evento(Long id, String n,Date d, String t, Float p, String ta, Boolean ver, Boolean bo, UsuarioRegistrado usuarioR,
            Anuncio a){
        id_evento=id;
        nombre=n;
        fecha=d;
        tipo=t;
        precio=p;
        tags=ta;
        verificado= ver;
        borrado=bo;
        usuarioRegistrado=usuarioR;
        anuncio=a;       
    }
    
    public Long getId() {
        return id_evento;
    }

    public void setId(Long id) {
        this.id_evento = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }
    
    public String getGeolocalizacion() {
        return geolocalizacion;
    }

    public void setGeolocalizacion(String geolocalizacion) {
        this.geolocalizacion = geolocalizacion;
    }
    
    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_evento != null ? id_evento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id_evento == null && other.id_evento != null) || (this.id_evento != null && !this.id_evento.equals(other.id_evento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Identificador del evento: " + id_evento ;
    }
    
}
