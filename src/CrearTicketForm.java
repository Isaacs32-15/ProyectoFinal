import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearTicketForm {
    private JPanel panelPrincipal6;
    private JLabel lblTitulo;
    private JLabel lblTituloTicket;
    private JTextField txtTitulo;
    private JLabel lblDescripcion;
    private JTextArea txtDescripcion;
    private JLabel lblTipoSoporte;
    private JComboBox cmbTipoSoporte;
    private JButton btnCrear;
    private JLabel lblCodigoBuscar;
    private JTextField txtCodigoBuscar;
    private JButton btnBuscar;
    private JButton btnMostrar;
    private JButton btnEliminar;
    private JTextArea txtResultado;

    private static GestionarTickets gestorTickets = new GestionarTickets();
    private Usuario cliente;

    public CrearTicketForm(Usuario cliente) {
        this.cliente = cliente;
        txtResultado.setEditable(false);

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearTicket();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTicket();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTickets();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarTicket();
            }
        });
    }

    private void crearTicket() {
        try {
            String titulo = txtTitulo.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            String tipoSoporte = cmbTipoSoporte.getSelectedItem().toString();

            Ticket ticket = gestorTickets.crearTicket(titulo, descripcion, tipoSoporte, cliente);

            JOptionPane.showMessageDialog(null,
                    "Ticket creado correctamente.\nCódigo: " + ticket.getCodigo());

            limpiarCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarTicket() {
        try {
            if (txtCodigoBuscar.getText().trim().isEmpty()) {
                throw new Exception("Ingrese el código del ticket.");
            }

            int codigo = Integer.parseInt(txtCodigoBuscar.getText().trim());
            String resultado = gestorTickets.visualizarTicket(codigo);

            txtResultado.setText(resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "El código debe ser numérico.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarTickets() {
        txtResultado.setText(gestorTickets.listarTicketsOrdenados());
    }

    private void eliminarTicket() {
        try {
            if (txtCodigoBuscar.getText().trim().isEmpty()) {
                throw new Exception("Ingrese el código del ticket.");
            }

            int codigo = Integer.parseInt(txtCodigoBuscar.getText().trim());

            gestorTickets.eliminarTicketPendiente(codigo);

            JOptionPane.showMessageDialog(null,
                    "Ticket eliminado correctamente.");

            txtResultado.setText(gestorTickets.listarTicketsOrdenados());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "El código debe ser numérico.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    public static GestionarTickets getGestorTickets() {
        return gestorTickets;
    }
    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        cmbTipoSoporte.setSelectedIndex(0);
        txtCodigoBuscar.setText("");
        txtResultado.setText("");
    }

    public static void abrir(Usuario cliente) {
        JFrame frame = new JFrame("Gestión de Tickets - URBE RED");
        frame.setContentPane(new CrearTicketForm(cliente).panelPrincipal6);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}