import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String URL = "jdbc:mariadb://localhost:3306/Biblioteca";
    static String USER = "root";
    static String PASS = "";

    public static Connection crearConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
