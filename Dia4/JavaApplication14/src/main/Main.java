package main;

import java.util.Scanner;
import dao.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Clientes Potenciales");
            System.out.println("3. Gestionar Departamento de Servicios");
            System.out.println("4. Gestionar Empleados");
            System.out.println("5. Gestionar Inventario de Carros");
            System.out.println("6. Gestionar Historial de Servicios");
            System.out.println("7. Gestionar Proveedores de Piezas");
            System.out.println("8. Gestionar Ventas");
            System.out.println("9. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    manejarClientes(scanner);
                    break;
                case 2:
                    manejarClientesPotenciales(scanner);
                    break;
                case 3:
                    manejarDepartamentoServicios(scanner);
                    break;
                case 4:
                    manejarEmpleados(scanner);
                    break;
                case 5:
                    manejarInventarioCarros(scanner);
                    break;
                case 6:
                    manejarHistorialServicio(scanner);
                    break;
                case 7:
                    manejarProveedoresPiezas(scanner);
                    break;
                case 8:
                    manejarVentas(scanner);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

    private static void manejarClientes(Scanner scanner) {
        ClienteDAO dao = new ClienteDAO();
        while (true) {
            System.out.println("Gestión de Clientes:");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Leer Clientes");
            System.out.println("3. Actualizar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el primer apellido del cliente:");
                    String apellido1 = scanner.nextLine();
                    System.out.println("Ingrese el segundo apellido del cliente:");
                    String apellido2 = scanner.nextLine();
                    System.out.println("Ingrese el documento del cliente:");
                    String documento = scanner.nextLine();
                    System.out.println("Ingrese la ciudad del cliente:");
                    String ciudad = scanner.nextLine();
                    dao.insertarCliente(nombre, apellido1, apellido2, documento, ciudad);
                    break;
                case 2:
                    dao.obtenerClientes();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del cliente a actualizar:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el nuevo nombre del cliente:");
                    nombre = scanner.nextLine();
                    System.out.println("Ingrese el nuevo primer apellido del cliente:");
                    apellido1 = scanner.nextLine();
                    System.out.println("Ingrese el nuevo segundo apellido del cliente:");
                    apellido2 = scanner.nextLine();
                    System.out.println("Ingrese el nuevo documento del cliente:");
                    documento = scanner.nextLine();
                    System.out.println("Ingrese la nueva ciudad del cliente:");
                    ciudad = scanner.nextLine();
                    dao.actualizarCliente(id, nombre, apellido1, apellido2, documento, ciudad);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del cliente a eliminar:");
                    id = scanner.nextInt();
                    dao.eliminarCliente(id);
                    break;
                case 5:
                    dao.cerrarConexion();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

    private static void manejarClientesPotenciales(Scanner scanner) {
        ClientesPotencialesDAO dao = new ClientesPotencialesDAO();
        while (true) {
            System.out.println("Gestión de Clientes Potenciales:");
            System.out.println("1. Crear Cliente Potencial");
            System.out.println("2. Leer Clientes Potenciales");
            System.out.println("3. Actualizar Cliente Potencial");
            System.out.println("4. Eliminar Cliente Potencial");
            System.out.println("5. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del cliente:");
                    int idCliente = scanner.nextInt();
                    System.out.println("Ingrese el ID del carro:");
                    int idCarro = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                    String fecha = scanner.nextLine();
                    System.out.println("Ingrese la descripción:");
                    String descripcion = scanner.nextLine();
                    dao.insertarClientePotencial(idCliente, idCarro, fecha, descripcion);
                    break;
                case 2:
                    dao.obtenerClientesPotenciales();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del cliente potencial a actualizar:");
                    int id = scanner.nextInt();
                    System.out.println("Ingrese el nuevo ID del cliente:");
                    idCliente = scanner.nextInt();
                    System.out.println("Ingrese el nuevo ID del carro:");
                    idCarro = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese la nueva fecha (YYYY-MM-DD):");
                    fecha = scanner.nextLine();
                    System.out.println("Ingrese la nueva descripción:");
                    descripcion = scanner.nextLine();
                    dao.actualizarClientePotencial(id, idCliente, idCarro, fecha, descripcion);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del cliente potencial a eliminar:");
                    id = scanner.nextInt();
                    dao.eliminarClientePotencial(id);
                    break;
                case 5:
                    dao.cerrarConexion();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

    private static void manejarDepartamentoServicios(Scanner scanner) {
        DepartamentoServiciosDAO dao = new DepartamentoServiciosDAO();
        while (true) {
            System.out.println("Gestión de Departamento de Servicios:");
            System.out.println("1. Crear Departamento de Servicios");
            System.out.println("2. Leer Departamento de Servicios");
            System.out.println("3. Actualizar Departamento de Servicios");
            System.out.println("4. Eliminar Departamento de Servicios");
            System.out.println("5. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ID del historial:");
                    int idHistorial = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el horario del servicio:");
                    String horario = scanner.nextLine();
                    System.out.println("Ingrese los servicios:");
                    String servicios = scanner.nextLine();
                    dao.insertarDepartamentoServicios(idHistorial, horario, servicios);
                    break;
                case 2:
                    dao.obtenerDepartamentoServicios();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del departamento de servicios a actualizar:");
                    int id = scanner.nextInt();
                    System.out.println("Ingrese el nuevo ID del historial:");
                    idHistorial = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el nuevo horario del servicio:");
                    horario = scanner.nextLine();
                    System.out.println("Ingrese los nuevos servicios:");
                    servicios = scanner.nextLine();
                    dao.actualizarDepartamentoServicios(id, idHistorial, horario, servicios);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del departamento de servicios a eliminar:");
                    id = scanner.nextInt();
                    dao.eliminarDepartamentoServicios(id);
                    break;
                case 5:
                    dao.cerrarConexion();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

    private static void manejarEmpleados(Scanner scanner) {
        EmpleadosDAO dao = new EmpleadosDAO();
        while (true) {
            System.out.println("Gestión de Empleados:");
            System.out.println("1. Crear Empleado");
            System.out.println("2. Leer Empleados");
            System.out.println("3. Actualizar Empleado");
            System.out.println("4. Eliminar Empleado");
            System.out.println("5. Volver al menú principal");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del empleado:");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el primer apellido del empleado:");
                    String apellido1 = scanner.nextLine();
                    System.out.println("Ingrese el segundo apellido del empleado:");
                    String apellido2 = scanner.nextLine();
                    System.out.println("Ingrese el documento del empleado:");
                    String documento = scanner.nextLine();
                    System.out.println("Ingrese el rol del empleado (Ej: ADMIN, USER):");
                    String rol = scanner.nextLine();
                    dao.insertarEmpleado(nombre, apellido1, apellido2, documento, rol);
                    break;
                case 2:
                    dao.obtenerEmpleados();
                    break;
                case 3:
                    System.out.println("Ingrese el ID del empleado a actualizar:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese el nuevo nombre del empleado:");
                    nombre = scanner.nextLine();
                    System.out.println("Ingrese el nuevo primer apellido del empleado:");
                    apellido1 = scanner.nextLine();
                    System.out.println("Ingrese el nuevo segundo apellido del empleado:");
                    apellido2 = scanner.nextLine();
                    System.out.println("Ingrese el nuevo documento del empleado:");
                    documento = scanner.nextLine();
                    System.out.println("Ingrese el nuevo rol del empleado (Ej: ADMIN, USER):");
                    rol = scanner.nextLine();
                    dao.actualizarEmpleado(id, nombre, apellido1, apellido2, documento, rol);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del empleado a eliminar:");
                    id = scanner.nextInt();
                    dao.eliminarEmpleado(id);
                    break;
                case 5:
                    dao.cerrarConexion();
                    return;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

private static void manejarInventarioCarros(Scanner scanner) {
    InventarioCarrosDAO dao = new InventarioCarrosDAO();
    while (true) {
        System.out.println("Gestión de Inventario de Carros:");
        System.out.println("1. Crear Carro");
        System.out.println("2. Leer Carros");
        System.out.println("3. Actualizar Carro");
        System.out.println("4. Eliminar Carro");
        System.out.println("5. Volver al menú principal");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el modelo del carro:");
                String modelo = scanner.nextLine();
                System.out.println("Ingrese la marca del carro:");
                String marca = scanner.nextLine();
                System.out.println("Ingrese el año del carro:");
                int ano = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el color del carro:");
                String color = scanner.nextLine();
                dao.insertarCarro(modelo, marca, ano, color);
                break;
            case 2:
                dao.obtenerCarros();
                break;
            case 3:
                System.out.println("Ingrese el ID del carro a actualizar:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo modelo del carro:");
                String nuevoModelo = scanner.nextLine();
                System.out.println("Ingrese la nueva marca del carro:");
                String nuevaMarca = scanner.nextLine();
                System.out.println("Ingrese el nuevo año del carro:");
                int nuevoAno = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo color del carro:");
                String nuevoColor = scanner.nextLine();
                dao.actualizarCarro(id, nuevoModelo, nuevaMarca, nuevoAno, nuevoColor);
                break;
            case 4:
                System.out.println("Ingrese el ID del carro a eliminar:");
                id = scanner.nextInt();
                dao.eliminarCarro(id);
                break;
            case 5:
                dao.cerrarConexion();
                return;
            default:
                System.out.println("Opción inválida. Inténtelo de nuevo.");
        }
    }
}
private static void manejarHistorialServicio(Scanner scanner) {
    HistorialServicioDAO dao = new HistorialServicioDAO();
    while (true) {
        System.out.println("Gestión de Historial de Servicios:");
        System.out.println("1. Crear Historial de Servicio");
        System.out.println("2. Leer Historial de Servicios");
        System.out.println("3. Actualizar Historial de Servicio");
        System.out.println("4. Eliminar Historial de Servicio");
        System.out.println("5. Volver al menú principal");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el ID del carro:");
                int idCarro = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                String fecha = scanner.nextLine();
                System.out.println("Ingrese la descripción del servicio:");
                String descripcion = scanner.nextLine();
                dao.insertarHistorialServicio(idCarro, fecha, descripcion);
                break;
            case 2:
                dao.obtenerHistorialServicio();
                break;
            case 3:
                System.out.println("Ingrese el ID del historial de servicio a actualizar:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo ID del carro:");
                idCarro = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese la nueva fecha (YYYY-MM-DD):");
                fecha = scanner.nextLine();
                System.out.println("Ingrese la nueva descripción del servicio:");
                descripcion = scanner.nextLine();
                dao.actualizarHistorialServicio(id, idCarro, fecha, descripcion);
                break;
            case 4:
                System.out.println("Ingrese el ID del historial de servicio a eliminar:");
                id = scanner.nextInt();
                dao.eliminarHistorialServicio(id);
                break;
            case 5:
                dao.cerrarConexion();
                return;
            default:
                System.out.println("Opción inválida. Inténtelo de nuevo.");
        }
    }
}


 private static void manejarProveedoresPiezas(Scanner scanner) {
    ProveedoresPiezasDAO dao = new ProveedoresPiezasDAO();
    while (true) {
        System.out.println("Gestión de Proveedores de Piezas:");
        System.out.println("1. Crear Proveedor de Piezas");
        System.out.println("2. Leer Proveedores de Piezas");
        System.out.println("3. Actualizar Proveedor de Piezas");
        System.out.println("4. Eliminar Proveedor de Piezas");
        System.out.println("5. Volver al menú principal");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre del proveedor:");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese el contacto del proveedor:");
                String contacto = scanner.nextLine();
                dao.insertarProveedorPieza(nombre, contacto);
                break;
            case 2:
                dao.obtenerProveedoresPiezas();
                break;
            case 3:
                System.out.println("Ingrese el ID del proveedor a actualizar:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo nombre del proveedor:");
                nombre = scanner.nextLine();
                System.out.println("Ingrese el nuevo contacto del proveedor:");
                contacto = scanner.nextLine();
                dao.actualizarProveedorPieza(id, nombre, contacto);
                break;
            case 4:
                System.out.println("Ingrese el ID del proveedor a eliminar:");
                id = scanner.nextInt();
                dao.eliminarProveedorPieza(id);
                break;
            case 5:
                dao.cerrarConexion();
                return;
            default:
                System.out.println("Opción inválida. Inténtelo de nuevo.");
        }
    }
}


private static void manejarVentas(Scanner scanner) {
    VentasDAO dao = new VentasDAO();
    while (true) {
        System.out.println("Gestión de Ventas:");
        System.out.println("1. Crear Venta");
        System.out.println("2. Leer Ventas");
        System.out.println("3. Actualizar Venta");
        System.out.println("4. Eliminar Venta");
        System.out.println("5. Volver al menú principal");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el ID del carro:");
                int idCarro = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el ID del cliente:");
                int idCliente = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese la fecha (YYYY-MM-DD):");
                String fecha = scanner.nextLine();
                System.out.println("Ingrese el precio de venta:");
                double precio = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea
                dao.insertarVenta(idCarro, idCliente, fecha, precio);
                break;
            case 2:
                dao.obtenerVentas();
                break;
            case 3:
                System.out.println("Ingrese el ID de la venta a actualizar:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese el nuevo ID del carro:");
                idCarro = scanner.nextInt();
                System.out.println("Ingrese el nuevo ID del cliente:");
                idCliente = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                System.out.println("Ingrese la nueva fecha (YYYY-MM-DD):");
                fecha = scanner.nextLine();
                System.out.println("Ingrese el nuevo precio de venta:");
                precio = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea
                dao.actualizarVenta(id, idCarro, idCliente, fecha, precio);
                break;
            case 4:
                System.out.println("Ingrese el ID de la venta a eliminar:");
                id = scanner.nextInt();
                dao.eliminarVenta(id);
                break;
            case 5:
                dao.cerrarConexion();
                return;
            default:
                System.out.println("Opción inválida. Inténtelo de nuevo.");
        }
    }
}
}
