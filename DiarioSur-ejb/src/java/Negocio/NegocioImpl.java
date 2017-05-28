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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
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

    private static Long contId = 10L;

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
    public void addPeri(Periodista per) {
        em.merge(per);
    }

    @Override
    public void addAdmin(Administrador adm) {
        em.merge(adm);
    }

    @Override
    public void addUR(UsuarioRegistrado ur) {
        em.merge(ur);
    }

    @Override
    public void addJdr(JefeDeRedactores jdre) {
        em.merge(jdre);
    }

    @Override
    public void addSuperu(SuperUsuario sup) {
        em.merge(sup);
    }

    @Override
    public void eliminarUR(UsuarioRegistrado a) {
        em.remove(em.merge(a));
    }

    @Override
    public void eliminarSU(SuperUsuario a) {
        em.remove(em.merge(a));
    }

    @Override
    public void eliminarPeriodista(Periodista a) {
        em.remove(em.merge(a));
    }

    @Override
    public void eliminarJDR(JefeDeRedactores a) {
        em.remove(em.merge(a));
    }

    @Override
    public void eliminarAdmin(Administrador a) {
        em.remove(em.merge(a));
    }

    @Override
    public List<UsuarioRegistrado> getUR() {
        return em.createQuery("SELECT u FROM UsuarioRegistrado u WHERE u.idUser LIKE 'U%'").getResultList();
    }

    @Override
    public List<SuperUsuario> getSuperu() {
        return em.createQuery("SELECT u FROM SuperUsuario u WHERE u.idUser LIKE 'S%'").getResultList();
    }

    @Override
    public List<Periodista> getPeri() {
        return em.createQuery("SELECT u FROM Periodista u WHERE u.idUser LIKE 'P%'").getResultList();
    }

    @Override
    public List<JefeDeRedactores> getJdr() {
        return em.createQuery("SELECT u FROM JefeDeRedactores u WHERE u.idUser LIKE 'J%'").getResultList();
    }

    @Override
    public List<Administrador> getAdmin() {
        return em.createQuery("SELECT u FROM Administrador u WHERE u.idUser LIKE 'A%'").getResultList();
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
        ano.setPrioridad("mucha");
        ano.setTags("vale");
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

        Evento e2 = new Evento();
        e2.setAnuncio(ano);
        e2.setCompra("Ninguna");
        e2.setDescripcion("Evento de prueba");
        e2.setFecha(new Date());
        e2.setGeolocalizacion("Montilla");
        e2.setId(3L);
        e2.setNombre("Evento inicial");
        e2.setPrecio(0F);
        e2.setTags("Ninguno");
        e2.setTipo("exposicion");
        e2.setVerificado(false);
        e2.setUsuarioRegistrado(ad);

        Evento e3 = new Evento();
        e3.setAnuncio(ano);
        e3.setCompra("Ninguna");
        e3.setDescripcion("Evento de prueba");
        e3.setFecha(new Date());
        e3.setGeolocalizacion("Montilla");
        e3.setId(3L);
        e3.setNombre("Evento inicial");
        e3.setPrecio(0F);
        e3.setTags("Ninguno");
        e3.setTipo("teatral");
        e3.setVerificado(false);
        e3.setUsuarioRegistrado(ad);

        Evento e4 = new Evento();
        e4.setAnuncio(ano);
        e4.setCompra("Ninguna");
        e4.setDescripcion("Evento de prueba");
        e4.setFecha(new Date());
        e4.setGeolocalizacion("Montilla");
        e4.setId(3L);
        e4.setNombre("Evento inicial");
        e4.setPrecio(0F);
        e4.setTags("Ninguno");
        e4.setTipo("otro");
        e4.setVerificado(false);
        e4.setUsuarioRegistrado(ad);

        Evento e5 = new Evento();
        e5.setAnuncio(ano);
        e5.setCompra("Ninguna");
        e5.setDescripcion("Evento de prueba");
        e5.setFecha(new Date());
        e5.setGeolocalizacion("Montilla");
        e5.setId(3L);
        e5.setNombre("Evento inicial");
        e5.setPrecio(0F);
        e5.setTags("Ninguno");
        e5.setTipo("musical");
        e5.setVerificado(false);
        e5.setUsuarioRegistrado(ad);

        Evento e6 = new Evento();
        e6.setAnuncio(ano);
        e6.setCompra("Ninguna");
        e6.setDescripcion("Evento de prueba");
        e6.setFecha(new Date());
        e6.setGeolocalizacion("Montilla");
        e6.setId(3L);
        e6.setNombre("Evento inicial");
        e6.setPrecio(0F);
        e6.setTags("Ninguno");
        e6.setTipo("exposicion");
        e6.setVerificado(false);
        e6.setUsuarioRegistrado(ad);

        Evento e7 = new Evento();
        e7.setAnuncio(ano);
        e7.setCompra("Ninguna");
        e7.setDescripcion("Evento de prueba");
        e7.setFecha(new Date());
        e7.setGeolocalizacion("Montilla");
        e7.setId(3L);
        e7.setNombre("Evento inicial");
        e7.setPrecio(0F);
        e7.setTags("Ninguno");
        e7.setTipo("deportivo");
        e7.setVerificado(false);
        e7.setUsuarioRegistrado(ad);

        Evento e8 = new Evento();
        e8.setAnuncio(ano);
        e8.setCompra("Ninguna");
        e8.setDescripcion("Evento de prueba");
        e8.setFecha(new Date());
        e8.setGeolocalizacion("Montilla");
        e8.setId(3L);
        e8.setNombre("Evento inicial");
        e8.setPrecio(0F);
        e8.setTags("Ninguno");
        e8.setTipo("musical");
        e8.setVerificado(false);
        e8.setUsuarioRegistrado(ad);

        crearEvento(e);
        crearEvento(e2);
        crearEvento(e3);
        crearEvento(e4);
        crearEvento(e5);
        crearEvento(e6);
        crearEvento(e7);
        crearEvento(e8);;

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
        List<Anuncio> l = em.createQuery("select a from Anuncio a").getResultList();
        return l.get(0);
    }

    @Override
    public List<Evento> getEv() {
        return em.createQuery("SELECT u FROM Evento u").getResultList();
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

    public void crearAnuncio(Anuncio anu) {
        contId++;
        anu.setId_anuncio(contId);

        em.persist(anu);

    }

    public void borrarAnuncio(Anuncio anu) throws DiarioSurException {
        Anuncio aux = em.find(Anuncio.class, anu.getId_anuncio());

        if (aux == null) {
            throw new DiarioSurException();
        } else {
            em.remove(em.merge(aux));
        }
    }

    @Override
    public List<Anuncio> getAnu() {
        return em.createQuery("select a from Anuncio a").getResultList();
    }

    @Override
    public void enviarRepVal(Reporte r) {
        contId++;
        r.setId_reporte(contId);

        //System.out.println(r.getId()+" "+r.getFecha()+" "+r.getTexto()+" "+r.getTipo()+" "+r.getEvento()+" "+r.getUsuarioRegistrado()+" "+r.getValoracion());
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
}
