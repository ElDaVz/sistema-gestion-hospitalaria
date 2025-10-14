package Laboratorio_3;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

/**
 * Vista principal del sistema hospitalario usando Java Swing.
 * Gestiona la interfaz gráfica y delega la lógica al HospitalManager.
 */
public class HospitalView {
    private HospitalManager manager;
    private JFrame frame;
    
    //Componentes de la interfaz
    private JTabbedPane tabbedPane;
    
    //Panel 1: Gestión de Personal
    private JComboBox<String> comboDepartamento;
    private JComboBox<String> comboTipoPersonal;
    private JTextArea txtAreaPersonal;
    private JScrollPane scrollPersonal;
    
    //Panel 2: Gestión de Citas
    private JTextField txtIdCita;
    private JTextField txtPaciente;
    private JComboBox<String> comboMedico;
    private JTextField txtFecha;
    private JTextField txtDuracion;
    private JComboBox<String> comboTipoCita;
    private JTextArea txtAreaCitas;
    private JScrollPane scrollCitas;
    
    //Panel 3: Reportes y Análisis
    private JComboBox<String> comboDepartamentoReporte;
    private JComboBox<String> comboEstadoCita;
    private JTextArea txtAreaReportes;
    private JScrollPane scrollReportes;
    
    //Panel 4: Búsqueda
    private JTextField txtBuscarId;
    private JComboBox<String> comboEspecialidad;
    private JTextField txtFechaBusqueda;
    
    /**
     * Constructor de la vista
     */
    public HospitalView(HospitalManager manager) {
        this.manager = manager;
        inicializarComponentes();
    }
    
    /**
     * Inicializa todos los componentes de la GUI
     */
    public void inicializarComponentes() {
        //Configurar frame principal
        frame = new JFrame("Sistema de Gestión Hospitalaria");
        frame.setSize(1200, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //Crear panel de pestañas
        tabbedPane = new JTabbedPane();
        
        //Crear cada panel
        JPanel panelPersonal = crearPanelPersonal();
        JPanel panelCitas = crearPanelCitas();
        JPanel panelReportes = crearPanelReportes();
        JPanel panelBusqueda = crearPanelBusqueda();
        
        //Agregar pestañas
        tabbedPane.addTab("Gestión de Personal", panelPersonal);
        tabbedPane.addTab("Gestión de Citas", panelCitas);
        tabbedPane.addTab("Reportes y Análisis", panelReportes);
        tabbedPane.addTab("Búsqueda Avanzada", panelBusqueda);
        
        frame.add(tabbedPane, BorderLayout.CENTER);
        
        //Cargar datos iniciales
        cargarDepartamentos();
        cargarTiposCita();
        cargarEstadosCita();
        cargarEspecialidades();
        cargarTiposPersonal();
        
        frame.setVisible(true);
    }
    
    /**
     * Crea el panel de gestión de personal
     */
    private JPanel crearPanelPersonal() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //Panel superior con controles
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton btnContratar = new JButton("Contratar Personal");
        JButton btnDespedir = new JButton("Despedir Personal");
        JButton btnVerReporte = new JButton("Ver Reporte de Personal");
        JButton btnActualizar = new JButton("Actualizar Lista");
        
        comboTipoPersonal = new JComboBox<>();
        comboDepartamento = new JComboBox<>();
        
        panelControles.add(new JLabel("Tipo:"));
        panelControles.add(comboTipoPersonal);
        panelControles.add(new JLabel("Departamento:"));
        panelControles.add(comboDepartamento);
        panelControles.add(btnContratar);
        panelControles.add(btnDespedir);
        panelControles.add(btnVerReporte);
        panelControles.add(btnActualizar);
        
        //Área de texto para mostrar personal
        txtAreaPersonal = new JTextArea();
        txtAreaPersonal.setEditable(false);
        scrollPersonal = new JScrollPane(txtAreaPersonal);
        
        //Agregar listeners
        btnVerReporte.addActionListener(e -> mostrarReportePersonal());
        btnActualizar.addActionListener(e -> actualizarListaPersonal());
        btnContratar.addActionListener(e -> mostrarDialogoContratarPersonal());
        btnDespedir.addActionListener(e -> mostrarDialogoDespedirPersonal());
        
        panel.add(panelControles, BorderLayout.NORTH);
        panel.add(scrollPersonal, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel de gestión de citas
     */
    private JPanel crearPanelCitas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2, 5, 5));
        
        txtIdCita = new JTextField();
        txtPaciente = new JTextField();
        comboMedico = new JComboBox<>();
        txtFecha = new JTextField();
        txtDuracion = new JTextField();
        comboTipoCita = new JComboBox<>();
        
        panelFormulario.add(new JLabel("ID Cita:"));
        panelFormulario.add(txtIdCita);
        panelFormulario.add(new JLabel("Paciente:"));
        panelFormulario.add(txtPaciente);
        panelFormulario.add(new JLabel("Médico:"));
        panelFormulario.add(comboMedico);
        panelFormulario.add(new JLabel("Fecha (YYYY-MM-DD HH:MM):"));
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Duración (horas):"));
        panelFormulario.add(txtDuracion);
        panelFormulario.add(new JLabel("Tipo de Cita:"));
        panelFormulario.add(comboTipoCita);
        
        //Botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnProgramar = new JButton("Programar Cita");
        JButton btnReagendar = new JButton("Reagendar Cita");
        JButton btnCancelar = new JButton("Cancelar Cita");
        JButton btnActualizarCitas = new JButton("Actualizar Lista");
        JButton btnActualizarDatos = new JButton("Actualizar Datos Cita");
        
        panelBotones.add(btnProgramar);
        panelBotones.add(btnReagendar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnActualizarDatos);
        panelBotones.add(btnActualizarCitas);
        
        //Área de texto para mostrar citas
        txtAreaCitas = new JTextArea();
        txtAreaCitas.setEditable(false);
        scrollCitas = new JScrollPane(txtAreaCitas);
        
        //Listeners
        btnProgramar.addActionListener(e -> programarCita());
        btnReagendar.addActionListener(e -> reagendarCita());
        btnCancelar.addActionListener(e -> cancelarCita());
        btnActualizarDatos.addActionListener(e -> actualizarDatosCita());
        btnActualizarCitas.addActionListener(e -> actualizarListaCitas());
        
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        
        panel.add(panelNorte, BorderLayout.NORTH);
        panel.add(scrollCitas, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel de reportes y análisis
     */
    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //Panel de controles
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        comboDepartamentoReporte = new JComboBox<>();
        comboEstadoCita = new JComboBox<>();
        
        JButton btnNominaDepartamento = new JButton("Nómina por Departamento");
        JButton btnNominaTotal = new JButton("Nómina Total");
        JButton btnReporteNomina = new JButton("Reporte de Nómina");
        JButton btnHistorial = new JButton("Historial Reagendamientos");
        JButton btnAnalisis = new JButton("Análisis Utilización");
        JButton btnEficiencia = new JButton("Eficiencia Personal");
        JButton btnConflictos = new JButton("Detectar Conflictos");
        JButton btnResolver = new JButton("Resolver Conflictos");
        JButton btnActualizarEstadisticas = new JButton("Actualizar Estadísticas");
        
        panelControles.add(new JLabel("Departamento:"));
        panelControles.add(comboDepartamentoReporte);
        panelControles.add(btnNominaDepartamento);
        panelControles.add(btnNominaTotal);
        panelControles.add(btnReporteNomina);
        
        JPanel panelControles2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelControles2.add(new JLabel("Estado:"));
        panelControles2.add(comboEstadoCita);
        panelControles2.add(btnHistorial);
        panelControles2.add(btnAnalisis);
        panelControles2.add(btnEficiencia);
        panelControles2.add(btnConflictos);
        panelControles2.add(btnResolver);
        panelControles2.add(btnActualizarEstadisticas);
        
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.add(panelControles);
        panelSuperior.add(panelControles2);
        
        //Área de texto para reportes
        txtAreaReportes = new JTextArea();
        txtAreaReportes.setEditable(false);
        scrollReportes = new JScrollPane(txtAreaReportes);
        
        //Listeners
        btnNominaDepartamento.addActionListener(e -> calcularNominaDepartamento());
        btnNominaTotal.addActionListener(e -> calcularNominaTotal());
        btnReporteNomina.addActionListener(e -> mostrarReporteNomina());
        btnHistorial.addActionListener(e -> mostrarHistorialReagendamientos());
        btnAnalisis.addActionListener(e -> mostrarAnalisisUtilizacion());
        btnEficiencia.addActionListener(e -> mostrarEficienciaPersonal());
        btnConflictos.addActionListener(e -> detectarConflictos());
        btnResolver.addActionListener(e -> resolverConflictos());
        btnActualizarEstadisticas.addActionListener(e -> actualizarEstadisticas());
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollReportes, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Crea el panel de búsqueda avanzada
     */
    private JPanel crearPanelBusqueda() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        txtBuscarId = new JTextField(15);
        comboEspecialidad = new JComboBox<>();
        txtFechaBusqueda = new JTextField(15);
        
        JButton btnBuscarPersonal = new JButton("Buscar Personal por ID");
        JButton btnBuscarCita = new JButton("Buscar Cita por ID");
        JButton btnBuscarDisponible = new JButton("Buscar Personal Disponible");
        
        panelControles.add(new JLabel("ID:"));
        panelControles.add(txtBuscarId);
        panelControles.add(btnBuscarPersonal);
        panelControles.add(btnBuscarCita);
        
        JPanel panelControles2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelControles2.add(new JLabel("Especialidad:"));
        panelControles2.add(comboEspecialidad);
        panelControles2.add(new JLabel("Fecha:"));
        panelControles2.add(txtFechaBusqueda);
        panelControles2.add(btnBuscarDisponible);
        
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.add(panelControles);
        panelSuperior.add(panelControles2);
        
        JTextArea txtAreaBusqueda = new JTextArea();
        txtAreaBusqueda.setEditable(false);
        JScrollPane scrollBusqueda = new JScrollPane(txtAreaBusqueda);
        
        //Listeners
        btnBuscarPersonal.addActionListener(e -> {
            String id = txtBuscarId.getText();
            GeneralMedic medico = manager.buscarPersonalPorId(id);
            if (medico != null) {
                txtAreaBusqueda.setText(medico.toString());
            } else {
                txtAreaBusqueda.setText("Personal no encontrado");
            }
        });
        
        btnBuscarCita.addActionListener(e -> {
            String id = txtBuscarId.getText();
            Appointment cita = manager.getAppointmentsController().buscarCitaPorId(id);
            if (cita != null) {
                txtAreaBusqueda.setText(cita.toString());
            } else {
                txtAreaBusqueda.setText("Cita no encontrada");
            }
        });
        
        btnBuscarDisponible.addActionListener(e -> buscarPersonalDisponible(txtAreaBusqueda));
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollBusqueda, BorderLayout.CENTER);
        
        return panel;
    }
    
    //Métodos de carga de datos en ComboBox
    
    public void cargarDepartamentos() {
        String[] departamentos = {"Cardiología", "Cirugía", "Emergencias", "Pediatría", "Oncología"};
        for (String dept : departamentos) {
            comboDepartamento.addItem(dept);
            comboDepartamentoReporte.addItem(dept);
        }
    }
    
    public void cargarTiposPersonal() {
        String[] tipos = {"Doctor", "Cirujano", "Enfermera", "Terapeuta"};
        for (String tipo : tipos) {
            comboTipoPersonal.addItem(tipo);
        }
    }
    
    public void cargarMedicos() {
        comboMedico.removeAllItems();
        ArrayList<GeneralMedic> personal = manager.obtenerTodoElPersonal();
        for (GeneralMedic medico : personal) {
            comboMedico.addItem(medico.getId() + " - " + medico.getNombre());
        }
    }
    
    public void cargarTiposCita() {
        String[] tipos = {"Consulta", "Cirugía", "Terapia", "Diagnóstico"};
        for (String tipo : tipos) {
            comboTipoCita.addItem(tipo);
        }
    }
    
    public void cargarEstadosCita() {
        for (EstadoActual estado : EstadoActual.values()) {
            comboEstadoCita.addItem(estado.toString());
        }
    }
    
    public void cargarEspecialidades() {
        String[] especialidades = {"Doctor", "Cirujano", "Enfermera", "Terapeuta"};
        for (String esp : especialidades) {
            comboEspecialidad.addItem(esp);
        }
    }
    
    //Métodos de actualización de listas
    
    public void actualizarListaPersonal() {
        ArrayList<GeneralMedic> personal = manager.obtenerTodoElPersonal();
        StringBuilder sb = new StringBuilder();
        for (GeneralMedic medico : personal) {
            sb.append(medico.toString()).append("\n\n");
        }
        txtAreaPersonal.setText(sb.toString());
        cargarMedicos();
    }
    
    private void actualizarListaCitas() {
        String reporte = manager.generarReporteCitas();
        txtAreaCitas.setText(reporte);
    }
    
    private void actualizarDatosCita() {
        //Crear diálogo para actualizar cita
        JDialog dialogo = new JDialog(frame, "Actualizar Datos de Cita", true);
        dialogo.setSize(500, 400);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        //Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JTextField txtIdBuscar = new JTextField();
        JTextField txtNuevoPaciente = new JTextField();
        JComboBox<String> comboNuevoMedico = new JComboBox<>();
        JTextField txtNuevaFecha = new JTextField();
        JTextField txtNuevaDuracion = new JTextField();
        JComboBox<String> comboNuevoEstado = new JComboBox<>();
        
        //Cargar médicos y estados
        ArrayList<GeneralMedic> personal = manager.obtenerTodoElPersonal();
        for (GeneralMedic medico : personal) {
            comboNuevoMedico.addItem(medico.getId() + " - " + medico.getNombre());
        }
        
        for (EstadoActual estado : EstadoActual.values()) {
            comboNuevoEstado.addItem(estado.toString());
        }
        
        panelFormulario.add(new JLabel("ID de la Cita:"));
        panelFormulario.add(txtIdBuscar);
        panelFormulario.add(new JLabel("Nuevo Paciente:"));
        panelFormulario.add(txtNuevoPaciente);
        panelFormulario.add(new JLabel("Nuevo Médico:"));
        panelFormulario.add(comboNuevoMedico);
        panelFormulario.add(new JLabel("Nueva Fecha:"));
        panelFormulario.add(txtNuevaFecha);
        panelFormulario.add(new JLabel("Nueva Duración:"));
        panelFormulario.add(txtNuevaDuracion);
        panelFormulario.add(new JLabel("Nuevo Estado:"));
        panelFormulario.add(comboNuevoEstado);
        
        //Botón para buscar y cargar datos
        JButton btnBuscar = new JButton("Buscar Cita");
        btnBuscar.addActionListener(e -> {
            String id = txtIdBuscar.getText();
            Appointment cita = manager.getAppointmentsController().buscarCitaPorId(id);
            
            if (cita != null) {
                txtNuevoPaciente.setText(cita.getPaciente());
                txtNuevaFecha.setText(cita.getFecha());
                txtNuevaDuracion.setText(String.valueOf(cita.getDuracion()));
                
                //Seleccionar médico actual
                String medicoActual = cita.getMedico().getId() + " - " + cita.getMedico().getNombre();
                comboNuevoMedico.setSelectedItem(medicoActual);
                
                //Seleccionar estado actual
                comboNuevoEstado.setSelectedItem(cita.getEstadoActual().toString());
                
                mostrarMensaje("Cita encontrada. Modifique los campos que desee actualizar.");
            } else {
                mostrarError("Cita no encontrada");
            }
        });
        
        //Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnActualizar.addActionListener(e -> {
            try {
                String id = txtIdBuscar.getText();
                Appointment cita = manager.getAppointmentsController().buscarCitaPorId(id);
                
                if (cita == null) {
                    mostrarError("Primero busque una cita válida");
                    return;
                }
                
                //Actualizar campos
                String nuevoPaciente = txtNuevoPaciente.getText();
                String nuevaFecha = txtNuevaFecha.getText();
                int nuevaDuracion = Integer.parseInt(txtNuevaDuracion.getText());
                String nuevoEstadoStr = (String) comboNuevoEstado.getSelectedItem();
                EstadoActual nuevoEstado = EstadoActual.valueOf(nuevoEstadoStr);
                
                //Obtener nuevo médico
                String medicoSeleccionado = (String) comboNuevoMedico.getSelectedItem();
                String idMedico = medicoSeleccionado.split(" - ")[0];
                GeneralMedic nuevoMedico = manager.buscarPersonalPorId(idMedico);
                
                //Aplicar cambios
                cita.setPaciente(nuevoPaciente);
                cita.setFecha(nuevaFecha);
                cita.setDuracion(nuevaDuracion);
                cita.setEstadoActual(nuevoEstado);
                cita.setMedico(nuevoMedico);
                
                mostrarMensaje("Cita actualizada exitosamente");
                actualizarListaCitas();
                dialogo.dispose();
                
            } catch (NumberFormatException ex) {
                mostrarError("Error: La duración debe ser un número válido");
            } catch (Exception ex) {
                mostrarError("Error al actualizar cita: " + ex.getMessage());
            }
        });
        
        btnCancelar.addActionListener(e -> dialogo.dispose());
        
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCancelar);
        
        dialogo.add(panelFormulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        
        dialogo.setLocationRelativeTo(frame);
        dialogo.setVisible(true);
    }
    
    //Métodos de acción
    
    private void mostrarDialogoContratarPersonal() {
        //Crear diálogo personalizado
        JDialog dialogo = new JDialog(frame, "Contratar Personal Médico", true);
        dialogo.setSize(500, 600);
        dialogo.setLayout(new BorderLayout(10, 10));
        
        //Panel principal con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(12, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //Campos comunes
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JComboBox<String> comboDept = new JComboBox<>();
        for (int i = 0; i < comboDepartamento.getItemCount(); i++) {
            comboDept.addItem(comboDepartamento.getItemAt(i));
        }
        JTextField txtAnios = new JTextField();
        JTextField txtSalarioBase = new JTextField();
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Doctor", "Cirujano", "Enfermera", "Terapeuta"});
        
        //Campos específicos (se mostrarán según el tipo)
        JTextField txtEspecializacion = new JTextField();
        JTextField txtCapacidad = new JTextField();
        JTextField txtTarifa = new JTextField();
        JTextField txtHorasCirugia = new JTextField();
        JTextField txtBono = new JTextField();
        JComboBox<String> comboTurno = new JComboBox<>(new String[]{"Diurno", "Nocturno"});
        JTextField txtCertificacion = new JTextField();
        JTextField txtTerapia = new JTextField();
        JTextField txtDuracionSesion = new JTextField();
        
        //Panel para campos específicos
        JPanel panelEspecifico = new JPanel(new GridLayout(3, 2, 5, 5));
        panelEspecifico.setBorder(BorderFactory.createTitledBorder("Datos Específicos"));
        
        //Agregar campos comunes
        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre Completo:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Departamento:"));
        panelFormulario.add(comboDept);
        panelFormulario.add(new JLabel("Años de Experiencia:"));
        panelFormulario.add(txtAnios);
        panelFormulario.add(new JLabel("Salario Base:"));
        panelFormulario.add(txtSalarioBase);
        panelFormulario.add(new JLabel("Tipo de Personal:"));
        panelFormulario.add(comboTipo);
        
        //Listener para cambiar campos específicos según el tipo
        comboTipo.addActionListener(e -> {
            panelEspecifico.removeAll();
            String tipo = (String) comboTipo.getSelectedItem();
            
            switch (tipo) {
                case "Doctor":
                    panelEspecifico.add(new JLabel("Especialización:"));
                    panelEspecifico.add(txtEspecializacion);
                    panelEspecifico.add(new JLabel("Capacidad Pacientes/Día:"));
                    panelEspecifico.add(txtCapacidad);
                    panelEspecifico.add(new JLabel("Tarifa de Consulta:"));
                    panelEspecifico.add(txtTarifa);
                    break;
                    
                case "Cirujano":
                    panelEspecifico.add(new JLabel("Horas Cirugía Disponibles:"));
                    panelEspecifico.add(txtHorasCirugia);
                    panelEspecifico.add(new JLabel("Bono por Riesgo:"));
                    panelEspecifico.add(txtBono);
                    panelEspecifico.add(new JLabel("Tipos Operaciones (separar con coma):"));
                    JTextArea txtOperaciones = new JTextArea(2, 20);
                    JScrollPane scrollOp = new JScrollPane(txtOperaciones);
                    panelEspecifico.add(scrollOp);
                    break;
                    
                case "Enfermera":
                    panelEspecifico.add(new JLabel("Tipo de Turno:"));
                    panelEspecifico.add(comboTurno);
                    panelEspecifico.add(new JLabel("Nivel de Certificación:"));
                    panelEspecifico.add(txtCertificacion);
                    break;
                    
                case "Terapeuta":
                    panelEspecifico.add(new JLabel("Terapia Especializada:"));
                    panelEspecifico.add(txtTerapia);
                    panelEspecifico.add(new JLabel("Duración Sesión (min):"));
                    panelEspecifico.add(txtDuracionSesion);
                    break;
            }
            
            panelEspecifico.revalidate();
            panelEspecifico.repaint();
        });
        
        //Trigger inicial
        comboTipo.setSelectedIndex(0);
        
        //Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Contratar");
        JButton btnCancelar = new JButton("Cancelar");
        
        btnGuardar.addActionListener(e -> {
            try {
                //Obtener datos comunes
                String id = txtId.getText();
                String nombre = txtNombre.getText();
                String departamento = (String) comboDept.getSelectedItem();
                int anios = Integer.parseInt(txtAnios.getText());
                double salarioBase = Double.parseDouble(txtSalarioBase.getText());
                String tipo = (String) comboTipo.getSelectedItem();
                
                //Crear el médico según el tipo
                GeneralMedic nuevoMedico = null;
                
                switch (tipo) {
                    case "Doctor":
                        String especializacion = txtEspecializacion.getText();
                        int capacidad = Integer.parseInt(txtCapacidad.getText());
                        double tarifa = Double.parseDouble(txtTarifa.getText());
                        nuevoMedico = new GeneralDoctor(id, nombre, departamento, anios, salarioBase, 
                                                        especializacion, capacidad, tarifa);
                        break;
                        
                    case "Cirujano":
                        int horasCirugia = Integer.parseInt(txtHorasCirugia.getText());
                        double bono = Double.parseDouble(txtBono.getText());
                        
                        //Obtener operaciones del TextArea
                        Component[] components = panelEspecifico.getComponents();
                        String operacionesText = "";
                        for (Component comp : components) {
                            if (comp instanceof JScrollPane) {
                                JScrollPane scroll = (JScrollPane) comp;
                                if (scroll.getViewport().getView() instanceof JTextArea) {
                                    operacionesText = ((JTextArea) scroll.getViewport().getView()).getText();
                                }
                            }
                        }
                        
                        ArrayList<String> operaciones = new ArrayList<>();
                        if (!operacionesText.isEmpty()) {
                            String[] ops = operacionesText.split(",");
                            for (String op : ops) {
                                operaciones.add(op.trim());
                            }
                        }
                        
                        nuevoMedico = new Surgeon(id, nombre, departamento, anios, salarioBase, 
                                                 operaciones, horasCirugia, bono);
                        break;
                        
                    case "Enfermera":
                        String turno = (String) comboTurno.getSelectedItem();
                        String certificacion = txtCertificacion.getText();
                        nuevoMedico = new Nurse(id, nombre, departamento, anios, salarioBase, 
                                               turno, certificacion);
                        break;
                        
                    case "Terapeuta":
                        String terapia = txtTerapia.getText();
                        int duracionSesion = Integer.parseInt(txtDuracionSesion.getText());
                        nuevoMedico = new Therapist(id, nombre, departamento, anios, salarioBase, 
                                                    terapia, duracionSesion);
                        break;
                }
                
                //Contratar personal
                if (nuevoMedico != null) {
                    String resultado = manager.contratarPersonal(nuevoMedico);
                    mostrarMensaje(resultado);
                    actualizarListaPersonal();
                    dialogo.dispose();
                }
                
            } catch (NumberFormatException ex) {
                mostrarError("Error: Verifique que todos los campos numéricos sean válidos");
            } catch (Exception ex) {
                mostrarError("Error al contratar personal: " + ex.getMessage());
            }
        });
        
        btnCancelar.addActionListener(e -> dialogo.dispose());
        
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        
        //Agregar todo al diálogo
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(panelFormulario, BorderLayout.CENTER);
        panelNorte.add(panelEspecifico, BorderLayout.SOUTH);
        
        dialogo.add(panelNorte, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);
        
        dialogo.setLocationRelativeTo(frame);
        dialogo.setVisible(true);
    }
    
    private void mostrarDialogoDespedirPersonal() {
        String id = JOptionPane.showInputDialog(frame, "Ingrese el ID del personal a despedir:");
        if (id != null && !id.isEmpty()) {
            String resultado = manager.despedirPersonal(id);
            mostrarMensaje(resultado);
            actualizarListaPersonal();
        }
    }
    
    private void programarCita() {
        try {
            String id = txtIdCita.getText();
            String paciente = txtPaciente.getText();
            String medicoSeleccionado = (String) comboMedico.getSelectedItem();
            String fecha = txtFecha.getText();
            int duracion = Integer.parseInt(txtDuracion.getText());
            String tipo = (String) comboTipoCita.getSelectedItem();
            
            //Extraer ID del médico del combobox
            String idMedico = medicoSeleccionado.split(" - ")[0];
            GeneralMedic medico = manager.buscarPersonalPorId(idMedico);
            
            String resultado = manager.programarCita(id, paciente, medico, fecha, tipo, duracion);
            mostrarMensaje(resultado);
            actualizarListaCitas();
            
        } catch (Exception ex) {
            mostrarError("Error al programar cita: " + ex.getMessage());
        }
    }
    
    private void reagendarCita() {
        String id = JOptionPane.showInputDialog(frame, "Ingrese el ID de la cita a reagendar:");
        String nuevaFecha = JOptionPane.showInputDialog(frame, "Ingrese la nueva fecha:");
        
        if (id != null && nuevaFecha != null) {
            String resultado = manager.reagendarCitaHospital(id, nuevaFecha);
            mostrarMensaje(resultado);
            actualizarListaCitas();
        }
    }
    
    private void cancelarCita() {
        String id = JOptionPane.showInputDialog(frame, "Ingrese el ID de la cita a cancelar:");
        if (id != null && !id.isEmpty()) {
            String resultado = manager.cancelarCita(id);
            mostrarMensaje(resultado);
            actualizarListaCitas();
        }
    }
    
    private void mostrarReportePersonal() {
        String reporte = manager.generarReportePersonal();
        txtAreaPersonal.setText(reporte);
    }
    
    private void calcularNominaDepartamento() {
        String departamento = (String) comboDepartamentoReporte.getSelectedItem();
        double nomina = manager.calcularNominaPorDepartamento(departamento);
        txtAreaReportes.setText("Nómina del departamento " + departamento + ": $" + String.format("%.2f", nomina));
    }
    
    private void calcularNominaTotal() {
        double nomina = manager.calcularNominaTotal();
        txtAreaReportes.setText("Nómina Total del Hospital: $" + String.format("%.2f", nomina));
    }
    
    private void mostrarReporteNomina() {
        String reporte = manager.generarReporteNomina();
        txtAreaReportes.setText(reporte);
    }
    
    private void mostrarHistorialReagendamientos() {
        String historial = manager.obtenerHistorialCompleto();
        txtAreaReportes.setText(historial);
    }
    
    private void mostrarAnalisisUtilizacion() {
        String analisis = manager.generarAnalisisUtilizacion();
        txtAreaReportes.setText(analisis);
    }
    
    private void mostrarEficienciaPersonal() {
        String eficiencia = manager.calcularEficienciaPersonal();
        txtAreaReportes.setText(eficiencia);
    }
    
    private void detectarConflictos() {
        ArrayList<String> conflictos = manager.detectarConflictosDeHorario();
        StringBuilder sb = new StringBuilder("=== CONFLICTOS DETECTADOS ===\n\n");
        if (conflictos.isEmpty()) {
            sb.append("No se detectaron conflictos");
        } else {
            for (String conflicto : conflictos) {
                sb.append(conflicto).append("\n");
            }
        }
        txtAreaReportes.setText(sb.toString());
    }
    
    private void resolverConflictos() {
        String resultado = manager.resolverConflictosAutomaticamente();
        txtAreaReportes.setText(resultado);
        actualizarListaCitas();
    }
    
    private void actualizarEstadisticas() {
        manager.actualizarEstadisticasPersonal();
        mostrarMensaje("Estadísticas del personal actualizadas correctamente");
    }
    
    private void buscarPersonalDisponible(JTextArea txtArea) {
        String especialidad = (String) comboEspecialidad.getSelectedItem();
        String fecha = txtFechaBusqueda.getText();
        
        ArrayList<GeneralMedic> disponibles = manager.encontrarPersonalDisponible(especialidad, fecha);
        StringBuilder sb = new StringBuilder("=== PERSONAL DISPONIBLE ===\n\n");
        
        if (disponibles.isEmpty()) {
            sb.append("No hay personal disponible con esos criterios");
        } else {
            for (GeneralMedic medico : disponibles) {
                sb.append(medico.toString()).append("\n\n");
            }
        }
        
        txtArea.setText(sb.toString());
    }
    
    //Métodos de utilidad
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarError(String error) {
        JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}