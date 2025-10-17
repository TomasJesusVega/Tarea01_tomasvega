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
import java.util.HashSet;
import java.util.Set;

import entidades.Espectaculo;
import entidades.Sesion;

public class Metodos extends Objetos {

	/*
	 * IMPORTANTE recordar eliminar el directorio ficheros antes de ejecutar el
	 * programa en caso de usar este metodo. Metodo que crea una carpeta donde
	 * se almacenara los archivos creados por el programa y/o externos
	 */
	public static void crearFichero() {
		File fichero = new File("ficheros");
		if (!fichero.exists()) {
			fichero.mkdirs();
			System.out.println("Carpeta " + fichero.getName() + " creada en "
					+ fichero.getAbsolutePath());
			try (ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("ficheros/espectaculo.dat"))) {
				oos.writeObject(Objetos.espectaculo1);
				oos.writeObject(Objetos.espectaculo2);
				oos.writeObject(Objetos.espectaculo3);
			} catch (IOException | ClassCastException e) {
				System.err.println(
						"Error al guardar el espectáculo: " + e.getMessage());
			}
			System.out.println(
					"Espectaculos.dat creada en " + fichero.getAbsolutePath());
			try (ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("ficheros/espectaculo.dat"))) {
				Object espectaculoOis = ois.readObject();
				System.out.println(espectaculoOis);
			} catch (IOException | ClassNotFoundException e) {
				System.err.println(
						"Error al leer Espectaculos.dat" + e.getMessage());
			}
		} else {
			if (fichero.exists()) {
				System.out.println("La carpeta ya existe");
			}
			System.out.println(
					"No se pudo crear la carpeta, compruebe si ya ha sido creada");
		}
	}

	public static Set<Espectaculo> crearEspectaculo() {
		Set<Espectaculo> espectaculosIniciales = new HashSet<>();
		return espectaculosIniciales;
	}

	public static void mostrarMenuInvitado(Sesion nuevaSesion) {
		System.out.println("Sesion: " + nuevaSesion.getNombre());
		System.out.println("1. Login");
		System.out.println("2. Ver espectaculos");
		System.out.println("3. Salir");

	}

}