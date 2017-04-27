/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import diariosur.Anuncio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
/**
 *
 * @author pablo
 */
@Named(value = "publicarAnuncio")
@RequestScoped
public class PublicarAnuncio {

    private String nombreEmpresa;
    private long id;
    private String dimensiones;
    private int prioridad;
    private Date fechaPublicacion;
    private Date fechaExpiracion;
    private File multimedia;
    private String tags;
    private static List<Anuncio> anuncios = new ArrayList<Anuncio>();
    private Anuncio anuncio;
   
    /**
     * Creates a new instance of PublicarAnuncio
     */
    public PublicarAnuncio() {
    }
    
    public void crear(){
        anuncio.setDimensiones(dimensiones);
        anuncio.setEmpresa(nombreEmpresa);
        anuncio.setId_anuncio(id);
        anuncio.setFechaPublicacion(fechaPublicacion);
        anuncio.setFechaExpiracion(getFechaExpiracion());
        anuncio.setMultimedia(multimedia);
        anuncio.setPrioridad(dimensiones);
        anuncio.setTags(getTags());
        
        
    }
    
  
       
        
    
     
    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    public void insertar(){
        crear();
        anuncios.add(anuncio);
        addMessage("Anuncio subido correctamente!!");
    }
    public void eliminar(PublicarAnuncio anuncio){
        anuncios.remove(this.anuncio);
    }

    public Anuncio getAnuncio() {
        return anuncio;
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
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
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
    public File getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(File multimedia) {
        if(multimedia.getName().endsWith(".jpg")){
            this.multimedia = multimedia;
        }else if(multimedia.getName().endsWith(".png")){
            this.multimedia = multimedia;
        }else if(multimedia.getName().endsWith(".gif")){
            this.multimedia = multimedia;
        }else{
            throw new IllegalArgumentException("La imagen debe estar en uno de los siguientes formatos: .jpg, .gif, .png");
        }
        
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
    
    
}
