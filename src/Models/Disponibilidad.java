package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Disponibilidad {
    private List<Reserva> reservasEstandar = new ArrayList<>();
    private List<Reserva> reservasVip = new ArrayList<>();

    public boolean esDisponible(Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = habitacion.getTipo().equalsIgnoreCase("Estandar") ? reservasEstandar : reservasVip;

        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().equals(habitacion) && !fechaFin.isBefore(LocalDate.parse(reserva.getFechaInicio())) && !fechaInicio.isAfter(LocalDate.parse(reserva.getFechaFin()))) {
                return false; // La habitación está ocupada en las fechas solicitadas
            }
        }
        return true; // La habitación está disponible
    }

    public void agregarReserva(Reserva reserva) {
        // Asegurar que la reserva se agrega a la lista correcta
        if (reserva.getHabitacion().getTipo().equalsIgnoreCase("Estandar")) {
            reservasEstandar.add(reserva);
        } else if (reserva.getHabitacion().getTipo().equalsIgnoreCase("VIP")) {
            reservasVip.add(reserva);
        }
    }
}
