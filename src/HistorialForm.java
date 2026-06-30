import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorialForm {
    private JPanel panelPrincipal8;
    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JButton btnVerHistorial;
    private JButton btnVerRespuestas;
    private JButton btnTiempo;
    private JTextArea txtResultado;

    private GestionarHistorial gestionarHistorial;
    private Ticket ticketActual;

    public HistorialForm() {
        gestionarHistorial = new GestionarHistorial();
        ticketActual = null;

        txtResultado.setEditable(false);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarTicket();
            }
        });

        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verHistorial();
            }
        });

        btnVerRespuestas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verRespuestas();
            }
        });

        btnTiempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verTiempoResolucion();
            }
        });
    }

    private void buscarTicket() {
        try {
            if (txtCodigo.getText().trim().isEmpty()) {
                throw new Exception("Ingrese el código del ticket.");
            }

            int codigo = Integer.parseInt(txtCodigo.getText().trim());

            ticketActual = CrearTicketForm.getGestorTickets().buscarTicketPorCodigo(codigo);

            if (ticketActual == null) {
                throw new Exception("No existe un ticket con ese código.");
            }

            txtResultado.setText(ticketActual.toString());

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

    private void verHistorial() {
        try {
            String historial = gestionarHistorial.verHistorialEstados(ticketActual);
            txtResultado.setText(historial);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void verRespuestas() {
        try {
            String respuestas = gestionarHistorial.verRespuestas(ticketActual);
            txtResultado.setText(respuestas);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void verTiempoResolucion() {
        try {
            String tiempo = gestionarHistorial.calcularTiempoResolucion(ticketActual);
            txtResultado.setText(tiempo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void abrir() {
        JFrame frame = new JFrame("Historial y Seguimiento - URBE RED");
        frame.setContentPane(new HistorialForm().panelPrincipal8);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}