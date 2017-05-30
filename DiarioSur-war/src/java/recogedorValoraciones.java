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
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
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
    private UsuarioRegistrado usuario;
    private static Valoracion seleccionada;

    @EJB
    private Negocio negocio;

    public String reportar(Valoracion v) {
        seleccionada = v;
        return "EnviarReporteVal.xhtml";
    }

    public String ver(Valoracion va) {
        seleccionada = va;
        return "evento.xhtml";
    }

    public List<Valoracion> getValev(Evento seleccionado) throws DiarioSurException {
        return negocio.getValoraciones(seleccionado);
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

    public String enviarValoracion(Evento seleccionado, UsuarioRegistrado usuarior) throws DiarioSurException {
        evento = seleccionado;
        usuario = usuarior;
        if(rating==null){
            rating =0;
        }
        Valoracion aux = new Valoracion(rating, comentario, fecha, usuario, evento);

        setSeleccionada(aux);
        negocio.crearValoracion(aux);
        return "evento.xhtml";
    }
}
