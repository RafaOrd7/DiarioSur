/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.Administrador;
import Entidades.Anuncio;
import Entidades.Evento;
import Entidades.JefeDeRedactores;
import Entidades.Notificacion;
import Entidades.Periodista;
import Entidades.SuperUsuario;
import Entidades.Reporte;
import Entidades.UsuarioRegistrado;
import java.util.List;

import Entidades.Valoracion;
import javax.ejb.Local;



/**
 *
 * @author Garri
 */
@Local
public interface Negocio {

    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException;

    public void compruebaLogin(UsuarioRegistrado u) throws DiarioSurException;

    public UsuarioRegistrado refrescarUsuario(UsuarioRegistrado u) throws DiarioSurException;

    public boolean existeUsuario(UsuarioRegistrado u) throws DiarioSurException;

    public void editarEvento(Evento e);

    public void eliminarEvento(Evento e) throws DiarioSurException;

    public void meGusta(Evento e, UsuarioRegistrado u) throws DiarioSurException;

    public void crearEvento(Evento e);

    public Anuncio devolverAnuncio();

    public void eliminarUR(UsuarioRegistrado u);

    public List<UsuarioRegistrado> getUR();

    public Administrador buscarAdmin(Administrador a);

    public JefeDeRedactores buscarJDR(JefeDeRedactores a);

    public Periodista buscarPeriodista(Periodista a);

    public SuperUsuario buscarSU(SuperUsuario a);

    public List<SuperUsuario> getSuperu();

    public void eliminarSU(SuperUsuario a);

    public List<Periodista> getPeri();

    public void eliminarPeriodista(Periodista a);

    public List<JefeDeRedactores> getJdr();

    public void eliminarJDR(JefeDeRedactores a);

    public List<Administrador> getAdmin();

    public void eliminarAdmin(Administrador a);

    public UsuarioRegistrado buscarUR(UsuarioRegistrado a);

    public void addPeri(Periodista per);

    public void addAdmin(Administrador adm);

    public void addJdr(JefeDeRedactores jdre);

    public void addSuperu(SuperUsuario sup);

    public void addUR(UsuarioRegistrado ur);
    public List<Evento> getEv();

    public int numMeGusta(Long id);

    public void crearValoracion(Valoracion v) throws DiarioSurException;

    public void crearAdmin(Administrador a);
    public void crearJDR(JefeDeRedactores a);
    public void crearPeriodista(Periodista a);
    public void crearSU(SuperUsuario a);
    public void crearUR(UsuarioRegistrado a);
    public boolean checkDNI(UsuarioRegistrado a);
    public List<Notificacion> getNotif(UsuarioRegistrado u);
    public void eliminarNotificacion(Notificacion n);
    public UsuarioRegistrado buscarURmail(String email);

    public void crearAnuncio(Anuncio anu) throws DiarioSurException;

    public void rellenarBd();

    public void borrarAnuncio(Anuncio anuncio) throws DiarioSurException;

    public List<Anuncio> getAnu();

    public void enviarRepVal(Reporte r) throws DiarioSurException;

    public void enviarRepEv(Reporte r) throws DiarioSurException;

    public List<Reporte> getReportesVal();

    public List<Reporte> getReportesEv();

    public void eliminarReporteEv(Reporte r) throws DiarioSurException;

    public void eliminarReporteVal(Reporte r) throws DiarioSurException;

    public List<Valoracion> getValoraciones(Evento e) throws DiarioSurException;

    public void editaPeri(Periodista per);

    public void editaUR(UsuarioRegistrado aux);

    public void editaSuperu(SuperUsuario sup);

    public void editaJdr(JefeDeRedactores jdre);

    public void editaAdmin(Administrador adm);
    public void tipoVisitado(UsuarioRegistrado usuarioLogeado,Evento evento)throws DiarioSurException;

    public String getTiposVisitadosDe(UsuarioRegistrado usuarioLogeado);

    public String devolverPref(UsuarioRegistrado usuarioLogeado);

    public boolean tieneImagen(Evento e);
    public void eliminarVal(Valoracion seleccionada);

    public boolean tieneImagenA(Anuncio a);

}
