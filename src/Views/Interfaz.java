package Views;

import Models.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.ImageIcon;

public class Interfaz extends JFrame {
    private Hotel hotel;
    private JTable tablaHabitaciones;
    private DefaultTableModel modeloTablaHabitaciones;

    public Interfaz(Hotel hotel) {
        this.hotel = hotel;
        setTitle("Sistema de Reservas de Hotel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // Transparencia
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };

        // Nueva pestaña de bienvenida
        JPanel panelBienvenida = crearPanelBienvenida();
        tabbedPane.addTab("Bienvenida", panelBienvenida);

        // Reordenar las pestañas
        JPanel panelCrearHabitaciones = crearPanelCrearHabitaciones();
        tabbedPane.addTab("Crear Habitaciones", panelCrearHabitaciones);

        JPanel panelReservas = crearPanelReservas();
        tabbedPane.addTab("Reservas", panelReservas);

        // Eliminar la pestaña "Mostrar Reserva"

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelBienvenida() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("C:\\Users\\ariel\\OneDrive - Universidad de Las Américas\\Desktop\\Pura basura\\U\\Cuarto Semestre\\Programacion 2\\Reserva\\53420926.jpg");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(new BorderLayout());

        // Etiqueta para el nombre del hotel
        JLabel lblNombreHotel = new JLabel(hotel.getNombre(), SwingConstants.CENTER);
        lblNombreHotel.setFont(new Font("Serif", Font.BOLD, 36));
        lblNombreHotel.setForeground(Color.WHITE);

        // Panel semitransparente para teléfono y dirección
        JPanel panelInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Transparencia
                g2d.setColor(Color.black);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        panelInfo.setLayout(new GridLayout(2, 1));
        panelInfo.setOpaque(false);

        JLabel lblTelefono = new JLabel("Teléfono: " + hotel.getTelefono(), SwingConstants.CENTER);
        lblTelefono.setFont(new Font("Serif", Font.ITALIC, 20));
        lblTelefono.setForeground(Color.WHITE);

        JLabel lblDireccion = new JLabel("Dirección: " + hotel.getDireccion(), SwingConstants.CENTER);
        lblDireccion.setFont(new Font("Serif", Font.ITALIC, 20));
        lblDireccion.setForeground(Color.WHITE);

        panelInfo.add(lblTelefono);
        panelInfo.add(lblDireccion);

        // Agregar componentes al panel principal
        panel.add(lblNombreHotel, BorderLayout.NORTH);
        panel.add(panelInfo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelCrearHabitaciones() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(245, 245, 220)); // Color crema
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Mostrar directamente el formulario de creación
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(0, 2));

        JLabel lblTipo = new JLabel("Tipo de Habitación:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Estandar", "VIP"});
        JLabel lblCodigo = new JLabel("Código:");
        JTextField txtCodigo = new JTextField();
        JLabel lblPiso = new JLabel("Piso:");
        JTextField txtPiso = new JTextField();
        JLabel lblPrecio = new JLabel("Precio Base:");
        JTextField txtPrecio = new JTextField();

        // Campos adicionales para VIP
        JLabel lblNumCuartos = new JLabel("Número de Cuartos:");
        JTextField txtNumCuartos = new JTextField();
        JLabel lblNumTvs = new JLabel("Número de TVs:");
        JTextField txtNumTvs = new JTextField();

        // Ocultar campos adicionales por defecto
        lblNumCuartos.setVisible(false);
        txtNumCuartos.setVisible(false);
        lblNumTvs.setVisible(false);
        txtNumTvs.setVisible(false);

        // Mostrar/ocultar campos adicionales según el tipo de habitación
        comboTipo.addActionListener(e -> {
            boolean esVip = comboTipo.getSelectedItem().equals("VIP");
            lblNumCuartos.setVisible(esVip);
            txtNumCuartos.setVisible(esVip);
            lblNumTvs.setVisible(esVip);
            txtNumTvs.setVisible(esVip);
        });

        panelFormulario.add(lblTipo);
        panelFormulario.add(comboTipo);
        panelFormulario.add(lblCodigo);
        panelFormulario.add(txtCodigo);
        panelFormulario.add(lblPiso);
        panelFormulario.add(txtPiso);
        panelFormulario.add(lblPrecio);
        panelFormulario.add(txtPrecio);
        panelFormulario.add(lblNumCuartos);
        panelFormulario.add(txtNumCuartos);
        panelFormulario.add(lblNumTvs);
        panelFormulario.add(txtNumTvs);

        // Botón para agregar habitación
        JButton btnAgregarHabitacion = new JButton("Agregar Habitación");
        btnAgregarHabitacion.setBackground(new Color(245, 245, 220)); // Color beige
        btnAgregarHabitacion.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            String codigo = txtCodigo.getText().trim();
            String pisoStr = txtPiso.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (codigo.isEmpty() || pisoStr.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que piso y precio sean numéricos
            int piso;
            double precio;
            try {
                piso = Integer.parseInt(pisoStr);
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Piso y precio deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar código único
            for (Habitacion habitacion : hotel.getListaHabitaciones()) {
                if (habitacion.getCodigo().equals(codigo)) {
                    JOptionPane.showMessageDialog(this, "El código de habitación ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Crear habitación
            Habitacion habitacion;
            if (tipo.equals("Estandar")) {
                habitacion = new Estandar(codigo, piso, precio, false, false, 2, 2, false, false);
            } else {
                String numCuartosStr = txtNumCuartos.getText().trim();
                String numTvsStr = txtNumTvs.getText().trim();

                if (numCuartosStr.isEmpty() || numTvsStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Los campos de número de cuartos y TVs son obligatorios para habitaciones VIP.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validar que número de cuartos y TVs sean numéricos
                int numCuartos, numTvs;
                try {
                    numCuartos = Integer.parseInt(numCuartosStr);
                    numTvs = Integer.parseInt(numTvsStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Número de cuartos y TVs deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                habitacion = new Vip(codigo, piso, precio, false, false, "Matrimonial", 30.0, numCuartos, numTvs, false, false, false);
            }

            hotel.getListaHabitaciones().add(habitacion);
            JOptionPane.showMessageDialog(this, "Habitación agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar tabla
            actualizarTablaHabitaciones();

            // Limpiar campos
            txtCodigo.setText("");
            txtPiso.setText("");
            txtPrecio.setText("");
            txtNumCuartos.setText("");
            txtNumTvs.setText("");
        });

        // Botón para eliminar habitación
        JButton btnEliminarHabitacion = new JButton("Eliminar Habitación");
        btnEliminarHabitacion.setBackground(new Color(245, 245, 220)); // Color beige
        btnEliminarHabitacion.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código de la habitación a eliminar:", "Eliminar Habitación", JOptionPane.PLAIN_MESSAGE);

            if (codigo == null || codigo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código de habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Habitacion habitacionAEliminar = hotel.getListaHabitaciones().stream().filter(habitacion -> habitacion.getCodigo().equals(codigo.trim())).findFirst().orElse(null);

            if (habitacionAEliminar == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una habitación con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Eliminar la habitación y sus reservas asociadas
            hotel.getListaHabitaciones().remove(habitacionAEliminar);
            hotel.getListaReservas().removeIf(reserva -> reserva.getHabitacion().equals(habitacionAEliminar));
            JOptionPane.showMessageDialog(this, "Habitación eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar tabla
            actualizarTablaHabitaciones();
        });

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregarHabitacion);
        panelBotones.add(btnEliminarHabitacion);

        // Tabla para mostrar habitaciones
        modeloTablaHabitaciones = new DefaultTableModel(new String[]{"Código", "Tipo", "Piso", "Precio", "Disponible", "Num. TVs", "Num. Cuartos"}, 0);
        tablaHabitaciones = new JTable(modeloTablaHabitaciones) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 4) { // Columna "Disponible"
                    return new DisponibilidadCellRenderer();
                }
                return super.getCellRenderer(row, column);
            }
        };
        JScrollPane scrollTabla = new JScrollPane(tablaHabitaciones);

        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollTabla, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    // Clase personalizada para renderizar la celda de disponibilidad
    private static class DisponibilidadCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null && value.toString().equalsIgnoreCase("Sí")) {
                cell.setBackground(Color.GREEN);
                cell.setForeground(Color.BLACK);
            } else if (value != null && value.toString().equalsIgnoreCase("No")) {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
            } else {
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
            }
            return cell;
        }
    }

    private void actualizarTablaHabitaciones() {
        modeloTablaHabitaciones.setRowCount(0); // Limpiar tabla
        for (Habitacion habitacion : hotel.getListaHabitaciones()) {
            boolean disponible = true;
            for (Reserva reserva : hotel.getListaReservas()) {
                if (reserva.getHabitacion().equals(habitacion)) {
                    disponible = false;
                    break;
                }
            }

            int numTvs = 0;
            int numCuartos = 0;

            if (habitacion instanceof Estandar) {
                numTvs = 1; // Por defecto para estándar
                numCuartos = 2; // Por defecto para estándar
            } else if (habitacion instanceof Vip) {
                Vip vip = (Vip) habitacion;
                numTvs = vip.getNumTvs();
                numCuartos = vip.getNumCuartos();
            }

            modeloTablaHabitaciones.addRow(new Object[]{
                    habitacion.getCodigo(),
                    habitacion.getTipo(),
                    habitacion.getPiso(),
                    habitacion.getPrecio(),
                    disponible ? "Sí" : "No", // Actualizar disponibilidad
                    numTvs,
                    numCuartos
            });
        }
    }

    private JPanel crearPanelReservas() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(245, 245, 220)); // Color crema
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Selección del tipo de habitación
        JPanel panelSuperior = new JPanel();
        JLabel lblTipoHabitacion = new JLabel("Tipo de Habitación:");
        JComboBox<String> comboTipoHabitacion = new JComboBox<>(new String[]{"Estandar", "VIP"});
        panelSuperior.add(lblTipoHabitacion);
        panelSuperior.add(comboTipoHabitacion);

        // Botón para crear reserva
        JButton btnCrearReserva = new JButton("Crear Reserva");
        btnCrearReserva.setBackground(new Color(245, 245, 220)); // Color beige
        panelSuperior.add(btnCrearReserva);

        btnCrearReserva.addActionListener(e -> {
            String tipoHabitacion = (String) comboTipoHabitacion.getSelectedItem();
            if (!hayHabitacionesDisponibles(tipoHabitacion)) {
                JOptionPane.showMessageDialog(this, "No hay habitaciones disponibles del tipo " + tipoHabitacion + ". Por favor, cree habitaciones antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            abrirDialogoReserva(tipoHabitacion);
        });

        // Botón para mostrar fechas
        JButton btnMostrarFechas = new JButton("Mostrar Fechas");
        btnMostrarFechas.setBackground(new Color(245, 245, 220)); // Color beige
        panelSuperior.add(btnMostrarFechas);

        btnMostrarFechas.addActionListener(e -> {
            String codigoHabitacion = JOptionPane.showInputDialog(this, "Ingrese el código de la habitación:", "Mostrar Fechas", JOptionPane.PLAIN_MESSAGE);

            if (codigoHabitacion == null || codigoHabitacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código de habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Habitacion habitacionSeleccionada = null;
            for (Habitacion habitacion : hotel.getListaHabitaciones()) {
                if (habitacion.getCodigo().equals(codigoHabitacion.trim())) {
                    habitacionSeleccionada = habitacion;
                    break;
                }
            }

            if (habitacionSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una habitación con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mostrarFechasHabitacion(habitacionSeleccionada);
        });

        // Botón para mostrar servicios de habitación
        JButton btnServiciosHabitacion = new JButton("Servicios de Habitación");
        btnServiciosHabitacion.setBackground(new Color(245, 245, 220)); // Color beige
        panelSuperior.add(btnServiciosHabitacion);

        btnServiciosHabitacion.addActionListener(e -> {
            String codigoHabitacion = JOptionPane.showInputDialog(this, "Ingrese el código de la habitación:", "Servicios de Habitación", JOptionPane.PLAIN_MESSAGE);

            if (codigoHabitacion == null || codigoHabitacion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un código de habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Habitacion habitacionSeleccionada = null;
            for (Habitacion habitacion : hotel.getListaHabitaciones()) {
                if (habitacion.getCodigo().equals(codigoHabitacion.trim())) {
                    habitacionSeleccionada = habitacion;
                    break;
                }
            }

            if (habitacionSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró una habitación con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mostrarServiciosHabitacion(habitacionSeleccionada);
        });

        // Tabla para mostrar la información de las reservas
        String[] columnas = {"Huésped", "Habitación", "Tipo Habitación", "Precio Total"};
        Object[][] datos = {}; // Inicialmente vacío
        JTable tablaReservas = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tablaReservas);

        // Panel derecho con dos cuadros
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1, 10, 10));
        JTextArea cuadro1 = new JTextArea();
        JTextArea cuadro2 = new JTextArea("Cuadro 2");
        cuadro1.setBorder(BorderFactory.createTitledBorder("Servicios (Estandar)"));
        cuadro2.setBorder(BorderFactory.createTitledBorder("Servicios (Vip)"));

        // Mostrar precios de servicios para habitaciones tipo "Estandar"
        cuadro1.setText("TV: $15 por día\nDesayuno: $10 por día");
        cuadro2.setText("jacuzzi: $30 por día\nMayordomo: $50 por día\nNumero Tvs: $15 por televisor\nStreaming Premium: $25 por día\nNumero Cuartos: $50 por cuarto");

        panelDerecho.add(new JScrollPane(cuadro1));
        panelDerecho.add(new JScrollPane(cuadro2));

        // Botón para cargar reservas
        JButton btnCargarReservas = new JButton("Cargar Reservas");
        btnCargarReservas.setBackground(new Color(245, 245, 220)); // Color beige
        btnCargarReservas.addActionListener(e -> {
            List<Reserva> reservas = hotel.getListaReservas();
            Object[][] nuevaData = new Object[reservas.size()][4];
            for (int i = 0; i < reservas.size(); i++) {
                Reserva reserva = reservas.get(i);
                nuevaData[i][0] = reserva.getHuesped().getNombre();
                nuevaData[i][1] = reserva.getHabitacion().getCodigo();
                nuevaData[i][2] = reserva.getHabitacion().getTipo();
                nuevaData[i][3] = reserva.getHabitacion().calcularPrecio(reserva.calcularDuracion());
            }
            tablaReservas.setModel(new javax.swing.table.DefaultTableModel(nuevaData, columnas));
        });

        // Panel central con tabla y cuadros
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        panelCentral.add(panelDerecho, BorderLayout.EAST);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(panelCentral, BorderLayout.CENTER);
        panel.add(btnCargarReservas, BorderLayout.SOUTH);

        return panel;
    }

    private void mostrarFechasHabitacion(Habitacion habitacion) {
        JDialog dialogo = new JDialog(this, "Fechas de Reserva", true);
        dialogo.setSize(400, 300);
        dialogo.setLayout(new BorderLayout());

        JPanel panelCalendarios = new JPanel(new GridLayout(2, 1));

        JLabel lblFechaInicio = new JLabel("Fecha de Ingreso:");
        JDateChooser dateChooserInicio = new JDateChooser();
        JLabel lblFechaFin = new JLabel("Fecha de Salida:");
        JDateChooser dateChooserFin = new JDateChooser();

        // Buscar la reserva asociada a la habitación
        for (Reserva reserva : hotel.getListaReservas()) {
            if (reserva.getHabitacion().equals(habitacion)) {
                dateChooserInicio.setDate(java.sql.Date.valueOf(reserva.getFechaInicio()));
                dateChooserFin.setDate(java.sql.Date.valueOf(reserva.getFechaFin()));
                break;
            }
        }

        panelCalendarios.add(lblFechaInicio);
        panelCalendarios.add(dateChooserInicio);
        panelCalendarios.add(lblFechaFin);
        panelCalendarios.add(dateChooserFin);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dialogo.dispose());

        dialogo.add(panelCalendarios, BorderLayout.CENTER);
        dialogo.add(btnCerrar, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }

    private void mostrarServiciosHabitacion(Habitacion habitacion) {
        StringBuilder servicios = new StringBuilder("Servicios de la habitación:\n");

        if (habitacion instanceof Estandar) {
            Estandar estandar = (Estandar) habitacion;
            servicios.append("TV: ").append(estandar.isTv() ? "Sí" : "No").append("\n");
            servicios.append("Desayuno: ").append(estandar.isDesayuno() ? "Sí" : "No").append("\n");
        } else if (habitacion instanceof Vip) {
            Vip vip = (Vip) habitacion;
            servicios.append("Jacuzzi: ").append(vip.isJacuzzi() ? "Sí" : "No").append("\n");
            servicios.append("Servicio a la Habitación: ").append(vip.isServiciohabitacion() ? "Sí" : "No").append("\n");
            servicios.append("Streaming Premium: ").append(vip.isTvStreamingPremium() ? "Sí" : "No").append("\n");
            servicios.append("Número de TVs: ").append(vip.getNumTvs()).append("\n");
            servicios.append("Número de Cuartos: ").append(vip.getNumCuartos()).append("\n");
        }

        JOptionPane.showMessageDialog(this, servicios.toString(), "Servicios de Habitación", JOptionPane.INFORMATION_MESSAGE);
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
        JDialog dialogo = new JDialog(this, "Seleccionar Habitación", true);
        dialogo.setSize(400, 200);
        dialogo.setLayout(new GridLayout(3, 1));

        JLabel lblCodigoHabitacion = new JLabel("Ingrese el código de la habitación:");
        JTextField txtCodigoHabitacion = new JTextField();

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> {
            String codigoHabitacion = txtCodigoHabitacion.getText().trim();

            if (codigoHabitacion.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Debe ingresar un código de habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Habitacion habitacionSeleccionada = null;
            for (Habitacion habitacion : hotel.getListaHabitaciones()) {
                if (habitacion.getCodigo().equals(codigoHabitacion)) {
                    habitacionSeleccionada = habitacion;
                    break;
                }
            }

            if (habitacionSeleccionada == null) {
                JOptionPane.showMessageDialog(dialogo, "No se encontró una habitación con el código ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!habitacionSeleccionada.esDisponible(LocalDate.now(), LocalDate.now(), hotel.getListaReservas())) {
                JOptionPane.showMessageDialog(dialogo, "La habitación seleccionada no está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dialogo.dispose();
            abrirDialogoFechas(habitacionSeleccionada);
        });

        dialogo.add(lblCodigoHabitacion);
        dialogo.add(txtCodigoHabitacion);
        dialogo.add(btnSiguiente);

        dialogo.setVisible(true);
    }

    private void abrirDialogoFechas(Habitacion habitacionSeleccionada) {
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
                abrirDialogoHuesped(habitacionSeleccionada, fechaInicio, fechaFin);
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

    private void abrirDialogoHuesped(Habitacion habitacionSeleccionada, LocalDate fechaInicio, LocalDate fechaFin) {
        JDialog dialogo = new JDialog(this, "Datos del Huésped", true);
        dialogo.setSize(400, 400);
        dialogo.setLayout(new GridLayout(6, 2));

        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        // Panel para seleccionar servicios
        JPanel panelServicios = new JPanel(new GridLayout(0, 1));
        JLabel lblServicios = new JLabel("Seleccione Servicios:");
        JCheckBox chkTv = new JCheckBox("TV");
        JCheckBox chkDesayuno = new JCheckBox("Desayuno");
        JCheckBox chkJacuzzi = new JCheckBox("Jacuzzi");
        JCheckBox chkStreaming = new JCheckBox("Streaming Premium");
        JCheckBox chkServicioHabitacion = new JCheckBox("Servicio a la Habitación");

        if (habitacionSeleccionada instanceof Estandar) {
            panelServicios.add(chkTv);
            panelServicios.add(chkDesayuno);
        } else if (habitacionSeleccionada instanceof Vip) {
            panelServicios.add(chkJacuzzi);
            panelServicios.add(chkStreaming);
            panelServicios.add(chkServicioHabitacion);
        }

        JButton btnFinalizar = new JButton("Finalizar Reserva");
        btnFinalizar.addActionListener(e -> {
            String cedula = txtCedula.getText().trim();
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();

            if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que cédula y teléfono sean numéricos
            if (!cedula.matches("\\d+")) {
                JOptionPane.showMessageDialog(dialogo, "La cédula debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(dialogo, "El teléfono debe contener solo números.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!habitacionSeleccionada.esDisponible(fechaInicio, fechaFin, hotel.getListaReservas())) {
                JOptionPane.showMessageDialog(dialogo, "La habitación seleccionada no está disponible para las fechas ingresadas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Configurar servicios seleccionados
            if (habitacionSeleccionada instanceof Estandar) {
                Estandar estandar = (Estandar) habitacionSeleccionada;
                estandar.setTv(chkTv.isSelected());
                estandar.setDesayuno(chkDesayuno.isSelected());
            } else if (habitacionSeleccionada instanceof Vip) {
                Vip vip = (Vip) habitacionSeleccionada;
                vip.setJacuzzi(chkJacuzzi.isSelected());
                vip.setTvStreamingPremium(chkStreaming.isSelected());
                vip.setServiciohabitacion(chkServicioHabitacion.isSelected());
            }

            Huesped huesped = new Huesped(cedula, nombre, telefono);
            Reserva reserva = new Reserva(fechaInicio.toString(), fechaFin.toString(), "12:00", huesped, 1, habitacionSeleccionada);
            hotel.getListaReservas().add(reserva);
            Habitacion.registrarReserva(reserva);

            JOptionPane.showMessageDialog(dialogo, "Reserva creada con éxito.\nTotal a pagar: $" + habitacionSeleccionada.calcularPrecio(reserva.calcularDuracion()), "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);
            dialogo.dispose();

            // Actualizar la tabla de habitaciones
            actualizarTablaHabitaciones();
        });

        dialogo.add(lblCedula);
        dialogo.add(txtCedula);
        dialogo.add(lblNombre);
        dialogo.add(txtNombre);
        dialogo.add(lblTelefono);
        dialogo.add(txtTelefono);
        dialogo.add(lblServicios);
        dialogo.add(panelServicios);
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

