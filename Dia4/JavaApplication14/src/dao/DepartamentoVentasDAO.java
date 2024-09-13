package dao;

import java.sql.*;
import conection.Conection;

public class DepartamentoVentasDAO {
    private Conection conexion;

    public DepartamentoVentasDAO() {
        this.conexion = new Conection();
    }

    // Create
public void insertarDepartamentoVentas(int idEmpleado, double comisionesGeneradas, int ventasRealizadas) {
    String ajusteSecuenciaSQL = "SELECT setval('departamento_ventas_id_dpventas_seq', (SELECT COALESCE(MAX(id_dpventas), 0) FROM departamento_ventas))";
    String insertarDepartamentoVentasSQL = "INSERT INTO departamento_ventas (id_empleado, comisiones_generadas, ventas_realizadas) VALUES (?, ?, ?)";

    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Ajusta la secuencia
        stmt.executeUpdate(ajusteSecuenciaSQL);

        // Inserta el departamento de ventas
        try (PreparedStatement pstmt = con.prepareStatement(insertarDepartamentoVentasSQL)) {
            pstmt.setInt(1, idEmpleado);
            pstmt.setDouble(2, comisionesGeneradas);
            pstmt.setInt(3, ventasRealizadas);
            pstmt.executeUpdate();
            System.out.println("Departamento de ventas insertado exitosamente");
        }

    } catch (SQLException e) {
        System.err.println("Error al insertar departamento de ventas: " + e.getMessage());
    }
}

    // Read
    public void obtenerDepartamentoVentas() {
        String sql = "SELECT * FROM departamento_ventas";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_departamento_ventas");
                int idEmpleado = rs.getInt("id_empleado");
                double comisionesGeneradas = rs.getDouble("comisiones_generadas");
                int ventasRealizadas = rs.getInt("ventas_realizadas");

                System.out.printf("ID: %d, ID Empleado: %d, Comisiones Generadas: %.2f, Ventas Realizadas: %d%n",
                        id, idEmpleado, comisionesGeneradas, ventasRealizadas);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener departamento de ventas: " + e.getMessage());
        }
    }

    // Update
    public void actualizarDepartamentoVentas(int id, int idEmpleado, double comisionesGeneradas, int ventasRealizadas) {
        String sql = "UPDATE departamento_ventas SET id_empleado = ?, comisiones_generadas = ?, ventas_realizadas = ? WHERE id_departamento_ventas = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEmpleado);
            stmt.setDouble(2, comisionesGeneradas);
            stmt.setInt(3, ventasRealizadas);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Departamento de ventas actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar departamento de ventas: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarDepartamentoVentas(int id) {
        String sql = "DELETE FROM departamento_ventas WHERE id_departamento_ventas = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Departamento de ventas eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento de ventas: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
