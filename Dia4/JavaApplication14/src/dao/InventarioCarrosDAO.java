package dao;

import java.sql.*;
import conection.Conection;

public class InventarioCarrosDAO {
    private Conection conexion;

    public InventarioCarrosDAO() {
        this.conexion = new Conection();
    }

    // Create
    public void insertarCarro(String modelo, String marca, int ano, String color) {
        String ajusteSecuenciaSQL = "SELECT setval('inventario_carros_id_carro_seq', (SELECT COALESCE(MAX(id_carro), 0) FROM inventario_carros))";
        String insertarCarroSQL = "INSERT INTO inventario_carros (modelo, marca, ano, color) VALUES (?, ?, ?, ?)";

        try (Connection con = conexion.getConnection();
             Statement stmt = con.createStatement()) {

            // Ajusta la secuencia
            stmt.execute(ajusteSecuenciaSQL);

            // Inserta el carro
            try (PreparedStatement pstmt = con.prepareStatement(insertarCarroSQL)) {
                pstmt.setString(1, modelo);
                pstmt.setString(2, marca);
                pstmt.setInt(3, ano);
                pstmt.setString(4, color);
                pstmt.executeUpdate();
                System.out.println("Carro insertado exitosamente");
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar carro: " + e.getMessage());
        }
    }

    // Read
    public void obtenerCarros() {
        String sql = "SELECT * FROM inventario_carros";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_carro");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                int ano = rs.getInt("ano");
                String color = rs.getString("color");

                System.out.printf("ID: %d, Modelo: %s, Marca: %s, Año: %d, Color: %s%n",
                        id, modelo, marca, ano, color);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener carros: " + e.getMessage());
        }
    }

    // Update
    public void actualizarCarro(int id, String modelo, String marca, int ano, String color) {
        String sql = "UPDATE inventario_carros SET modelo = ?, marca = ?, ano = ?, color = ? WHERE id_carro = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, modelo);
            stmt.setString(2, marca);
            stmt.setInt(3, ano);
            stmt.setString(4, color);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Carro actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al actualizar carro: " + e.getMessage());
        }
    }

    // Delete
    public void eliminarCarro(int id) {
        String sql = "DELETE FROM inventario_carros WHERE id_carro = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Carro eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al eliminar carro: " + e.getMessage());
        }
    }

    // Close connection
    public void cerrarConexion() {
        conexion.closeConnection();
    }
}
