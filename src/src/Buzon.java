package src;

public class Buzon {
	private char id;
	private int capacidad;
	

	private int cantidadActual;
	private String[] memoria;
	private Object almacenadoresDormidos;
	private Object entregadoresDormidos;
	
	public Buzon(char id, int capacidad) {
		this.id = id;
		this.capacidad = capacidad;
		memoria = new String[capacidad];
		almacenadoresDormidos = new Object();
		entregadoresDormidos = new Object();
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	public void guardarMensaje(String mensaje) {
		System.out.println("El buzon: " + id  + " recibe el mensaje " + mensaje);
		synchronized (almacenadoresDormidos) {
			while(cantidadActual == capacidad ) {
				try {
					almacenadoresDormidos.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			synchronized(entregadoresDormidos) {
				cantidadActual++;
				memoria[cantidadActual-1] = mensaje;
				
				entregadoresDormidos.notify();
			}
		}
	}
	public String retirarMensaje() {
		String mensaje = "";
		synchronized(entregadoresDormidos) {
			while(cantidadActual == 0) {
				try {
					entregadoresDormidos.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized(this) {
				mensaje = memoria[cantidadActual-1];
				memoria[cantidadActual-1] = "";
				cantidadActual--;
				almacenadoresDormidos.notify();
			}
		}
		return mensaje;
		
	}

	public String[] getMemoria() {
		return memoria;
	}

	public boolean tieneMensaje() {
		if(cantidadActual > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getCantidadActual() {
		return cantidadActual;
	}




}
