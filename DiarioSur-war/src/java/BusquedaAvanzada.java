/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Evento;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author alumnos
 */
@Named(value = "busquedaAvanzada")
@Dependent
public class BusquedaAvanzada {
    
    private String nombre;
    private String tipo;
    private float precio;
    private Date fecha;
    private String lugar;
    private String descripcion;
    private String tags;
    private List<Evento> busqueda;
    
    private Evento evento;
    @Inject
    private BdBean bd;

    public BusquedaAvanzada() {}

    public void buscar(){
        List<Evento> eventos = bd.getEv();
        ListIterator it = eventos.listIterator();
        while(it.hasNext()){
            evento = (Evento) it.next();
            if(evento.getNombre().toUpperCase().contains(getNombre().toUpperCase())){
                
                if(evento.getDescripcion().toUpperCase().equals(descripcion.toUpperCase())){
                    busqueda.add(evento);
                }else if(evento.getFecha().equals(fecha)){
                    busqueda.add(evento);
                }else if(evento.getGeolocalizacion().toUpperCase().equals(lugar.toUpperCase())){
                    busqueda.add(evento);
                }else if(evento.getTipo().toUpperCase().equals(tipo.toUpperCase())){
                    busqueda.add(evento);
                }else if(evento.getTags().toUpperCase().equals(tags.toUpperCase())){
                    busqueda.add(evento);
                }else if(evento.getPrecio().equals(precio)){
                    busqueda.add(evento);
                }
            }
        }
    }
    /**
     * @return the tipo
     */
    public List<Evento> getBusqueda(){
        return busqueda;
    }
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}