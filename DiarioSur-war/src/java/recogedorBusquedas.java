/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Entidades.UsuarioRegistrado;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import javax.ejb.EJB;
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
    private ctrlAutorizacion cta;

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

    public List<Evento> getListaEvento() throws DiarioSurException {
        List<Evento> lista = negocio.getEv();
        
        if (cta.getUsuarioLogeado() != null && !negocio.devolverPref(cta.getUsuarioLogeado()).isEmpty()) {

            lista = ordenarEvPorTipo(cta.getUsuarioLogeado(), lista);
        }
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

    private List<Evento> ordenarEvPorTipo(UsuarioRegistrado usuarioLogeado, List<Evento> lista) {
        String tiposVisitados = negocio.getTiposVisitadosDe(usuarioLogeado);

        HashMap<String, Integer> ordenar = new HashMap<>();

        List<Evento> ordenada = new ArrayList<>();
        int concierto = 0, exposicion = 0, musical = 0, deportivo = 0, teatral = 0, otro = 0;
        Scanner sc = new Scanner(tiposVisitados);
        int cantidad = tiposVisitados.length();
        int inicio = cantidad-10;
        
        int cont = 0;
        sc.useDelimiter("[&]");
        while (sc.hasNext()) {
            cont+=2;
            
            if (cont > inicio) {
                switch (sc.next()) {
                    case "c":
                        concierto++;
                        break;
                    case "e":
                        exposicion++;
                        break;
                    case "m":
                        musical++;
                        break;
                    case "d":
                        deportivo++;
                        break;
                    case "t":
                        teatral++;
                        break;
                    case "o":
                        otro++;
                        break;
                }
            }else{
                sc.next();
            }
        }
        ordenar.put("concierto", concierto);
        ordenar.put("exposicion", exposicion);
        ordenar.put("musical", musical);
        ordenar.put("deportivo", deportivo);
        ordenar.put("teatral", teatral);
        ordenar.put("otro", otro);
        LinkedHashMap<String, Integer> mapaOrdenado
                = sortHashMapByValues(ordenar);

        List<String> listadetipos = new ArrayList<>();

        for (String tipo : mapaOrdenado.keySet()) {

            listadetipos.add(tipo);
        }

        for (String tipo : listadetipos) {

            List<Evento> aux = new ArrayList<>();
            for (Evento e : lista) {
                if (e.getTipo().equals(tipo)) {
                    ordenada.add(e);
                }
            }
        }
        Collections.reverse(ordenada);
        return ordenada;
    }

    public LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap
                = new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp2.equals(comp1)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
