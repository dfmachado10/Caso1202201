/**
 * 
 */
package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;


/**
 * @author Gabriel
 *
 */
public class Principal {
	
	private static T[] procesos = new T[4];
	private static Buzon[] buzones = new Buzon[4];
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Principal instancia = new Principal();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String lines = br.readLine();
			while (lines!=null) {
				System.out.println(lines);
				lines = br.readLine();
			}

			for (int i = 0; i < 4; i++) {
				String line = br.readLine();
				String[] buzon = line.split(" ");
				System.out.println(Arrays.toString(buzon));
				buzones[i] = new Buzon(buzon[0].charAt(0), Integer.parseInt(buzon[1]));
			}
			for (int i = 0; i < 4; i++) {
				String line = br.readLine();
				String[] proceso = line.split(" ");
				System.out.println(Arrays.toString(proceso));
				int id = Integer.parseInt(proceso[0]);
				int tiempoEspera = Integer.parseInt(proceso[1]);
				boolean envioActivo = Boolean.parseBoolean(proceso[2]);
				boolean reciboActivo = Boolean.parseBoolean(proceso[3]);
				procesos[i] = new T(id, tiempoEspera, envioActivo, reciboActivo);
			}

			
		}

	}


	

}
