/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Administrador;
import diariosur.JefeDeRedactores;
import diariosur.Notificacion;
import diariosur.Periodista;
import diariosur.Reporte;
import diariosur.SuperUsuario;
import diariosur.UsuarioRegistrado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jajip
 */
@Named(value = "ctrlAutorizacion")
@SessionScoped
public class ctrlAutorizacion implements Serializable {

    private UsuarioRegistrado usuarioLogeado;
    
    public ctrlAutorizacion() {
    }
    
    public String logout() {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuarioLogeado = null;
        return "index.xhtml";
    }

    public UsuarioRegistrado getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(UsuarioRegistrado usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
    
    public int comprobarUserAdmin(){
        if(usuarioLogeado!= null && usuarioLogeado.getIdUser().substring(0,1).equals("A")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserJDR(){
        if(usuarioLogeado!= null && usuarioLogeado.getIdUser().substring(0,1).equals("J")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserPeriodista(){
        if(usuarioLogeado!= null && usuarioLogeado.getIdUser().substring(0,1).equals("P")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserSU(){
        if(usuarioLogeado!= null && usuarioLogeado.getIdUser().substring(0,1).equals("S")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserRegistrado(){
        if(usuarioLogeado!= null && usuarioLogeado.getIdUser().substring(0,1).equals("U")){
            return 2;
        }
        return 1;
    }


    
}