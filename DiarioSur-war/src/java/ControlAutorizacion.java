/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Administrador;
import diariosur.JefeDeRedactores;
import diariosur.Periodista;
import diariosur.SuperUsuario;
import diariosur.UsuarioRegistrado;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author alberto
 */
@Named(value = "controlAutorizacion")
@RequestScoped
public class ControlAutorizacion {
    
    private UsuarioRegistrado usuario;
    private SuperUsuario superUsuario;
    private Periodista periodista;
    private JefeDeRedactores jefeRedactores;
    private Administrador administrador;
    
    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public SuperUsuario getSuperUsuario() {
        return superUsuario;
    }

    public void setSuperUsuario(SuperUsuario superUsuario) {
        this.superUsuario = superUsuario;
    }
    
    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }
    
    public JefeDeRedactores getJefeRedactores() {
        return jefeRedactores;
    }

    public void setJefeRedactores(JefeDeRedactores jefeRedactores) {
        this.jefeRedactores = jefeRedactores;
    }
    
    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
    
    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
    
}
