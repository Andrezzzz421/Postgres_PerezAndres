package dao;

import java.sql.*;
import conection.Conection;

public class ClientesPotencialesDAO {
    private Conection conexion;

    public ClientesPotencialesDAO() {
        this.conexion = new Conection();
    }

    // Create
public void insertarClientePotencial(int idCliente, int idCarro, String fecha, String descripcion) {
    String ajusteSecuenciaSQL = "SELECT setval('clientes_potenciales_id_cliente_seq', (SELECT COALESCE(MAX(id_cliente), 0) FROM clientes_potenciales))";
    String insertarClientePotencialSQL = "INSERT INTO clientes_potenciales (id_cliente, id_carro, fecha, descripcion) VALUES (?, ?, ?, ?)";

    try (Connection con = conexion.getConnection();
         Statement stmt = con.createStatement()) {

        // Ajusta la secuencia
        stmt.execute(ajusteSecuenciaSQL);

        // Inserta el cliente potencial
        try (PreparedStatement pstmt = con.prepareStatement(insertarClientePotencialSQL)) {
            pstmt.setInt(1, idCliente);
            pstmt.setInt(2, idCarro);
            pstmt.setDate(3, Date.valueOf(fecha)); // Convierte la fecha a java.sql.Date
            pstmt.setString(4, descripcion);
            pstmt.executeUpdate();
            System.out.println("Cliente potencial insertado exitosamente");
        }

    } catch (SQLException e) {
        System.err.println("Error al insertar cliente potencial: " + e.getMessage());
    }
}



    // Read
    public void obtenerClientesPotenciales() {
        String sql = "SELECT * FROM clientes_potenciales";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_cliente_potencial");
                int idCliente = rs.getInt("id_cliente");
                int idCarro = rs.getInt("id_carro");
                String fecha = rs.getDate("fecha").toString();
                String descripcion = rs.getString("descripcion");

                System.out.printf("ID: %d, ID Cliente: %d, ID Carro: %d, Fecha: %s, Descripción: %s%n",
                        id, idCliente, idCarro, fecha, descripcion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes potenciales: " + e.getMessage());
        }
    }

    // Update
    public void actualizarClientePotencial(int id, int idCliente, int idCarro, String fecha, String descripcion) {
        String sql = "UPDATE clientes_potenciales SET id_cliente = ?, id_carro = ?, fecha = ?, descripcion = ? WHERE id_cliente_potencial = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idCarro);
            stmt.setDate(3, java.sql.Date.valueOf(fecha));
            stmt.setString(4, descripcion);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Cliente potencial actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente potencial: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarClientePotencial(int id) {
        String sql = "DELETE FROM clientes_potenciales WHERE id_cliente_potencial = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente potencial eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente potencial: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
