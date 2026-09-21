package registro;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Formulario "Guardar Datos": Nombre, Apellido, Edad, Ciudad y botones.
 */
public class FormRegistro extends JFrame {

    private final Conexion conexion = new Conexion();

    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JLabel lblEdad;
    private JLabel lblCiudad;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JComboBox<String> cboCiudad;
    private JButton btnAgregar;
    private JButton btnLimpiar;
    private JButton btnSalir;

    public FormRegistro() {
        initComponents();
        setLocationRelativeTo(null);
        conectar();
    }

    /** Conecta a la base de datos y muestra el estado en el título de la ventana. */
    private void conectar() {
        try {
            conexion.conectar();
            setTitle("Registro de estudiantes - Conexión establecida");
        } catch (ClassNotFoundException | SQLException e) {
            setTitle("Registro de estudiantes - Sin conexión");
            JOptionPane.showMessageDialog(this, "Error de conexión: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Registro de estudiantes");
        setResizable(false);

        lblTitulo = new JLabel("Guardar Datos", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));

        lblNombre = new JLabel("Nombre");
        lblApellido = new JLabel("Apellido");
        lblEdad = new JLabel("Edad");
        lblCiudad = new JLabel("Ciudad");

        txtNombre = new JTextField(18);
        txtApellido = new JTextField(18);
        txtEdad = new JTextField(18);
        cboCiudad = new JComboBox<>(new String[]{
            "Seleccione...", "Tarija", "La Paz", "Cochabamba", "Santa Cruz",
            "Sucre", "Oruro", "Potosí", "Trinidad", "Cobija"
        });

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        btnSalir = new JButton("Salir");

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 6, 18, 6);
        panel.add(lblTitulo, gbc);

        agregarFila(panel, lblNombre, txtNombre, 1);
        agregarFila(panel, lblApellido, txtApellido, 2);
        agregarFila(panel, lblEdad, txtEdad, 3);
        agregarFila(panel, lblCiudad, cboCiudad, 4);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(18, 6, 0, 6);
        panel.add(panelBotones, gbc);

        btnAgregar.addActionListener(this::btnAgregarActionPerformed);
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        // Si cierran la ventana con la X también se cierra la base de datos
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                conexion.cerrar();
            }
        });

        getContentPane().add(panel);
        pack();
    }

    private void agregarFila(JPanel panel, JLabel etiqueta, JComponent campo, int fila) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(etiqueta, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, gbc);
    }

    /** Guarda un estudiante en la tabla "estudiante". */
    private void btnAgregarActionPerformed(ActionEvent evt) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String textoEdad = txtEdad.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || textoEdad.isEmpty()
                || cboCiudad.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(textoEdad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número entero",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            txtEdad.requestFocus();
            return;
        }
        if (edad < 1 || edad > 120) {
            JOptionPane.showMessageDialog(this, "La edad debe estar entre 1 y 120",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            txtEdad.requestFocus();
            return;
        }

        String ciudad = (String) cboCiudad.getSelectedItem();

        // Si la conexión se cerró o nunca se abrió, se intenta de nuevo
        if (!conexion.estaConectado()) {
            conectar();
            if (!conexion.estaConectado()) {
                return;
            }
        }

        String sql = "INSERT INTO estudiante(nombre, apellido, edad, ciudad) VALUES(?,?,?,?)";
        try (PreparedStatement pst = conexion.getConnection().prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setInt(3, edad);
            pst.setString(4, ciudad);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(this, "Dato guardado");
                limpiar();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnLimpiarActionPerformed(ActionEvent evt) {
        limpiar();
    }

    /** Cierra la conexión a la base de datos y sale del programa. */
    private void btnSalirActionPerformed(ActionEvent evt) {
        conexion.cerrar();
        dispose();
        System.exit(0);
    }

    private void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        cboCiudad.setSelectedIndex(0);
        txtNombre.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormRegistro().setVisible(true));
    }
}
