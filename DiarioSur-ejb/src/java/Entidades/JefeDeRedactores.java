/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Garri
 */
@Entity
@DiscriminatorValue("JefeDeRedactores")
public class JefeDeRedactores extends Periodista implements Serializable {

   
    public JefeDeRedactores () {
        
    }
    
    public JefeDeRedactores (String idUser, String nombre, String apellidos, String dni, String email, String password, String empresa, String cargo, String telefono) {
        super(idUser, nombre, apellidos, dni, email, password, empresa, cargo, telefono);
    }

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getIdUser() != null ? getIdUser().hashCode() : 0);
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
        return "diariosur.JefeDeRedactores[ id=" + getIdUser()  + " ]";
    }
    
}
