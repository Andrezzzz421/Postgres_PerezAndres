package dao;

import java.sql.*;
import conection.Conection;

public class ProveedoresPiezasDAO {
    private Conection conexion;

    public ProveedoresPiezasDAO() {
        this.conexion = new Conection();
    }

    // Create
    public void insertarProveedorPieza(String nombre, String contacto) {
        String ajusteSecuenciaSQL = "SELECT setval('proveedor_pieza_id_proveedor_seq', (SELECT COALESCE(MAX(id_proveedor), 0) FROM proveedor_pieza))";
        String insertarProveedorSQL = "INSERT INTO proveedor_pieza (nombre, contacto) VALUES (?, ?)";

        try (Connection con = conexion.getConnection();
             Statement stmt = con.createStatement()) {

            // Ajusta la secuencia
            stmt.execute(ajusteSecuenciaSQL);

            // Inserta el proveedor de piezas
            try (PreparedStatement pstmt = con.prepareStatement(insertarProveedorSQL)) {
                pstmt.setString(1, nombre);
                pstmt.setString(2, contacto);
                pstmt.executeUpdate();
                System.out.println("Proveedor de piezas insertado exitosamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar proveedor de piezas: " + e.getMessage());
        }
    }

    // Read
    public void obtenerProveedoresPiezas() {
        String sql = "SELECT * FROM proveedor_pieza";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String contacto = rs.getString("contacto");

                System.out.printf("ID: %d, Nombre: %s, Contacto: %s%n", id, nombre, contacto);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener proveedores de piezas: " + e.getMessage());
        }
    }

    // Update
    public void actualizarProveedorPieza(int id, String nombre, String contacto) {
        String sql = "UPDATE proveedor_pieza SET nombre = ?, contacto = ? WHERE id_proveedor = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contacto);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Proveedor de piezas actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor de piezas: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarProveedorPieza(int id) {
        String sql = "DELETE FROM proveedor_pieza WHERE id_proveedor = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Proveedor de piezas eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor de piezas: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
