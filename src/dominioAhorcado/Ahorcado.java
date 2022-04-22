package dominioAhorcado;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ahorcado {
	
	private Jugador jugador;
	private String palabraSeleccionada;
	private ArrayList<Character> letrasUsadas;
	private ArrayList<Integer> posicionesDescubiertas;
	private boolean juegoEnCurso;

	public Ahorcado(String nombre, String dificultadJuego, String idioma) {
		jugador = new Jugador(nombre);
		palabraSeleccionada = seleccionarPalabraDeLista(dificultadJuego, idioma);
		letrasUsadas = new ArrayList<Character>();
		posicionesDescubiertas = new ArrayList<Integer>();
		juegoEnCurso = true;
	}

	public static String seleccionarPalabraDeLista(String dificultadJuego, String idioma) {

		ArrayList<String> listaDePalabras = generarListaPalabras(dificultadJuego, idioma);

		Random random = new Random();

		return listaDePalabras.get(random.nextInt(listaDePalabras.size()));
	}

	public static ArrayList<String> generarListaPalabras(String dificultadJuego, String idioma) {

		ArrayList<String> listaDePalabrasTotales = new ArrayList<String>();

		if (idioma.equals("espaniol")) {
			listaDePalabrasTotales.add("moto");
			listaDePalabrasTotales.add("gato");
			listaDePalabrasTotales.add("casa");
			listaDePalabrasTotales.add("auto");
			listaDePalabrasTotales.add("pato");
			listaDePalabrasTotales.add("galleta");
			listaDePalabrasTotales.add("elastico");
			listaDePalabrasTotales.add("cohete");
			listaDePalabrasTotales.add("triciclo");
			listaDePalabrasTotales.add("mercado");
			listaDePalabrasTotales.add("electromagnetico");
			listaDePalabrasTotales.add("hipertiroidismo");
			listaDePalabrasTotales.add("gigantosaurus");
			listaDePalabrasTotales.add("mimetizacion");
			listaDePalabrasTotales.add("paleontologo");
		} else {
			listaDePalabrasTotales.add("cat");
			listaDePalabrasTotales.add("car");
			listaDePalabrasTotales.add("hope");
			listaDePalabrasTotales.add("stop");
			listaDePalabrasTotales.add("moon");
			listaDePalabrasTotales.add("cookie");
			listaDePalabrasTotales.add("tangerine");
			listaDePalabrasTotales.add("plane");
			listaDePalabrasTotales.add("motorbike");
			listaDePalabrasTotales.add("computer");
			listaDePalabrasTotales.add("hopelessness");
			listaDePalabrasTotales.add("hyperfunctional");
			listaDePalabrasTotales.add("tyranosaurus");
			listaDePalabrasTotales.add("hippopotamus");
			listaDePalabrasTotales.add("kaleidoscope");
		}
		return filtrarPalabrasPorDificultad(dificultadJuego, listaDePalabrasTotales);
	}

	public static ArrayList<String> filtrarPalabrasPorDificultad(String dificultadJuego,
			ArrayList<String> listaDePalabrasTotales) {
		ArrayList<String> listaPalabrasFiltradasPorIdioma = new ArrayList<String>();

		for (String palabra : listaDePalabrasTotales) {
			if (dificultadJuego.equals("facil")) {
				if (palabra.length() < 5)
					listaPalabrasFiltradasPorIdioma.add(palabra);
			}

			else if (dificultadJuego.equals("moderado")) {
				if (palabra.length() > 4 && palabra.length() < 10)
					listaPalabrasFiltradasPorIdioma.add(palabra);
			}

			else if (dificultadJuego.equals("dificil")) {
				if (palabra.length() > 9)
					listaPalabrasFiltradasPorIdioma.add(palabra);
			}
		}
		return listaPalabrasFiltradasPorIdioma;
	}

	private char normalizarLetra(char letraDelJugador) {
		return letraDelJugador = Character.toLowerCase(letraDelJugador);
	}
	
	public void chequearLetraNoRepetida(char letraDelJugador) throws IllegalArgumentException {
		if (letrasUsadas.contains(letraDelJugador))
			throw new IllegalArgumentException("Ya has utilizado esta letra!");
	}

	public boolean chequearLetraJugador(char letraDelJugador) {

		letraDelJugador = normalizarLetra(letraDelJugador);
		
		chequearLetraNoRepetida(letraDelJugador);

		boolean acerto = false;

		letrasUsadas.add(letraDelJugador);

		for (int i = 0; i < palabraSeleccionada.length(); i++) {
			if (palabraSeleccionada.charAt(i) == letraDelJugador) {
				posicionesDescubiertas.add(i);
				acerto = true;
			}

		}
		if (acerto == false) {
			jugador.descontarIntentos();
		}
		return acerto;
	}

	public String chequearEstadoDeJuego() {

		String nombreDeEstado = "en progreso";

		if (jugador.getIntentos() == 0) {
			nombreDeEstado = "perdio";
			juegoEnCurso = false;
		} else if (palabraSeleccionada.length() == posicionesDescubiertas.size()) {
			nombreDeEstado = "gano";
			juegoEnCurso = false;
		}
		return nombreDeEstado;
	}

	public boolean getJuegoEnCurso() {
		return juegoEnCurso;
	}

	public String getNombreJugador() {
		return jugador.getNombre();
	}
	//
	public int getIntentosRestantesJugador() {
		return this.jugador.getIntentos();
	}

	public ArrayList<Integer> getPosicionesDescubiertas() {
		return posicionesDescubiertas;
	}

	public ArrayList<Character> getLetrasUsadas() {
		return letrasUsadas;
	}

	public String getPalabraSeleccionada() {
		return palabraSeleccionada;
	}

	public void setPalabraSeleccionada(String nuevaPalabra) {
		this.palabraSeleccionada = nuevaPalabra;
	}
}
