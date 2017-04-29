/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Evento;
import diariosur.UsuarioRegistrado;
import java.io.File;
import static java.time.Clock.system;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rafao
 */
@Named(value = "recogedorEventos")
@RequestScoped
public class recogedorEventos {
    private String nombre;
    private String descripcion;
    private File imagen;
    private Date fecha;
    private String lugar;
    private String tipo;
    private float precio;
    private String tags;
    private UsuarioRegistrado like;

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    
     public String getNombre() {
        return nombre;
    }
      
        public void setNombre(String t) {
        nombre=t;
    }
        
         public String getDescripcion() {
        return descripcion;
    }
      
        public void setDescripcion(String d) {
        descripcion=d;
    }
        
         public File getImagen() {
        return imagen;
    }
      
        public void setImagen(File f) {
        imagen=f;
    }
       
       public void mostrarTipo(){
           System.out.println(tipo);
       }
    
    
}
