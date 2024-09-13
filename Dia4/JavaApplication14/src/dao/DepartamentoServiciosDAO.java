package dao;

import java.sql.*;
import conection.Conection;

public class DepartamentoServiciosDAO {
    private Conection conexion;

    public DepartamentoServiciosDAO() {
        this.conexion = new Conection();
    }

    // Create
public void insertarDepartamentoServicios(int idHistorial, String horario, String servicios) {
    String ajusteSecuenciaSQL = "SELECT setval('departamento_servicios_id_dpservicios_seq', (SELECT COALESCE(MAX(id_dpservicios), 0) FROM departamento_servicios))";
    String insertarDepartamentoServiciosSQL = "INSERT INTO departamento_servicios (id_historial, horario, servicios) VALUES (?, ?, ?)";

    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Ajusta la secuencia
        stmt.execute(ajusteSecuenciaSQL);

        // Inserta el departamento de servicios
        try (PreparedStatement pstmt = con.prepareStatement(insertarDepartamentoServiciosSQL)) {
            pstmt.setInt(1, idHistorial);
            pstmt.setString(2, horario);
            pstmt.setString(3, servicios);
            pstmt.executeUpdate();
            System.out.println("Departamento de servicios insertado exitosamente");
        }

    } catch (SQLException e) {
        System.err.println("Error al insertar departamento de servicios: " + e.getMessage());
    }
}


    // Read
    public void obtenerDepartamentoServicios() {
        String sql = "SELECT * FROM departamento_servicios";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_departamento_servicios");
                int idHistorial = rs.getInt("id_historial");
                String horario = rs.getString("horario");
                String servicios = rs.getString("servicios");

                System.out.printf("ID: %d, ID Historial: %d, Horario: %s, Servicios: %s%n",
                        id, idHistorial, horario, servicios);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener departamento de servicios: " + e.getMessage());
        }
    }

    // Update
    public void actualizarDepartamentoServicios(int id, int idHistorial, String horario, String servicios) {
        String sql = "UPDATE departamento_servicios SET id_historial = ?, horario = ?, servicios = ? WHERE id_departamento_servicios = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idHistorial);
            stmt.setString(2, horario);
            stmt.setString(3, servicios);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Departamento de servicios actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento de servicios: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarDepartamentoServicios(int id) {
        String sql = "DELETE FROM departamento_servicios WHERE id_departamento_servicios = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Departamento de servicios eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento de servicios: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
