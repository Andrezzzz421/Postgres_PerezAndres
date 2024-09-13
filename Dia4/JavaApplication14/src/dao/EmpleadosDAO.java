package dao;

import java.sql.*;
import conection.Conection;

public class EmpleadosDAO {
    private Conection conexion;

    public EmpleadosDAO() {
        this.conexion = new Conection();
    }

    // Create
public void insertarEmpleado(String nombre, String apellido1, String apellido2, String fechaContratacion, String rol) {
    String ajusteSecuenciaSQL = "SELECT setval('empleados_id_empleado_seq', (SELECT COALESCE(MAX(id_empleado), 0) FROM empleados))";
    String insertarEmpleadoSQL = "INSERT INTO empleados (nombre, apellido1, apellido2, fecha_contratacion, rol) VALUES (?, ?, ?, ?, ?::rolez)";

    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Ajusta la secuencia
        stmt.execute(ajusteSecuenciaSQL);

        // Inserta el empleado
        try (PreparedStatement pstmt = con.prepareStatement(insertarEmpleadoSQL)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido1);
            pstmt.setString(3, apellido2);
            pstmt.setDate(4, Date.valueOf(fechaContratacion)); // Convierte la fecha a java.sql.Date
            pstmt.setString(5, rol); // El rol se convierte con el cast ::rolez en la consulta
            pstmt.executeUpdate();
            System.out.println("Empleado insertado exitosamente");
        }

    } catch (SQLException e) {
        System.err.println("Error al insertar empleado: " + e.getMessage());
    }
}



    // Read
    public void obtenerEmpleados() {
        String sql = "SELECT * FROM empleados";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                String apellido1 = rs.getString("apellido1");
                String apellido2 = rs.getString("apellido2");
                String fechaContratacion = rs.getDate("fecha_contratacion").toString();
                String rol = rs.getString("rol");

                System.out.printf("ID: %d, Nombre: %s, Apellido1: %s, Apellido2: %s, Fecha Contratación: %s, Rol: %s%n",
                        id, nombre, apellido1, apellido2, fechaContratacion, rol);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener empleados: " + e.getMessage());
        }
    }

    // Update
    public void actualizarEmpleado(int id, String nombre, String apellido1, String apellido2, String fechaContratacion, String rol) {
        String sql = "UPDATE empleados SET nombre = ?, apellido1 = ?, apellido2 = ?, fecha_contratacion = ?, rol = ? WHERE id_empleado = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido1);
            stmt.setString(3, apellido2);
            stmt.setDate(4, java.sql.Date.valueOf(fechaContratacion));
            stmt.setString(5, rol);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            System.out.println("Empleado actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarEmpleado(int id) {
        String sql = "DELETE FROM empleados WHERE id_empleado = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Empleado eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
