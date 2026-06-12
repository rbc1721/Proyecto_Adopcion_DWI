package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Conexion instancia;
    private Connection conexion = null;

        private static final String host = "localhost";
        private static final String port = "3306";
        private static final String db = "krasota_db";
        private static final String user = "root";
        private static final String pass = "";
        private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + db
            + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private Conexion() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida a servidor en nube");

        } catch (SQLException e) {
            System.out.println("Error al intentar conectarse a la base de datos");
            e.printStackTrace();
        }

    }

    public static Conexion getInstancia() throws ClassNotFoundException {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Se ha cerrado la conexión");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
