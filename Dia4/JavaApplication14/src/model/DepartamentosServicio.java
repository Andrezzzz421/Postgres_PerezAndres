package model;

public class DepartamentosServicio {
    private int idDpServicios;
    private int idHistorial;
    private String horario;
    private String servicios;

    public DepartamentosServicio(int idDpServicios, int idHistorial, String horario, String servicios) {
        this.idDpServicios = idDpServicios;
        this.idHistorial = idHistorial;
        this.horario = horario;
        this.servicios = servicios;
    }

    // Getters and Setters
    public int getIdDpServicios() {
        return idDpServicios;
    }

    public void setIdDpServicios(int idDpServicios) {
        this.idDpServicios = idDpServicios;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
}
