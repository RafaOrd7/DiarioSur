/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Administrador;
import Entidades.JefeDeRedactores;
import Entidades.Notificacion;
import Entidades.Periodista;
import Entidades.SuperUsuario;
import Entidades.UsuarioRegistrado;
import Negocio.ContraseniaInvalidaException;
import Negocio.DiarioSurException;
import Negocio.Negocio;
import Negocio.UsuarioNoRegistradoException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Garri
 */
@Named(value = "ctrlUsuarios")
@RequestScoped
public class ctrlUsuarios implements Serializable {

    private UsuarioRegistrado usuario = new UsuarioRegistrado();
    private SuperUsuario su = new SuperUsuario();
    private Periodista p = new Periodista();
    private JefeDeRedactores jdr = new JefeDeRedactores();
    private Administrador a = new Administrador();
    private static boolean propio = false; // flag que indica si la edición de usuario es de sí mismo(confUsuario)
    private static String rol = "";
    private static int cont = 0;
    @EJB
    private Negocio negocio;

    @Inject
    private ctrlAutorizacion cta;

    public ctrlUsuarios() {
        this.p = new Periodista("P1333", "Dista", "Perio", "12312312K", "peri@uma.es", "asdf", "McDonalds", "Barrendero", "696969696");
    }

    public int rolAdm() {
        if (rol.equals("Administrador")) {
            return 2;
        }
        return 1;
    }

    public String nuevoUsuario() throws DiarioSurException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        if (cont == 0) {
            negocio.rellenarBd();
            cont++;
        }
        
        String pag = null;
        if (negocio.existeUsuario(usuario)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El email " + usuario.getEmail() + " está en uso por otro usuario.",
                    "El email " + usuario.getEmail() + " está en uso por otro usuario."));
        } else if (negocio.checkDNI(usuario)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ya existe un usuario con DNI " + usuario.getDni(),
                    "Ya existe un usuario con DNI " + usuario.getDni()));
        } else {
            usuario.setHistorialEventos("");
            usuario.setBorrado(false);
            usuario.setMegusta(new ArrayList<>());
            negocio.registrarUsuario(usuario);
            usuario.setPreferencias("");

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario " + usuario.getEmail() + " registrado correctamente.",
                    "Usuario " + usuario.getEmail() + " registrado correctamente."));
            pag = "index.xhtml";
        }
        return pag;
    }

    //POR HACER
    public String nuevoUsuarioGestion() throws DiarioSurException {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String pag = null;
        if (negocio.existeUsuario((UsuarioRegistrado) a)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "El email " + a.getEmail() + " está en uso por otro usuario.",
                    "El email " + a.getEmail() + " está en uso por otro usuario."));
        } else if (negocio.checkDNI(a)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ya existe un usuario con DNI " + a.getDni(),
                    "Ya existe un usuario con DNI " + a.getDni()));
        } else {
            /*dependiendo del tipo de usuario*/
            switch (rol.charAt(0)) {
                case 'A':
                    negocio.crearAdmin(a);
                    break;
                case 'J':
                    jdr.setApellidos(a.getApellidos());
                    jdr.setBorrado(false);
                    jdr.setDni(a.getDni());
                    jdr.setEmail(a.getEmail());
                    jdr.setHistorialEventos("");
                    jdr.setNombre(a.getNombre());
                    jdr.setPassword(a.getPassword());
                    jdr.setPreferencias("");
                    jdr.setEmpresa(a.getEmpresa());
                    jdr.setCargo(a.getCargo());
                    jdr.setTelefono(a.getTelefono());
                    negocio.crearJDR(jdr);
                    break;
                case 'P':
                    p.setApellidos(a.getApellidos());
                    p.setBorrado(false);
                    p.setDni(a.getDni());
                    p.setEmail(a.getEmail());
                    p.setHistorialEventos("");
                    p.setNombre(a.getNombre());
                    p.setPassword(a.getPassword());
                    p.setPreferencias("");
                    p.setEmpresa(a.getEmpresa());
                    p.setCargo(a.getCargo());
                    p.setTelefono(a.getTelefono());
                    negocio.crearPeriodista(p);
                    break;
                case 'S':
                    su.setApellidos(a.getApellidos());
                    su.setBorrado(false);
                    su.setDni(a.getDni());
                    su.setEmail(a.getEmail());
                    su.setHistorialEventos("");
                    su.setNombre(a.getNombre());
                    su.setPassword(a.getPassword());
                    su.setPreferencias("");
                    su.setEmpresa(a.getEmpresa());
                    negocio.crearSU(su);
                    break;
                case 'U':
                    usuario.setApellidos(a.getApellidos());
                    usuario.setBorrado(false);
                    usuario.setDni(a.getDni());
                    usuario.setEmail(a.getEmail());
                    usuario.setHistorialEventos("");
                    usuario.setNombre(a.getNombre());
                    usuario.setPassword(a.getPassword());
                    usuario.setPreferencias("");
                    negocio.crearUR(usuario);
                    break;
            }
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Usuario " + a.getEmail() + " registrado correctamente.",
                    "Usuario " + a.getEmail() + " registrado correctamente."));
            pag = "gestionUsuario.xhtml";
        }
        return pag;
    }

    public String logIn() throws DiarioSurException {

        try {
            negocio.compruebaLogin(usuario);

            cta.setUsuarioLogeado(negocio.refrescarUsuario(usuario));

            return "index.xhtml";

        } catch (UsuarioNoRegistradoException | ContraseniaInvalidaException e) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación, "
                    + "pareja email - contraseña incorrectos.", "Error de autenticación, "
                    + "pareja email - contraseña incorrectos."));
        }
        return null;
    }

    public String validarEmail() {

        String page = null;

        if (!usuario.getEmail().equals("")) {

            String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            // compila la expresion regular
            Pattern pattern = Pattern.compile(PATTERN_EMAIL);

            // compara la expresion regular con el email introducido por el usuario
            Matcher matcher = pattern.matcher(usuario.getEmail());

            if (matcher.matches()) {
                page = "index.xhtml";
            } else {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, email no válido.",
                        "Error, email no válido."));
            }
        }
        return page;
    }

    public String sendEmail() throws DiarioSurException {
        String pag = validarEmail();
        if (pag == null) {
            return pag;
        } else {
            if (negocio.existeUsuario(usuario)) {
                UsuarioRegistrado u = negocio.buscarURmail(usuario.getEmail());

                final String username = "sinfuma17@gmail.com";
                final String password = "topillosmike";
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

                try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("sinfuma17@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(u.getEmail()));
                    message.setSubject("Recuperación de contraseña Agenda Diario El Sur");
                    message.setText("Ha solicitado la recuperación de contraseña, su contraseña actual"
                            + " es: " + u.getPassword());

                    Transport.send(message);

                    FacesContext ctx = FacesContext.getCurrentInstance();
                    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje enviado, compruebe su email.",
                            "Mensaje enviado, compruebe su email."));

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            } else {
                pag = null;
                FacesContext ctx = FacesContext.getCurrentInstance();
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, email no registrado.",
                        "Error, email no registrado."));
            }
        }

        return pag;
    }

    public List<Notificacion> getListaNotif() throws DiarioSurException {
        return negocio.getNotif(cta.getUsuarioLogeado());
    }

    public String eliminarNotif(Notificacion n) {
        negocio.eliminarNotificacion(n);
        return null;
    }

    public String eliminarUR(UsuarioRegistrado u) {
        negocio.eliminarUR(u);
        return null;
    }

    public String eliminarSU(SuperUsuario u) {
        negocio.eliminarSU(u);
        return null;
    }

    public String eliminarP(Periodista u) {
        negocio.eliminarPeriodista(u);
        return null;
    }

    public String eliminarJDR(JefeDeRedactores u) {
        negocio.eliminarJDR(u);
        return null;
    }

    public String eliminarA(Administrador u) {
        negocio.eliminarAdmin(u);
        return null;
    }

    public List<UsuarioRegistrado> getListaUR() {
        return negocio.getUR();
    }

    public List<SuperUsuario> getListaSU() {
        return negocio.getSuperu();
    }

    public List<JefeDeRedactores> getListaJDR() {
        return negocio.getJdr();
    }

    public List<Periodista> getListaP() {
        return negocio.getPeri();
    }

    public List<Administrador> getListaA() {
        return negocio.getAdmin();
    }

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }

    public Administrador getA() {
        return a;
    }

    public String getRol() {
        return rol;
    }

    public void setSu(SuperUsuario su) {
        this.su = su;
    }

    public void setP(Periodista p) {
        this.p = p;
    }

    public void setJdr(JefeDeRedactores jdr) {
        this.jdr = jdr;
    }

    public void setA(Administrador a) {
        this.a = a;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public SuperUsuario getSu() {
        return su;
    }

    public Periodista getP() {
        return p;
    }

    public JefeDeRedactores getJdr() {
        return jdr;
    }

    public String asigRol(String rol) {
        this.rol = rol;
        return null;
    }
    
    public boolean hayNotif() {
        return !negocio.getNotif(cta.getUsuarioLogeado()).isEmpty();
    }

    public String mostrarUsuario(UsuarioRegistrado user) {
        switch (user.getIdUser().charAt(0)) {

            case 'A':
                this.a = negocio.buscarAdmin((Administrador) user);
                this.rol = "Administrador";
                break;
            case 'J':
                this.jdr = negocio.buscarJDR((JefeDeRedactores) user);
                this.rol = "JefeDeRedactores";
                break;
            case 'P':
                this.p = negocio.buscarPeriodista((Periodista) user);
                this.rol = "Periodista";
                break;
            case 'S':
                this.su = negocio.buscarSU((SuperUsuario) user);
                this.rol = "SuperUsuario";
                break;
            case 'U':
                this.usuario = user;
                this.rol = "UsuarioRegistrado";
                break;
        }
        return "EditarUsuario.xhtml";
    }

    public String mostrarUsuarioPropio() throws DiarioSurException {
        UsuarioRegistrado u = cta.getUsuarioLogeado();
        switch (u.getIdUser().charAt(0)) {
            case 'A':
                this.a = negocio.buscarAdmin((Administrador) u);
                this.rol = "Administrador";
                break;
            case 'J':
                this.jdr = negocio.buscarJDR((JefeDeRedactores) u);
                this.rol = "JefeDeRedactores";
                break;
            case 'P':
                this.p = negocio.buscarPeriodista((Periodista) u);
                this.rol = "Periodista";
                break;
            case 'S':
                this.su = negocio.buscarSU((SuperUsuario) u);
                this.rol = "SuperUsuario";
                break;
            case 'U':
                this.usuario = negocio.buscarUR(u);
                this.rol = "UsuarioRegistrado";
                break;
        }
        propio = true;
        return "confUsuario.xhtml";

    }

    
    public String editarUsuarioRegistrado() {
        switch (rol) {
            case "Administrador":
                Administrador adm = new Administrador();
//                adm.setIdUser("A" + usuario.getIdUser().substring(1));
                adm.setNombre(usuario.getNombre());
                adm.setApellidos(usuario.getApellidos());
                adm.setDni(usuario.getDni());
                adm.setEmail(usuario.getEmail());
                adm.setPassword(usuario.getPassword());
                adm.setPreferencias(usuario.getPreferencias());
                adm.setMultimedia(usuario.getMultimedia());
                adm.setHistorialEventos(usuario.getHistorialEventos());
                adm.setBorrado(false);
                adm.setEmpresa("------");
                adm.setCargo("------");
                adm.setTelefono("------");

                negocio.eliminarUR(usuario);
                negocio.addAdmin(adm);
                break;
            case "JefeDeRedactores":
                JefeDeRedactores jdre = new JefeDeRedactores();
//                jdre.setIdUser("J" + usuario.getIdUser().substring(1));
                jdre.setNombre(usuario.getNombre());
                jdre.setApellidos(usuario.getApellidos());
                jdre.setDni(usuario.getDni());
                jdre.setEmail(usuario.getEmail());
                jdre.setPassword(usuario.getPassword());
                jdre.setPreferencias(usuario.getPreferencias());
                jdre.setMultimedia(usuario.getMultimedia());
                jdre.setHistorialEventos(usuario.getHistorialEventos());
                jdre.setBorrado(false);
                jdre.setEmpresa("------");
                jdre.setCargo("------");
                jdre.setTelefono("------");

                negocio.eliminarUR(usuario);
                negocio.addJdr(jdre);
                break;
            case "Periodista":
                Periodista per = new Periodista();
//                per.setIdUser("P" + usuario.getIdUser().substring(1));
                per.setNombre(usuario.getNombre());
                per.setApellidos(usuario.getApellidos());
                per.setDni(usuario.getDni());
                per.setEmail(usuario.getEmail());
                per.setPassword(usuario.getPassword());
                per.setPreferencias(usuario.getPreferencias());
                per.setMultimedia(usuario.getMultimedia());
                per.setHistorialEventos(usuario.getHistorialEventos());
                per.setBorrado(false);
                per.setEmpresa("------");
                per.setCargo("------");
                per.setTelefono("------");

                negocio.eliminarUR(usuario);
                negocio.addPeri(per);
                break;
            case "SuperUsuario":
                SuperUsuario sup = new SuperUsuario();
//                sup.setIdUser("S" + usuario.getIdUser().substring(1));
                sup.setNombre(usuario.getNombre());
                sup.setApellidos(usuario.getApellidos());
                sup.setDni(usuario.getDni());
                sup.setEmail(usuario.getEmail());
                sup.setPassword(usuario.getPassword());
                sup.setPreferencias(usuario.getPreferencias());
                sup.setMultimedia(usuario.getMultimedia());
                sup.setHistorialEventos(usuario.getHistorialEventos());
                sup.setEmpresa("------");
                sup.setBorrado(false);
                
                negocio.eliminarUR(usuario);
                negocio.addSuperu(sup);
                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado aux = negocio.buscarUR(usuario);
                aux.setNombre(usuario.getNombre());
                aux.setApellidos(usuario.getApellidos());
                aux.setDni(usuario.getDni());
                aux.setEmail(usuario.getEmail());
                aux.setPassword(usuario.getPassword());
                aux.setPreferencias(usuario.getPreferencias());
                aux.setMultimedia(usuario.getMultimedia());
                aux.setHistorialEventos(usuario.getHistorialEventos());

                negocio.editaUR(aux);

                break;
        }
        return propio ? "index.xhtml" : "gestionUsuario.xhtml";
    }

    public String editarSuperUsuario() {
        switch (rol) {
            case "Administrador":
                Administrador adm = new Administrador();
//                adm.setIdUser("A" + su.getIdUser().substring(1));
                adm.setNombre(su.getNombre());
                adm.setApellidos(su.getApellidos());
                adm.setDni(su.getDni());
                adm.setEmail(su.getEmail());
                adm.setPassword(su.getPassword());
                adm.setPreferencias(su.getPreferencias());
                adm.setMultimedia(su.getMultimedia());
                adm.setHistorialEventos(su.getHistorialEventos());
                adm.setBorrado(false);
                adm.setEmpresa(su.getEmpresa());
                adm.setCargo("------");
                adm.setTelefono("------");

                negocio.eliminarSU(su);
                negocio.addAdmin(adm);
                break;
            case "JefeDeRedactores":
                JefeDeRedactores jdre = new JefeDeRedactores();
//                jdre.setIdUser("J" + su.getIdUser().substring(1));
                jdre.setNombre(su.getNombre());
                jdre.setApellidos(su.getApellidos());
                jdre.setDni(su.getDni());
                jdre.setEmail(su.getEmail());
                jdre.setPassword(su.getPassword());
                jdre.setPreferencias(su.getPreferencias());
                jdre.setMultimedia(su.getMultimedia());
                jdre.setHistorialEventos(su.getHistorialEventos());
                jdre.setEmpresa(su.getEmpresa());
                jdre.setBorrado(false);
                jdre.setCargo("------");
                jdre.setTelefono("------");

                negocio.eliminarSU(su);
                negocio.addJdr(jdre);
                break;
            case "Periodista":
                Periodista per = new Periodista();
//                per.setIdUser("P" + su.getIdUser().substring(1));
                per.setNombre(su.getNombre());
                per.setApellidos(su.getApellidos());
                per.setDni(su.getDni());
                per.setEmail(su.getEmail());
                per.setPassword(su.getPassword());
                per.setPreferencias(su.getPreferencias());
                per.setMultimedia(su.getMultimedia());
                per.setHistorialEventos(su.getHistorialEventos());
                per.setCargo("------");
                per.setTelefono("------");
                per.setEmpresa(su.getEmpresa());
                negocio.eliminarSU(su);
                negocio.addPeri(per);
                break;
            case "SuperUsuario":
                SuperUsuario sup = negocio.buscarSU(su);
                sup.setNombre(su.getNombre());
                sup.setApellidos(su.getApellidos());
                sup.setDni(su.getDni());
                sup.setEmail(su.getEmail());
                sup.setPassword(su.getPassword());
                sup.setPreferencias(su.getPreferencias());
                sup.setMultimedia(su.getMultimedia());
                sup.setHistorialEventos(su.getHistorialEventos());
                sup.setEmpresa(su.getEmpresa());

                negocio.editaSuperu(sup);

                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado ure = new UsuarioRegistrado();
//                ure.setIdUser("U" + su.getIdUser().substring(1));
                ure.setNombre(su.getNombre());
                ure.setApellidos(su.getApellidos());
                ure.setDni(su.getDni());
                ure.setEmail(su.getEmail());
                ure.setPassword(su.getPassword());
                ure.setPreferencias(su.getPreferencias());
                ure.setMultimedia(su.getMultimedia());
                ure.setHistorialEventos(su.getHistorialEventos());
                ure.setBorrado(false);

                negocio.eliminarSU(su);
                negocio.addUR(ure);
                break;
        }
        return propio ? "index.xhtml" : "gestionUsuario.xhtml";
    }

    public String editarPeriodista() {
        switch (rol) {
            case "Administrador":
                Administrador adm = new Administrador();
//                adm.setIdUser("A" + p.getIdUser().substring(1));
                adm.setNombre(p.getNombre());
                adm.setApellidos(p.getApellidos());
                adm.setDni(p.getDni());
                adm.setEmail(p.getEmail());
                adm.setPassword(p.getPassword());
                adm.setPreferencias(p.getPreferencias());
                adm.setMultimedia(p.getMultimedia());
                adm.setHistorialEventos(p.getHistorialEventos());
                adm.setEmpresa(p.getEmpresa());
                adm.setCargo(p.getCargo());
                adm.setTelefono(p.getTelefono());
                adm.setBorrado(false);

                negocio.eliminarPeriodista(p);
                negocio.addAdmin(adm);
                break;
            case "JefeDeRedactores":
                JefeDeRedactores jdre = new JefeDeRedactores();
//                jdre.setIdUser("J" + p.getIdUser().substring(1));
                jdre.setNombre(p.getNombre());
                jdre.setApellidos(p.getApellidos());
                jdre.setDni(p.getDni());
                jdre.setEmail(p.getEmail());
                jdre.setPassword(p.getPassword());
                jdre.setPreferencias(p.getPreferencias());
                jdre.setMultimedia(p.getMultimedia());
                jdre.setHistorialEventos(p.getHistorialEventos());
                jdre.setEmpresa(p.getEmpresa());
                jdre.setCargo(p.getCargo());
                jdre.setTelefono(p.getTelefono());
                jdre.setBorrado(false);

                negocio.eliminarPeriodista(p);
                negocio.addJdr(jdre);
                break;
            case "Periodista":
                Periodista per = negocio.buscarPeriodista(p);
                per.setNombre(p.getNombre());
                per.setApellidos(p.getApellidos());
                per.setDni(p.getDni());
                per.setEmail(p.getEmail());
                per.setPassword(p.getPassword());
                per.setPreferencias(p.getPreferencias());
                per.setMultimedia(p.getMultimedia());
                per.setHistorialEventos(p.getHistorialEventos());
                per.setEmpresa(p.getEmpresa());
                per.setCargo(p.getCargo());
                per.setTelefono(p.getTelefono());

                negocio.editaPeri(per);
                break;
            case "SuperUsuario":
                SuperUsuario sup = new SuperUsuario();
//                sup.setIdUser("S" + p.getIdUser().substring(1));
                sup.setNombre(p.getNombre());
                sup.setApellidos(p.getApellidos());
                sup.setDni(p.getDni());
                sup.setEmail(p.getEmail());
                sup.setPassword(p.getPassword());
                sup.setPreferencias(p.getPreferencias());
                sup.setMultimedia(p.getMultimedia());
                sup.setHistorialEventos(p.getHistorialEventos());
                sup.setEmpresa(p.getEmpresa());
                sup.setBorrado(false);

                negocio.eliminarPeriodista(p);
                negocio.addSuperu(sup);
                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado ure = new UsuarioRegistrado();
//                ure.setIdUser("U" + p.getIdUser().substring(1));
                ure.setNombre(p.getNombre());
                ure.setApellidos(p.getApellidos());
                ure.setDni(p.getDni());
                ure.setEmail(p.getEmail());
                ure.setPassword(p.getPassword());
                ure.setPreferencias(p.getPreferencias());
                ure.setMultimedia(p.getMultimedia());
                ure.setHistorialEventos(p.getHistorialEventos());
                ure.setBorrado(false);

                negocio.eliminarPeriodista(p);
                negocio.addUR(ure);
                break;
        }
        return propio ? "index.xhtml" : "gestionUsuario.xhtml";
    }

    public String editarJefeDeRedactores() {
        switch (rol) {
            case "Administrador":
                Administrador adm = new Administrador();
//                adm.setIdUser("A" + jdr.getIdUser().substring(1));
                adm.setNombre(jdr.getNombre());
                adm.setApellidos(jdr.getApellidos());
                adm.setDni(jdr.getDni());
                adm.setEmail(jdr.getEmail());
                adm.setPassword(jdr.getPassword());
                adm.setPreferencias(jdr.getPreferencias());
                adm.setMultimedia(jdr.getMultimedia());
                adm.setHistorialEventos(jdr.getHistorialEventos());
                adm.setEmpresa(jdr.getEmpresa());
                adm.setCargo(jdr.getCargo());
                adm.setTelefono(jdr.getTelefono());
                adm.setBorrado(false);

                negocio.eliminarJDR(jdr);
                negocio.addAdmin(adm);
                break;
            case "JefeDeRedactores":
                JefeDeRedactores jdre = negocio.buscarJDR(jdr);
                jdre.setNombre(jdr.getNombre());
                jdre.setApellidos(jdr.getApellidos());
                jdre.setDni(jdr.getDni());
                jdre.setEmail(jdr.getEmail());
                jdre.setPassword(jdr.getPassword());
                jdre.setPreferencias(jdr.getPreferencias());
                jdre.setMultimedia(jdr.getMultimedia());
                jdre.setHistorialEventos(jdr.getHistorialEventos());
                jdre.setEmpresa(jdr.getEmpresa());
                jdre.setCargo(jdr.getCargo());
                jdre.setTelefono(jdr.getTelefono());

                negocio.editaJdr(jdre);
                break;
            case "Periodista":
                Periodista per = new Periodista();
//                per.setIdUser("P" + jdr.getIdUser().substring(1));
                per.setNombre(jdr.getNombre());
                per.setApellidos(jdr.getApellidos());
                per.setDni(jdr.getDni());
                per.setEmail(jdr.getEmail());
                per.setPassword(jdr.getPassword());
                per.setPreferencias(jdr.getPreferencias());
                per.setMultimedia(jdr.getMultimedia());
                per.setHistorialEventos(jdr.getHistorialEventos());
                per.setEmpresa(jdr.getEmpresa());
                per.setCargo(jdr.getCargo());
                per.setTelefono(jdr.getTelefono());
                per.setBorrado(false);

                negocio.eliminarJDR(jdr);
                negocio.addPeri(per);
                break;
            case "SuperUsuario":
                SuperUsuario sup = new SuperUsuario();
//                sup.setIdUser("S" + jdr.getIdUser().substring(1));
                sup.setNombre(jdr.getNombre());
                sup.setApellidos(jdr.getApellidos());
                sup.setDni(jdr.getDni());
                sup.setEmail(jdr.getEmail());
                sup.setPassword(jdr.getPassword());
                sup.setPreferencias(jdr.getPreferencias());
                sup.setMultimedia(jdr.getMultimedia());
                sup.setHistorialEventos(jdr.getHistorialEventos());
                sup.setEmpresa(jdr.getEmpresa());
                sup.setBorrado(false);

                negocio.eliminarJDR(jdr);
                negocio.addSuperu(sup);
                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado ure = new UsuarioRegistrado();
//                ure.setIdUser("U" + jdr.getIdUser().substring(1));
                ure.setNombre(jdr.getNombre());
                ure.setApellidos(jdr.getApellidos());
                ure.setDni(jdr.getDni());
                ure.setEmail(jdr.getEmail());
                ure.setPassword(jdr.getPassword());
                ure.setPreferencias(jdr.getPreferencias());
                ure.setMultimedia(jdr.getMultimedia());
                ure.setHistorialEventos(jdr.getHistorialEventos());
                ure.setBorrado(false);

                negocio.eliminarJDR(jdr);
                negocio.addUR(ure);
                break;
        }
        return propio ? "index.xhtml" : "gestionUsuario.xhtml";
    }

    public String editarAdministrador() {
        switch (rol) {
            case "Administrador":
                Administrador adm = negocio.buscarAdmin(a);
                adm.setNombre(a.getNombre());
                adm.setApellidos(a.getApellidos());
                adm.setDni(a.getDni());
                adm.setEmail(a.getEmail());
                adm.setPassword(a.getPassword());
                adm.setPreferencias(a.getPreferencias());
                adm.setMultimedia(a.getMultimedia());
                adm.setHistorialEventos(a.getHistorialEventos());
                adm.setEmpresa(a.getEmpresa());
                adm.setCargo(a.getCargo());
                adm.setTelefono(a.getTelefono());

                negocio.editaAdmin(adm);
                break;
            case "JefeDeRedactores":
                JefeDeRedactores jdre = new JefeDeRedactores();
//                jdre.setIdUser("J" + a.getIdUser().substring(1));
                jdre.setNombre(a.getNombre());
                jdre.setApellidos(a.getApellidos());
                jdre.setDni(a.getDni());
                jdre.setEmail(a.getEmail());
                jdre.setPassword(a.getPassword());
                jdre.setPreferencias(a.getPreferencias());
                jdre.setMultimedia(a.getMultimedia());
                jdre.setHistorialEventos(a.getHistorialEventos());
                jdre.setEmpresa(a.getEmpresa());
                jdre.setCargo(a.getCargo());
                jdre.setTelefono(a.getTelefono());
                jdre.setBorrado(false);
                
                negocio.eliminarAdmin(a);
                negocio.addJdr(jdre);
                break;
            case "Periodista":
                Periodista per = new Periodista();
//                per.setIdUser("P" + a.getIdUser().substring(1));
                per.setNombre(a.getNombre());
                per.setApellidos(a.getApellidos());
                per.setDni(a.getDni());
                per.setEmail(a.getEmail());
                per.setPassword(a.getPassword());
                per.setPreferencias(a.getPreferencias());
                per.setMultimedia(a.getMultimedia());
                per.setHistorialEventos(a.getHistorialEventos());
                per.setEmpresa(a.getEmpresa());
                per.setCargo(a.getCargo());
                per.setTelefono(a.getTelefono());
                per.setBorrado(false);

                negocio.eliminarAdmin(a);
                negocio.addPeri(per);
                break;
            case "SuperUsuario":
                SuperUsuario sup = new SuperUsuario();
//                sup.setIdUser("S" + a.getIdUser().substring(1));
                sup.setNombre(a.getNombre());
                sup.setApellidos(a.getApellidos());
                sup.setDni(a.getDni());
                sup.setEmail(a.getEmail());
                sup.setPassword(a.getPassword());
                sup.setPreferencias(a.getPreferencias());
                sup.setMultimedia(a.getMultimedia());
                sup.setHistorialEventos(a.getHistorialEventos());
                sup.setEmpresa(a.getEmpresa());
                sup.setBorrado(false);

                negocio.eliminarAdmin(a);
                negocio.addSuperu(sup);
                break;
            case "UsuarioRegistrado":
                UsuarioRegistrado ure = new UsuarioRegistrado();
//                ure.setIdUser("U" + a.getIdUser().substring(1));
                ure.setNombre(a.getNombre());
                ure.setApellidos(a.getApellidos());
                ure.setDni(a.getDni());
                ure.setEmail(a.getEmail());
                ure.setPassword(a.getPassword());
                ure.setPreferencias(a.getPreferencias());
                ure.setMultimedia(a.getMultimedia());
                ure.setHistorialEventos(a.getHistorialEventos());
                ure.setBorrado(false);

                negocio.eliminarAdmin(a);
                negocio.addUR(ure);
                break;
        }
        return propio ? "index.xhtml" : "gestionUsuario.xhtml";
    }

}
