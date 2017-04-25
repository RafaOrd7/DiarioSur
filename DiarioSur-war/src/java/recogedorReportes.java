/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Reporte;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Garri
 */
@Named(value = "recogedorReporte")
@Dependent
public class recogedorReportes {

    
    private int tipoReporte;
    private String comentario;
    private List<Reporte> reportes;

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
        reportes=new ArrayList<Reporte>();
    }
    
    
    
    public String enviarReporte(){       
        Reporte aux= new Reporte(comentario,String.valueOf(tipoReporte));
        reportes.add(aux);
        return "index.xhtml";
    }
}
