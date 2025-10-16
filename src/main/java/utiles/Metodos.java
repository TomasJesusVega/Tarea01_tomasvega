/**
* Class Metodos.java
*
* @author TOMÁS JESÚS VEGA LEIVA
* @version 1.0

*/
package utiles;

import java.io.File;

public class Metodos {

	// Metodo que crea una carpeta donde se almacenara los archivos creados por
	// el programa y/o externos
	public static void crearFichero() {
		File fichero = new File("ficheros");
		if (!fichero.exists()) {
			fichero.mkdirs();
			System.out.println("Carpeta " + fichero.getName() + " creada en "
					+ fichero.getAbsolutePath());
		} else {
			System.out.println("No se pudo crear la carpeta o ya existe.");
		}
	}
	
	//Metodo que crea un archivo binario donde se guardara el objeto persona
	public static void crearEspectaculo() {
		
	}
}
