/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Entidades.Administrador;
import Entidades.Anuncio;
import Entidades.SuperUsuario;
import Entidades.Evento;
import Entidades.UsuarioRegistrado;
import Entidades.Valoracion;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Garri
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static Long contId = 10L;

    @PersistenceContext(unitName = "DiarioSurEE-Entidades")
    private EntityManager em;

   
    public void rellenarBd(){
       
        Administrador ad = new Administrador();
        ad.setApellidos("a");
        ad.setBorrado(false);
        ad.setCargo("si");
        ad.setDni("12345678E");
        ad.setEmail("prueba@uma.es");
        ad.setEmpresa("si");
        ad.setHistorialEventos("nada");
        ad.setNombre("prueba");
        ad.setPassword("123");
        ad.setPreferencias("si");
        ad.setTelefono("123456789");
        ad.setIdUser("A" + 1L);
        
        em.persist(ad);

        Anuncio ano = new Anuncio();
        ano.setDimensiones("si");
        ano.setEmpresa("Anuncios funcionan SL");
        ano.setEvento(new ArrayList<>());
        ano.setFechaExpiracion(new Date());
        ano.setFechaPublicacion(new Date());
        ano.setId_anuncio(2L);
        ano.setPrioridad("mucha");
        ano.setTags("vale");       
        ano.setAdministrador(em.find(Administrador.class, ad.getIdUser()));
        
        em.persist(ano);
        
        Evento e=new Evento();
        e.setAnuncio(ano);
        e.setCompra("Ninguna");
        e.setDescripcion("Evento de prueba");
        e.setFecha(new Date());
        e.setGeolocalizacion("Montilla");
        e.setId(3L);
        e.setNombre("Evento inicial");
        e.setPrecio(0F);
        e.setTags("Ninguno");
        e.setTipo("musical");
        e.setVerificado(false);
        e.setUsuarioRegistrado(ad);
        
        em.persist(e);
    
    
    }
    
 
    @Override
    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException {
        //UsuarioRegistrado user = em.find(UsuarioRegistrado.class, u.getEmail());

        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.email = '" + u.getEmail() + "'").getResultList();

        if (!lu.isEmpty()) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }

        contId++;
        u.setIdUser("U" + contId);
        em.persist(u);
    }

    @Override
    public void compruebaLogin(UsuarioRegistrado u) throws DiarioSurException {

        //UsuarioRegistrado aux = em.find(UsuarioRegistrado.class, u.getIdUser());
        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.email = '" + u.getEmail() + "'").getResultList();

        if (lu.isEmpty()) {
            throw new UsuarioNoRegistradoException();
        } else {
            UsuarioRegistrado user = lu.get(0);
            if (!user.getPassword().equals(u.getPassword())) {
                throw new ContraseniaInvalidaException();
            }
        }
    }

    @Override
    public UsuarioRegistrado refrescarUsuario(UsuarioRegistrado u) throws DiarioSurException {
        compruebaLogin(u);
        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.email = '" + u.getEmail() + "'").getResultList();
        UsuarioRegistrado user = lu.get(0);
        //UsuarioRegistrado aux = em.find(UsuarioRegistrado.class, u.getIdUser());
        em.refresh(user);
        return user;

    }

    @Override
    public boolean existeUsuario(UsuarioRegistrado u) throws DiarioSurException {
        boolean existe = false;
        //UsuarioRegistrado aux = em.find(UsuarioRegistrado.class, u.getEmail());
         List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.email = '" + u.getEmail() + "'").getResultList();
        if (!lu.isEmpty()) {
            existe = true;
        }

        return existe;
    }

    @Override
    public void editarEvento(Evento e) {
        em.merge(e);
    }

    @Override
    public void eliminarEvento(Evento e) {
        em.remove(em.merge(e));
    }

    @Override
    public void meGusta(Evento e, UsuarioRegistrado u) throws DiarioSurException {

        Evento aux = em.find(Evento.class, e.getId_evento());
        UsuarioRegistrado ur = em.find(UsuarioRegistrado.class, u.getIdUser());
        
       
        
        if (aux == null || u == null) {
            throw new EventoNoEncontradoException();
        } else {
            List<UsuarioRegistrado> mg = aux.getUser_megusta();
            if (mg.contains(ur)) {
                mg.remove(ur);
            } else {
                mg.add(ur);
            }

            List<Evento> mg2 = ur.getMegusta();

            if (mg2.contains(aux)) {
                mg2.remove(aux);
            } else {
                mg2.add(aux);
            }

            aux.setUser_megusta(mg);
            ur.setMegusta(mg2);

            em.merge(aux);
            em.merge(ur);

        }
    }

    @Override
    public void crearEvento(Evento e) {
        contId++;
        e.setId(contId);
        em.persist(e);

    }

    @Override
    public Anuncio devolverAnuncio() {
        List<Anuncio>l=em.createQuery("select a from Anuncio a").getResultList();
        return l.get(0);
    }
    
    @Override
    public List<Evento> getEv() {
        return em.createQuery("SELECT u FROM Evento u").getResultList();
    }
    
    @Override
    public int numMeGusta(Long id){
        
        Evento aux = em.find(Evento.class, id);
        int num = -1;
        if(aux != null){
            if(aux.getUser_megusta().isEmpty()){
               num = 0;
           } else{
               num = aux.getUser_megusta().size();
           } 
        }
        return num;
    }

    @Override
    public void crearValoracion(Valoracion v) throws DiarioSurException {
        Evento aux = em.find(Evento.class, v.getEvento().getId());
        if (aux == null) {
            throw new EventoNoEncontradoException();
        }

        contId++;
        v.setId(contId);
        em.persist(v);
    }
    
    public  void crearAnuncio(Anuncio anu){
        contId++;
        anu.setId_anuncio(contId);
        
        em.persist(anu);
        
        
        
    }
  
    public void borrarAnuncio(Anuncio anu)throws DiarioSurException{
        Anuncio aux=em.find(Anuncio.class, anu.getId_anuncio());
        
        if(aux==null){
            throw new DiarioSurException();
        }else{
            em.remove(em.merge(aux));
        }
    }

    @Override
    public List<Anuncio> getAnu() {
        return em.createQuery("select a from Anuncio a").getResultList();
    }
  
  
}
