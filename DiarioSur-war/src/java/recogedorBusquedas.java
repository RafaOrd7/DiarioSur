/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Evento;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author alberto
 */
@Named(value = "recogedorBusquedas")
@RequestScoped
public class recogedorBusquedas {

    private String busqueda;
    private static List<Evento> eventos;


    
    @Inject
    private BdBean bd;
    
    
    
    public String buscar(){
        
        if(busqueda != null){
            for(Evento e : bd.getEv()){
                if(e.getDescripcion() != null){
                    if(e.getNombre().contains(busqueda) || e.getDescripcion().contains(busqueda)){
                        eventos.add(e);
                    }  
                }
            }
        }
        
        return "index";
    }
    
    
    public static List<Evento> getEventos() {
        return eventos;
    }

    public static void setEventos(List<Evento> eventos) {
        recogedorBusquedas.eventos = eventos;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public BdBean getBd() {
        return bd;
    }

    public void setBd(BdBean bd) {
        this.bd = bd;
    }
    
    
    /**
     * Creates a new instance of recogedorBusquedas
     */
    public recogedorBusquedas(){
    }
}
