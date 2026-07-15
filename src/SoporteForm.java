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

    private JLabel lblEstado;
    private JComboBox cmbEstado;
    private JButton btnCambiarEstado;

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

        btnCambiarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarEstado();
            }
        });
    }

    private void cargarTickets() {
        try {
            String area = cmbArea.getSelectedItem().toString();

            if (area.equals("SELECCIONE")) {
                throw new Exception("Seleccione un área de soporte.");
            }

            gestionarSoporte.cargarTickets(
                    CrearTicketForm.getGestorTickets().listarTickets(),
                    area
            );

            txtTickets.setText(
                    gestionarSoporte.verTicketsPendientes()
            );

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void abrirTicket() {
        try {
            ticketActual = gestionarSoporte.abrirTicket();

            JOptionPane.showMessageDialog(
                    null,
                    "Ticket abierto correctamente."
            );

            txtTickets.setText(ticketActual.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void responderTicket() {
        try {
            String respuesta = txtRespuesta.getText().trim();

            gestionarSoporte.responderTicket(
                    ticketActual,
                    respuesta
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Respuesta registrada correctamente."
            );

            txtTickets.setText(ticketActual.toString());
            txtRespuesta.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void cambiarEstado() {
        try {
            if (ticketActual == null) {
                throw new Exception("Primero debe abrir un ticket.");
            }

            String nuevoEstado = cmbEstado.getSelectedItem().toString();

            if (nuevoEstado.equals("SELECCIONE")) {
                throw new Exception("Seleccione un nuevo estado.");
            }

            ticketActual.cambiarEstado(nuevoEstado);

            JOptionPane.showMessageDialog(
                    null,
                    "Estado cambiado correctamente a: " + nuevoEstado
            );

            txtTickets.setText(ticketActual.toString());
            cmbEstado.setSelectedIndex(0);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void abrir() {
        JFrame frame = new JFrame("Atención de Soporte - URBE RED");
        frame.setContentPane(new SoporteForm().panelPrincipal7);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(650, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}