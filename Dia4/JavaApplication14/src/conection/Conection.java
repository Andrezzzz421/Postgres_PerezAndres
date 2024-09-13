package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conection {
    private Connection con;

    public Conection() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Archivo config.properties no encontrado");
            }

            props.load(input);

            String url = props.getProperty("URL");
            String user = props.getProperty("USER");
            String password = props.getProperty("PASSWORD");
            String driver = props.getProperty("db.driver"); 

            if (url == null || user == null || password == null || driver == null) {
                throw new IllegalStateException("Una o más propiedades de conexión no están definidas");
            }

            Class.forName(driver); // Cargar el driver de PostgreSQL usando la propiedad
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (IOException | ClassNotFoundException | SQLException | IllegalStateException e) {
            System.err.println("Error en la conexión :(, error: " + e);
            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e.toString());
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada exitosamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e);
            }
        }
    }
}
