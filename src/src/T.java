/**
 * 
 */
package src;

import java.util.Iterator;

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
		this.envioActivo = envioActivo;
		this.tiempoTransformacion = tiempoTransformacion;

		mensaje = "";

	}
	private void recibirAsincrono() {
		//Activo
		while(!buzonRecibir.tieneMensaje()) {
			this.yield();
		}
		mensaje = buzonRecibir.retirarMensaje();
		transformarMensaje();
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
		transformarMensaje();
		if(envioActivo) {
			enviarAsincrono();
		}
		else {
			enviarSincrono();
		}

	}
	private void enviarAsincrono() {
		while(buzonEnviar.getCantidadActual() == buzonEnviar.getCapacidad()) {
			System.out.println(buzonEnviar.getMemoria().length);
			System.out.println(buzonEnviar.getCapacidad());
			System.out.println("Validacion");
			Thread.yield();
		}
		try {

			this.sleep(tiempoTransformacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buzonEnviar.guardarMensaje(mensaje);


	}
	private void enviarSincrono( ) {
		try {
			this.sleep(tiempoTransformacion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					enviarAsincrono();
					System.out.println("holi");
				}
				else {
					enviarSincrono();
					System.out.println("holi");
				}

			}
			mensaje = MENSAJE_FIN;
			if(envioActivo) {
				enviarAsincrono();
				
			}
			else {
				enviarSincrono();
				System.out.println("holi");
			}


		}
		while(!mensaje.equals(MENSAJE_FIN)) {
			if(reciboActivo) {
				recibirAsincrono();
				System.out.println("holi");
			}
			else {
				recibirSincrono();
				System.out.println("holi");
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
