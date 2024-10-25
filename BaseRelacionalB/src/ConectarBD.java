import javax.swing.*;
import java.sql.Statement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Clase de Conección a la base de datos con métodos crud
 * @author Susana
 */
public class ConectarBD {

    static Connection conectar = null;
    static String driver = "jdbc:postgresql:";
    static String host = "//localhost:";
    static String porto = "5432";
    static String sid = "postgres";
    static String usuario = "postgres";
    static String password = "postgres";
    static String url = driver + host+ porto + "/" + sid;

    /**
     * Metodo para conectar con la base de datos PostgreSQL
     * @return conectar de tipo Connection
     */
    public static Connection conexion(){

        try{
            conectar = DriverManager.getConnection(url,usuario,password);

        }catch(SQLException ex){
            System.out.println("Error al conectar a la base de datos"+ ex.getMessage());
        }
        return conectar;
    }

}
