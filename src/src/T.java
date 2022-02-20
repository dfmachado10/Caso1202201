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
	
	
	
	
	public T(int id, int tiempoTransformacion , boolean envioActivo, boolean reciboActivo) {
		super();
		this.id = id;
		this.reciboActivo = reciboActivo;
		this.envioActivo = envioActivo;
		this.tiempoTransformacion = tiempoTransformacion;
	}
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
