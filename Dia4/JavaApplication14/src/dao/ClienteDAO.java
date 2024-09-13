package dao;

import java.sql.*;
import conection.Conection;

public class ClienteDAO {
    private Conection conexion;

    public ClienteDAO() {
        this.conexion = new Conection();
    }

    // Create
public void insertarCliente(String nombre, String apellido1, String apellido2, String documento, String ciudad) {
    String ajusteSecuenciaSQL = "SELECT setval('cliente_id_cliente_seq', (SELECT COALESCE(MAX(id_cliente), 0) FROM cliente))";
    String insertarClienteSQL = "INSERT INTO cliente (nombre, apellido1, apellido2, documento, ciudad) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Ajusta la secuencia
        stmt.execute(ajusteSecuenciaSQL);

        // Inserta el cliente
        try (PreparedStatement pstmt = con.prepareStatement(insertarClienteSQL)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido1);
            pstmt.setString(3, apellido2);
            pstmt.setString(4, documento);
            pstmt.setString(5, ciudad);
            pstmt.executeUpdate();
            System.out.println("Cliente insertado exitosamente");
        }

    } catch (SQLException e) {
        System.err.println("Error al insertar cliente: " + e.getMessage());
    }
}


    // Read
    public void obtenerClientes() {
        String sql = "SELECT * FROM cliente";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String documento = rs.getString("documento");
                String ciudad = rs.getString("ciudad");

                System.out.printf("ID: %d, Nombre: %s, Apellido1: %s, Apellido2: %s, Documento: %s, Ciudad: %s%n",
                        id, nombre, apellido1, apellido2, documento, ciudad);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
    }

    // Update
    public void actualizarCliente(int id, String nombre, String apellido1, String apellido2, String documento, String ciudad) {
        String sql = "UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, documento = ?, ciudad = ? WHERE id_cliente = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            stmt.setString(4, documento);
            stmt.setString(5, ciudad);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            System.out.println("Cliente actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
