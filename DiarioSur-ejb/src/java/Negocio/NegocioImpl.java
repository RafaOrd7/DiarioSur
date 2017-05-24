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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        ad.setIdUser("A" + contId++);
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
