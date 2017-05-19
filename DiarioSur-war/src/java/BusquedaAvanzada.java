/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import java.util.*;
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
    private Date fecha;
    private String lugar="";
    private String descripcion = "";
    private String tags ="";

    private static boolean encontrado = false;

    @Inject
    private BdBean bd;

    public BusquedaAvanzada() {
    }

    public String buscar() {
        List<Evento> aux = new ArrayList<Evento>();
        encontrado = false;
        if (!nombre.equals("")) {
            for (Evento e : bd.getEv()) {
                if (e.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                    if(!descripcion.equals("") && e.getDescripcion().toUpperCase().contains(descripcion.toUpperCase())){
                        aux.add(e);
                        
                    } else if(!lugar.equals("") && e.getGeolocalizacion().toUpperCase().equals(lugar.toUpperCase())){
                        aux.add(e);
                        
                    } else if(tipo != null &&  e.getTipo().toUpperCase().equals(tipo.toUpperCase())){
                        aux.add(e);
                        
                    } else if(fecha != null && e.getFecha().equals(fecha)){
                        aux.add(e);
                        
                    } else if(!tags.equals("") && e.getTags().toUpperCase().contains(tags.toUpperCase())){
                        aux.add(e);
                        
                    } else if(e.getPrecio() == precio){
                        aux.add(e);
                    }
                }
            }
            encontrado = true;
            bd.setBusqueda(aux);
        }

        return "busqAvanzada.xhtml";
    }
    
    public List<Evento> getListaBusqueda(){
        return bd.getBusqueda();
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
