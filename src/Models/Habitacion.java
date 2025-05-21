package Models;

import java.time.LocalDate;
import java.util.List;

public class Habitacion {
    private String codigo;
    private int piso;
    private double precio;
    private boolean aireAcondicionado;
    private boolean wifi;
    private String tipo; // "Estandar" or "Vip"

    public Habitacion(String codigo, int piso, double precio, boolean aireAcondicionado, boolean wifi, String tipo) {
        this.codigo = codigo;
        this.piso = piso;
        this.precio = precio;
        this.aireAcondicionado = aireAcondicionado;
        this.wifi = wifi;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean esDisponible(LocalDate fechaInicio, LocalDate fechaFin, List<Reserva> reservasExistentes) {
        for (Reserva reserva : reservasExistentes) {
            if (reserva.getHabitacion().equals(this) && reserva.seSolapan(fechaInicio, fechaFin)) {
                System.out.println("Habitación " + codigo + " no disponible en el rango de fechas: " + fechaInicio + " - " + fechaFin);
                return false;
            }
        }
        System.out.println("Habitación " + codigo + " disponible en el rango de fechas: " + fechaInicio + " - " + fechaFin);
        return true;
    }

    public double calcularPrecio(int dias) {
        return precio * dias;
    }
}
