/**
 * 
 */
package src;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;


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
		try ( 
				FileInputStream is= new FileInputStream("docs/configuracion.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(is))
				) {


			for (int i = 0; i < 4; i++) {
				String line = br.readLine();
				String[] buzon = line.split(" ");
				buzones[i] = new Buzon(buzon[0].charAt(0), Integer.parseInt(buzon[1]));
			}
			for (int i = 0; i < 4; i++) {
				String line = br.readLine();
				String[] proceso = line.split(" ");
				int id = Integer.parseInt(proceso[0]);
				int tiempoEspera = Integer.parseInt(proceso[1]);
				boolean envioActivo = Boolean.parseBoolean(proceso[2]);
				boolean reciboActivo = Boolean.parseBoolean(proceso[3]);
				procesos[i] = new T(id, tiempoEspera, envioActivo, reciboActivo);
				procesos[i].asignarBuzon(buzones[i]);
				if(i == 0) {
					procesos[i].asignarRecibir(buzones[3]);
				}
				else {
					procesos[i].asignarRecibir(buzones[i-1]);
				}
				
			}
			pedirMensajes();
			
			for (int i = 0; i < 4; i++) {
				procesos[i].start();
			}
			
			T[] copia1 = procesos;
			Buzon[] copia2 = buzones;
			br.close();
			is.close();
			
			

		}





	}


	private static void pedirMensajes() {
		System.out.println("Ingrese el numero de mensajes a mandar: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = Integer.parseInt(s);
		procesos[0].setCantidadMensajes(i);
	}




}
