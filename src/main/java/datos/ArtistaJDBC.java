/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

import java.sql.*;
import java.util.*;

public class ArtistaJDBC {
    private static final String SQL_SELECT = "SELECT * FROM artista";     //Querry para ver que hay en la tabla artista.
    private static final String SQL_INSERT = "INSERT INTO artista (id_artista, nombre) VALUES (?, ?)";       //Querry para crear un nuevo artista.
    private static final String SQL_UPDATE = "UPDATE artista SET nombre=? WHERE id_artista=?";    //Querry para actualizar un artista.
    private static final String SQL_DELETE = "DELETE FROM artista WHERE id_artista=?";      //Querry para borrar un artista.
    private static final String SQL_SELECT_VALIDAR = "SELECT * FROM artista WHERE id_artista  =?";      //Querry para buscar un id_artista en la tabla artista.
    private static final String SQL_SELECT_LIKE = "SELECT * FROM artista WHERE nombre LIKE ?";   //Querry para realizar busquedas en la tabla artista.
    
    public List<Artista> select (){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Artista artista = null;
        List<Artista> artistas = new ArrayList <Artista>();
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idArtista=rs.getInt(1);
                String nombreArtista = rs.getString(2);
                artista = new Artista();
                artista.setIdArtista(idArtista);
                artista.setNombreArtista(nombreArtista);
                artistas.add(artista);
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return artistas;
    }
    
    public int selectCondicion(int idArtista){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Artista artista = new Artista();
        int condicion = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_VALIDAR);
            stmt.setInt(1, idArtista);
            rs = stmt.executeQuery();
            if(rs.next()){
                condicion = 1;
                idArtista=rs.getInt(1);
                String nombreArtista = rs.getString(2);
                artista.setIdArtista(idArtista);
                artista.setNombreArtista(nombreArtista);
                System.out.println("Informacion encontrada:\n"+artista+"\n");
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
                int idArtista=rs.getInt(1);
                nombre = rs.getString(2);
                System.out.println("| ID: "+idArtista+" | "+nombre);
            }   
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
    }
   
    public int insert(Artista artista){
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, artista.getIdArtista());
            stmt.setString(2, artista.getNombreArtista());
            row = stmt.executeUpdate();
            System.out.println("Registro guardado ");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
        }
        return row;
    }
    
    public int update(Artista artista){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try{
            conn = Conexion.getConnection();
            System.out.println("Iniciando ejecucion update ");
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, artista.getNombreArtista());
            stmt.setInt(2, artista.getIdArtista());
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
    
    public int delete(Artista artista){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            System.out.println("Inicaiando ejecuion Delete ");
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, artista.getIdArtista());
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
