package vistasAhorcado.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class VictoriaWindow extends JFrame {

	private JPanel contentPane;
	
	public VictoriaWindow(String nombre) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VictoriaWindow.class.getResource("/vistasAhorcado/img/rope-icon2.png")));
		setTitle("VICTORIA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¡Felicidades " + nombre + " has ganado el juego del Ahorcado!");
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 59, 725, 137);
		contentPane.add(lblNewLabel);
	}
}
