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
import static java.time.Clock.system;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
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
    @Inject
    private BdBean bd;
    
    @EJB
    private Negocio negocio;
    
    @Inject
    private ctrlAutorizacion cta;
    private static Evento seleccionado=new Evento();

    public String editarEvento() {
        System.out.println(seleccionado.getId_evento());
        Evento aux = new Evento(seleccionado.getNombre(), seleccionado.getFecha(), seleccionado.getGeolocalizacion(), seleccionado.getTipo(), seleccionado.getPrecio(), seleccionado.getCompra(), seleccionado.getDescripcion(), seleccionado.getTags(), seleccionado.getUsuarioRegistrado(), seleccionado.getVerificado(), seleccionado.getAnuncio());
        aux.setId_evento(seleccionado.getId_evento());
        seleccionado = aux;
        bd.editarEvento(seleccionado);
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

    public String ver(Evento evento) {
        setSeleccionado(evento);
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

    public String eliminarEvento() {
        bd.eliminarEvento(seleccionado);
        negocio.eliminarEvento(seleccionado);

        return "index.xhtml";
    }

    public String megusta() throws DiarioSurException {
        usuario = cta.getUsuarioLogeado();
        bd.MeGusta(seleccionado, usuario);
        negocio.meGusta(seleccionado, usuario);
        return "evento.xhtml";
    }

    public int getNumeroMG() {
        if (bd.getMegusta().isEmpty()) {
            return 0;
        } else if (bd.getMegusta().get(getSeleccionado()) == null) {
            return 0;
        }
        return bd.getMegusta().get(getSeleccionado()).size();
    }

    public String enviarEvento() {
        usuario = cta.getUsuarioLogeado();
        anuncio = bd.getAnu().get(new Random().nextInt(bd.getAnu().size()));
        Evento aux = new Evento(nombre, fecha, lugar, tipo, precio, compra, descripcion, tags, usuario, verificado, anuncio);
        aux.setImagen(imagen);
        setSeleccionado(aux);
        bd.crearEvento(aux);
        negocio.crearEvento(aux);
        return "evento";
    }

}
