package vistasAhorcado.juego;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class LetraAhorcado extends JLabel {
	//
	private String letra;
	
	public LetraAhorcado(String letra) {
		
		this.letra = letra;
		setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(0, 0, 0)));
		setFont(new Font("Sitka Text", Font.ITALIC, 18));
		setText("");
		setBackground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public String getLetra() {
		return this.letra;
	}
	
	public void mostrar() {
		this.setText(letra);
	}
	
	public void ocultar() {
		this.setText("");
	}
	
}
