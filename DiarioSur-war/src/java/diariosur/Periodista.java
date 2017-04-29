/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosur;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author VersusPC
 */
@Entity
@DiscriminatorValue("Periodista")
public class Periodista extends SuperUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable= false)
    private String cargo;
    @Column(nullable= false)
    private String telefono;

<<<<<<< HEAD
   
=======
       /////////////// CONSTRUCTORES ////////////
    public Periodista () {
        
    }
    
    public Periodista (String idUser, String nombre, String apellidos, String dni, String email, String password, String empresa, String cargo, String telefono) {
        super(idUser, nombre, apellidos, dni, email, password, empresa);
        this.cargo=cargo;
        this.telefono=telefono;
    }
    
    
>>>>>>> origin/master
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUser() != null ? getIdUser().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodista)) {
            return false;
        }
        Periodista other = (Periodista) object;
        if ((this.getIdUser() == null && other.getIdUser() != null) || (this.getIdUser() != null && !this.getIdUser().equals(other.getIdUser()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAproyecto.Periodista[ id=" + getIdUser() + " ]";
    }
    
}
