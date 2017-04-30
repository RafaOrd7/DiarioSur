/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Evento;
import diariosur.Reporte;
import diariosur.UsuarioRegistrado;
import diariosur.Valoracion;
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

    @Inject
    private BdBean bd;

    private static Reporte seleccionado;

    public static Reporte getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Reporte seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String ver(Reporte reporte) {
        seleccionado = reporte;
        return "VerReporte.xhtml";
    }

    public String eliminarVal() {
        bd.eliminarReporteVal(seleccionado);
        return "GestionarReporte";

    }
    public String eliminarEv() {
        bd.eliminarReporteEv(seleccionado);
        return "GestionarReporte";

    }

    public List<Reporte> getReportesEv() {
        return bd.getRepEv();
    }
    
    public List<Reporte> getReportesVal() {
        return bd.getRepVal();
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
        Evento ev=recogedorEventos.getSeleccionado();
        UsuarioRegistrado user=ctrlUsuarios.getUsuarioLogeado();
        Valoracion val=null;//recogedorValoraciones.getSeleccionado();
        Reporte aux = new Reporte(comentario, new Date(), String.valueOf(tipoReporte), ev, null, user);
        if(val==null){
            bd.crearReporteEv(aux);
        }else if(ev==null){
            bd.crearReporteVal(aux);
        }
        
        return "index.xhtml";
    }


}
