/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Anuncio;
import diariosur.Evento;
import diariosur.UsuarioRegistrado;
import java.io.File;
import static java.time.Clock.system;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author rafao
 */
@Named(value = "recogedorEventos")
@RequestScoped
public class recogedorEventos {

    private String nombre;
    private String descripcion;
    private File imagen;
    private Date fecha;
    private String lugar;
    private String tipo;
    private float precio;
    private String tags;
    private Boolean verificado = false;
    private Boolean borrado = false;
    private UsuarioRegistrado usuario = new UsuarioRegistrado();

    @Inject
    private BdBean bd;

    private static Evento seleccionado;

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
    private Anuncio anuncio;

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public static Evento getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Evento seleccionado) {
        recogedorEventos.seleccionado = seleccionado;
    }

    public String ver(Evento evento) {
        seleccionado = evento;
        return "evento.xhtml";
    }

    public List<Evento> getEventos() {
        return bd.getEv();
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String t) {
        nombre = t;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String d) {
        descripcion = d;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File f) {
        imagen = f;
    }

    public void mostrarTipo() {
        System.out.println(tipo);
    }

    public recogedorEventos() {

    }

    public String reportar() {    
        
        return "EnviarReporte";
    }

    public String enviarEvento() {
       
        Evento aux = new Evento(nombre, fecha, tipo, precio, descripcion, tags, usuario, verificado, borrado, anuncio);
        setSeleccionado(aux);
        bd.crearEvento(aux);
        return "evento";
    }

}
