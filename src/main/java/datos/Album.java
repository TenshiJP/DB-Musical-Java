/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

public class Album extends Artista{
    private int idAlbum;
    private String nombre;
    private int idArtista;
    private String anio;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
    
    public void imprimirRelacion(){
        System.out.println("| ID: "+idAlbum+" | Album: "+nombre+"   Artista: "+super.getNombreArtista()+"     Lanzamiento: "+anio);
    }
   
    @Override
    public String toString() {
        return "| ID: "+idAlbum+" | Album: "+nombre+"    ID del Artista: "+idArtista+"    Lanzamiento: "+anio;
    }

    
    
}
