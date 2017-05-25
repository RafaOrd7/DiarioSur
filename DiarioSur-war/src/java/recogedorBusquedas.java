/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Entidades.Usuario;
import Negocio.Negocio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.Cookie;

/**
 *
 * @author alberto
 */
@Named(value = "recogedorBusquedas")
@RequestScoped
public class recogedorBusquedas {

    private String busqueda = "";
    private static boolean encontrado;
    private CookieHelper ch;

    private List<Evento> busq = new ArrayList<>();

    @Inject
    private ctrlAutorizacion cta;

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

    public List<Evento> getListaEvento() {
        List<Evento> lista = negocio.getEv();
        
       
       ch.getCookie(cta.getUsuarioLogeado().getIdUser());
       ;

        lista=ordenarPorTipo(lista, cta.getC());

        return lista;
    }

    public List<Evento> getListaBusqueda() {
        return busq;
    }

    /**
     * Creates a new instance of recogedorBusquedas
     */
    public recogedorBusquedas() {
    }

    private List<Evento> ordenarPorTipo(List<Evento> lista, Cookie c) {
        String tipo = c.getValue();
        List<Evento> ordenada = new ArrayList<>();
        List<Evento> aux = new ArrayList<>();
        for (Evento e : lista) {
            if (e.getTipo().equals(tipo)) {
                ordenada.add(e);
            }else{
                aux.add(e);
            }
        }
       
       for(Evento e:aux){
           ordenada.add(e);
       }
        return ordenada;
    }
}
