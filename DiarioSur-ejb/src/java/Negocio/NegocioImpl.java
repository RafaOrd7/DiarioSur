/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.UsuarioRegistrado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Garri
 */
@Stateless
public class NegocioImpl implements Negocio{
    
    
    private static Long contId=0L;
    
    @PersistenceContext(unitName = "DiarioSurEE-Entidades")
    private EntityManager em;

    
    @Override
    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException{
         UsuarioRegistrado user = em.find(UsuarioRegistrado.class, u.getIdUser());
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }
        contId++;
        u.setIdUser("U" + contId);
        em.persist(u);

    }
    
    @Override
    public void compruebaLogin(UsuarioRegistrado u)  throws DiarioSurException {
        // TODO
        UsuarioRegistrado aux=em.find(UsuarioRegistrado.class, u.getIdUser());
        if(aux==null){
            throw new DiarioSurException();
        }else{
          
        }
    }
    
    
    
}
