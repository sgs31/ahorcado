package vistasAhorcado.menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vistasAhorcado.util.ErrorWindow;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class Configuracion extends JFrame {

	private JPanel contentPane;
	private ButtonGroup dificultadGrupo = new ButtonGroup();
	private ButtonGroup idiomaGrupo = new ButtonGroup();
	private String dificultad;
	private String idioma;
	private String nombreJugador;
	//
	public Configuracion() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Configuracion.class.getResource("/vistasAhorcado/img/config-icon.png")));
		setTitle("Configuracion");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 247, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jugadorLabel = new JLabel("Nombre");
		jugadorLabel.setFont(new Font("Sitka Text", Font.PLAIN, 17));
		jugadorLabel.setBounds(23, 30, 71, 22);
		contentPane.add(jugadorLabel);
		
		JTextField inputNombre = new JTextField();
		
		inputNombre.setBounds(103, 29, 93, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		JLabel dificultadLabel = new JLabel("Dificultad");
		dificultadLabel.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		dificultadLabel.setBounds(23, 73, 71, 20);
		contentPane.add(dificultadLabel);

		JRadioButton facilRadioButton = new JRadioButton("Facil");
		facilRadioButton.setActionCommand("facil");
		facilRadioButton.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		facilRadioButton.setBounds(23, 100, 49, 23);
		contentPane.add(facilRadioButton);

		JRadioButton moderadoRadioButton = new JRadioButton("Moderado");
		moderadoRadioButton.setActionCommand("moderado");
		moderadoRadioButton.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		moderadoRadioButton.setBounds(23, 126, 77, 23);
		contentPane.add(moderadoRadioButton);

		JRadioButton dificilRadioButton = new JRadioButton("Dificil");
		dificilRadioButton.setActionCommand("dificil");
		dificilRadioButton.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		dificilRadioButton.setBounds(23, 153, 57, 23);
		contentPane.add(dificilRadioButton);

		dificultadGrupo.add(facilRadioButton);
		dificultadGrupo.add(moderadoRadioButton);
		dificultadGrupo.add(dificilRadioButton);

		JLabel idiomaLabel = new JLabel("Idioma");
		idiomaLabel.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		idiomaLabel.setBounds(131, 73, 49, 20);
		contentPane.add(idiomaLabel);

		JRadioButton espanolRadioButton = new JRadioButton("Espanol");
		espanolRadioButton.setActionCommand("espaniol");
		espanolRadioButton.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		espanolRadioButton.setBounds(131, 100, 65, 23);
		contentPane.add(espanolRadioButton);

		JRadioButton inglesRadioButton = new JRadioButton("Ingles");
		inglesRadioButton.setActionCommand("ingles");
		inglesRadioButton.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		inglesRadioButton.setBounds(131, 126, 57, 23);
		contentPane.add(inglesRadioButton);

		idiomaGrupo.add(espanolRadioButton);
		idiomaGrupo.add(inglesRadioButton);

		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (idiomaGrupo.getSelection() == null || dificultadGrupo.getSelection() == null) {
					ErrorWindow alerta = new ErrorWindow("Error",
							"Debe completar la configuracion para continuar.");
					alerta.setVisible(true);
				} else {
					final String idiomaSeleccionado = idiomaGrupo.getSelection().getGroup().getSelection()
							.getActionCommand();
					final String dificultadSeleccionada = dificultadGrupo.getSelection().getGroup().getSelection()
							.getActionCommand();
					final String nombreSeleccionado = inputNombre.getText();
					setIdioma(idiomaSeleccionado);
					setDificultad(dificultadSeleccionada);
					setNombreJugador(nombreSeleccionado);
					dispose();
				}

			}

			
		});
		aceptarButton.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		aceptarButton.setBounds(10, 196, 100, 25);
		contentPane.add(aceptarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelarButton.setFont(new Font("Sitka Text", Font.PLAIN, 13));
		cancelarButton.setBounds(120, 196, 100, 25);
		contentPane.add(cancelarButton);
		
	}
	
	public String getNombreJugador() {
		return nombreJugador;
	}

	public String getDificultad() {
		return dificultad;
	}

	public String getIdioma() {
		return idioma;
	}

	private void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	private void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	
	private void setNombreJugador(String nombreSeleccionado) {
		this.nombreJugador = nombreSeleccionado;
	}

	public boolean estaSeteado() {
		return dificultad != null && idioma != null;
	}

	public void mostrar() {
		this.setVisible(true);
	}
}
