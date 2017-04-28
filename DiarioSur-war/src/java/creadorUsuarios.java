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
import javax.inject.Inject;

/**
 *
 * @author Garri
 */
@Named(value = "creadorUsuarios")
@RequestScoped
public class creadorUsuarios {

    private UsuarioRegistrado usuario;
    private String nombre, apellidos, email, dni, password, password2;
    private List<UsuarioRegistrado> usuarios;

    public creadorUsuarios() {  
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
