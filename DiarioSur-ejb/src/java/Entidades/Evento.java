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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

/**
 *
 * @author rafaord
 */
@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
   
    private Long id_evento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String lugar;
    @Column(nullable = false)
    private Float precio;
    @Column(nullable = false)
    private String tags;
    @Column(name = "descripción")
    private String descripcion;
    @Column(nullable = false)
    private String compra;
    private Boolean verificado;
    private byte[] imagen;
    
    @Column(name = "geolocalización")
    private String geolocalizacion;
    
    @OneToMany(mappedBy = "evento", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Valoracion> valoraciones;
    
    @OneToMany(mappedBy = "evento", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Reporte> reportes;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private UsuarioRegistrado usuarioRegistrado;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Anuncio anuncio;
    
    @ManyToMany(mappedBy = "megusta")
    private List<UsuarioRegistrado> user_megusta;
    
    
    //// GETTERS, SETTERS Y FUNCIONES
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public UsuarioRegistrado getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public List<UsuarioRegistrado> getUser_megusta() {
        return user_megusta;
    }

    public void setUser_megusta(List<UsuarioRegistrado> user_megusta) {
        this.user_megusta = user_megusta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
    public Evento() {

    }


    public Evento(String n, Date d, String l, String g, String t, Float p, String c, String des, String ta, UsuarioRegistrado usuarioR, Anuncio a) {
        nombre = n;
        fecha = d;
        lugar = l;
        geolocalizacion=g;
        tipo = t;
        precio = p;
        compra=c;
        descripcion= des;
        tags = ta;
        usuarioRegistrado = usuarioR;
        anuncio = a;
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
    
    public String FechaString(){
        DateFormat formato = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
        return formato.format(fecha);
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
    
    public String VerificadoString(){
        String res = "No";
        if(verificado) res = "Si";
        
        return res;
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
        return "Identificador del evento: " + id_evento;
    }

}
