package dao;

import java.sql.*;
import conection.Conection;

public class VentasDAO {
    private Conection conexion;

    public VentasDAO() {
        this.conexion = new Conection();
    }

    // Create
    public void insertarVenta(int idCarro, int idCliente, String fecha, double precio) {
        String ajusteSecuenciaSQL = "SELECT setval('ventas_id_venta_seq', (SELECT COALESCE(MAX(id_venta), 0) FROM ventas))";
        String insertarVentaSQL = "INSERT INTO ventas (id_carro, id_cliente, fecha, precio) VALUES (?, ?, ?, ?)";

        try (Connection con = conexion.getConnection();
             Statement stmt = con.createStatement()) {

            // Ajusta la secuencia
            stmt.execute(ajusteSecuenciaSQL);

            // Inserta la venta
            try (PreparedStatement pstmt = con.prepareStatement(insertarVentaSQL)) {
                pstmt.setInt(1, idCarro);
                pstmt.setInt(2, idCliente);
                pstmt.setString(3, fecha);
                pstmt.setDouble(4, precio);
                pstmt.executeUpdate();
                System.out.println("Venta insertada exitosamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
        }
    }

    // Read
    public void obtenerVentas() {
        String sql = "SELECT * FROM ventas";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_venta");
                int idCarro = rs.getInt("id_carro");
                int idCliente = rs.getInt("id_cliente");
                String fecha = rs.getString("fecha");
                double precio = rs.getDouble("precio");

                System.out.printf("ID: %d, ID Carro: %d, ID Cliente: %d, Fecha: %s, Precio: %.2f%n",
                        id, idCarro, idCliente, fecha, precio);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
        }
    }

    // Update
    public void actualizarVenta(int id, int idCarro, int idCliente, String fecha, double precio) {
        String sql = "UPDATE ventas SET id_carro = ?, id_cliente = ?, fecha = ?, precio = ? WHERE id_venta = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idCarro);
            stmt.setInt(2, idCliente);
            stmt.setString(3, fecha);
            stmt.setDouble(4, precio);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Venta actualizada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar venta: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarVenta(int id) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Venta eliminada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar venta: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
