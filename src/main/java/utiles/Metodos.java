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
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

import entidades.Credenciales;
import entidades.Perfil;
import entidades.Sesion;

public class Metodos extends Objetos {

	/*
	 * IMPORTANTE recordar eliminar el directorio ficheros antes de ejecutar el
	 * programa en caso de usar este metodo. Metodo que crea una carpeta donde se
	 * almacenara los archivos creados por el programa y/o externos
	 */
	
	private static File fichero = new File("ficheros");
	private static File ArchivoCredenciales = new File("creadenciales.txt");
	private static String rutaEspectaculo = "ficheros/espectaculo.dat";
	private static String rutaCredenciales = "ficheros/credenciales.txt";

	public static void crearFichero() {
		if (!fichero.exists()) {
			fichero.mkdirs();
			System.out.println("Carpeta " + fichero.getName() + " creada en " + fichero.getAbsolutePath());
		} else {
			System.out.println("No se pudo crear la carpeta, compruebe si ya ha sido creada");
		}
	}

	public static void crearEspectaculosIniciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
		} else if (fichero.exists()) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaEspectaculo))) {
					oos.writeObject(Objetos.espectaculos);
					System.out.println("Espectaculos.dat creada en " + fichero.getAbsolutePath());
				} catch (IOException | ClassCastException e) {
					System.err.println("Error al crear los espectaculos en espectaculo.dat: " + e.getMessage());
				}
			}
		}

	public static void crearCredencialesIniciales( ) {
		if(!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
		} else if(fichero.exists()) {
				try (FileWriter writer = new FileWriter(rutaCredenciales)){
					for (Credenciales i:Objetos.credenciales) {
						
					}
				} catch (IOException e) {
					System.err.println("Error al crear las credenciales en credendiales.dat " + e.getMessage());
				}
			}
		}
	//CU1 ver espectaculos
	public static void leerEspectaculos() {
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaEspectaculo))) {
			Object espectaculoOis = ois.readObject();
			System.out.println(espectaculoOis);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer Espectaculos.dat" + e.getMessage());
		}
	}
	
	//CU2 Login
	public static void validarLogin(Scanner sc) {
		
	}
	
	//CU2 LogOut
	public static void validarlogOut(Sesion nuevaSesion, String opcionSalir) {
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

	//CU2 cerrar programa
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
	//CU5 a.Crear espectaculo
	public static void validarNombreEspectaculo(Scanner sc) {
		String nombre;
		do {
			System.out.println("Introduzca un nombre para el espectaculo entre 1 y 25 caracteres:");
			nombre = sc.nextLine();
			if (nombre.length() > 25 || nombre.length() == 0 ) {
				System.out.println("Error al registrar el nombre, debe tener entre 1 y 25 caracteres");
			} else if(nombre.isEmpty()){
				System.out.println();
			}
		} while (nombre.isEmpty() && nombre.length() > 25);
	}
	
	//CU5 a.Crear espectaculo
	public static void validarFechaEspectaculo(Scanner sc) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = null;
		LocalDate fechaInicial = null;
		LocalDate fechaFinal = null;
		Period duracion = Period.between(fechaInicial, fechaFinal);
		
		do {
			try {
				System.out.println("Introduzca la fecha de inicio del espectaculo");
				fecha = sc.nextLine();
				fechaInicial = LocalDate.parse(fecha, dtf);
				sc.nextLine();
				System.out.println("Introduzca la fecha final del espectaculo");
				fecha = sc.nextLine();
				fechaFinal = LocalDate.parse(fecha, dtf);
				if (fechaFinal.isBefore(fechaInicial)) {
					System.out.println("La fecha final no puede ser anterior a la fecha inicial del espectaculo");
				}
				if(duracion.getYears() >= 1) {
					System.out.println("La duracion del espectaculo no puede exceder el anio");
				}
			} catch (DateTimeParseException e) {
				System.err.println("Error en el formato de las fechas " + e.getMessage());
			}
		} while (fechaInicial == null && fechaFinal == null);
	}

	public static void validarGestionEspectaculo() {
		
	}

	

	
	// Metodos menu
	public static void menuInvitado(Sesion nuevaSesion) {
		System.out.println("Sesion actual: " + nuevaSesion.getNombre());
		System.out.println("1. Login");
		System.out.println("2. Ver espectaculos");
		System.out.println("3. Cerrar programa");

	}
	
	public static void menuInvitadoLogin() {
		System.out.println("Inicio de Sesion");
		System.out.println("Usuario: ");
		System.out.println("Contrasenia: ");

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
	
	//Agrupacion de metodos
	public static void iniciarPrograma() {
		crearFichero();
		crearEspectaculosIniciales();
		crearCredencialesIniciales();
	}
	
	public static void crearespectaculo(Scanner sc) {
		validarNombreEspectaculo(sc);
		validarFechaEspectaculo(sc);
	}
}
