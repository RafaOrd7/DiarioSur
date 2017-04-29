/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosur;

import diariosur.UsuarioRegistrado;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author pablo
 */
@Entity
@DiscriminatorValue("Superusuario")
public class SuperUsuario extends UsuarioRegistrado  implements Serializable {

    private static long serialVersionUID = 1L;
    
    @Column(nullable  = false, length=50)
    private String empresa;
    
<<<<<<< HEAD
=======
        
    /////////////// CONSTRUCTORES ////////////
    public SuperUsuario () {
        
    }
    
    public SuperUsuario (String idUser, String nombre, String apellidos, String dni, String email, String password, String empresa) {
        super(idUser, nombre, apellidos, dni, email, password);
        this.empresa=empresa;
    }
    
>>>>>>> origin/master
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUser() != null ? getIdUser().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SuperUsuario)) {
            return false;
        }
        SuperUsuario other = (SuperUsuario) object;
        if ((this.getIdUser() == null && other.getIdUser() != null) || (this.getIdUser() != null && !this.getIdUser().equals(other.getIdUser()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.SuperUsuario[ id=" + getIdUser() + " ]";
    }

   
    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

  
    
}
