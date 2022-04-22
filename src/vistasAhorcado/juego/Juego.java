package vistasAhorcado.juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
//
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import dominioAhorcado.Ahorcado;
import vistasAhorcado.util.DerrotaWindow;
import vistasAhorcado.util.Mensaje;
import vistasAhorcado.util.VictoriaWindow;
import vistasAhorcado.menu.Configuracion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Juego {
	
	private Configuracion config;
	private Ahorcado ahorcado;
	private JFrame frmAhorcado;
	private String palabra;
	private JTextField inputLetra;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void play() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego window = new Juego(config);
					window.initialize();
					window.frmAhorcado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Juego(Configuracion config) {
		this.config = config;
		final String nombreJugador = config.getNombreJugador();
		final String dificultad = config.getDificultad();
		final String idioma = config.getIdioma();
		this.ahorcado = new Ahorcado(nombreJugador, dificultad, idioma);
	}

	private void initialize() {
		frmAhorcado = new JFrame();
		frmAhorcado.setIconImage(Toolkit.getDefaultToolkit().getImage(Juego.class.getResource("/vistasAhorcado/img/rope-icon2.png")));
		frmAhorcado.setFocusCycleRoot(false);
		frmAhorcado.setTitle("Ahorcado");
		frmAhorcado.getContentPane().setBackground(Color.WHITE);
		frmAhorcado.getContentPane().setLayout(null);
		
		String palabraAhorcado = ahorcado.getPalabraSeleccionada();
		System.out.println(palabraAhorcado);
		setPalabra(palabraAhorcado);
		
		JPanel letrasOcultasContainer = new JPanel();
		letrasOcultasContainer.setOpaque(false);
		letrasOcultasContainer.setBackground(Color.WHITE);
		letrasOcultasContainer.setBounds(29, 24, 508, 101);
		frmAhorcado.getContentPane().add(letrasOcultasContainer);
		letrasOcultasContainer.setLayout(new GridLayout(1, this.palabra.length(), 10, 3));
		
		JLabel intentosLabel = new JLabel("Intentos:");
		intentosLabel.setFont(new Font("Sitka Text", Font.BOLD, 23));
		intentosLabel.setBounds(365, 208, 111, 30);
		frmAhorcado.getContentPane().add(intentosLabel);
		
		JLabel imgLabel = new JLabel("");
		imgLabel.setIcon(new ImageIcon(Juego.class.getResource("/vistasAhorcado/img/gamebg.png")));
		imgLabel.setBounds(-35, 123, 312, 311);
		frmAhorcado.getContentPane().add(imgLabel);
		
		JLabel nombreLabel = new JLabel("Jugador:");
		nombreLabel.setFont(new Font("Sitka Text", Font.BOLD, 23));
		nombreLabel.setBounds(365, 153, 99, 30);
		frmAhorcado.getContentPane().add(nombreLabel);
		
		JLabel contenidoNombreLabel = new JLabel(ahorcado.getNombreJugador());
		contenidoNombreLabel.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		contenidoNombreLabel.setBounds(465, 156, 103, 27);
		frmAhorcado.getContentPane().add(contenidoNombreLabel);
		
		JLabel contenidoIntentosLabel = new JLabel(String.valueOf(ahorcado.getIntentosRestantesJugador()));
		contenidoIntentosLabel.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		contenidoIntentosLabel.setBounds(475, 208, 103, 27);
		frmAhorcado.getContentPane().add(contenidoIntentosLabel);
	

		frmAhorcado.setBounds(100, 100, 594, 484);
		frmAhorcado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ArrayList<String> letraSecretas = (ArrayList<String>) getLetrasOcultas(palabra);
		
		for (String letraSecreta : letraSecretas) {
			LetraAhorcado temp = new LetraAhorcado(letraSecreta);
			letrasOcultasContainer.add(temp);
		}
		
		JLabel feedbackLabel = new JLabel("");
		feedbackLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feedbackLabel.setBounds(198, 343, 179, 26);
		frmAhorcado.getContentPane().add(feedbackLabel);
		
		inputLetra = new JTextField("",1);
		inputLetra.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (inputLetra.getText().trim().length() == 1 || 
						e.getKeyChar() == java.awt.event.KeyEvent.VK_BACK_SPACE || 
						(c > '0' && c <'9')) {
			        e.consume();
			    }  
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
					String input = inputLetra.getText();
					if(input == "") {
						return;
					}else {
						char letraEnInput = input.charAt(0);
						ingresarLetra(letraEnInput);
					}									
				}
			}
			private void ingresarLetra(char letraEnInput) {
				
				if(ahorcado.getLetrasUsadas().contains(letraEnInput)) {
					inputLetra.setBorder(new LineBorder(new Color(255, 0, 0)));
					feedbackLabel.setForeground(new Color(220, 20, 60));
					feedbackLabel.setText("Ya usaste esa letra!");
					inputLetra.setText("");
					return;
				}
				
				boolean resultado = ahorcado.chequearLetraJugador(letraEnInput);
				
				if(resultado) {
					mostrarPalabrasAcertadas();
					inputLetra.setBorder(new LineBorder(new Color(0, 255, 0)));
					inputLetra.setText("");
					feedbackLabel.setText(Mensaje.getMensajePositivo());
					feedbackLabel.setForeground(new Color(50, 205, 50));
				}else {
					contenidoIntentosLabel.setText(String.valueOf(ahorcado.getIntentosRestantesJugador()));
					inputLetra.setBorder(new LineBorder(new Color(255, 0, 0)));
					inputLetra.setText("");
					feedbackLabel.setText(Mensaje.getMensajeNegativo());
					feedbackLabel.setForeground(new Color(220, 20, 60));
					
				}
				
				isJuegoTerminado();
			}
			
			private void mostrarPalabrasAcertadas() {
				ArrayList<Integer> posicionesDescubiertas = ahorcado.getPosicionesDescubiertas();
				
				for(Integer posicion : posicionesDescubiertas) {
					LetraAhorcado temp = (LetraAhorcado) letrasOcultasContainer.getComponent(posicion);
					temp.mostrar();
				}
			}
			
		});
		inputLetra.setFocusCycleRoot(true);
		inputLetra.setHorizontalAlignment(SwingConstants.CENTER);
		inputLetra.setFont(new Font("Sitka Text", Font.PLAIN, 23));
		inputLetra.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		inputLetra.setBounds(241, 254, 86, 78);
		frmAhorcado.getContentPane().add(inputLetra);
		inputLetra.setColumns(10);
		
		
	
	}
	
	private List<String> getLetrasOcultas(String palabra) {
		ArrayList<String> ret = new ArrayList<>();
		
		for (int i = 0; i < palabra.length(); i++) {
			String l = String.valueOf(palabra.charAt(i));
			ret.add(l);	
		}
		return ret;
	}
	
	private void setPalabra(String palabra) {
		this.palabra = palabra;
	}
	
	private void isJuegoTerminado() {
		String resultado = ahorcado.chequearEstadoDeJuego();
		
		if(resultado == "gano") {
			VictoriaWindow victoria = new VictoriaWindow(ahorcado.getNombreJugador());
			victoria.setVisible(true);
		}
		else if(resultado == "perdio") {
			DerrotaWindow derrota = new DerrotaWindow(palabra);
			derrota.setVisible(true);
		}
		else {
			return;
		}
	}
}
