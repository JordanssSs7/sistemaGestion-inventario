package dao;

import model.Producto;
import model.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> obtenerTodos() {
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT ID_PRODUCTO, NOMBRE, STOCK_ACTUAL, PRECIO_UNITARIO FROM PRODUCTOS ORDER BY ID_PRODUCTO";

        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("ID_PRODUCTO");
                String nombre = rs.getString("NOMBRE");
                int stock = rs.getInt("STOCK_ACTUAL");
                double precio = rs.getDouble("PRECIO_UNITARIO");

                Producto p = new Producto(id, nombre, stock, precio);
                listaProductos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("ERROR DAO: Falló la consulta SELECT.");
            e.printStackTrace();
        }
        return listaProductos;
    }

    public boolean insertar(Producto producto) {

        String sql = "INSERT INTO PRODUCTOS (ID_PRODUCTO, NOMBRE, STOCK_ACTUAL, PRECIO_UNITARIO) " +
                "VALUES (SEQ_PRODUCTOS.NEXTVAL, ?, ?, ?)";


        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {


            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getStockActual());
            ps.setDouble(3, producto.getPrecioUnitario());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("ERROR DAO: Falló la inserción del producto.");
            e.printStackTrace();
            return false;
        }
    }
}