package model;

import java.math.BigDecimal;

public class DepartamentoVentas {
    private int idDpVentas;
    private int idEmpleado;
    private BigDecimal comisionesGeneradas;
    private int ventasRealizadas;

    public DepartamentoVentas(int idDpVentas, int idEmpleado, BigDecimal comisionesGeneradas, int ventasRealizadas) {
        this.idDpVentas = idDpVentas;
        this.idEmpleado = idEmpleado;
        this.comisionesGeneradas = comisionesGeneradas;
        this.ventasRealizadas = ventasRealizadas;
    }

    // Getters and Setters
    public int getIdDpVentas() {
        return idDpVentas;
    }

    public void setIdDpVentas(int idDpVentas) {
        this.idDpVentas = idDpVentas;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getComisionesGeneradas() {
        return comisionesGeneradas;
    }

    public void setComisionesGeneradas(BigDecimal comisionesGeneradas) {
        this.comisionesGeneradas = comisionesGeneradas;
    }

    public int getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(int ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }
}
