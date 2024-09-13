package model;

import java.sql.Date;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaContratacion;
    private String rol;

    // Constructor, getters y setters

    public Empleado(int idEmpleado, String nombre, String apellido1, String apellido2, Date fechaContratacion, String rol) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaContratacion = fechaContratacion;
        this.rol = rol;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
