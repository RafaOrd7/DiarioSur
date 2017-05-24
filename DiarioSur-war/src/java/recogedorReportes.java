/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Entidades.Reporte;
import Entidades.UsuarioRegistrado;
import Entidades.Valoracion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Garri
 */
@Named(value = "recogedorReportes")
@RequestScoped
public class recogedorReportes {

    private int tipoReporte;
    private String comentario;
    private UsuarioRegistrado usuario;
    private static Reporte seleccionado;
    private List<Reporte> RepEv;
    private List<Reporte> RepVal;
    private Negocio.NegocioImpl neg;
    
     @Inject
    private ctrlAutorizacion cta;
     
    public static Reporte getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Reporte seleccionado) {
        recogedorReportes.seleccionado = seleccionado;
    }

    public String ver(Reporte reporte) {
        seleccionado = reporte;
        return "VerReporte.xhtml";
    }
public String verVal(Reporte reporte) {
        seleccionado = reporte;
        return "VerReporteVal.xhtml";
    }
    
    public String eliminarVal() {
        neg.eliminarReporte(seleccionado);
        return "GestionarReporte";

    }

    public String eliminarEv() {
       neg.eliminarReporte(seleccionado);
        return "GestionarReporte";

    }

    public List<Reporte> getRepEv() {
        
        if(RepEv.size()==0){
            return null;
        }
        return RepEv;
    }
    public void setRepEv(List<Reporte> l){
        RepEv = l;
    }

    public List<Reporte> getRepVal() {
        if(RepVal.size()==0){
            return null;
        }
        return RepVal;
    }
    public void setRepVal(List<Reporte> l){
        RepVal = l;
    }
    

    public int getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public recogedorReportes() {

    }

    public String enviarReporte() {
        Evento ev = recogedorEventos.getSeleccionado();
        UsuarioRegistrado user = cta.getUsuarioLogeado();
        Valoracion val = null;//recogedorValoraciones.getSeleccionado();
        Reporte aux = new Reporte(comentario, new Date(), String.valueOf(tipoReporte), ev, null, user);
        RepVal.add(aux);
        neg.crearReporte(aux);

        return "index.xhtml";
    }

    public String enviarReporteVal() {
        Evento ev = recogedorEventos.getSeleccionado();
        UsuarioRegistrado user = cta.getUsuarioLogeado();
        Valoracion val = recogedorValoraciones.getSeleccionada();
        val.setEvento(ev);
        Reporte aux = new Reporte(comentario, new Date(), String.valueOf(tipoReporte), ev, val, user);
        RepEv.add(aux);
        neg.crearReporte(aux);

        return "index.xhtml";
    }

}
