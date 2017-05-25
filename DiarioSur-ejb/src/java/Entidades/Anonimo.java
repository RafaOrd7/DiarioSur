/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.Cookie;

/**
 *
 * @author Garri
 */
@Entity
public class Anonimo extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
   @Column(nullable  = false, length=50)
    private Cookie c;

    public Cookie getC() {
        return c;
    }

    public void setC(Cookie c) {
        this.c = c;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUser() != null ? getIdUser().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idUser fields are not set
        if (!(object instanceof Anonimo)) {
            return false;
        }
        Anonimo other = (Anonimo) object;
        if ((this.getIdUser() == null && other.getIdUser() != null) || (this.getIdUser() != null && !this.getIdUser().equals(other.getIdUser()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Anonimo[ id=" + getIdUser() + " ]";
    }
    
}
