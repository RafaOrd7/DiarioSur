import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

 
@ManagedBean
@ViewScoped
public class DataScrollerView implements Serializable {
     
    private List<PublicarAnuncio> anuncios;

    public List<PublicarAnuncio> getAnuncios() {
        return anuncios;
    }
    public void Eliminar(PublicarAnuncio anuncio){
        anuncios.remove(anuncio);
    }
 
}