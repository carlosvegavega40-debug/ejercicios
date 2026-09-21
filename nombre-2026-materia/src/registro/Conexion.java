package registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Maneja la conexión a la base de datos MySQL "Registro".
 * Driver nuevo: com.mysql.cj.jdbc.Driver (el anterior com.mysql.jdbc.Driver está obsoleto).
 */
public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Registro";
    private static final String USER = "root";
    // Clave de la base de datos: cámbiela si su MySQL tiene contraseña.
    private static final String PASS = "";

    private Connection con;

    /** Abre la conexión con la base de datos. */
    public Connection conectar() throws ClassNotFoundException, SQLException {
        con = null;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, USER, PASS);
        return con;
    }

    public Connection getConnection() {
        return con;
    }

    public boolean estaConectado() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /** Cierra la conexión con la base de datos. */
    public void cerrar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        } finally {
            con = null;
        }
    }
}
