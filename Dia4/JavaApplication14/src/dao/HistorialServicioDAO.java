package dao;

import java.sql.*;
import conection.Conection;

public class HistorialServicioDAO {
    private Conection conexion;

    public HistorialServicioDAO() {
        this.conexion = new Conection();
    }

    // Create
    public void insertarHistorialServicio(int idCarro, String fecha, String descripcion) {
        String ajusteSecuenciaSQL = "SELECT setval('historial_servicio_id_historial_seq', (SELECT COALESCE(MAX(id_historial), 0) FROM historial_servicio))";
        String insertarHistorialSQL = "INSERT INTO historial_servicio (id_carro, fecha, descripcion) VALUES (?, ?, ?)";

        try (Connection con = conexion.getConnection();
             Statement stmt = con.createStatement()) {

            // Ajusta la secuencia
            stmt.execute(ajusteSecuenciaSQL);

            // Inserta el historial de servicio
            try (PreparedStatement pstmt = con.prepareStatement(insertarHistorialSQL)) {
                pstmt.setInt(1, idCarro);
                pstmt.setString(2, fecha);
                pstmt.setString(3, descripcion);
                pstmt.executeUpdate();
                System.out.println("Historial de servicio insertado exitosamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar historial de servicio: " + e.getMessage());
        }
    }

    // Read
    public void obtenerHistorialServicio() {
        String sql = "SELECT * FROM historial_servicio";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_historial");
                int idCarro = rs.getInt("id_carro");
                String fecha = rs.getString("fecha");
                String descripcion = rs.getString("descripcion");

                System.out.printf("ID: %d, ID Carro: %d, Fecha: %s, Descripción: %s%n",
                        id, idCarro, fecha, descripcion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener historial de servicios: " + e.getMessage());
        }
    }

    // Update
    public void actualizarHistorialServicio(int id, int idCarro, String fecha, String descripcion) {
        String sql = "UPDATE historial_servicio SET id_carro = ?, fecha = ?, descripcion = ? WHERE id_historial = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idCarro);
            stmt.setString(2, fecha);
            stmt.setString(3, descripcion);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Historial de servicio actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar historial de servicio: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarHistorialServicio(int id) {
        String sql = "DELETE FROM historial_servicio WHERE id_historial = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Historial de servicio eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar historial de servicio: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
