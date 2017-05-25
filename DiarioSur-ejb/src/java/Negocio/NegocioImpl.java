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
import Entidades.Notificacion;
import Entidades.Periodista;
import Entidades.UsuarioRegistrado;
import Entidades.Valoracion;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<Notificacion> getNotif(UsuarioRegistrado u){
        return em.createQuery("select n from Notificacion n where n.usuarioRegistrado.idUser = '"
                + u.getIdUser() + "'").getResultList();
    }
    
    @Override
    public void eliminarNotificacion(Notificacion n){
        em.remove(em.merge(n));
    }
    
    
    @Override
    public boolean checkDNI(UsuarioRegistrado a){
        boolean existe = false;
        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.dni = '" + a.getDni() + "'").getResultList();
        if (!lu.isEmpty()) {
            existe = true;
        }
        return existe;
    }
    
    
    @Override
    public void crearAdmin(Administrador a){
        contId++;
        a.setIdUser("A"+contId);
        
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + a.getNombre() + "! :D");
        n.setUsuarioRegistrado(a);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        a.setNotificacion(new ArrayList<Notificacion>());
        em.persist(a);
        List<Notificacion> l = a.getNotificacion();
        l.add(n);
        a.setNotificacion(l);

        em.merge(a);
        em.persist(n);
    }
    
    @Override
    public void crearJDR(JefeDeRedactores a){
        contId++;
        a.setIdUser("J"+contId);
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + a.getNombre() + "! :D");
        n.setUsuarioRegistrado(a);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        a.setNotificacion(new ArrayList<Notificacion>());
        em.persist(a);
        List<Notificacion> l = a.getNotificacion();
        l.add(n);
        a.setNotificacion(l);

        em.merge(a);
        em.persist(n);
    }
    
    @Override
    public void crearPeriodista(Periodista a){
        contId++;
        a.setIdUser("P" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + a.getNombre() + "! :D");
        n.setUsuarioRegistrado(a);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        a.setNotificacion(new ArrayList<Notificacion>());
        em.persist(a);
        List<Notificacion> l = a.getNotificacion();
        l.add(n);
        a.setNotificacion(l);

        em.merge(a);
        em.persist(n);
    }
    
    @Override
    public void crearSU(SuperUsuario a){
        contId++;
        a.setIdUser("S" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + a.getNombre() + "! :D");
        n.setUsuarioRegistrado(a);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        a.setNotificacion(new ArrayList<Notificacion>());
        em.persist(a);
        List<Notificacion> l = a.getNotificacion();
        l.add(n);
        a.setNotificacion(l);

        em.merge(a);
        em.persist(n);
    }
    
    @Override
    public void crearUR(UsuarioRegistrado a){
        contId++;
        a.setIdUser("U" + contId);
        
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + a.getNombre() + "! :D");
        n.setUsuarioRegistrado(a);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        a.setNotificacion(new ArrayList<Notificacion>());
        em.persist(a);
        List<Notificacion> l = a.getNotificacion();
        l.add(n);
        a.setNotificacion(l);

        em.merge(a);
        em.persist(n);
    }
    
    
    
    @Override
    public void addPeri(Periodista per){
        em.merge(per);
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
        return em.find(Administrador.class, a.getIdUser());
    }
    
    @Override
    public JefeDeRedactores buscarJDR(JefeDeRedactores a){
        return em.find(JefeDeRedactores.class, a.getIdUser());
    }
    
    @Override
    public Periodista buscarPeriodista(Periodista a){
        return em.find(Periodista.class, a.getIdUser());
    }
    
    @Override
    public SuperUsuario buscarSU(SuperUsuario a){
        return em.find(SuperUsuario.class, a.getIdUser());
    }
    
    @Override
    public UsuarioRegistrado buscarUR(UsuarioRegistrado a){
        return em.find(UsuarioRegistrado.class, a.getIdUser());
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
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte "+u.getNombre()+"! :D");
        n.setUsuarioRegistrado(u);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        u.setNotificacion(new ArrayList<Notificacion>());
        em.persist(u);
        List<Notificacion> l = u.getNotificacion();
        l.add(n);
        u.setNotificacion(l);
        
        em.merge(u);
        em.persist(n);
        

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
        ad.setIdUser("A" + ++contId);
        em.persist(ad);
        
        SuperUsuario su = new SuperUsuario();
        su.setApellidos("supi");
        su.setBorrado(false);
        su.setDni("66666666E");
        su.setEmail("su@uma.es");
        su.setEmpresa("Mc Donalds");
        su.setHistorialEventos("nada");
        su.setNombre("Usi");
        su.setPassword("123");
        su.setPreferencias("si");
        su.setIdUser("S" + ++contId);
        em.persist(su);
        
        

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
        UsuarioRegistrado ur = em.find(UsuarioRegistrado.class, u.getEmail());

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
        return em.find(Anuncio.class, 69L);
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
}
