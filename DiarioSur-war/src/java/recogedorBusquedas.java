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

    private String busqueda = "";
    private static boolean encontrado;

    @Inject
    private BdBean bd;

    public String buscar() {
        List<Evento> aux = new ArrayList<Evento>();
        encontrado = false;
        if (!busqueda.equals("")) {
            for (Evento e : bd.getEv()) {
                if (e.getNombre().contains(busqueda) || e.getDescripcion().contains(busqueda)) {
                    aux.add(e);
                }
            }
            encontrado = true;
            bd.setBusqueda(aux);
        }

        return "index";
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

    public static boolean isEncontrado() {
        return encontrado;
    }

    public static void setEncontrado(boolean encontrado) {
        recogedorBusquedas.encontrado = encontrado;
    }
    
    /**
     * Creates a new instance of recogedorBusquedas
     */
    public recogedorBusquedas() {
    }
}
