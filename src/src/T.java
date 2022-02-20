/**
 * 
 */
package src;

/**
 * @author Gabriel
 *
 */
public class T extends Thread{
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


	}
	private void enviarSincrono( ) {

	}
	public void asignarBuzon(Buzon buzon) {
		buzonEnviar = buzon;
		
	}
	public void asignarRecibir(Buzon buzon) {
		buzonRecibir = buzon;		
	}
	private void transformarMensaje ( ) {
		mensaje += "ID: " + id + " R: " + reciboStr + " E: " + envioStr + "\n";
	}
	@Override
	public void run() {
		while(!mensaje.equals("FIN")) {
			if(id == 1) {
				
			}
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
		
	}

}
