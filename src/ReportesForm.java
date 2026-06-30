import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesForm {
    private JPanel panelPrincipal9;
    private JLabel lblTitulo;
    private JButton btnReporteGeneral;
    private JButton btnRelaciones;
    private JTextArea txtResultado;

    private GestionarReportes gestionarReportes;
    private GrafoRelaciones grafoRelaciones;

    public ReportesForm() {

        gestionarReportes = new GestionarReportes();
        grafoRelaciones = new GrafoRelaciones();

        txtResultado.setEditable(false);

        btnReporteGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });

        btnRelaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRelaciones();
            }
        });
    }

    private void generarReporte() {

        txtResultado.setText(
                gestionarReportes.generarReporteGeneral(
                        CrearTicketForm.getGestorTickets().listarTickets()
                )
        );

    }

    private void mostrarRelaciones() {

        txtResultado.setText(
                grafoRelaciones.mostrarRelaciones(
                        CrearTicketForm.getGestorTickets().listarTickets()
                )
        );

    }

    public static void abrir() {

        JFrame frame = new JFrame("Reportes - URBE RED");
        frame.setContentPane(new ReportesForm().panelPrincipal9);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}