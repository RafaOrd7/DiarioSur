/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.Anuncio;
import Entidades.Evento;
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
    public boolean existeUsuario(UsuarioRegistrado u)throws DiarioSurException;
    public void editarEvento(Evento e);
    public void eliminarEvento(Evento e);
    public void meGusta(Evento e, UsuarioRegistrado u) throws DiarioSurException;
    public void crearEvento(Evento e);
    public Anuncio devolverAnuncio();
    public List<Evento> getEv();
    public int numMeGusta(Long id);
    public void crearValoracion(Valoracion v)throws DiarioSurException;
    public void crearAnuncio(Anuncio anu)throws DiarioSurException;
    public void rellenarBd();
    public void borrarAnuncio(Anuncio anuncio)throws DiarioSurException;
    public List<Anuncio> getAnu();

    public void enviarRepVal(Reporte r)throws DiarioSurException;
    public void enviarRepEv(Reporte r)throws DiarioSurException;
    public List<Reporte>getReportesVal();
    public List<Reporte>getReportesEv();
    public void eliminarReporteEv(Reporte r)throws DiarioSurException;
    public void eliminarReporteVal(Reporte r)throws DiarioSurException;
    
    public List<Valoracion> getValoraciones(Evento e) throws DiarioSurException;
    
}