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
import Entidades.JefeDeRedactores;
import Entidades.Periodista;
import Entidades.UsuarioRegistrado;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Garri
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static Long contId = 0L;

    @PersistenceContext(unitName = "DiarioSurEE-Entidades")
    private EntityManager em;
    
    
    
    @Override
    public void addPeri(Periodista per){
        Periodista p = em.find(Periodista.class, per.getEmail());
        em.detach(p);
        p.setIdUser(per.getIdUser());
        p.setNombre(per.getNombre());
        p.setApellidos(per.getApellidos());
        p.setDni(per.getDni());
        p.setEmail(per.getEmail());
        p.setPassword(per.getPassword());
        p.setPreferencias(per.getPreferencias());
        p.setMultimedia(per.getMultimedia());
        p.setHistorialEventos(per.getHistorialEventos());
        em.persist(p);
    }
    
    
    @Override
    public void addAdmin(Administrador adm){
        em.merge(adm);
    }
    
    @Override
    public void addUR(UsuarioRegistrado ur){
        em.merge(ur);
    }
    
    @Override
    public void addJdr(JefeDeRedactores jdre){
        em.merge(jdre);
    }
    
    @Override
    public void addSuperu(SuperUsuario sup){
       // no se si es necesario el detach
       //em.detach(em.find(SuperUsuario.class, sup.getEmail()));
        em.merge(sup);
    }
    
    @Override
    public void eliminarUR(UsuarioRegistrado a) {
        em.remove(em.merge(a));
    }
    
    @Override
    public void eliminarSU(SuperUsuario a){
        em.remove(em.merge(a));
    }
    
    @Override
    public void eliminarPeriodista(Periodista a){
        em.remove(em.merge(a));
    }
    
    @Override
    public void eliminarJDR(JefeDeRedactores a){
        em.remove(em.merge(a));
    }
    
    @Override
    public void eliminarAdmin(Administrador a){
        em.remove(em.merge(a));
    }
    
    @Override
    public List<UsuarioRegistrado> getUR(){
        return em.createQuery("SELECT u FROM UsuarioRegistrado u WHERE u.idUser LIKE 'U%'").getResultList();

    }
    
    @Override
    public List<SuperUsuario> getSuperu(){
        return em.createQuery("SELECT u FROM SuperUsuario u WHERE u.idUser LIKE 'S%'").getResultList();
    }
    
    @Override
    public List<Periodista> getPeri(){
        return em.createQuery("SELECT u FROM Periodista u WHERE u.idUser LIKE 'P%'").getResultList();
    }
    
    @Override
    public List<JefeDeRedactores> getJdr(){
        return em.createQuery("SELECT u FROM JefeDeRedactores u WHERE u.idUser LIKE 'J%'").getResultList();
    }
    
    @Override
    public List<Administrador> getAdmin(){
        return em.createQuery("SELECT u FROM Administrador u WHERE u.idUser LIKE 'A%'").getResultList();
    }
    
    @Override
    public Administrador buscarAdmin(Administrador a){
        return em.find(Administrador.class, a.getEmail());
    }
    
    @Override
    public JefeDeRedactores buscarJDR(JefeDeRedactores a){
        return em.find(JefeDeRedactores.class, a.getEmail());
    }
    
    @Override
    public Periodista buscarPeriodista(Periodista a){
        return em.find(Periodista.class, a.getEmail());
    }
    
    @Override
    public SuperUsuario buscarSU(SuperUsuario a){
        return em.find(SuperUsuario.class, a.getEmail());
    }
    
    @Override
    public UsuarioRegistrado buscarUR(UsuarioRegistrado a){
        return em.find(UsuarioRegistrado.class, a.getEmail());
    }
    
    
    @Override
    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException {
        UsuarioRegistrado user = em.find(UsuarioRegistrado.class, u.getEmail());
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }
        contId++;
        u.setIdUser("U" + contId);
        em.persist(u);
        
        
        /* Esto es un administrador para probar */
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
        ad.setIdUser("A"+ contId++);
        em.persist(ad);
        
        Anuncio ano = new Anuncio();
        ano.setDimensiones("si");
        ano.setEmpresa("Prueba SL");
        ano.setEvento(new ArrayList<Evento>());
        ano.setFechaExpiracion(new Date());
        ano.setFechaPublicacion(new Date());
        ano.setId_anuncio(69L);
        ano.setPrioridad("mucha");
        ano.setTags("vale");
        ano.setAdministrador(ad);
        em.persist(ano);
        
    }

    @Override
    public void compruebaLogin(UsuarioRegistrado u) throws DiarioSurException {

        UsuarioRegistrado aux = em.find(UsuarioRegistrado.class, u.getEmail());
        if (aux == null) {
            throw new UsuarioNoRegistradoException();
        } else {
            if (!aux.getPassword().equals(u.getPassword())) {
                throw new ContraseniaInvalidaException();
            }
        }
    }

    @Override
    public UsuarioRegistrado refrescarUsuario(UsuarioRegistrado u) throws DiarioSurException {
        compruebaLogin(u);
        
        UsuarioRegistrado aux=em.find(UsuarioRegistrado.class,u.getEmail());
        em.refresh(aux);
        return aux;
        
    }

    @Override
    public boolean existeUsuario(UsuarioRegistrado u) throws DiarioSurException {
        boolean existe=false;
        UsuarioRegistrado aux=em.find(UsuarioRegistrado.class,u.getEmail());
        if(aux!=null){
            existe=true;
        }
        
        return existe;
    }
    
    @Override
    public void editarEvento(Evento e){
        em.merge(e);
    }
    
    @Override
    public void eliminarEvento(Evento e){
        em.remove(em.merge(e));
    }
    
    @Override
    public void meGusta(Evento e, UsuarioRegistrado u) throws DiarioSurException{
        Evento aux = em.find(Evento.class, e.getId_evento());
        
        if (aux == null) {
            throw new EventoNoEncontradoException();
        } else {
            List<UsuarioRegistrado> mg = aux.getUser_megusta();
            
            if(mg.contains(u)) mg.remove(u);   
            else mg.add(u);
            
            aux.setUser_megusta(mg);
            em.merge(aux);
        }
    }
    
    @Override
    public void crearEvento(Evento e) {
        contId++;
        e.setId(contId);
        em.persist(e);
        
        
    
    }
    
    @Override
    public Anuncio devolverAnuncio(){
        return em.find(Anuncio.class,69L);
    }

    

}
