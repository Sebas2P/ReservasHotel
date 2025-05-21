import Models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear hotel
        Hotel hotel = new Hotel("Hotel Paradise", "Calle Principal 123", "555-1234");

        // Crear habitaciones dinámicamente
        System.out.println("Ingrese el tipo de habitación a reservar (Estandar/VIP): ");
        String tipoHabitacion = scanner.nextLine();

        if (hotel.getListaHabitaciones().isEmpty()) {
            System.out.println("No hay habitaciones creadas en el hotel. Por favor, cree habitaciones antes de continuar.");
            return;
        }

        Habitacion habitacion = null;
        for (Habitacion h : hotel.getListaHabitaciones()) {
            if (h.getTipo().equalsIgnoreCase(tipoHabitacion)) {
                habitacion = h;
                break;
            }
        }

        if (habitacion == null) {
            System.out.println("No hay habitaciones disponibles del tipo solicitado: " + tipoHabitacion);
            return;
        }

        // Crear huésped
        System.out.println("Ingrese la cédula del huésped: ");
        String cedula = scanner.nextLine();
        System.out.println("Ingrese el nombre del huésped: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el teléfono del huésped: ");
        String telefono = scanner.nextLine();
        Huesped huesped = new Huesped(cedula, nombre, telefono);

        // Ingresar fechas de reserva
        String fechaInicio, fechaFin;
        while (true) {
            System.out.println("Ingrese la fecha de ingreso (yyyy-MM-dd): ");
            fechaInicio = scanner.nextLine();
            System.out.println("Ingrese la fecha de salida (yyyy-MM-dd): ");
            fechaFin = scanner.nextLine();
            if (Reserva.validarFechas(fechaInicio, fechaFin)) {
                break;
            } else {
                System.out.println("La fecha de salida debe ser posterior o igual a la fecha de ingreso. Intente nuevamente.");
            }
        }

        // Crear reserva
        Reserva reserva = hotel.crearReserva(fechaInicio, fechaFin, habitacion.getTipo(), huesped, 2);

        if (reserva != null) {
            System.out.println("Reserva creada con éxito:");
            System.out.println("Huésped: " + huesped.getNombre());
            System.out.println("Habitación: " + reserva.getHabitacion().getCodigo());
            System.out.println("Fecha de ingreso: " + reserva.getFechaInicio());
            System.out.println("Fecha de salida: " + reserva.getFechaFin());
            System.out.println("Precio total: $" + reserva.getHabitacion().calcularPrecio(reserva.calcularDuracion()));
        } else {
            System.out.println("No hay habitaciones disponibles.");
        }
    }
}
