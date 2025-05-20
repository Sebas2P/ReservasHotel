package Models;

public class Habitacion{
    private String codigo;
    private int piso;
    private double precio;
    private boolean disponible;
    private boolean aireAcondicionado;
    private boolean wifi;
    private boolean bañoPrivado;

    public Habitacion(String codigo, int piso, double precio, boolean disponible, boolean aireAcondicionado, boolean wifi) {
        this.codigo = codigo;
        this.piso = piso;
        this.precio = precio;
        this.disponible = disponible;
        this.aireAcondicionado = aireAcondicionado;
        this.wifi = wifi;
    }

    public Habitacion() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public double calcularPrecio(int dias) {
        double precioFinal = getPrecio() * dias; // Precio base por los días de estancia
        return precioFinal;
    }



}
