package dominioAhorcado;

public class Jugador {
	
		String nombre;
		int intentos = 5;

		public Jugador(String nombre) {
			this.nombre = nombre;
		}

		public int descontarIntentos() {
			return intentos--;
		}

		public String getNombre() {
			return nombre;
		}

		public int getIntentos() {
			return intentos;
		}
}
