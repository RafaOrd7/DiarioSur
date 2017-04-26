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
/**
 *
 * @author pablo
 */
@Named(value = "publicarAnuncio")
@RequestScoped
public class PublicarAnuncio {

    private String nombreAnuncio;
    private String id;
    private int dimensiones;
    private int prioridad;
    private Date fechaPublicacion;
    private int diasContratados;
    private File multimedia;
    private static List<Anuncio> anuncios = new ArrayList<Anuncio>();
    private Anuncio anuncio;
   
    /**
     * Creates a new instance of PublicarAnuncio
     */
    public PublicarAnuncio() {
    }
    
     public void insertar(){
        anuncios.add(anuncio);
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
    public String getNombreAnuncio() {
        return nombreAnuncio;
    }

    /**
     * @param nombreAnuncio the nombreAnuncio to set
     */
    public void setNombreAnuncio(String nombreAnuncio) {
        this.nombreAnuncio = nombreAnuncio;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the dimensiones
     */
    public int getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(int dimensiones) {
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
    public int getDiasContratados() {
        return diasContratados;
    }

    /**
     * @param diasContratados the diasContratados to set
     */
    public void setDiasContratados(int diasContratados) {
        this.diasContratados = diasContratados;
    }

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
    
    
}
