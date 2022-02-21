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
		
		while(buzonEnviar.getMemoria().length == buzonEnviar.getCapacidad()) {
			this.yield();
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
		mensaje += "ID: " + id + " R: " + reciboStr + " E: " + envioStr + "\n";
	}
	@Override
	public void run() {
		while(!mensaje.equals("FIN")) {
			if(id == 1) {
				
				if(envioActivo) {
					enviarAsincrono();
					System.out.println("holi");
				}
				else {
					enviarSincrono();
					System.out.println("holi");
				}

			}
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
		
	}

}
