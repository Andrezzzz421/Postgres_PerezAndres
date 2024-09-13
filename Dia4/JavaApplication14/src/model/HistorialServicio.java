package model;

import java.sql.Date;

public class HistorialServicio {
    private int idHistorial;
    private int idCarro;
    private int idProveedor;
    private Date fechaServicio;
    private String descripcion;

    // Constructor, getters y setters

    public HistorialServicio(int idHistorial, int idCarro, int idProveedor, Date fechaServicio, String descripcion) {
        this.idHistorial = idHistorial;
        this.idCarro = idCarro;
        this.idProveedor = idProveedor;
        this.fechaServicio = fechaServicio;
        this.descripcion = descripcion;
    }

    public int getIdHistorial() { return idHistorial; }
    public void setIdHistorial(int idHistorial) { this.idHistorial = idHistorial; }
    public int getIdCarro() { return idCarro; }
    public void setIdCarro(int idCarro) { this.idCarro = idCarro; }
    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public Date getFechaServicio() { return fechaServicio; }
    public void setFechaServicio(Date fechaServicio) { this.fechaServicio = fechaServicio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
