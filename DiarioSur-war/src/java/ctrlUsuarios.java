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
 * @author Garri
 */
@Named(value = "ctrlUsuarios")
@RequestScoped
public class ctrlUsuarios {

    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    private SuperUsuario su = new SuperUsuario();
    private Periodista p = new Periodista();
    private JefeDeRedactores jdr = new JefeDeRedactores();
    private Administrador a = new Administrador();
    
    private String rol = "";
    
    
    @Inject
    private BdBean bd;
    
    @Inject
    private ctrlAutorizacion cta;


    
    public ctrlUsuarios() {  
    }
    
    public int rolAdm(){
        if (rol.equals("Administrador")){
            return 2;
        }
        return 1;
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
    
    
    public String nuevoUsuarioGestion(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        String pag = null;
        if (bd.existeUsuario(a)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El email " + a.getEmail() + " está en uso por otro usuario.",
                    "El email " + a.getEmail() + " está en uso por otro usuario."));
        } else {
            /*dependiendo del tipo de usuario*/
            switch (rol.charAt(0)) {
                case 'A':
                    bd.crearAdmin(a);
                    break;
                case 'J':
                    bd.crearJDR(a);
                    break;
                case 'P':
                    bd.crearPeriodista(a);
                    break;
                case 'S':
                    bd.crearSU(a);
                    break;
                case 'U':
                    bd.crearUR(a);
                    break;
            } 
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario " + a.getEmail() + " registrado correctamente.",
                    "Usuario " + a.getEmail() + " registrado correctamente."));
            pag = "gestionUsuario.xhtml";
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
                cta.setUsuarioLogeado(user);
            }
        }else{
           FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación, "
                    + "pareja email - contraseña incorrectos.", "Error de autenticación, "
                            + "pareja email - contraseña incorrectos."));
        }
        usuario = new UsuarioRegistrado();
        return pag;
    }

    public Administrador getA() {
        return a;
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
    
    
    
    public List<Notificacion> getListaNotif(){
        return bd.getNotif();
    }
    
     public String eliminarNotif(Notificacion n){
        bd.eliminarNotificacion(n);
        return null;
    }
    
    
    public String eliminarUR(UsuarioRegistrado u){
        bd.eliminarUR(u);
        return null;
    }
    
    public String eliminarSU(SuperUsuario u) {
        bd.eliminarSU(u);
        return null;
    }
    
    public String eliminarP(Periodista u) {
        bd.eliminarPeriodista(u);
        return null;
    }
    
    public String eliminarJDR(JefeDeRedactores u) {
        bd.eliminarJDR(u);
        return null;
    }
    
    public String eliminarA(Administrador u) {
        bd.eliminarAdmin(u);
        return null;
    }
    
    public List<UsuarioRegistrado> getListaUR(){
        return bd.getUr();
    }
    
    public List<SuperUsuario> getListaSU(){
        return bd.getSuperu();
    }
    
    public List<JefeDeRedactores> getListaJDR(){
        return bd.getJdr();
    }

    public List<Periodista> getListaP(){
        return bd.getPeri();
    }
    
    public List<Administrador> getListaA(){
        return bd.getAdmin();
    }
    
    public UsuarioRegistrado getUsuario() {
        return usuario;
    }
    
    
    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }
    
    
    public SuperUsuario getSu() {
        return su;
    }

    public void setSu(SuperUsuario su) {
        this.su = su;
    }

    public Periodista getP() {
        return p;
    }

    public void setP(Periodista p) {
        this.p = p;
    }

    public JefeDeRedactores getJdr() {
        return jdr;
    }

    public void setJdr(JefeDeRedactores jdr) {
        this.jdr = jdr;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String  asigRol(String rol) {
        this.rol = rol;
        return null;
    }

        
    

    
    public String mostrarUsuario(UsuarioRegistrado usuario) {
        switch (usuario.getIdUser().charAt(0)) {
            case 'A': this.a=bd.buscarAdmin((Administrador) usuario);
            break;
            case 'J': this.jdr = bd.buscarJDR((JefeDeRedactores) usuario);
            break;
            case 'P': this.p = bd.buscarPeriodista((Periodista) usuario);
            break;
            case 'S': this.su = bd.buscarSU((SuperUsuario) usuario);
            break;
            case 'U': this.usuario=usuario;
            break;
        }
        return "EditarUsuario.xhtml";
    }
    
    public String editarUsuario (UsuarioRegistrado u) {
        usuario.setNombre(u.getNombre());
        usuario.setApellidos(u.getApellidos());
        usuario.setDni(u.getDni());
        usuario.setEmail(u.getEmail());
        usuario.setPassword(u.getPassword());
        //usuario.set
        // Por implementar
        return null;
    }
    
    
}
