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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Garri
 */
@Named(value = "ctrlUsuarios")
@RequestScoped
public class ctrlUsuarios implements Serializable {

    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    private UsuarioRegistrado u = new UsuarioRegistrado();
    private SuperUsuario su = new SuperUsuario();
    private Periodista p = new Periodista();
    private JefeDeRedactores jdr = new JefeDeRedactores();
    private Administrador a = new Administrador();
    private String rol = new String();
    
    @Inject
    private BdBean bd;
    
    @Inject
    private ctrlAutorizacion cta;

    
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

    public UsuarioRegistrado getU() {
        return u;
    }

    public void setU(UsuarioRegistrado u) {
        this.u = u;
    }

    public Administrador getA() {
        return a;
    }

    public String getRol() {
        return rol;
    }

    public void setSu(SuperUsuario su) {
        this.su = su;
    }

    public void setP(Periodista p) {
        this.p = p;
    }

    public void setJdr(JefeDeRedactores jdr) {
        this.jdr = jdr;
    }

    public void setA(Administrador a) {
        this.a = a;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public SuperUsuario getSu() {
        return su;
    }

    public Periodista getP() {
        return p;
    }

    public JefeDeRedactores getJdr() {
        return jdr;
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

    
    public String mostrarUsuario(UsuarioRegistrado usuario) {
        switch (usuario.getIdUser().charAt(0)) {
            case 'A':   this.a=bd.buscarAdmin((Administrador) usuario);
                        this.rol="Administrador";
            break;
            case 'J':   this.jdr = bd.buscarJDR((JefeDeRedactores) usuario);
                        this.rol="JefeDeRedactores";
            break;
            case 'P':   this.p = bd.buscarPeriodista((Periodista) usuario);
                        this.rol="Periodista";
            break;
            case 'S':   this.su = bd.buscarSU((SuperUsuario) usuario);
                        this.rol="SuperUsuario";
            break;
            case 'U':   this.u=usuario;
                        this.rol="UsuarioRegistrado";
            break;
        }
        return "EditarUsuario.xhtml";
    }
    
    public String editarUsuarioRegistrado () {

            //(rol.equals("UsuarioRegistrado")) 
                    UsuarioRegistrado aux = bd.buscarPorId(u.getIdUser());
                    aux.setNombre(usuario.getNombre());
                    aux.setApellidos(usuario.getApellidos());
                    aux.setDni(usuario.getDni());
                    aux.setEmail(usuario.getEmail());
                    return "gestionUsuario.xhtml";

       /* switch (rol) {
            case "Administrador":
                
                break;
            case "JefeDeRedactores":
                break;
            case "Periodista":
                break;
            case "SuperUsuario":
                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado aux = bd.buscarUR(usuario);
                aux.setNombre(usuario.getNombre());
                aux.setApellidos(usuario.getApellidos());
                aux.setDni(usuario.getDni());
                aux.setEmail(usuario.getEmail());  
                break;
        }*/


        //usuario.set
    }
    
    public String editarSuperUsuario() {

        //usuario.set
        // Por implementar
        return null;
    }
    
    public String editarPeriodista() {

        //usuario.set
        // Por implementar
        return null;
    }
    
    public String editarJefeDeRedactores() {

        //usuario.set
        // Por implementar
        return null;
    }
    
    public String editarAdministrador() {
        
        //usuario.set
        // Por implementar
        return null;
    }
    
    
}
