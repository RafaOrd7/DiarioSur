/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Entidades.Anuncio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author jajip
 */
@Entity
@DiscriminatorValue("Administrador")
public class Administrador  extends JefeDeRedactores implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public Administrador () {

    }

    public Administrador (String idUser, String nombre, String apellidos, String dni, String email, String password, String empresa, String cargo, String telefono) {
        super(idUser, nombre, apellidos, dni, email, password, empresa, cargo, telefono);
    }

   @OneToMany(mappedBy="administrador")
    private List<Anuncio> anuncios;
    
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUser()  != null ? getIdUser() .hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.getIdUser() == null && other.getIdUser() != null) || (this.getIdUser() != null && !this.getIdUser().equals(other.getIdUser()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyectogrupo.Administrador[ id=" + getIdUser() + " ]";
    }
    
}
