/**
* Class Metodos.java
*
* @author TOMÁS JESÚS VEGA LEIVA
* @version 1.0

*/
package utiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

	private static ArrayList<String> datosPersonales = new ArrayList<>();
			
		
	
	private static File fichero = new File("ficheros");
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

	public static void aniadirCredenciales() {
		datosPersonales.add("1 | Usuario | Pass | usuario@email.com | Nombre Apellido | Pais | Perfil");
		}
	

	public static void crearCredenciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
		} else if (fichero.exists()) {
			try (FileWriter writer = new FileWriter(rutaCredenciales)) {
				for (String a: datosPersonales) {
					writer.write(a);
				}
				System.out.println("Credenciales aniadidas"); //borrar al final
			} catch (IOException e) {
				System.err.println("Error al crear las credenciales en credendiales.dat " + e.getMessage());
			}
		}
	}
	
	public static void leerCredenciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
		} else if (fichero.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(rutaCredenciales))){
				
			} catch (IOException e) {
				System.err.println("Error al crear las credenciales en credendiales.txt " + e.getMessage());
			}
		}
	}

	// CU1 ver espectaculos
	public static void leerEspectaculos() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaEspectaculo))) {
			Object espectaculoOis = ois.readObject();
			System.out.println(espectaculoOis);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer Espectaculos.dat" + e.getMessage());
		}
	}

	// CU2 Login
	public static void validarLoginUsuario(Scanner sc) {
		boolean esValido = false;
		String usuario;
		
		do {
			System.out.println("Introduzca nombre de usuario");
			usuario = sc.nextLine().trim();
			if (!usuario.matches("[a-zA-Z]*")) {
				System.err.println("Error al introducir el usuario");
				System.out.println("Asegurese de usar caracteres validos! (caracteres de la A a la Z) \n");
				esValido = true;
			} else if (usuario.length() < 2) {
				System.err.println("Error al introducier el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
			}
			
		} while (!esValido);
	}

	//CU2 Login
	public static void validarLoginContrasenia(Scanner sc) {
		boolean esValido = false;
		String contrasenia;
		do {
			System.out.println("Introduzca la contrasenia");
			contrasenia = sc.nextLine().trim();
			if (contrasenia.length() < 2) {
				System.err.println("Error al introducier la contrasenia");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
			} else {
				esValido = true;
			}
		} while (!esValido);
	}
	
	// CU2 LogOut
	public static void validarlogOut(Sesion nuevaSesion, String opcionSalir) {
		boolean esValido = false;
		do {
			System.out.println("Desea cerrar la sesion? Y para si, N para no");
			switch (opcionSalir.toUpperCase()) {
			case "Y": {
				nuevaSesion.setNombre("Invitado");
				nuevaSesion.setPerfil(Perfil.INVITADO);
				esValido = true;
				break;
			}
			case "N": {
				esValido = true;
				break;
			}
			case " ": {
			}
			default:
				System.out.println("Opcion Invalida, solo se admite Y o N. \n");
			}
		} while (!esValido);
	}

	
	// CU2 cerrar programa
	public static boolean cerrarPrograma(Scanner sc) {
		String opcionSalir = null;
		do {
			System.out.println("Desea cerrar el programa? Y para si, N para no");
			opcionSalir = sc.nextLine().toUpperCase().trim();
			switch (opcionSalir) {
			case "Y": {
				System.out.println("Saliendo...");
				return false;
			}
			case "N": {
				return true;
			}
			case " ": {
				System.out.println("No puedes dejar en blanco este espcio");
			}
			default:
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de usar caracteres validos! (Y o N.) \n");
			}
			return true;
		} while (true);
	}

	//CU3 Registrar persona
	public static void validarEmail(Scanner sc) {
		boolean esValido = false;
		String email;
			do {
				System.out.println("Introduzca un Email");
				email = sc.nextLine().trim();
				for(int i = 0; i < email.length(); i++) {
					
				}
			} while (!esValido);
	}
	
	public static void validadNombre() {
		
	}
	
	public static void validadNacionalidad() {
		
	}
	// CU5 a.Crear espectaculo
	public static void validarNombreEspectaculo(Scanner sc) {
		String nombre;
		do {
			System.out.println("Introduzca un nombre para el espectaculo entre 1 y 25 caracteres:");
			nombre = sc.nextLine();
			if (nombre.length() > 25 || nombre.length() == 0) {
				System.out.println("Error al registrar el nombre, debe tener entre 1 y 25 caracteres");
			} else if (nombre.isEmpty()) {
				System.out.println();
			}
		} while (nombre.isEmpty() && nombre.length() > 25);
	}

	// CU5 a.Crear espectaculo
	public static void validarFechaEspectaculo(Scanner sc) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fecha = null;
		LocalDate fechaInicial = null;
		LocalDate fechaFinal = null;
		boolean esValido = false;

		do {
			try {
				System.out.println("Introduzca la fecha de inicio del espectaculo");
				fecha = sc.nextLine().trim();
				fechaInicial = LocalDate.parse(fecha, dtf);

				System.out.println("Introduzca la fecha final del espectaculo");
				fecha = sc.nextLine().trim();
				fechaFinal = LocalDate.parse(fecha, dtf);

				Period duracion = Period.between(fechaInicial, fechaFinal);

				if (fechaFinal.isBefore(fechaInicial)) {
					System.out.println("La fecha final no puede ser anterior a la fecha inicial del espectaculo");
				} else if (duracion.getYears() >= 1) {
					System.out.println("La duracion del espectaculo no puede exceder el anio");
				} else {
					System.out.println("Espectaculo aniadido con exito");
					esValido = true;
				}

			} catch (DateTimeParseException e) {
				System.err.println("Error en el formato de las fechas ");
				System.out.println("Asegurese de usar el formato correcto! (dd-mm-aaaa)");
			}
		} while (!esValido);
	}

	public static void validarGestionEspectaculo() {

	}

	public static void consumirLinea(Scanner sc) {
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
	}
	
	public static void nuevoId() {
		
	}
	
	// Metodos menu
	// IMPORTANTE nextInt no consume linea de scanner, usar metodo consumirLinea
	// para que no salte exepcion despues de llamar a un metodo menu
	public static int menuInvitado(Scanner sc, int opcion) {
		boolean esValido = false;
		do {
			try {
				System.out.println("Introduzca el numero al lado de la opcion para seleccionarla");
				System.out.println("1. Login");
				System.out.println("2. Ver espectaculos");
				System.out.println("3. Cerrar programa");
				opcion = sc.nextInt();
				if (opcion > 0 && opcion <= 3) {
					esValido = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 3)");
				sc.nextLine();
			}
		} while (!esValido);
		return opcion;
	}

	public static void menuInvitadoLogin(Scanner sc) {
		boolean esValido = false;
		String usuario = null;
		String contrasenia = null;
		do {
			try {
				System.out.println("Inicio de Sesion");
				System.out.println("Introduzca el nombre Usuario:");
				usuario = sc.nextLine().trim();
				System.out.println("Introduzca la contrasenia :");
				contrasenia = sc.nextLine().trim();
				System.out.println(usuario);
				System.out.println(contrasenia);
				esValido = true;
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (cualquier caracter?)");
			}
		} while (!esValido);
		
	}

	public static int menuAdmin(Scanner sc, int opcion) {
		boolean esValido = false;
		do {
			try {
				System.out.println("Introduzca el numero al lado de la opcion para seleccionarla");
				System.out.println("1. Gestionar personas y credenciales");
				System.out.println("2. Gestionar espectaculos");
				System.out.println("3. Logout");
				opcion = sc.nextInt();
				if (opcion > 0 && opcion <= 3) {
					esValido = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 3)");
				sc.nextLine();
			}
		} while (!esValido);
		return opcion;
	}

	public static int menuArtista(Scanner sc, int opcion) {
		boolean esValido = false;
		do {
			try {
				System.out.println("Introduzca el numero al lado de la opcion para seleccionarla");
				System.out.println("1. Ver ficha");
				System.out.println("2. Logout");
				opcion = sc.nextInt();
				if (opcion > 0 && opcion <= 3) {
					esValido = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 2)");
				sc.nextLine();
			}
		} while (!esValido);
		return opcion;
	}

	public static int menuCoordinacion(Scanner sc, int opcion) {
		boolean esValido = false;
		do {
			try {
				System.out.println("Introduzca el numero al lado de la opcion para seleccionarla");
				System.out.println("1. Gestionar Espectaculos");
				System.out.println("2. Logout");
				opcion = sc.nextInt();
				if (opcion > 0 && opcion <= 3) {
					esValido = true;
				}
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 2)");
				sc.nextLine();
			}
		} while (!esValido);
		return opcion;
	}

	// Agrupacion de metodos
	public static void iniciarPrograma() {
		crearFichero();
		crearEspectaculosIniciales();
		aniadirCredenciales();
		crearCredenciales();
	}

	public static void crearespectaculo(Scanner sc) {
		validarNombreEspectaculo(sc);
		validarFechaEspectaculo(sc);
	}
}
