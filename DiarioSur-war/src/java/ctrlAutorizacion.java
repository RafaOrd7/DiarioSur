/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Administrador;
import Entidades.JefeDeRedactores;
import Entidades.Notificacion;
import Entidades.Periodista;
import Entidades.Reporte;
import Entidades.SuperUsuario;
import Entidades.UsuarioRegistrado;
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

    public boolean checkAdm() {
        if (usuarioLogeado != null) {
            char rol = usuarioLogeado.getIdUser().charAt(0);
            if (rol == 'A') {
                return true;
            }
        }
        return false;
    }

    public boolean checkJDR() {
        if (usuarioLogeado != null) {
            char rol = usuarioLogeado.getIdUser().charAt(0);
            if (rol == 'J' || rol == 'A') {
                return true;
            }
        }
        return false;
    }

    public boolean checkP() {
        if (usuarioLogeado != null) {
            char rol = usuarioLogeado.getIdUser().charAt(0);
            if (rol == 'P' || rol == 'J' || rol == 'A') {
                return true;
            }
        }
        return false;
    }

    public boolean checkSU() {
        if (usuarioLogeado != null) {
            char rol = usuarioLogeado.getIdUser().charAt(0);
            if (rol == 'S' || rol == 'P' || rol == 'J' || rol == 'A') {
                return true;
            }
        }
        return false;
    }

    public boolean checkUR() {
        return usuarioLogeado != null;
    }

    public boolean checkLog() {
        return usuarioLogeado != null  && !usuarioLogeado.isBorrado();
    }

    public UsuarioRegistrado getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(UsuarioRegistrado usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public int comprobarUserAdmin() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("A")) {
            return 2;
        }
        return 1;
    }

    public int comprobarUserJDR() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("J")) {
            return 2;
        }
        return 1;
    }

    public int comprobarUserPeriodista() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("P")) {
            return 2;
        }
        return 1;
    }

    public int comprobarUserSU() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("S")) {
            return 2;
        }
        return 1;
    }

    public int comprobarUserRegistrado() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("U")) {
            return 2;
        }
        return 1;
    }

    public int comprobarUserSUEv() {
        if ((usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("S"))) {
            if (usuarioLogeado.getIdUser().equals(recogedorEventos.getSeleccionado().getUsuarioRegistrado().getIdUser())) {
                return 2;
            }
            return 1;
        }
        return 1;
    }

    public int comprobarUserRegistradoEv() {
        if (usuarioLogeado != null && usuarioLogeado.getIdUser().substring(0, 1).equals("U")) {
            if (usuarioLogeado.getIdUser().equals(recogedorEventos.getSeleccionado().getUsuarioRegistrado().getIdUser())) {
                return 2;
            }
            return 1;
        }
        return 1;
    }

}
