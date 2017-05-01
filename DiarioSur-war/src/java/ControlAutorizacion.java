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

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
    
}
