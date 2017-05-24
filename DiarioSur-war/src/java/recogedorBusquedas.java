/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author alberto
 */
@Named(value = "recogedorBusquedas")
@RequestScoped
public class recogedorBusquedas {

    private String busqueda = "";
    private static boolean encontrado;
    
    private List<Evento> busq = new ArrayList<>();
    
    @EJB
    private Negocio negocio;

    public String buscar() {
        List<Evento> aux = new ArrayList<Evento>();
        encontrado = false;
        if (!busqueda.equals("")) {
            for (Evento e : negocio.getEv()) {
                if (e.getNombre().toLowerCase().contains(busqueda.toLowerCase()) || e.getDescripcion().toLowerCase().contains(busqueda.toLowerCase())) {
                    aux.add(e);
                }
            }
            encontrado = true;
            busq = aux;
        }

        return "index";
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public static boolean isEncontrado() {
        return encontrado;
    }

    public static void setEncontrado(boolean encontrado) {
        recogedorBusquedas.encontrado = encontrado;
    }
    
    public List<Evento> getListaEvento(){
        return negocio.getEv();
    }
    
    public List<Evento> getListaBusqueda(){
        return busq;
    }
    
    /**
     * Creates a new instance of recogedorBusquedas
     */
    public recogedorBusquedas() {
    }
}
