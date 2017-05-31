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
import Entidades.Reporte;
import Entidades.UsuarioRegistrado;
import Entidades.Valoracion;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private static Long contId = 10L;
    private static Long contBorrado = 10L;

    @PersistenceContext(unitName = "DiarioSurEE-Entidades")
    private EntityManager em;

    @Override
    public List<Notificacion> getNotif(UsuarioRegistrado u) {
        return em.createQuery("select n from Notificacion n where n.usuarioRegistrado.idUser = '"
                + u.getIdUser() + "'").getResultList();
    }

    @Override
    public void eliminarNotificacion(Notificacion n) {
        em.remove(em.merge(n));
    }

    @Override
    public boolean checkDNI(UsuarioRegistrado a) {
        boolean existe = false;
        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.dni = '" + a.getDni() + "'").getResultList();
        if (!lu.isEmpty()) {
            existe = true;
        }
        return existe;
    }

    @Override
    public void eliminarVal(Valoracion seleccionada) {
        Query q = em.createQuery("delete from Reporte r where r.valoracion.idValoracion = " + seleccionada.getId());
        q.executeUpdate();
        em.remove(em.merge(seleccionada));
    }

    @Override
    public void crearAdmin(Administrador a) {
        contId++;
        a.setIdUser("A" + contId);

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
    public void crearJDR(JefeDeRedactores a) {
        contId++;
        a.setIdUser("J" + contId);
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
    public void crearPeriodista(Periodista a) {
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
    public void crearSU(SuperUsuario a) {
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
    public void crearUR(UsuarioRegistrado a) {
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
    public void editaPeri(Periodista per) {
        em.merge(per);
    }

    @Override
    public void editaUR(UsuarioRegistrado aux) {
        em.merge(aux);
    }

    @Override
    public void editaSuperu(SuperUsuario sup) {
        em.merge(sup);
    }

    @Override
    public void editaJdr(JefeDeRedactores jdre) {
        em.merge(jdre);
    }

    @Override
    public void editaAdmin(Administrador adm) {
        em.merge(adm);
    }

    @Override
    public void addPeri(Periodista per) {
        contId++;
        per.setIdUser("P" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Su rol de usuario ha cambiado, ahora tiene permisos de Periodista");
        n.setUsuarioRegistrado(per);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        per.setNotificacion(new ArrayList<>());
        em.persist(per);
        List<Notificacion> l = per.getNotificacion();
        l.add(n);
        per.setNotificacion(l);
        em.merge(per);
        em.persist(n);
    }

    @Override
    public void addAdmin(Administrador adm) {
        contId++;
        adm.setIdUser("A" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Su rol de usuario ha cambiado, ahora tiene permisos de Administrador");
        n.setUsuarioRegistrado(adm);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        adm.setNotificacion(new ArrayList<>());
        em.persist(adm);
        List<Notificacion> l = adm.getNotificacion();
        l.add(n);
        adm.setNotificacion(l);
        em.merge(adm);
        em.persist(n);
    }

    @Override
    public void addUR(UsuarioRegistrado ur) {
        contId++;
        ur.setIdUser("U" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Su rol de usuario ha cambiado, ahora tiene permisos de Usuario Registrado");
        n.setUsuarioRegistrado(ur);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        ur.setNotificacion(new ArrayList<>());
        em.persist(ur);
        List<Notificacion> l = ur.getNotificacion();
        l.add(n);
        ur.setNotificacion(l);
        em.merge(ur);
        em.persist(n);
    }

    @Override
    public void addJdr(JefeDeRedactores jdre) {
        contId++;
        jdre.setIdUser("J" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Su rol de usuario ha cambiado, ahora tiene permisos de Jefe de Redactores");
        n.setUsuarioRegistrado(jdre);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        jdre.setNotificacion(new ArrayList<>());
        em.persist(jdre);
        List<Notificacion> l = jdre.getNotificacion();
        l.add(n);
        jdre.setNotificacion(l);
        em.merge(jdre);
        em.persist(n);
    }

    @Override
    public void addSuperu(SuperUsuario sup) {
        contId++;
        sup.setIdUser("S" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Su rol de usuario ha cambiado, ahora tiene permisos de Super Usuario");
        n.setUsuarioRegistrado(sup);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        sup.setNotificacion(new ArrayList<>());
        em.persist(sup);
        List<Notificacion> l = sup.getNotificacion();
        l.add(n);
        sup.setNotificacion(l);
        em.merge(sup);
        em.persist(n);
    }

    @Override
    public void eliminarUR(UsuarioRegistrado a) {
        a.setBorrado(true);
        contBorrado++;
        a.setEmail(a.getEmail() + "Borrado" + contBorrado);
        a.setDni(a.getDni() + "Borrado" + contBorrado);
        em.merge(a);
        //em.remove(em.merge(a));
    }

    @Override
    public void eliminarSU(SuperUsuario a) {
        a.setBorrado(true);
        contBorrado++;
        a.setEmail(a.getEmail() + "Borrado" + contBorrado);
        a.setDni(a.getDni() + "Borrado" + contBorrado);
        em.merge(a);
        //em.remove(em.merge(a));
    }

    @Override
    public void eliminarPeriodista(Periodista a) {
        a.setBorrado(true);
        contBorrado++;
        a.setEmail(a.getEmail() + "Borrado" + contBorrado);
        a.setDni(a.getDni() + "Borrado" + contBorrado);
        em.merge(a);
        //em.remove(em.merge(a));
    }

    @Override
    public void eliminarJDR(JefeDeRedactores a) {
        a.setBorrado(true);
        contBorrado++;
        a.setEmail(a.getEmail() + "Borrado" + contBorrado);
        a.setDni(a.getDni() + "Borrado" + contBorrado);
        em.merge(a);
        //em.remove(em.merge(a));
    }

    @Override
    public void eliminarAdmin(Administrador a) {
        a.setBorrado(true);
        contBorrado++;
        a.setEmail(a.getEmail() + "Borrado" + contBorrado);
        a.setDni(a.getDni() + "Borrado" + contBorrado);
        em.merge(a);
        //em.remove(em.merge(a));
    }

    @Override
    public List<UsuarioRegistrado> getUR() {
//        return em.createQuery("SELECT u FROM UsuarioRegistrado u WHERE u.idUser LIKE 'U%'").getResultList();
        return em.createQuery("SELECT u FROM UsuarioRegistrado u WHERE u.idUser LIKE 'U%' and u.borrado = false").getResultList();
    }

    @Override
    public List<SuperUsuario> getSuperu() {
//        return em.createQuery("SELECT u FROM SuperUsuario u WHERE u.idUser LIKE 'S%'").getResultList();
        return em.createQuery("SELECT u FROM SuperUsuario u WHERE u.idUser LIKE 'S%' and u.borrado = false").getResultList();
    }

    @Override
    public List<Periodista> getPeri() {
//        return em.createQuery("SELECT u FROM Periodista u WHERE u.idUser LIKE 'P%'").getResultList();
        return em.createQuery("SELECT u FROM Periodista u WHERE u.idUser LIKE 'P%' and u.borrado = false").getResultList();
    }

    @Override
    public List<JefeDeRedactores> getJdr() {
//        return em.createQuery("SELECT u FROM JefeDeRedactores u WHERE u.idUser LIKE 'J%'").getResultList();
        return em.createQuery("SELECT u FROM JefeDeRedactores u WHERE u.idUser LIKE 'J%' and u.borrado = false").getResultList();
    }

    @Override
    public List<Administrador> getAdmin() {
//        return em.createQuery("SELECT u FROM Administrador u WHERE u.idUser LIKE 'A%'").getResultList();
        return em.createQuery("SELECT u FROM Administrador u WHERE u.idUser LIKE 'A%' and u.borrado = false").getResultList();
    }

    @Override
    public Administrador buscarAdmin(Administrador a) {
        return em.find(Administrador.class, a.getIdUser());
    }

    @Override
    public JefeDeRedactores buscarJDR(JefeDeRedactores a) {
        return em.find(JefeDeRedactores.class, a.getIdUser());
    }

    @Override
    public Periodista buscarPeriodista(Periodista a) {
        return em.find(Periodista.class, a.getIdUser());
    }

    @Override
    public SuperUsuario buscarSU(SuperUsuario a) {
        return em.find(SuperUsuario.class, a.getIdUser());
    }

    @Override
    public UsuarioRegistrado buscarUR(UsuarioRegistrado a) {
        return em.find(UsuarioRegistrado.class, a.getIdUser());
    }

    @Override
    public UsuarioRegistrado buscarURmail(String email) {
        List<UsuarioRegistrado> l = em.createQuery("SELECT u FROM UsuarioRegistrado u WHERE u.email = '" + email + "'").getResultList();
        return l.get(0);
    }

    @Override
    public void rellenarBd() {

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
        ad.setPreferencias("");
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
        ano.setMultimedia(new byte[0]);
        ano.setPrioridad("3");
        ano.setTags("vale");
        List<Evento> l = new ArrayList<>();
        ano.setEvento(l);
        ano.setAdministrador(em.find(Administrador.class, ad.getIdUser()));
        em.persist(ano);

        Evento e = new Evento();
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
        e.setImagen(new byte[0]);

        em.persist(e);
        l.add(e);
        ano.setEvento(l);
        editarAnuncio(ano);

    }

    @Override
    public void registrarUsuario(UsuarioRegistrado u) throws DiarioSurException {
        //UsuarioRegistrado user = em.find(UsuarioRegistrado.class, u.getEmail());

        contId++;
        u.setIdUser("U" + contId);
        Notificacion n = new Notificacion();
        n.setTexto("Bienvenido a la agenda de eventos El Sur, gracias por registrarte " + u.getNombre() + "! :D");
        n.setUsuarioRegistrado(u);
        n.setFecha(new Date());
        contId++;
        n.setId(contId);
        u.setNotificacion(new ArrayList<>());
        em.persist(u);
        List<Notificacion> l = u.getNotificacion();
        l.add(n);
        u.setNotificacion(l);

        em.merge(u);
        em.persist(n);

    }

    @Override
    public void compruebaLogin(UsuarioRegistrado u) throws DiarioSurException {
        List<UsuarioRegistrado> lu = em.createQuery("select u from UsuarioRegistrado u where u.email = '" + u.getEmail() + "'").getResultList();

        if (lu.isEmpty()) {
            throw new UsuarioNoRegistradoException();
        } else {
            UsuarioRegistrado user = lu.get(0);
            if (user.isBorrado()) {
                throw new ContraseniaInvalidaException();
            }
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
        em.refresh(user);
        return user;
    }

    @Override
    public boolean existeUsuario(UsuarioRegistrado u) throws DiarioSurException {
        boolean existe = false;
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
    public void eliminarEvento(Evento e) throws DiarioSurException {
        Query q = em.createQuery("delete from Reporte r where r.evento.id_evento = " + e.getId());
        q.executeUpdate();
        for (Valoracion v : getValoraciones(e)) {
            eliminarVal(v);
        }
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
        List<Integer> lp = Arrays.asList(3, 3, 3, 2, 2, 1);
        Random r = new Random();
        int pri = lp.get(r.nextInt(lp.size()));
        List<Anuncio> l = em.createQuery("select a from Anuncio a where a.prioridad >= '" + pri + "'").getResultList();
        if (!l.isEmpty()) {
            return l.get(r.nextInt(l.size()));
        } else {
            l = em.createQuery("select a from Anuncio a").getResultList();
            return l.get(r.nextInt(l.size()));
        }
    }

    @Override
    public void editarAnuncio(Anuncio anuncio) {
        em.merge(anuncio);
    }

    @Override
    public List<Evento> getEv() {
        //return em.createQuery("SELECT u FROM Evento u").getResultList();
        List<Evento>l=new ArrayList<>();
        Query q=em.createQuery("select e from Evento e where e.verificado=true");
        l=q.getResultList();
        return l;
        
    }

    @Override
    public int numMeGusta(Long id) {

        Evento aux = em.find(Evento.class, id);
        int num = -1;
        if (aux != null) {
            if (aux.getUser_megusta().isEmpty()) {
                num = 0;
            } else {
                num = aux.getUser_megusta().size();
            }
        }
        return num;
    }

    @Override
    public void crearAnuncio(Anuncio anu) {
        contId++;
        anu.setId_anuncio(contId);
        em.persist(anu);

    }

    @Override
    public void borrarAnuncio(Anuncio anu) throws DiarioSurException {
        Anuncio aux = em.find(Anuncio.class, anu.getId_anuncio());

        if (aux == null) {
            throw new DiarioSurException();
        } else if (em.createQuery("SELECT u FROM Anuncio u").getResultList().size() > 1) {
            for (Evento e : aux.getEvento()) {
                Anuncio a = cambiaAnuncio(anu);
                List<Evento> l = a.getEvento();
                l.add(e);
                e.setAnuncio(a);
                a.setEvento(l);
                editarAnuncio(a);
                em.merge(e);
            }
            em.remove(em.merge(aux));
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No se puede eliminar el anuncio " + anu.getId_anuncio() + " ya que debe haber, al menos, un anuncio en la bd",
                    "No se puede eliminar el anuncio " + anu.getId_anuncio() + " ya que debe haber, al menos, un anuncio en la bd"));
        }

    }

    public Anuncio cambiaAnuncio(Anuncio a) {
        Random r = new Random();
        List<Integer> lp = Arrays.asList(3, 3, 3, 2, 2, 1);
        int pri = lp.get(r.nextInt(lp.size()));
        List<Anuncio> l = em.createQuery("select a from Anuncio a where a.prioridad >= '" + pri + "'").getResultList();
        if (l.contains(a) && l.size() == 1 || l.isEmpty()) {
            l = em.createQuery("select a from Anuncio a").getResultList();
        }
        Anuncio res = l.get(r.nextInt(l.size()));
        while (Objects.equals(res.getId_anuncio(), a.getId_anuncio())) {
            res = l.get(r.nextInt(l.size()));
        }
        return res;
    }

    @Override
    public List<Anuncio> getAnu() {
        return em.createQuery("select a from Anuncio a").getResultList();
    }

    @Override
    public void enviarRepVal(Reporte r) {
        contId++;
        r.setId_reporte(contId);
        em.persist(r);
    }

    @Override
    public void enviarRepEv(Reporte r) {
        contId++;
        r.setId_reporte(contId);
        em.persist(r);
    }

    @Override
    public List<Reporte> getReportesVal() {
        List<Reporte> lista = new ArrayList<>();
        lista = em.createQuery("select r from Reporte r where r.valoracion is not null").getResultList();
        return lista;
    }

    @Override
    public List<Reporte> getReportesEv() {
        List<Reporte> lista = new ArrayList<>();
        lista = em.createQuery("select r from Reporte r where r.valoracion is null").getResultList();
        return lista;
    }

    @Override
    public void eliminarReporteEv(Reporte r) throws DiarioSurException {
        Reporte aux = em.find(Reporte.class, r.getId_reporte());
        if (aux == null) {
            throw new DiarioSurException();
        } else {
            em.remove(em.merge(aux));
        }
    }

    @Override
    public void eliminarReporteVal(Reporte r) throws DiarioSurException {
        Reporte aux = em.find(Reporte.class, r.getId_reporte());
        if (aux == null) {
            throw new DiarioSurException();
        } else {
            em.remove(em.merge(aux));
        }
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

    @Override
    public List<Valoracion> getValoraciones(Evento e) throws DiarioSurException {

        Query q;
        //Evento aux= em.find(Evento.class, e.getId_evento());
        q = em.createQuery("select v from Valoracion v where v.evento=:evento");
        q.setParameter("evento", e);
        return q.getResultList();

        //aux2 = em.createQuery("SELECT u FROM VALORACION u where EVENTO_ID_EVENTO = "+e.getId_evento()+"").getResultList();
        /*if(aux==null){
            throw new EventoNoEncontradoException();
        }
        else{
            aux2= aux.getValoraciones();
            System.out.println(aux2.isEmpty());
        }*/
    }

    @Override
    public void tipoVisitado(UsuarioRegistrado usuarioLogeado, Evento evento) throws DiarioSurException {
        UsuarioRegistrado user = em.find(UsuarioRegistrado.class, usuarioLogeado.getIdUser());
        if (user != null) {

            String prefer = user.getPreferencias();
            prefer += "&" + evento.getTipo().substring(0, 1);
            user.setPreferencias(prefer);
            em.merge(user);
        }

    }

    @Override
    public String getTiposVisitadosDe(UsuarioRegistrado usuarioLogeado) {
        if (usuarioLogeado != null) {

            UsuarioRegistrado user = em.find(UsuarioRegistrado.class, usuarioLogeado.getIdUser());

            return user.getPreferencias();
        } else {
            return "";
        }
    }

    @Override
    public String devolverPref(UsuarioRegistrado usuarioLogeado) {
        UsuarioRegistrado user = em.find(UsuarioRegistrado.class, usuarioLogeado.getIdUser());
        return user.getPreferencias();
    }

    @Override
    public boolean tieneImagen(Evento e) {
        Evento aux=em.find(Evento.class, e.getId());
        if(aux.getImagen()==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean tieneImagenA(Anuncio a) {
        Anuncio aux=em.find(Anuncio.class, a.getId_anuncio());
        if(aux.getMultimedia()==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public List<Evento> getAllEv() {
        return em.createQuery("SELECT u FROM Evento u").getResultList();
    }

    @Override
    public List<Evento> getDosRecomendados(Evento e, UsuarioRegistrado u) {
        List<Evento>l=new ArrayList<>();
        Query q=em.createQuery("select e from Evento e where e.tipo=tipoE");
        q.setParameter("tipoE", e.getTipo());
        
        Evento e1=(Evento)q.getResultList().get(0);
        
        return null;
    }

    @Override
    public List<Evento> getEvNV() {
        List<Evento>l=new ArrayList<>();
        Query q=em.createQuery("select e from Evento e where e.verificado=false");
        l=q.getResultList();
        return l;
        
    }
}
