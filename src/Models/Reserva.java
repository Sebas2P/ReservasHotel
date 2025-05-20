package Models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private String fechaInicio;
    private String fechaFin;
    private String hora;
    private Huesped huesped;
    private int numPersonas;
    private Habitacion habitacion;
    private double precioTotal;

    public Reserva(String fechaInicio, String fechaFin, String hora, Huesped huesped, int numPersonas, Habitacion habitacion) {
        if (!validarFechas(fechaInicio, fechaFin)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior o igual a la fecha de inicio.");
        }
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.hora = hora;
        this.huesped = huesped;
        this.numPersonas = numPersonas;
        this.habitacion = habitacion;
    }

    public Reserva() {
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int calcularDuracion() {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        return (int) ChronoUnit.DAYS.between(inicio, fin);
    }

    public boolean seSolapan(LocalDate inicio, LocalDate fin) {
        LocalDate reservaInicio = LocalDate.parse(fechaInicio);
        LocalDate reservaFin = LocalDate.parse(fechaFin);
        return !(fin.isBefore(reservaInicio) || inicio.isAfter(reservaFin));
    }

    public static boolean validarFechas(String fechaInicio, String fechaFin) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        return !fin.isBefore(inicio);
    }
}
