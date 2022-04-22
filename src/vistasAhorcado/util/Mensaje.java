package vistasAhorcado.util;

import java.util.Random;

public class Mensaje {
	//
	public static String getMensajePositivo() {
		
		String[] mensajesPositivos = {"Bien hecho!", "Ya casi", "Un paso mas..", "Dale que se salva"};
		
		Random random = new Random();
		String mensajePositivo = mensajesPositivos[random.nextInt(mensajesPositivos.length)];
		
		return mensajePositivo;
	}
	
	public static String getMensajeNegativo() {
		
		String[] mensajesNegativos = {"Ups!", "Fallaste..", "Se ahoga el stickman!!", ":("};
		
		Random random = new Random();
		String mensajeNegativo = mensajesNegativos[random.nextInt(mensajesNegativos.length)];
		
		return mensajeNegativo;
	}
}
