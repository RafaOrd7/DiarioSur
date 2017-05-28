/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Anuncio;
import Entidades.Evento;
import Entidades.UsuarioRegistrado;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
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
    private String compra;
    private String tags;
    private Boolean verificado = false;
    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    @ManagedProperty("#{request.requestURI}")
    private String url; // +setter

    @EJB
    private Negocio negocio;

    @Inject
    private ctrlAutorizacion cta;

    @Inject
    private BusquedaAvanzada ba;

    private static Evento seleccionado = new Evento();

    public String editarEvento() {
        Evento aux = new Evento(seleccionado.getNombre(), seleccionado.getFecha(), seleccionado.getGeolocalizacion(), seleccionado.getTipo(), seleccionado.getPrecio(), seleccionado.getCompra(), seleccionado.getDescripcion(), seleccionado.getTags(), seleccionado.getUsuarioRegistrado(), seleccionado.getVerificado(), seleccionado.getAnuncio());
        aux.setId_evento(seleccionado.getId_evento());
        aux.setUser_megusta(seleccionado.getUser_megusta());
        aux.setValoraciones(seleccionado.getValoraciones());
        aux.setReportes(seleccionado.getReportes());
        aux.setImagen(seleccionado.getImagen());
        seleccionado = aux;
        negocio.editarEvento(seleccionado);
        return "evento.xhtml";
    }

    public String editar() {
        return "editarEvento.xhtml";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

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

    public String ver(Evento evento) throws DiarioSurException {
        setSeleccionado(evento);
        if (cta.getUsuarioLogeado() != null) {
            negocio.tipoVisitado(cta.getUsuarioLogeado(), evento);
        }
        return "evento";
    }

    public List<Evento> getEventos() {
        return negocio.getEv();
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

    public String eliminarEvento() {
        negocio.eliminarEvento(seleccionado);
        return "index.xhtml";
    }

    public String megusta() throws DiarioSurException {
        usuario = cta.getUsuarioLogeado();
        negocio.meGusta(seleccionado, usuario);
        return "evento.xhtml";
    }

    public int getNumeroMG() {
        return negocio.numMeGusta(seleccionado.getId_evento());
    }

    public String enviarEvento() {
        usuario = cta.getUsuarioLogeado();
        anuncio = negocio.devolverAnuncio();

        Evento aux = new Evento(nombre, fecha, lugar, tipo, precio, compra, descripcion, tags, usuario, verificado, anuncio);
        aux.setImagen(imagen);
        aux.setUser_megusta(new ArrayList<>());
        setSeleccionado(aux);
        negocio.crearEvento(aux);
        return "evento";
    }

}
