package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {


    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USER = "Rambito";
    private static final String PASSWORD = "Melocoton";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                if (connection != null && !connection.isClosed()) {
                    closeConnection();
                    System.out.println("Conexión no válida/expirada. Recreando...");
                } else {
                    System.out.println("Creando nueva conexión a Oracle...");
                }

                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connection.setAutoCommit(true);
                System.out.println("Conexión creada exitosamente.");
            } else {
                System.out.println("Reutilizando conexión existente.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: Driver no encontrado. Verifique ojdbc.jar.");
            mostrarError("Driver no encontrado.", e);
            connection = null;
        } catch (SQLException e) {
            System.err.println("ERROR: Falló la conexión a la Base de Datos.");
            mostrarError("Error SQL: Verifique credenciales/servicio Oracle.", e);
            connection = null;
        }
        return connection;
    }

    public static void closeConnection() {
        // ... (método closeConnection completo de tu código anterior) ...
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexión cerrada");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }

    private static void mostrarError(String mensaje, Exception e) {
        JOptionPane.showMessageDialog(null,
                mensaje + "\nDetalle: " + e.getMessage(),
                "Error de Conexión a Oracle",
                JOptionPane.ERROR_MESSAGE);
    }
}