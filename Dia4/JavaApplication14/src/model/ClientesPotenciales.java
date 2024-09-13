package model;

import java.time.LocalDate;

public class ClientesPotenciales {
    private int idCliente;
    private int idCarro;
    private LocalDate fecha;
    private String descripcion;

    public ClientesPotenciales(int idCliente, int idCarro, LocalDate fecha, String descripcion) {
        this.idCliente = idCliente;
        this.idCarro = idCarro;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    // Getters and Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
