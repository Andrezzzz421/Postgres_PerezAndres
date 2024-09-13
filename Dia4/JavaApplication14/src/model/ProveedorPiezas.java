package model;

public class ProveedorPiezas {
    private int idProveedor;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    private String ciudad;

    // Constructor, getters y setters

    public ProveedorPiezas(int idProveedor, String nombre, String apellido1, String apellido2, String documento, String ciudad) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.documento = documento;
        this.ciudad = ciudad;
    }

    public int getIdProveedor() { return idProveedor; }
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
}
