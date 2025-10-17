/**
* Class Metodos.java
*
* @author TOMÁS JESÚS VEGA LEIVA
* @version 1.0

*/
package utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import entidades.Espectaculo;
import utiles.Objetos;

public class Metodos extends Objetos {

	/*IMPORTANTE recordar eliminar el directorio ficheros antes de ejecutar el programa en caso de usar este metodo.
	Metodo que crea una carpeta donde se almacenara los archivos creados por el programa y/o externos*/
	public static void crearFichero() {
		File fichero = new File("ficheros");
		if (!fichero.exists()) {
			fichero.mkdirs();
			System.out.println("Carpeta " + fichero.getName() + " creada en " + fichero.getAbsolutePath());
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ficheros/espectaculo.dat"))) {
				oos.writeObject(Objetos.crearEspectaculo());
			} catch (IOException e) {
				System.err.println("Error al guardar el espectáculo: " + e.getMessage());
	            e.printStackTrace();
			}
			System.out.println("Espectaculos.dat creada en " + fichero.getAbsolutePath());
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ficheros/espectaculo.dat"))) {
				Espectaculo espectaculoOis = (Espectaculo) ois.readObject();
				System.out.println(espectaculoOis);
			} catch (IOException  | ClassNotFoundException e) {
				System.err.println("Error al leer Espectaculos.dat" + e.getMessage());
			}
		} else {
			if (fichero.exists()) {
				System.out.println("La carpeta ya existe");
			}
			System.out.println("No se pudo crear la carpeta, compruebe si ya ha sido creada");
		}
	}

}