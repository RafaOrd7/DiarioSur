/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Anuncio;
import Entidades.Evento;
import Negocio.Negocio;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author alumnos
 */
@Named(value = "busquedaAvanzada")
@RequestScoped
public class BusquedaAvanzada {

    private String nombre = "";
    private List<String> tipo = new ArrayList<>();
    private float precio;
    private Date fecha = null;
    private String lugar = "";
    private String descripcion = "";
    private String tags = "";
    private static boolean encontrado = false;

    private List<Evento> busq = new ArrayList<>();

    @EJB
    private Negocio negocio;

    public BusquedaAvanzada() {
    }

    public String buscar() {
        List<Evento> aux = new ArrayList<>();
        List<Evento> todo = new ArrayList<>();
        encontrado = false;
        aux = negocio.getEv();

        if (!nombre.equals("")) {
            for (Evento e : negocio.getEv()) {
                if (!e.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
                    aux.remove(e);
                }
            }
        }

        todo.addAll(aux);

        if (!descripcion.equals("")) {
            for (Evento e : todo) {
                if (!e.getDescripcion().toUpperCase().contains(descripcion.toUpperCase())) {
                    aux.remove(e);
                }
            }
        }

        todo = new ArrayList<>();
        todo.addAll(aux);

        if (!lugar.equals("")) {
            for (Evento e : todo) {
                if (!e.getGeolocalizacion().toUpperCase().contains(lugar.toUpperCase())) {
                    aux.remove(e);
                }
            }
        }

        todo = new ArrayList<>();
        todo.addAll(aux);
        
        if (!tipo.isEmpty()) {
            List<Evento> auxTipos = new ArrayList<>();
            for (Evento e : todo) {
                for (String i : tipo) {
                    if (e.getTipo().equals(i)) {
                        auxTipos.add(e);
                    }
                }

            }
            aux = auxTipos;
        }

        todo = new ArrayList<>();
        todo.addAll(aux);

        if (fecha != null) {
            DateFormat formato = new SimpleDateFormat("YYYY/MM/dd");
            for (Evento e : todo) {
                String f = formato.format(e.getFecha());
                String f2 = formato.format(getFecha());

                if (!f.equals(f2)) {
                    aux.remove(e);
                }
            }
        }

        todo = new ArrayList<>();
        todo.addAll(aux);

        if (!tags.equals("")) {
            for (Evento e : todo) {
                if (!e.getTags().toUpperCase().contains(tags.toUpperCase())) {
                    aux.remove(e);
                }
            }
        }

        busq = aux;

        if (!busq.isEmpty()) {
            encontrado = true;
        }

        return "busqAvanzada.xhtml";
    }

    public List<Evento> getListaBusqueda() {
        return busq;
    }

    public static boolean isEncontrado() {
        return encontrado;
    }

    public static void setEncontrado(boolean encontrado) {
        BusquedaAvanzada.encontrado = encontrado;
    }

    public List<String> getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(List<String> tipo) {
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
