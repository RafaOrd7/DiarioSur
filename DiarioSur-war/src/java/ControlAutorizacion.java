/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Administrador;
import Entidades.JefeDeRedactores;
import Entidades.Periodista;
import Entidades.SuperUsuario;
import Entidades.UsuarioRegistrado;
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
