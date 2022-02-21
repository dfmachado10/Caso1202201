/**
 * 
 */
package src;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.border.LineBorder;

/**
 * @author Gabriel
 *
 */
public class T extends Thread{
	private static final String MENSAJE_FIN = "FIN";
	private int id;
	private Buzon buzonEnviar;
	private Buzon buzonRecibir;
	private boolean reciboActivo;
	private boolean envioActivo;
	private String reciboStr;
	private String envioStr;
	private int tiempoTransformacion;
	private String mensaje;
	private int cantidadMensajes;
	private String [] mensajes;




	public T(int id, int tiempoTransformacion , boolean envioActivo, boolean reciboActivo) {
		super();
		this.id = id;
		this.reciboActivo = reciboActivo;
		reciboStr = reciboActivo ? "Asincrono" : "Sincrono";
		this.envioActivo = envioActivo;
		envioStr = envioActivo ? "Asincrono" : "Sincrono";

		this.tiempoTransformacion = tiempoTransformacion;

		mensaje = "";

	}
	private void recibirAsincrono() {

		//Activo
		while(!buzonRecibir.tieneMensaje()) {
			Thread.yield();
		}
		mensaje = buzonRecibir.retirarMensaje();
		if(!mensaje.equals(MENSAJE_FIN)) {

			transformarMensaje();
		}
		if(envioActivo) {
			enviarAsincrono();
		}
		else {
			enviarSincrono();
		}

	}
	private void recibirSincrono() {

		//Pasivo
		mensaje = buzonRecibir.retirarMensaje();

		if(!mensaje.equals(MENSAJE_FIN)) {

			transformarMensaje();
		}
		if(envioActivo) {
			enviarAsincrono();
		}
		else {
			enviarSincrono();
		}

	}
	private void enviarAsincrono() {
		while(buzonEnviar.getCantidadActual() == buzonEnviar.getCapacidad()) {
			Thread.yield();
		}
		try {

			Thread.sleep(tiempoTransformacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id + " enviando " + mensaje + " al buzon " + buzonEnviar.getID());

		buzonEnviar.guardarMensaje(mensaje);


	}
	private void enviarSincrono( ) {
		try {
			Thread.sleep(tiempoTransformacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(id + " enviando " + mensaje + " al buzon " + buzonEnviar.getID());
		buzonEnviar.guardarMensaje(mensaje);

	}
	public void asignarBuzon(Buzon buzon) {
		buzonEnviar = buzon;

	}
	public void asignarRecibir(Buzon buzon) {
		buzonRecibir = buzon;		
	}


	private void transformarMensaje ( ) {
		mensaje += " ID: " + id + " R: " + reciboStr + " E: " + envioStr + " ";
	}
	@Override
	public void run() {
		if(id == 1) {
			for (int i = 0; i < mensajes.length; i++) {
				mensaje = mensajes[i];
				if(envioActivo) {
					System.out.println("enviando  " + mensajes[i]);
					enviarAsincrono();
				}
				else {
					enviarSincrono();
				}

			}
			mensaje = MENSAJE_FIN;
			if(envioActivo) {
				enviarAsincrono();

			}
			else {
				enviarSincrono();
			}


		}
		while(!mensaje.equals(MENSAJE_FIN)) {
			if(reciboActivo) {
				recibirAsincrono();
			}
			else {
				recibirSincrono();

			}
		}



	}
	
	public void setCantidadMensajes(int i) {
		cantidadMensajes = i;
		mensajes = new String[cantidadMensajes];

		for (int j = 0; j < mensajes.length; j++) {
			mensajes[j] = "Mensaje: " + j + " ";
			int a = 0;
		}

	}

}
