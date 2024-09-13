package model;

public class Venta {
    private int idVenta;
    private int idCarro;
    private int idCliente;

    // Constructor, getters y setters

    public Venta(int idVenta, int idCarro, int idCliente) {
        this.idVenta = idVenta;
        this.idCarro = idCarro;
        this.idCliente = idCliente;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdCarro() { return idCarro; }
    public void setIdCarro(int idCarro) { this.idCarro = idCarro; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
}
