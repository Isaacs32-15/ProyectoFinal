import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SoporteForm {
    private JPanel panelPrincipal7;
    private JLabel lblTitulo;
    private JLabel lblArea;
    private JComboBox cmbArea;
    private JButton btnCargarTickets;
    private JButton btnAbrirTicket;
    private JLabel lblTicketsPendientes;
    private JTextArea txtTickets;
    private JLabel lblRespuesta;
    private JTextArea txtRespuesta;
    private JButton btnResponder;

    private GestionarSoporte gestionarSoporte;
    private Ticket ticketActual;

    public SoporteForm() {
        gestionarSoporte = new GestionarSoporte();
        ticketActual = null;

        txtTickets.setEditable(false);

        btnCargarTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTickets();
            }
        });

        btnAbrirTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTicket();
            }
        });

        btnResponder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responderTicket();
            }
        });
    }

    private void cargarTickets() {
        try {
            String area = cmbArea.getSelectedItem().toString();

            if (area.equals("SELECCIONE")) {
                throw new Exception("Seleccione un área de soporte.");
            }

            gestionarSoporte.cargarTickets(CrearTicketForm.getGestorTickets().listarTickets(), area);

            txtTickets.setText(gestionarSoporte.verTicketsPendientes());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirTicket() {
        try {
            ticketActual = gestionarSoporte.abrirTicket();

            JOptionPane.showMessageDialog(null,
                    "Ticket abierto correctamente.\nEstado cambiado a EN PROCESO.");

            txtTickets.setText(ticketActual.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void responderTicket() {
        try {
            String respuesta = txtRespuesta.getText().trim();

            gestionarSoporte.responderTicket(ticketActual, respuesta);

            JOptionPane.showMessageDialog(null,
                    "Respuesta registrada correctamente.");

            txtTickets.setText(ticketActual.toString());
            txtRespuesta.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void abrir() {
        JFrame frame = new JFrame("Atención de Soporte - URBE RED");
        frame.setContentPane(new SoporteForm().panelPrincipal7);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}