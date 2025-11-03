package app;

import dao.ProductoDAO;
import model.Producto;
import model.DatabaseConnection;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final ProductoDAO productoDAO = new ProductoDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion = 0;

        do {
            mostrarMenu();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada no válida. Ingrese un número.");
                scanner.nextLine();
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    mostrarInventario();
                    break;
                case 2:
                    insertarNuevoProducto();
                    break;
                case 0:
                    System.out.println("Saliendo del Sistema Básico de Gestión de Inventario...");
                    break;
                default:
                    System.out.println("Opción no reconocida. Intente de nuevo.");
            }

        } while (opcion != 0);

        DatabaseConnection.closeConnection();
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU DE INVENTARIO (SBGI) ---");
        System.out.println("1. Ver Inventario Completo");
        System.out.println("2. Agregar Nuevo Producto");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarInventario() {
        System.out.println("\n--- INVENTARIO DE PRODUCTOS ---");
        List<Producto> inventario = productoDAO.obtenerTodos();

        if (inventario.isEmpty()) {
            System.out.println("No hay productos registrados en el inventario.");
        } else {
            System.out.println("===============================================================");
            System.out.printf("%-5s | %-30s | %-10s | %s%n", "ID", "NOMBRE", "STOCK", "PRECIO UNITARIO");
            System.out.println("---------------------------------------------------------------");
            for (Producto p : inventario) {
                System.out.println(p); // Usa el método toString() de Producto
            }
            System.out.println("===============================================================");
        }
    }

    private static void insertarNuevoProducto() {
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");

        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Stock Inicial: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consumir línea

        System.out.print("Precio Unitario: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir línea

        Producto nuevoProducto = new Producto(nombre, stock, precio);

        boolean insertado = productoDAO.insertar(nuevoProducto);

        if (insertado) {
            System.out.println("Producto agregado con éxito!");
        } else {
            System.out.println("ERROR al intentar agregar el producto.");
        }
    }
}