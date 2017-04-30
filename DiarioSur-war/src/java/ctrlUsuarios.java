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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Garri
 */
@Named(value = "ctrlUsuarios")
@RequestScoped
public class ctrlUsuarios {

    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    
    private static UsuarioRegistrado usuarioLogeado;
    
    @Inject
    private BdBean bd;


    
    public ctrlUsuarios() {  
    }

    
    
    public String nuevoUsuario() {
        bd.crearUR(usuario);
        return "registerSuccess.xhtml";
    }
    
    public String logIn(){
        /* Utiliza BdBean y comprueba que esta en la base de datos y que los
           datos introducidos son correctos */
        
        UsuarioRegistrado user=bd.buscarPorEmail(usuario);
        String pag = null;
        
        if(user != null){
            if(user.getPassword().equals(usuario.getPassword())){
                pag = "index.xhtml";
                usuarioLogeado = user;
            }
        }else{
           FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación, "
                    + "pareja email - contraseña incorrectos.", "Error de autenticación, "
                            + "pareja email - contraseña incorrectos."));
        }
        return pag;
    }

    
    public UsuarioRegistrado getUsuario() {
        return usuario;
    }
    
    public static UsuarioRegistrado getUsuarioLogeado() {
        return usuarioLogeado;
    }
    
        public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }
        
    public boolean comprobarUser(String id){
        if(usuarioLogeado.getIdUser().substring(0,1).equals(id)){
            return true;
        } 
        return false;
    }

    public static void setUsuarioLogeado(UsuarioRegistrado usuarioLogeado) {
        ctrlUsuarios.usuarioLogeado = usuarioLogeado;
    }
    
}
