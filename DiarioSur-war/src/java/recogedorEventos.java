/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Administrador;
import Entidades.Anuncio;
import Entidades.Evento;
import Entidades.JefeDeRedactores;
import Entidades.Periodista;
import Entidades.SuperUsuario;
import Entidades.UsuarioRegistrado;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author rafao
 */
@Named(value = "recogedorEventos")
@RequestScoped
public class recogedorEventos {

    private String nombre;
    private String descripcion;
    private byte[] imagen;
    
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

    private static Evento seleccionado;

    public String editarEvento() {
        Evento aux = new Evento(seleccionado.getNombre(), seleccionado.getFecha(), seleccionado.getGeolocalizacion(), seleccionado.getTipo(), seleccionado.getPrecio(), seleccionado.getCompra(), seleccionado.getDescripcion(), seleccionado.getTags(), seleccionado.getUsuarioRegistrado(), seleccionado.getAnuncio());
        aux.setVerificado(seleccionado.getVerificado());
        aux.setId_evento(seleccionado.getId_evento());
        aux.setUser_megusta(seleccionado.getUser_megusta());
        aux.setValoraciones(seleccionado.getValoraciones());
        aux.setReportes(seleccionado.getReportes());
        aux.setImagen(imagen);
        seleccionado = aux;
        negocio.editarEvento(seleccionado);
        return "evento.xhtml";
    }

    public static Evento getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Evento seleccionado) {
        this.seleccionado = seleccionado;
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

    public List<Evento> getAllEventos() {
        return negocio.getAllEv();
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

    public UploadedFile getImagen() {

        return null;
    }

    public void setImagen(UploadedFile f) {
        imagen = f.getContents();
    }

    public recogedorEventos() {

    }

    public String reportar() {

        return "EnviarReporte";
    }

    public String eliminarEvento() throws DiarioSurException {
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

    public String enviarEvento() throws DiarioSurException {
        usuario = cta.getUsuarioLogeado();
        anuncio = negocio.devolverAnuncio();

        Evento aux = new Evento(nombre, fecha, lugar, tipo, precio, compra, descripcion, tags, usuario, anuncio);
        if (usuario instanceof Administrador || usuario instanceof JefeDeRedactores || usuario instanceof Periodista || usuario instanceof SuperUsuario) {
            aux.setVerificado(true);

        } else {
            aux.setVerificado(false);
        }
        
        if(fecha.compareTo(new Date())<0){
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "La fecha del evento no puede ser anterior a la fecha actual",
                    "La fecha del evento no puede ser anterior a la fecha actual"));
            return null;
        }
        aux.setImagen(imagen);
        aux.setUser_megusta(new ArrayList<>());
        setSeleccionado(aux);
        List<Evento> l = anuncio.getEvento();
        l.add(aux);
        negocio.crearEvento(aux);
        anuncio.setEvento(l);
        negocio.editarAnuncio(anuncio);
        return "evento";
    }

    public void subirImagen(FileUploadEvent event) {
        FacesMessage mensaje = new FacesMessage();
        try {
            seleccionado.setImagen(event.getFile().getContents());
            mensaje.setSummary("Imagen subida correctamente");
        } catch (Exception e) {
            mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            mensaje.setSummary("Problemas al subir la imagen");
        }
        FacesContext.getCurrentInstance().addMessage("Mensaje", mensaje);

    }

    public StreamedContent sacarImagen(Evento e) throws IOException {
      
        if (negocio.tieneImagen(e)) {
            StreamedContent stm = new DefaultStreamedContent(new ByteArrayInputStream(e.getImagen()));
            return stm;
        } else {
            StreamedContent stm;
            HttpServletRequest origRequest=(HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String aux=origRequest.getRequestURL().toString();
            aux=aux.substring(0,aux.indexOf("faces/"));
            stm = new DefaultStreamedContent(new URL(aux+"resources/30.jpg").openStream());
            return stm;
        }

    }

    public byte[] cogerArBy() throws FileNotFoundException {
        File file = new File("resources/logoC.png");
        System.out.println(file.exists() + "!!");

        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {

        }

        //bytes is the ByteArray we need
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public List<Evento> getListaEventoRec(Evento e) {
        List<Evento> l = new ArrayList<>();
        // l=negocio.getDosRecomendados(e);
        return l;
    }

    public List<Evento> getEvNV() {
        return negocio.getEvNV();
    }

}
