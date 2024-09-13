package model;

import java.sql.Date;
    
public class Carro {
    private int idCarro;
    private String marca;
    private String modelo;
    private Date ano;
    private double precio;
    private String estado;

    // Constructor, getters y setters

    public Carro(int idCarro, String marca, String modelo, Date ano, double precio, String estado) {
        this.idCarro = idCarro;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.precio = precio;
        this.estado = estado;
    }

    public int getIdCarro() { return idCarro; }
    public void setIdCarro(int idCarro) { this.idCarro = idCarro; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Date getAno() { return ano; }
    public void setAno(Date ano) { this.ano = ano; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
