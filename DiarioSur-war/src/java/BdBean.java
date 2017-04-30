/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Administrador;
import diariosur.Anuncio;
import diariosur.Evento;
import diariosur.JefeDeRedactores;
import diariosur.Notificacion;
import diariosur.Periodista;
import diariosur.Reporte;
import diariosur.SuperUsuario;
import diariosur.UsuarioRegistrado;
import diariosur.Valoracion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Garri
 */
@Named(value = "bdBean")
@ApplicationScoped
public class BdBean implements Serializable{

    
    
    private Long contId=0L;
    private List<Administrador> admin=new ArrayList<>();
    private List<Anuncio> anu=new ArrayList<>();
    private List<Evento> ev=new ArrayList<>();
    private List<JefeDeRedactores> jdr=new ArrayList<>();
    private List<Notificacion> notif=new ArrayList<>();
    private List<Periodista> peri=new ArrayList<>();
    private List<Reporte> rep=new ArrayList<>();
    private List<SuperUsuario> superu=new ArrayList<>();
    private List<UsuarioRegistrado> ur=new ArrayList<>();
    private List<Valoracion> val=new ArrayList<>();

    public void crearAdmin(Administrador a){
       contId++;
       a.setIdUser("A"+contId);
       admin.add(a);
    }
    
    public Administrador buscarAdmin(Administrador a){
        for(Administrador aux:admin){
            if(a.getIdUser().equals(aux.getIdUser())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarAdmin(Administrador a){
        admin.remove(a);
    }
    
    public void crearAnuncio(Anuncio a){
        contId++;
       a.setId_anuncio(contId);
       anu.add(a);
    }
    
    public Anuncio buscarAnuncio(Anuncio a){
        for(Anuncio aux:anu){
            if(a.getId_anuncio().equals(aux.getId_anuncio())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarAnuncio(Anuncio a){      
        anu.remove(a);
    }
    
    public void crearEvento(Evento a){
        contId++;
       a.setId(contId);
       ev.add(a);
    }
    
    public Evento buscarAnuncio(Evento a){
        for(Evento aux:ev){
            if(a.getId().equals(aux.getId())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarEvento(Evento a){
        ev.remove(a);
    }
    
    public void crearJDR(JefeDeRedactores a){
       contId++;
       a.setIdUser("J"+contId);
       jdr.add(a);
    }
    
    public JefeDeRedactores buscarJDR(JefeDeRedactores a){
        for(JefeDeRedactores aux:jdr){
            if(a.getIdUser().equals(aux.getIdUser())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarJDR(JefeDeRedactores a){
        jdr.remove(a);
    }
    
    public void crearNotificacion(Notificacion a){
        contId++;
       a.setId(contId);
       notif.add(a);
    }
    
    public Notificacion buscarNotificacion(Notificacion a){
        for(Notificacion aux:notif){
            if(a.getId().equals(aux.getId())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarNotificacion(Notificacion a){
        notif.remove(a);
    }
    
    public void crearPeriodista(Periodista a){
         contId++;
       a.setIdUser("P"+contId);
       peri.add(a);
    }
    
    public Periodista buscarPeriodista(Periodista a){
        for(Periodista aux:peri){
            if(a.getIdUser().equals(aux.getIdUser())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarPeriodista(Periodista a){
        peri.remove(a);
    }
    
    public void crearReporte(Reporte a){
        contId++;
       a.setId(contId);
       rep.add(a);
    }
    
    public Reporte buscarReporte(Reporte a){
        for(Reporte aux:rep){
            if(a.getId_reporte().equals(aux.getId_reporte())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarReporte(Reporte a){
        rep.remove(a);
    }
    
    public void crearSU(SuperUsuario a){
       contId++;
       a.setIdUser("S"+contId);
       superu.add(a);
    }
    
    public SuperUsuario buscarSU(SuperUsuario a){
        for(SuperUsuario aux:superu){
            if(a.getIdUser().equals(aux.getIdUser())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarSU(SuperUsuario a){
        superu.remove(a);
    }
    
    public void crearUR(UsuarioRegistrado a){
       contId++;
       a.setIdUser("U"+contId);
       ur.add(a);
    }
    
    public UsuarioRegistrado buscarUR(UsuarioRegistrado a){
        for(UsuarioRegistrado aux:ur){
            if(a.getIdUser().equals(aux.getIdUser())){
                return aux;
            }
        }
        return null;
    }
    
    public UsuarioRegistrado buscarPorEmail(UsuarioRegistrado a){
        for(UsuarioRegistrado aux:ur){
            if(a.getEmail().equals(aux.getEmail())){
                return aux;
            }
        }
        for(SuperUsuario aux:superu){
            if(a.getEmail().equals(aux.getEmail())){
                return aux;
            }
        }
        for(Periodista aux:peri){
            if(a.getEmail().equals(aux.getEmail())){
                return aux;
            }
        }
        for(JefeDeRedactores aux:jdr){
            if(a.getEmail().equals(aux.getEmail())){
                return aux;
            }
        }
        for(Administrador aux:admin){
            if(a.getEmail().equals(aux.getEmail())){
                return aux;
            }
        }
        
        return null;
    }
    
    
    public void eliminarUR(UsuarioRegistrado a){
        ur.remove(a);
    }
    
    
    public void crearValoracion(Valoracion a){
        contId++;
       a.setId(contId);
       val.add(a);
    }
    
    public Valoracion buscarValoracion(Valoracion a){
        for(Valoracion aux:val){
            if(a.getIdValoracion().equals(aux.getIdValoracion())){
                return aux;
            }
        }
        return null;
    }
    
    public void eliminarValoracion(Valoracion a){
        val.remove(a);
    }
    
    
    
    public List<Administrador> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Administrador> admin) {
        this.admin = admin;
    }

    public List<Anuncio> getAnu() {
        return anu;
    }

    public void setAnu(List<Anuncio> anu) {
        this.anu = anu;
    }

    public List<Evento> getEv() {
        return ev;
    }

    public void setEv(List<Evento> ev) {
        this.ev = ev;
    }

    public List<JefeDeRedactores> getJdr() {
        return jdr;
    }

    public void setJdr(List<JefeDeRedactores> jdr) {
        this.jdr = jdr;
    }

    public List<Notificacion> getNotif() {
        return notif;
    }

    public void setNotif(List<Notificacion> notif) {
        this.notif = notif;
    }

    public List<Periodista> getPeri() {
        return peri;
    }

    public void setPeri(List<Periodista> peri) {
        this.peri = peri;
    }

    public List<Reporte> getRep() {
        return rep;
    }

    public void setRep(List<Reporte> rep) {
        this.rep = rep;
    }

    public List<SuperUsuario> getSuperu() {
        return superu;
    }

    public void setSuperu(List<SuperUsuario> superu) {
        this.superu = superu;
    }

    public List<UsuarioRegistrado> getUr() {
        return ur;
    }

    public void setUr(List<UsuarioRegistrado> ur) {
        this.ur = ur;
    }

    public List<Valoracion> getVal() {
        return val;
    }

    public void setVal(List<Valoracion> val) {
        this.val = val;
    }

   
    
    
    
    public BdBean() {
    }
    
}
