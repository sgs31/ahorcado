package vistasAhorcado.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import vistasAhorcado.juego.Juego;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

import vistasAhorcado.util.ErrorWindow;

public class Menu {

	private JFrame frmAhorcado;
	private Configuracion config = new Configuracion();
	private Juego ahorcado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frmAhorcado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		initialize();
	}

	private void initialize() {
		frmAhorcado = new JFrame();
		frmAhorcado.setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/vistasAhorcado/img/rope-icon2.png")));
		frmAhorcado.getContentPane().setBackground(Color.WHITE);
		frmAhorcado.setTitle("Ahorcado");
		frmAhorcado.setBounds(100, 100, 565, 400);
		frmAhorcado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAhorcado.getContentPane().setLayout(null);
		frmAhorcado.setResizable(false);

		JLabel title = new JLabel("AHORCADO");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Sitka Subheading", Font.BOLD, 26));
		title.setBounds(30, 56, 193, 54);
		frmAhorcado.getContentPane().add(title);
		
		JButton configAhorcado = new JButton("Configurar");
		configAhorcado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.mostrar();
			}
		});
		configAhorcado.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		configAhorcado.setBounds(51, 173, 138, 29);
		frmAhorcado.getContentPane().add(configAhorcado);

		JButton jugarAhorcado = new JButton("Jugar");
		jugarAhorcado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(config.estaSeteado()) {
					ahorcado = new Juego(config);
					ahorcado.play();
				}else {
					ErrorWindow error = new ErrorWindow("Error al iniciar", "Debe configurar el juego antes de empezar.");
					error.setVisible(true);
				}
			}
		});
		jugarAhorcado.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		jugarAhorcado.setBounds(77, 121, 89, 29);
		frmAhorcado.getContentPane().add(jugarAhorcado);

		JLabel backgroundImg = new JLabel("New label");
		backgroundImg.setIcon(new ImageIcon(Menu.class.getResource("/vistasAhorcado/img/ahorcadobg.png")));
		backgroundImg.setBounds(36, 0, 520, 394);
		frmAhorcado.getContentPane().add(backgroundImg);
	}
}
