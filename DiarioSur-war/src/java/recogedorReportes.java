/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Evento;
import Entidades.Reporte;
import Entidades.UsuarioRegistrado;
import Entidades.Valoracion;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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

    @EJB
    private Negocio negocio;

    @Inject
    private ctrlAutorizacion cta;

    private static Reporte seleccionado;

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

    public String eliminarVal() throws DiarioSurException {
        negocio.eliminarReporteVal(seleccionado);
        return "GestionarReporte.xhtml";
    }

    public String eliminarEv() throws DiarioSurException {
        negocio.eliminarReporteEv(seleccionado);
        return "GestionarReporte.xhtml";

    }

    public List<Reporte> getReportesEv() {
        return negocio.getReportesEv();
    }

    public List<Reporte> getReportesVal() {
        return negocio.getReportesVal();
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

    public String enviarReporte() throws DiarioSurException {
        Evento ev = recogedorEventos.getSeleccionado();
        UsuarioRegistrado user = cta.getUsuarioLogeado();
        Valoracion val = null;
        Reporte aux = new Reporte(comentario, new Date(), String.valueOf(tipoReporte), ev, null, user);
        negocio.enviarRepEv(aux);
        return "index.xhtml";
    }

    public String enviarReporteVal() throws DiarioSurException {
        Evento ev = recogedorEventos.getSeleccionado();
        UsuarioRegistrado user = cta.getUsuarioLogeado();
        Valoracion val = recogedorValoraciones.getSeleccionada();
        val.setEvento(ev);

        

        Reporte aux = new Reporte(comentario, new Date(), String.valueOf(tipoReporte), ev, val, user);
        negocio.enviarRepVal(aux);
        return "index.xhtml";
    }

}
