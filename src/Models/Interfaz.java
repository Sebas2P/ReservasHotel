package Models;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class Interfaz extends JFrame {
    private Hotel hotel;

    public Interfaz(Hotel hotel) {
        this.hotel = hotel;
        setTitle("Sistema de Reservas de Hotel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Reserva Estándar
        JPanel panelEstandar = crearPanelReserva("Estandar");
        tabbedPane.addTab("Reserva Estándar", panelEstandar);

        // Reserva VIP
        JPanel panelVip = crearPanelReserva("VIP");
        tabbedPane.addTab("Reserva VIP", panelVip);

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelReserva(String tipoHabitacion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton btnCrearReserva = new JButton("Crear Reserva");
        panel.add(btnCrearReserva, BorderLayout.CENTER);

        btnCrearReserva.addActionListener(e -> {
            if (!hayHabitacionesDisponibles(tipoHabitacion)) {
                JOptionPane.showMessageDialog(this, "No hay habitaciones disponibles del tipo " + tipoHabitacion + ". Por favor, cree habitaciones antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            abrirDialogoReserva(tipoHabitacion);
        });

        return panel;
    }

    private boolean hayHabitacionesDisponibles(String tipoHabitacion) {
        for (Habitacion habitacion : hotel.getListaHabitaciones()) {
            if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion)) {
                return true;
            }
        }
        return false;
    }

    private void abrirDialogoReserva(String tipoHabitacion) {
        JDialog dialogo = new JDialog(this, "Seleccionar Fechas", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new GridLayout(3, 2));

        JLabel lblFechaInicio = new JLabel("Fecha de Ingreso:");
        JDateChooser dateChooserInicio = new JDateChooser();
        JLabel lblFechaFin = new JLabel("Fecha de Salida:");
        JDateChooser dateChooserFin = new JDateChooser();

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            LocalDate fechaInicio = LocalDate.of(
                    dateChooserInicio.getDate().getYear() + 1900,
                    dateChooserInicio.getDate().getMonth() + 1,
                    dateChooserInicio.getDate().getDate()
            );
            LocalDate fechaFin = LocalDate.of(
                    dateChooserFin.getDate().getYear() + 1900,
                    dateChooserFin.getDate().getMonth() + 1,
                    dateChooserFin.getDate().getDate()
            );

            if (fechaFin.isBefore(fechaInicio)) {
                JOptionPane.showMessageDialog(dialogo, "La fecha de salida debe ser posterior a la fecha de ingreso.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dialogo.dispose();
                abrirDialogoServicios(tipoHabitacion, fechaInicio, fechaFin);
            }
        });

        dialogo.add(lblFechaInicio);
        dialogo.add(dateChooserInicio);
        dialogo.add(lblFechaFin);
        dialogo.add(dateChooserFin);
        dialogo.add(new JLabel()); // Empty space
        dialogo.add(btnSiguiente);

        dialogo.setVisible(true);
    }

    private void abrirDialogoServicios(String tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        JDialog dialogo = new JDialog(this, "Seleccionar Servicios", true);
        dialogo.setSize(400, 400);
        dialogo.setLayout(new GridLayout(0, 1));

        HashMap<String, Boolean> serviciosSeleccionados = new HashMap<>();
        JCheckBox cbTV = new JCheckBox("TV");
        JCheckBox cbDesayuno = new JCheckBox("Desayuno");
        JCheckBox cbAireAcondicionado = new JCheckBox("Aire Acondicionado");
        JCheckBox cbWifi = new JCheckBox("WiFi");

        dialogo.add(cbTV);
        dialogo.add(cbDesayuno);
        dialogo.add(cbAireAcondicionado);
        dialogo.add(cbWifi);

        if (tipoHabitacion.equals("VIP")) {
            JCheckBox cbJacuzzi = new JCheckBox("Jacuzzi");
            JCheckBox cbMinibar = new JCheckBox("Minibar");
            JCheckBox cbServicioHabitacion = new JCheckBox("Servicio a la Habitación");
            JCheckBox cbStreamingPremium = new JCheckBox("Streaming Premium");

            dialogo.add(cbJacuzzi);
            dialogo.add(cbMinibar);
            dialogo.add(cbServicioHabitacion);
            dialogo.add(cbStreamingPremium);

            serviciosSeleccionados.put("Jacuzzi", false);
            serviciosSeleccionados.put("Minibar", false);
            serviciosSeleccionados.put("Servicio a la Habitación", false);
            serviciosSeleccionados.put("Streaming Premium", false);

            cbJacuzzi.addActionListener(e -> serviciosSeleccionados.put("Jacuzzi", cbJacuzzi.isSelected()));
            cbMinibar.addActionListener(e -> serviciosSeleccionados.put("Minibar", cbMinibar.isSelected()));
            cbServicioHabitacion.addActionListener(e -> serviciosSeleccionados.put("Servicio a la Habitación", cbServicioHabitacion.isSelected()));
            cbStreamingPremium.addActionListener(e -> serviciosSeleccionados.put("Streaming Premium", cbStreamingPremium.isSelected()));
        }

        serviciosSeleccionados.put("TV", false);
        serviciosSeleccionados.put("Desayuno", false);
        serviciosSeleccionados.put("Aire Acondicionado", false);
        serviciosSeleccionados.put("WiFi", false);

        cbTV.addActionListener(e -> serviciosSeleccionados.put("TV", cbTV.isSelected()));
        cbDesayuno.addActionListener(e -> serviciosSeleccionados.put("Desayuno", cbDesayuno.isSelected()));
        cbAireAcondicionado.addActionListener(e -> serviciosSeleccionados.put("Aire Acondicionado", cbAireAcondicionado.isSelected()));
        cbWifi.addActionListener(e -> serviciosSeleccionados.put("WiFi", cbWifi.isSelected()));

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            dialogo.dispose();
            abrirDialogoHuesped(tipoHabitacion, fechaInicio, fechaFin, serviciosSeleccionados);
        });

        dialogo.add(btnSiguiente);
        dialogo.setVisible(true);
    }

    private void abrirDialogoHuesped(String tipoHabitacion, LocalDate fechaInicio, LocalDate fechaFin, HashMap<String, Boolean> serviciosSeleccionados) {
        JDialog dialogo = new JDialog(this, "Datos del Huésped", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new GridLayout(4, 2));

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JButton btnFinalizar = new JButton("Finalizar Reserva");
        btnFinalizar.addActionListener(e -> {
            String cedula = txtCedula.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();

            if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!hotel.consultarDisponibilidad(fechaInicio.toString(), fechaFin.toString(), tipoHabitacion)) {
                JOptionPane.showMessageDialog(dialogo, "No hay habitaciones disponibles para las fechas seleccionadas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Huesped huesped = new Huesped(cedula, nombre, telefono);
            Reserva reserva = hotel.crearReserva(fechaInicio.toString(), fechaFin.toString(), tipoHabitacion, huesped, 1);

            if (reserva != null) {
                JOptionPane.showMessageDialog(dialogo, "Reserva creada con éxito.\nTotal a pagar: $" + reserva.getHabitacion().calcularPrecio(reserva.calcularDuracion()), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialogo, "No se pudo crear la reserva. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            dialogo.dispose();
        });

        dialogo.add(lblCedula);
        dialogo.add(txtCedula);
        dialogo.add(lblNombre);
        dialogo.add(txtNombre);
        dialogo.add(lblTelefono);
        dialogo.add(txtTelefono);
        dialogo.add(new JLabel()); // Empty space
        dialogo.add(btnFinalizar);

        dialogo.setVisible(true);
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Hotel Paradise", "Calle Principal 123", "555-1234");
        Interfaz interfaz = new Interfaz(hotel);
        interfaz.setVisible(true);
    }
}
