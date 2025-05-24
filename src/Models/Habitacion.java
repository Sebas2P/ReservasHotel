package Models;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Habitacion {
    private String codigo;
    private int piso;
    private double precio;
    private boolean aireAcondicionado;
    private boolean wifi;
    private String tipo; // "Estandar" or "Vip"

    private static Disponibilidad disponibilidad = new Disponibilidad();

    public Habitacion(String codigo, int piso, double precio, boolean aireAcondicionado, boolean wifi, String tipo) {
        this.codigo = codigo;
        this.piso = piso;
        this.precio = precio;
        this.aireAcondicionado = aireAcondicionado;
        this.wifi = wifi;
        this.tipo = tipo;
    }

    public void eleminarHabitacion(String codigo) {
        List<Habitacion> habitaciones = new ArrayList<>();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCodigo().equals(codigo)) {
                habitaciones.remove(habitacion);
                break;
            }
        }
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

    public boolean esDisponible(LocalDate fechaInicio, LocalDate fechaFin, List<Reserva> listaReservas) {
        return disponibilidad.esDisponible(this, fechaInicio, fechaFin);
    }

    public static void registrarReserva(Reserva reserva) {
        disponibilidad.agregarReserva(reserva);
    }

    public double calcularPrecio(int dias) {
        return precio * dias;
    }
}
