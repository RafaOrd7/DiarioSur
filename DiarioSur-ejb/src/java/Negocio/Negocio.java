/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.UsuarioRegistrado;
import javax.ejb.Local;

/**
 *
 * @author Garri
 */
@Local
public interface Negocio {
    
    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException;
    public void compruebaLogin(UsuarioRegistrado u) throws DiarioSurException;
    
    
}
