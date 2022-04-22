package vistasAhorcado.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DerrotaWindow extends JFrame {

	private JPanel contentPane;

	public DerrotaWindow(String palabra) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DerrotaWindow.class.getResource("/vistasAhorcado/img/rope-icon2.png")));
		setTitle("DERROTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mensajeLabel = new JLabel("Has perdido :(");
		mensajeLabel.setFont(new Font("Sitka Text", Font.BOLD, 22));
		mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mensajeLabel.setBounds(81, 43, 197, 84);
		contentPane.add(mensajeLabel);
		
		JLabel palabraAhorcadoLabel = new JLabel("La palabra era " + palabra);
		palabraAhorcadoLabel.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		palabraAhorcadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		palabraAhorcadoLabel.setBounds(108, 113, 152, 14);
		contentPane.add(palabraAhorcadoLabel);
	}
}
