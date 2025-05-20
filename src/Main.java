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

        Habitacion habitacion = null;
        if (tipoHabitacion.equalsIgnoreCase("Estandar")) {
            System.out.println("Ingrese el código de la habitación: ");
            String codigo = scanner.nextLine();
            System.out.println("Ingrese el piso: ");
            int piso = scanner.nextInt();
            System.out.println("Ingrese el precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.println("¿Tiene aire acondicionado? (si/no): ");
            boolean aireAcondicionado = convertirBoolean(scanner.nextLine());
            System.out.println("¿Tiene wifi? (si/no): ");
            boolean wifi = convertirBoolean(scanner.nextLine());
            System.out.println("¿Tiene TV? (si/no): ");
            boolean tv = convertirBoolean(scanner.nextLine());
            System.out.println("¿Incluye desayuno? (si/no): ");
            boolean desayuno = convertirBoolean(scanner.nextLine());

            habitacion = new Estandar(codigo, piso, precio, aireAcondicionado, wifi, 2, 2, tv, desayuno);
        } else if (tipoHabitacion.equalsIgnoreCase("VIP")) {
            System.out.println("Ingrese el código de la habitación: ");
            String codigo = scanner.nextLine();
            System.out.println("Ingrese el piso: ");
            int piso = scanner.nextInt();
            System.out.println("Ingrese el precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.println("¿Tiene aire acondicionado? (si/no): ");
            boolean aireAcondicionado = convertirBoolean(scanner.nextLine());
            System.out.println("¿Tiene wifi? (si/no): ");
            boolean wifi = convertirBoolean(scanner.nextLine());

            // Selección del tipo de camas
            String tipoCamas = "";
            while (true) {
                System.out.println("Seleccione el tipo de camas (ingrese el número): ");
                System.out.println("1. matrimonial");
                System.out.println("2. litera");
                System.out.println("3. simple");
                System.out.println("4. de agua");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (opcion) {
                    case 1 -> tipoCamas = "matrimonial";
                    case 2 -> tipoCamas = "litera";
                    case 3 -> tipoCamas = "simple";
                    case 4 -> tipoCamas = "de agua";
                    default -> {
                        System.out.println("Opción no válida. Intente de nuevo.");
                        continue;
                    }
                }
                break;
            }

            System.out.println("¿Tiene TV con streaming premium? (si/no): ");
            boolean tvStreamingPremium = convertirBoolean(scanner.nextLine());
            System.out.println("¿Tiene jacuzzi? (si/no): ");
            boolean jacuzzi = convertirBoolean(scanner.nextLine());
            System.out.println("¿Incluye servicio a la habitación? (si/no): ");
            boolean servicioHabitacion = convertirBoolean(scanner.nextLine());

            habitacion = new Vip(codigo, piso, precio, aireAcondicionado, wifi, tipoCamas, 0, 0, 0, tvStreamingPremium, jacuzzi, servicioHabitacion);
        } else {
            System.out.println("Tipo de habitación no válido.");
            return;
        }

        // Agregar habitación al hotel
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(habitacion);
        hotel.setListaHabitaciones(habitaciones);

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

    private static boolean convertirBoolean(String respuesta) {
        return respuesta.equalsIgnoreCase("si");
    }
}
