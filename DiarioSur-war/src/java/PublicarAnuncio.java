/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Administrador;
import java.io.File;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import Entidades.Anuncio;
import Entidades.Evento;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Garri
 */
@Named(value = "publicarAnuncio")
@RequestScoped
public class PublicarAnuncio {

    @Inject
    private ctrlAutorizacion cta;
    @EJB
    private Negocio negocio;

    private String nombreEmpresa;
    private long id;
    private String dimensiones;
    private String prioridad;
    private Date fechaPublicacion;
    private Date fechaExpiracion;
    private byte[] multimedia;
    private String tags;
    private static Anuncio seleccionado;
    private String eventos;
    private boolean concierto;
    private boolean exposicion;
    private boolean musical;
    private boolean deportivo;
    private boolean teatral;
    private boolean otro;
    
    
    
    

    public boolean isConcierto() {
        return concierto;
    }

    public void setConcierto(boolean concierto) {
        this.concierto = concierto;
    }

    public boolean isExposicion() {
        return exposicion;
    }

    public void setExposicion(boolean exposicion) {
        this.exposicion = exposicion;
    }

    public boolean isMusical() {
        return musical;
    }

    public void setMusical(boolean musical) {
        this.musical = musical;
    }

    public boolean isDeportivo() {
        return deportivo;
    }

    public void setDeportivo(boolean deportivo) {
        this.deportivo = deportivo;
    }

    public boolean isTeatral() {
        return teatral;
    }

    public void setTeatral(boolean teatral) {
        this.teatral = teatral;
    }

    public boolean isOtro() {
        return otro;
    }

    public void setOtro(boolean otro) {
        this.otro = otro;
    }

    public String getEventos() {
        return eventos;
    }

    public void setEventos(String eventos) {
        this.eventos = eventos;
    }

    private Anuncio anuncio;

    /**
     * Creates a new instance of PublicarAnuncio
     */
    public PublicarAnuncio() {
    }

    public void crear() throws DiarioSurException {
        anuncio = new Anuncio();
        anuncio.setDimensiones(dimensiones);
        anuncio.setEmpresa(nombreEmpresa);
        anuncio.setId_anuncio(id);
        anuncio.setFechaPublicacion(fechaPublicacion);
        anuncio.setFechaExpiracion(fechaExpiracion);
        anuncio.setMultimedia(multimedia);
        anuncio.setPrioridad(prioridad);
        anuncio.setTags(tags);
        anuncio.setAdministrador((Administrador)cta.getUsuarioLogeado());

    }

    public String subirAnuncio() throws DiarioSurException {
        crear();
        negocio.crearAnuncio(anuncio);
        return "index.xhtml";

    }

    public String ver(Anuncio anuncio) {
        seleccionado = anuncio;
        return "anuncio.xhtml";
    }

    public Anuncio getSeleccionado() {
        return seleccionado;
    }

    /**
     * @param anuncio the anuncio to set
     */
    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    /**
     * @return the nombreAnuncio
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreAnuncio the nombreAnuncio to set
     */
    public void setNombreEmpresa(String nombreAnuncio) {
        this.nombreEmpresa = nombreAnuncio;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the dimensiones
     */
    public String getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * @param fechaPublicacion the fechaPublicacion to set
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * @return the diasContratados
     */
    /**
     * @return the multimedia
     */
    public UploadedFile getMultimedia() {
        return null;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(UploadedFile multimedia) {
       this.multimedia=multimedia.getContents();

    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * @return the fechaExpiracion
     */
    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    /**
     * @param fechaExpiracion the fechaExpiracion to set
     */
    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String eliminarAnuncio() throws DiarioSurException {
        negocio.borrarAnuncio(seleccionado);
        return "index.xhtml";
    }

    public List<Anuncio> getAnuncios() {
        return negocio.getAnu();

    }
    
     public StreamedContent sacarImagenA(Anuncio a) throws IOException {
        if (negocio.tieneImagenA(a)) {
            StreamedContent stm = new DefaultStreamedContent(new ByteArrayInputStream(a.getMultimedia()));
            return stm;
        } else {
            StreamedContent stm = new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]));
         
            return stm;
        }

    }
    
}
