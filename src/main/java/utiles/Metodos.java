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
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.sun.source.tree.DoWhileLoopTree;

import entidades.Espectaculo;
import entidades.Perfil;
import entidades.Sesion;

public class Metodos extends Objetos {

	/*
	 * IMPORTANTE recordar eliminar el directorio ficheros antes de ejecutar el
	 * programa en caso de usar este metodo. Metodo que crea una carpeta donde se
	 * almacenara los archivos creados por el programa y/o externos
	 */
	public static void crearFichero() {
		File fichero = new File("ficheros");
		if (!fichero.exists()) {
			fichero.mkdirs();
			System.out.println("Carpeta " + fichero.getName() + " creada en " + fichero.getAbsolutePath());
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ficheros/espectaculo.dat"))) {
				oos.writeObject(Objetos.espectaculo1);
				oos.writeObject(Objetos.espectaculo2);
				oos.writeObject(Objetos.espectaculo3);
			} catch (IOException | ClassCastException e) {
				System.err.println("Error al guardar el espectáculo: " + e.getMessage());
			}
			System.out.println("Espectaculos.dat creada en " + fichero.getAbsolutePath());
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ficheros/espectaculo.dat"))) {
				Object espectaculoOis = ois.readObject();
				System.out.println(espectaculoOis);
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error al leer Espectaculos.dat" + e.getMessage());
			}
		} else {
			if (fichero.exists()) {
				System.out.println("La carpeta ya existe");
			}
			System.out.println("No se pudo crear la carpeta, compruebe si ya ha sido creada");
		}
	}

	public static void crearEspectaculo(Scanner sc, boolean a) {
		do {
			sc.next();
			System.out.println("Introduzca un nombre para el espectaculo");
			String nombre = sc.nextLine();
			if (nombre.length() > 25 | nombre.length() == 0) {
				System.out.println("Error: El nombre debe tener entre 1 y 25 caracteres");
			} else {
				do {
					sc.next();
					System.out.println("Introduzca fecha inicial");
					String fechaInicial = sc.nextLine();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MM. yyyy");
					LocalDate.parse(fechaInicial,dtf);
					
				} while (a);
			}
		} while (a);
		
		
	}
	
	public static void guardarEspectaculo() {
		
	}

	public static void menuInvitado(Sesion nuevaSesion) {
		System.out.println("Sesion actual: " + nuevaSesion.getNombre());
		System.out.println("1. Login");
		System.out.println("2. Ver espectaculos");
		System.out.println("3. Cerrar programa");

	}

	public static void menuAdmin(Sesion nuevaSesion) {

		System.out.println("Sesion actual: " + nuevaSesion.getNombre());
		System.out.println("1. Gestionar personas y credenciales");
		System.out.println("2. Gestionar espectaculos");
		System.out.println("3. Logout");

	}

	public static void menuArtista(Sesion nuevaSesion) {
		System.out.println("Sesion actual: " + nuevaSesion.getNombre());
		System.out.println("1. Ver ficha");
		System.out.println("2. Logout");

	}

	public static void menuCoordinacion(Sesion nuevaSesion) {
		System.out.println("Sesion actual: " + nuevaSesion.getNombre());
		System.out.println("1. Gestionar Espectaculos");
		System.out.println("2. Logout");

	}

	public static void logOut(Sesion nuevaSesion, String opcionSalir) {
		switch (opcionSalir.toUpperCase()) {
		case "Y": {
			nuevaSesion.setNombre("Invitado");
			nuevaSesion.setPerfil(Perfil.INVITADO);
			break;
		}
		case "N": {
			break;
		}
		default:
			System.out.println("Opcion Invalida, solo se admite Y o N. \n");
		}
	}
	
	public static boolean cerrarPrograma(String opcionSalir) {
		switch (opcionSalir.toUpperCase()) {
		case "Y": {
			System.out.println("Saliendo...");
			return false;
		}
		
		case "N": {
			return true;
		}
		
		case " ": {
		}
		default:
			System.out.println("Opcion Invalida, solo se admite Y o N. \n");
			return true;
		}
	}
	
}
