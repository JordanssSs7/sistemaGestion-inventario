package model;

public class Producto {

    private int idProducto;
    private String nombre;
    private int stockActual;
    private double precioUnitario;

    public Producto(int idProducto, String nombre, int stockActual, double precioUnitario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.precioUnitario = precioUnitario;
    }

    public Producto(String nombre, int stockActual, double precioUnitario) {
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.precioUnitario = precioUnitario;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public int getStockActual() { return stockActual; }
    public double getPrecioUnitario() { return precioUnitario; }

    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    @Override
    public String toString() {

        return String.format("%-5d | %-30s | %-10d | $%.2f",
                idProducto, nombre, stockActual, precioUnitario);
    }
}