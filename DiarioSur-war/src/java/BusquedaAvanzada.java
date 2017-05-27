/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Negocio.Negocio;
import java.util.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author alumnos
 */
@Named(value = "busquedaAvanzada")
@RequestScoped
public class BusquedaAvanzada {

    private String nombre = "";
    private String tipo;
    private float precio;
    private Date fecha = null;
    private String lugar="";
    private String descripcion = "";
    private String tags ="";

    private static boolean encontrado = false;
    
    private List<Evento> busq = new ArrayList<>();

    @Inject
    private BdBean bd;
    
    @EJB
    private Negocio negocio;

    public BusquedaAvanzada() {
    }

    public String buscar() {
        List<Evento> aux = new ArrayList<Evento>();
        List<Evento> todo = new ArrayList<Evento>();
        encontrado = false;
        aux = negocio.getEv();
        
        
        if (!nombre.equals("")) {
            for (Evento e : negocio.getEv()) {
                if (!e.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }       
        
        todo.addAll(aux);
        
        if(!descripcion.equals("")){
            for (Evento e : todo) {
                if (!e.getDescripcion().toUpperCase().contains(descripcion.toUpperCase())) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }
        
        todo.addAll(aux);
        
        if(!lugar.equals("")){
            for (Evento e : todo) {
                if (!e.getGeolocalizacion().toUpperCase().contains(lugar.toUpperCase())) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }
        
        todo.addAll(aux);
        
        if(tipo != null){
            for (Evento e : todo) {
                if (!e.getTipo().equals(tipo)) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }
        
        todo.addAll(aux);    
        
        if(fecha != null){
            for (Evento e : todo) {
                if (e.getFecha().compareTo(fecha) != 0) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }
        
        todo.addAll(aux);
        
        if(!tags.equals("")){
            for (Evento e : todo) {
                if (!e.getTags().toUpperCase().contains(tags.toUpperCase())) {
                    aux.remove(e);
                }
            }
            //encontrado = true;
        }
        
        busq.addAll(aux);
        
        if(!busq.isEmpty()){
           encontrado = true; 
        }

        return "busqAvanzada.xhtml";
    }
    
    public List<Evento> getListaBusqueda(){
        return busq;
    }

    public static boolean isEncontrado() {
        return encontrado;
    }

    public static void setEncontrado(boolean encontrado) {
        BusquedaAvanzada.encontrado = encontrado;
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
