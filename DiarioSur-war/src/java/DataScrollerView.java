import diariosur.Anuncio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

 
@ManagedBean
@ViewScoped
public class DataScrollerView implements Serializable {
  
    public List<Anuncio> getAnuncios() {
        return PublicarAnuncio.anuncios;
    }
    public void Eliminar(Anuncio anuncio){
        PublicarAnuncio.anuncios.remove(anuncio);
    }
 
}