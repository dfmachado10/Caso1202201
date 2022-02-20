package src;

public class Buzon {
	private char id;
	private int capacidad;
	private int cantidadActual;
	private String[] memoria;
	private T thread;
	public Buzon(char id, int capacidad) {
		this.id = id;
		this.capacidad = capacidad;
		memoria = new String[capacidad];
		System.out.println(id +" " + capacidad);
	}
	
	private void guardarMensaje(String mensaje) {
		while(cantidadActual == capacidad ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
