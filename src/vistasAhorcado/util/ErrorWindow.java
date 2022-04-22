package vistasAhorcado.util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class ErrorWindow extends JFrame {

	private JPanel contentPane;
	//
	public ErrorWindow(String titulo, String mensaje) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorWindow.class.getResource("/vistasAhorcado/img/error-icon.png")));
		setTitle(titulo);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel mensajeLabel = new JLabel(mensaje);
		mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mensajeLabel.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		mensajeLabel.setBounds(0, 0, 434, 95);
		contentPane.add(mensajeLabel);
	}

}
