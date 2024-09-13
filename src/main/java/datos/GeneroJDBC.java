/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

import java.sql.*;
import java.util.*;

public class GeneroJDBC {
    private static final String SQL_SELECT = "SELECT * FROM genero";     //Querry para ver los datos de la tabla genero.
    private static final String SQL_SELECT_VALIDAR = "SELECT id_genero FROM genero WHERE id_genero=?";      //Querry para buscar un id_genero en la tabla genero.
    
   public List<Genero> select (){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Genero genero = null;
        List<Genero> generos = new ArrayList <Genero>();
        
        try{
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idGenero=rs.getInt(1);
                String nombreGenero = rs.getString(2);
                genero = new Genero();
                genero.setIdGenero(idGenero);
                genero.setNombre(nombreGenero);
                generos.add(genero);
            }
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return generos;
    } 
   
   public int selectCondicion(int idGenero){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int condicion = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_VALIDAR);
            stmt.setInt(1, idGenero);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                condicion = 1;
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
  
}
