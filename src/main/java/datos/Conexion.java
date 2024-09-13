/*
    PROGRAMACIÓN II - PROYECTO FINAL
    Angel Roberto Jacinto Payes 		5990-20-1756
    Melodie Andrea Alexandra García Muralles	5990-18-14753
    Hanson Jafet Chacón García  		5990-20-2001
*/
package datos;

import java.sql.*;

public class Conexion {
    private static final String URL ="jdbc:mysql://localhost:3306/biblioteca_musical?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrivial=true";
    private static final String USER ="root";
    private static final String PASS ="password123";
    
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stmt){
        try{
            stmt.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    
}
