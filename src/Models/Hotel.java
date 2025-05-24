package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String nombre;
    private String direccion;
    private String telefono;

    private List<Habitacion> listaHabitaciones = new ArrayList<>();
    private List<Reserva> listaReservas = new ArrayList<>();

    public Hotel(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Hotel() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Reserva crearReserva(String fechaInicio, String fechaFin, String tipoHabitacion, Huesped huesped, int numPersonas) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        System.out.println("Buscando habitaciones disponibles del tipo: " + tipoHabitacion);
        for (Habitacion habitacion : listaHabitaciones) {
            System.out.println("Verificando habitación: " + habitacion.getCodigo() + " de tipo: " + habitacion.getTipo());
            if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion) && habitacion.esDisponible(inicio, fin, listaReservas)) {
                System.out.println("Habitación disponible encontrada: " + habitacion.getCodigo());
                Reserva reserva = new Reserva(fechaInicio, fechaFin, "12:00", huesped, numPersonas, habitacion);
                listaReservas.add(reserva);
                Habitacion.registrarReserva(reserva); // Registrar la reserva en Disponibilidad
                System.out.println("Reserva creada con éxito para la habitación: " + habitacion.getCodigo());
                return reserva;
            }
        }
        System.out.println("No se encontraron habitaciones disponibles del tipo: " + tipoHabitacion);
        return null; // No hay habitaciones disponibles
    }

    public boolean consultarDisponibilidad(String fechaInicio, String fechaFin, String tipoHabitacion) {
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        for (Habitacion habitacion : listaHabitaciones) {
            if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion) && habitacion.esDisponible(inicio, fin, listaReservas)) {
                return true; // Hay al menos una habitación disponible
            }
        }
        return false; // No hay habitaciones disponibles
    }

    public List<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void setListaHabitaciones(List<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(List<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }
}
