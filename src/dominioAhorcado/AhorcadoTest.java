package dominioAhorcado;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

class AhorcadoTest {
	//
	@Test
	public void crearListaTest() {

		Ahorcado juego = new Ahorcado("Mica", "dificil", "ingles");

		assertEquals(5, juego.generarListaPalabras("dificil", "ingles").size());
		assertTrue(juego.generarListaPalabras("dificil", "ingles").contains("hippopotamus"));
	}

	@Test
	public void seleccionarPalabraDeListaTest() {

		ArrayList<String> listaPalabras = new ArrayList<String>(Ahorcado.generarListaPalabras("moderado", "espaniol"));
		String palabraSeleccionada = Ahorcado.seleccionarPalabraDeLista("moderado", "espaniol");

		assertTrue(listaPalabras.contains(palabraSeleccionada));
	}
	
	@Test
	public void dificultadFacilTest() {
		Ahorcado juego = new Ahorcado("Mica", "facil", "espaniol");
	
		assertEquals(4,juego.getPalabraSeleccionada().length());
	}
	
	public void dificultadDificilTest() {
		Ahorcado juego = new Ahorcado("Mica", "dificil", "espaniol");
	
		assertTrue(juego.getPalabraSeleccionada().length() > 9);
	}


	@Test
	public void cometioAciertoTest() {

		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");
		
		juego.setPalabraSeleccionada("pato");
		juego.chequearLetraJugador('a');
		juego.chequearLetraJugador('p');

		assertTrue(juego.getLetrasUsadas().contains('a'));
		assertTrue(juego.getLetrasUsadas().contains('p'));
		assertEquals(2, juego.getLetrasUsadas().size());
		assertTrue(juego.getPosicionesDescubiertas().contains(0));
		assertTrue(juego.getPosicionesDescubiertas().contains(1));
		assertEquals(2,juego.getPosicionesDescubiertas().size());
		assertEquals(5, juego.getIntentosRestantesJugador());
		
		assertEquals("en progreso", juego.chequearEstadoDeJuego());
	}

	@Test(expected = IllegalArgumentException.class)
	public void ingresarLetraRepetidaTest() {

		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		juego.chequearLetraJugador('a');
		juego.chequearLetraNoRepetida('a');
	}

	@Test
	public void ingresarLetraMayuscula() {

		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		juego.setPalabraSeleccionada("pato");
		
		juego.chequearLetraJugador('A');
		assertTrue(juego.getLetrasUsadas().contains('a'));
	}

	@Test
	public void cometioErrorTest() {
		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		juego.chequearLetraJugador('z');

		assertEquals(4, juego.getIntentosRestantesJugador());
		assertTrue(juego.getLetrasUsadas().contains('z'));
		assertEquals(1,juego.getLetrasUsadas().size());
	}

	@Test
	public void devolverLetrasAcertadasTest() {
		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		juego.setPalabraSeleccionada("pato");
		juego.chequearLetraJugador('p');

		assertTrue(juego.getPosicionesDescubiertas().contains(0));
	}

	@Test
	public void chequearEstadoDelJuegoGanadoTest() {
		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		String resultadoEsperado = "gano";
		juego.setPalabraSeleccionada("pato");
		juego.chequearLetraJugador('p');
		juego.chequearLetraJugador('a');
		juego.chequearLetraJugador('t');
		juego.chequearLetraJugador('o');
		juego.chequearEstadoDeJuego();

		assertEquals(resultadoEsperado, juego.chequearEstadoDeJuego());
	}

	@Test
	public void chequearEstadoDelJuegoEnProgresoTest() {
		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");

		juego.setPalabraSeleccionada("pato");
		juego.chequearLetraJugador('o');
		juego.chequearLetraJugador('j');
		juego.chequearLetraJugador('h');
		juego.chequearLetraJugador('a');
	
		assertEquals(4, juego.getLetrasUsadas().size());
		assertEquals(2, juego.getPosicionesDescubiertas().size());
		assertTrue(juego.getPosicionesDescubiertas().contains(1));
		assertTrue(juego.getPosicionesDescubiertas().contains(3));
		assertEquals(3,juego.getIntentosRestantesJugador());
		assertEquals("en progreso", juego.chequearEstadoDeJuego());
		
	}

	@Test
	public void chequearEstadoDelJuegoPerdidoTest() {
		Ahorcado juego = new Ahorcado("Mica", "moderado", "espaniol");
		
		juego.setPalabraSeleccionada("pato");
		juego.chequearLetraJugador('r');
		juego.chequearLetraJugador('y');
		juego.chequearLetraJugador('t');
		juego.chequearLetraJugador('e');
		juego.chequearLetraJugador('ñ');
		juego.chequearLetraJugador('i');

		assertEquals(6, juego.getLetrasUsadas().size());
		assertEquals(1, juego.getPosicionesDescubiertas().size());
		assertTrue(juego.getPosicionesDescubiertas().contains(2));
		assertEquals(0,juego.getIntentosRestantesJugador());
		assertEquals("perdio", juego.chequearEstadoDeJuego());
	}

}
