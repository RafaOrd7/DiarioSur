/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Reporte;
import diariosur.UsuarioRegistrado;
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
        FacesContext ctx = FacesContext.getCurrentInstance();
        String pag = null;
        if (bd.existeUsuario(usuario)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El email " + usuario.getEmail() + " está en uso por otro usuario.",
                    "El email " + usuario.getEmail() + " está en uso por otro usuario."));
        } else {
            bd.crearUR(usuario);  
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario " + usuario.getEmail() + " registrado correctamente.",
                    "Usuario " + usuario.getEmail() + " registrado correctamente."));
            pag="index.xhtml";
        }
        return pag;
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
    

    
    public String validarEmail(){
        
        String page = "forgpass.xhtml";
        
        if(!usuario.getEmail().equals("")){
            
            String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
             // compila la expresion regular
            Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
             // compara la expresion regular con el email introducido por el usuario
            Matcher matcher = pattern.matcher(usuario.getEmail());

            if(matcher.matches()){
                 page = "index.xhtml";
                 FacesContext ctx = FacesContext.getCurrentInstance();
                 ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje enviado, compruebe su email."
                        , "Mensaje enviado, compruebe su email."));
            }else{
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, email no válido.",
                     "Error, email no válido."));   
            }
        }
        return page;
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

    public int comprobarUserAdmin(){
        if(getUsuarioLogeado()!= null && getUsuarioLogeado().getIdUser().substring(0,1).equals("A")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserJDR(){
        if(getUsuarioLogeado()!= null && getUsuarioLogeado().getIdUser().substring(0,1).equals("J")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserPeriodista(){
        if(getUsuarioLogeado()!= null && getUsuarioLogeado().getIdUser().substring(0,1).equals("P")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserSU(){
        if(getUsuarioLogeado()!= null && getUsuarioLogeado().getIdUser().substring(0,1).equals("S")){
            return 2;
        }
        return 1;
    }    
    public int comprobarUserRegistrado(){
        if(getUsuarioLogeado()!= null && getUsuarioLogeado().getIdUser().substring(0,1).equals("U")){
            return 2;
        }
        return 1;
    }    
    
    public String logout(){
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuarioLogeado = null;
        return "index.xhtml";
    }
    
    public static void setUsuarioLogeado(UsuarioRegistrado usuarioLogeado) {
        ctrlUsuarios.usuarioLogeado = usuarioLogeado;
    }
    
}
