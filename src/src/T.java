/**
 * 
 */
package src;

/**
 * @author Gabriel
 *
 */
public class T {
	private int id;
	private Buzon buzonEnviar;
	private Buzon buzonRecibir;
	private boolean reciboSincrono;
	private boolean envioSincrono;
	private String reciboStr;
	private String envioStr;
	private int tiempoTransformacion;
	
	
	private void recibirAsincrono() {
		
	}
	private void recibirSincrono() {
		
	}
	private void enviarAsincrono() {
		
	}
	private void enviarSincrono() {
		
	}
	private void transformarMensaje (String mensaje) {
		mensaje += "ID: " + id + " R: " + reciboStr + " E: " + envioStr + "\n";
	}

}
