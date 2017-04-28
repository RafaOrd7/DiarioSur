/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Reporte;
import diariosur.UsuarioRegistrado;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
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
    
    private Reporte seleccionado;

    public Reporte getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Reporte seleccionado) {
        this.seleccionado = seleccionado;
    }
    
    public String ver(Reporte reporte){
        seleccionado=reporte;
        return "VerReporte.xhtml";
    }
    
    
    public List<Reporte> getReportes(){
        return bd.getRep();
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
        
        Reporte aux = new Reporte(comentario, String.valueOf(tipoReporte));
        bd.crearReporte(aux);
        return "index.xhtml";
    }
    
}
