package src;

import java.util.LinkedList;
import java.util.Queue;

public class Buzon {
	private char id;
	private int capacidad;
	

	private int cantidadActual;
	Queue<String> memoria ;
	private Object almacenadoresDormidos;
	private Object entregadoresDormidos;
	
	public Buzon(char id, int capacidad) {
		this.id = id;
		this.capacidad = capacidad;
		memoria = new LinkedList<String>();
		almacenadoresDormidos = new Object();
		entregadoresDormidos = new Object();
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	public void guardarMensaje(String mensaje) {
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
				memoria.add(mensaje);
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
			synchronized(almacenadoresDormidos) {
				mensaje = memoria.poll();
				cantidadActual--;
				almacenadoresDormidos.notify();
			}
		}
		return mensaje;
		
	}

	public Queue<String> getMemoria() {
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

	public char getID() {
		return id;
	}




}
