/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import diariosur.Reporte;
import diariosur.Valoracion;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author rafao
 */
@Named(value = "recogedorValoraciones")
@RequestScoped
public class recogedorValoraciones {

    private Integer rating;
    private String comentario;
    
      public Integer getRating() {
        return rating;
    }
      
        public void setRating(Integer n) {
        rating=n;
    }
        
      public String getComentario() {
        return comentario;
    }
      
        public void setComentario(String m) {
        comentario=m;
    }
        
    
   
}
