/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

import java.sql.*;
import java.util.*;

public class AlbumJDBC {
    private static final String SQL_SELECT = "SELECT album.id_album ,album.nombre, artista.nombre, album.anio FROM album INNER JOIN artista ON album.id_artista = artista.id_artista";     //Querry para ver que hay en la tabla artista.
    private static final String SQL_INSERT = "INSERT INTO album (id_album, nombre, id_artista, anio) VALUES (?, ?, ?, ?)";       //Querry para crear un nuevo album.
    private static final String SQL_UPDATE = "UPDATE album SET nombre=?, id_artista=?, anio=? WHERE id_album=?";    //Querry para actualizar un album.
    private static final String SQL_DELETE = "DELETE FROM album WHERE id_album=?";      //Querry para borrar un album.
    private static final String SQL_SELECT_VALIDAR = "SELECT * FROM album WHERE id_album =?";      //Querry para buscar un id_album en la tabla album.
    private static final String SQL_SELECT_LIKE = "SELECT album.nombre, artista.nombre, album.anio "
            + "FROM album INNER JOIN artista ON album.id_artista = artista.id_artista WHERE album.nombre LIKE ?";      //Querry para realizar busquedas en la tabla album.
    
    public List<Album> select (){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Album album = null;
        List<Album> albumes = new ArrayList <Album>();
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idAlbum=rs.getInt(1);
                String nombreAlbum = rs.getString(2);
                String nombreArtista = rs.getString(3);
                String anio = rs.getString(4);
                album = new Album();
                album.setIdAlbum(idAlbum);
                album.setNombre(nombreAlbum);
                album.setNombreArtista(nombreArtista);
                album.setAnio(anio);
                albumes.add(album);
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return albumes;
    }
    
    public int selectCondicion(int idAlbum){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Album album = new Album();
        int condicion = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_VALIDAR);
            stmt.setInt(1, idAlbum);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                condicion = 1;
                idAlbum = rs.getInt(1);
                String nombreAlbum = rs.getString(2);
                int idArtista = rs.getInt(3);
                String anio = rs.getString(4);
                album.setIdAlbum(idAlbum);
                album.setNombre(nombreAlbum);
                album.setIdArtista(idArtista);
                album.setAnio(anio);
                System.out.println("Informacion encontrada:\n"+album+"\n");
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
                String anio = rs.getString(3);
                System.out.println("| "+nombre+" | Artista: "+artista+"     Lanzamiento: "+anio);
            }   
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
    }
    
    public int insert(Album album){
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, album.getIdAlbum());
            
            stmt.setString(2, album.getNombre());
            stmt.setInt(3, album.getIdArtista());
            stmt.setString(4, album.getAnio());
            row = stmt.executeUpdate();
            System.out.println("Registro guardado");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return row;
    }
    
    public int update(Album album){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = Conexion.getConnection();
            System.out.println("Iniciando ejecucion update");
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, album.getNombre());
            stmt.setInt(2, album.getIdArtista());
            stmt.setString(3, album.getAnio());
            stmt.setInt(4, album.getIdAlbum());
            rows = stmt.executeUpdate();
            System.out.println("Registro modificado");
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return rows;
    }
    
    public int delete(Album album){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("Iniciando ejecucion Delete");
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, album.getIdAlbum());
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
