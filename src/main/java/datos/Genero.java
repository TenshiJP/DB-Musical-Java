/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

public class Genero {
    private int idGenero;
    private String nombre;

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void imprimirInfo(){
        System.out.println("| ID: "+idGenero+"  |   Genero: "+nombre);
    }
    
    @Override
    public String toString() {
        return "Genero{" + "idGenero=" + idGenero + ", nombre=" + nombre + '}';
    }
    
}
