/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

import java.sql.*;
import java.util.*;

public class CancionJDBC {
    private static final String SQL_SELECT_JOIN = "SELECT cancion.id_cancion, cancion.nombre, artista.nombre, album.nombre, genero.nombre, cancion.descripcion\n" +
                            "	FROM cancion INNER JOIN artista ON cancion.id_artista = artista.id_artista\n" +
                            "    INNER JOIN album ON cancion.id_album = album.id_album\n" +
                            "    INNER JOIN genero ON cancion.id_genero = genero.id_genero";     //Querry para ver que hay en la tabla canciones, con su relacion.
    private static final String SQL_INSERT = "INSERT INTO cancion (id_cancion, nombre, id_artista, id_album, id_genero, descripcion) VALUES (? , ?, ?, ?, ?, ?)";       //Querry para crear una nueva cancion.
    private static final String SQL_UPDATE = "UPDATE cancion SET nombre=?, id_artista=?, id_album=?, id_genero=?, descripcion=? WHERE id_cancion=?";    //Querry para actualizar una cancion.
    private static final String SQL_DELETE = "DELETE FROM cancion WHERE id_cancion=?";      //Querry para borrar una cancion.
    private static final String SQL_SELECT_VALIDAR = "SELECT * FROM cancion WHERE id_cancion =?";      //Querry para buscar un id_cancion en la tabla album.
    private static final String SQL_SELECT_LIKE = "SELECT cancion.nombre, artista.nombre, album.nombre, genero.nombre, cancion.descripcion\n" +
                            "	FROM cancion INNER JOIN artista ON cancion.id_artista = artista.id_artista\n" +
                            "    INNER JOIN album ON cancion.id_album = album.id_album\n" +
                            "    INNER JOIN genero ON cancion.id_genero = genero.id_genero WHERE cancion.nombre LIKE ?";   //Querry para realizar busquedas en la tabla cancion.
    
    public void selectInnerJoin(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_JOIN);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idCancion = rs.getInt(1);
                String nombreCancion = rs.getString(2);
                String artista = rs.getString(3);
                String album = rs.getString(4);
                String genero = rs.getString(5);
                String descripcion = rs.getString(6);
                System.out.println("| ID: "+idCancion+" | Cancion: "+nombreCancion+"     Artista: "+artista+"    Album: "+album+"    Genero: "+genero+"  Descripcion: "+descripcion);
            }
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
           Conexion.close(conn);
           Conexion.close(stmt);
           Conexion.close(rs);
        }
    }
     
    public int selectCondicion(int idCancion){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cancion cancion = new Cancion();
        int condicion = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_VALIDAR);
            stmt.setInt(1, idCancion);
            rs = stmt.executeQuery();

            if(rs.next()){
                condicion = 1;
                idCancion = rs.getInt(1);
                String nombreCancion = rs.getString(2);
                int idArtista = rs.getInt(3);
                int idAlbum = rs.getInt(4);
                int idGenero = rs.getInt(5);
                String descripcion = rs.getString(6);
                cancion.setIdCancion(idCancion);
                cancion.setNombre(nombreCancion);
                cancion.setIdArtista(idArtista);
                cancion.setIdAlbum(idAlbum);
                cancion.setIdGenero(idGenero);
                cancion.setDescripcion(descripcion);
                System.out.println("Informacion encontrada:\n"+cancion+"\n");
            }else{}

        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return condicion;
    }
    
    public void selectLike(String nombre){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_LIKE);
            stmt.setString(1, "%"+nombre+"%");
            rs = stmt.executeQuery();
            while(rs.next()){
                nombre = rs.getString(1);
                String artista = rs.getString(2);
                String album = rs.getString(3);
                String genero = rs.getString(4);
                String descripcion = rs.getString(5);
                System.out.println("| "+nombre+" | Artista: "+artista+"     Album: "+album+"     Genero: "+genero+"     Descripcion: "+descripcion);
            }   
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
    }
    
    public int insert(Cancion cancion){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

            try {
                conn = Conexion.getConnection();
                stmt = conn.prepareStatement(SQL_INSERT);
                stmt.setInt (1, cancion.getIdCancion());
                stmt.setString (2, cancion.getNombre());
                stmt.setInt (3, cancion.getIdArtista());
                stmt.setInt (4, cancion.getIdAlbum());
                stmt.setInt(5, cancion.getIdGenero());
                stmt.setString (6, cancion.getDescripcion());
                rows = stmt.executeUpdate();
                System.out.println("Registro guardado");

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            finally{
                Conexion.close(stmt);
                Conexion.close(conn);
            }
            return rows;
    }
    
    public int update(Cancion cancion){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = Conexion.getConnection();
            System.out.println("Iniciando ejecucion update");
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cancion.getNombre());
            stmt.setInt(2, cancion.getIdArtista());
            stmt.setInt(3, cancion.getIdAlbum());
            stmt.setInt(4, cancion.getIdGenero());
            stmt.setString(5, cancion.getDescripcion());
            stmt.setInt(6, cancion.getIdCancion());
            rows = stmt.executeUpdate();
            System.out.println("Registro modificado ");
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;
    }

    public int delete(Cancion cancion){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("Inicaiando ejecucuion Delete");
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cancion.getIdCancion());
            rows = stmt.executeUpdate();
            System.out.println("Registro Eliminado");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;
    }
  
}
