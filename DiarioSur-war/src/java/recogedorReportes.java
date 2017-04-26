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
    private static List<Reporte> reportes=new ArrayList<Reporte>();

    ;

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

    public List<String> getReportes() {
        List<String>aux=new ArrayList<String>();
        int i=0;
        for(Reporte r:reportes){
            aux.add(reportes.get(i).getTexto());
            i++;
        }
        return aux;
    }

    public String enviarReporte() {
        Reporte aux = new Reporte(comentario, String.valueOf(tipoReporte));
        reportes.add(aux);
        return "index.xhtml";
    }
}
