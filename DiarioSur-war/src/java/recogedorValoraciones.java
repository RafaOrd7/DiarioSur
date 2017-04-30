/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Evento;
import diariosur.Reporte;
import diariosur.UsuarioRegistrado;
import diariosur.Valoracion;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author rafao
 */
@Named(value = "recogedorValoraciones")
@RequestScoped
public class recogedorValoraciones {

    private Integer rating;
    private String comentario;
    private Date fecha = new Date();
    private File archivo;
    private Evento evento;
    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    private static Valoracion seleccionada = new Valoracion();
    @Inject
    private BdBean bd;

    
    
    public String ver(Valoracion va) {
        seleccionada = va;
        return "evento.xhtml";
    }

    public List<Valoracion> getValoraciones() {
        return bd.getVal();
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public static Valoracion getSeleccionada() {
        return seleccionada;
    }

    public static void setSeleccionada(Valoracion seleccionada) {
        recogedorValoraciones.seleccionada = seleccionada;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer n) {
        rating = n;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String m) {
        comentario = m;
    }
    
    public String enviarValoracion() {
        Valoracion aux = new Valoracion(rating, comentario, fecha, usuario, evento);
        bd.crearValoracion(aux);
        System.out.println(comentario);
        return "evento.xhtml";
    }
}
