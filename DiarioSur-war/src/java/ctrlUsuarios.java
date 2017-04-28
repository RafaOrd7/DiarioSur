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
    private String nombre, apellidos, email, dni, password, password2;
    private List<UsuarioRegistrado> usuarios = new ArrayList<>();

    public ctrlUsuarios() {  
        usuarios = new ArrayList<>();
    }
    
    public String nuevoUsuario() {
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setDni(dni);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuarios.add(usuario);
        return "registerSuccess.xhtml";
    }
    
    public String logIn(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        int cont = 0;
        UsuarioRegistrado user = null;
        boolean esta = false, passok = false;
        
        while(cont < usuarios.size() && !esta){
            user = usuarios.get(cont);
            if(user.getEmail().equals(usuario.getEmail())){
                esta = true;
                if(user.getPassword().equals(usuario.getPassword())){
                    passok = true;
                }
            }
            cont++;
        }
        
        if(!esta){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario " 
                    + getUsuario()+ " no está registrado.", "El usuario "+getUsuario()+
                            " no está registrado."));
            return null;
        }else{
            if(!passok){
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Error, contraseña incorrecta.", "Error, contraseña incorrecta."));
                return "registerSuccess.xhtml";
            }
        }
        setUsuario(user);
        return "index.xhtml";
    }

    
    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword2() {
        return password2;
    }

    public List<UsuarioRegistrado> getUsuarios() {
        return usuarios;
    }

    
    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setUsuarios(List<UsuarioRegistrado> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
