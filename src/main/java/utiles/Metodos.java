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
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import entidades.Perfil;
import entidades.Sesion;

public class Metodos extends Objetos {

	/*
	 * IMPORTANTE recordar eliminar el directorio ficheros antes de ejecutar el
	 * programa en caso de usar este metodo. Metodo que crea una carpeta donde se
	 * almacenara los archivos creados por el programa y/o externos
	 */

	private static ArrayList<String> listaCredenciales = new ArrayList<>();
	private static ArrayList<String> listaNombrePais = new ArrayList<>();
	private static ArrayList<String> listaIdPais = new ArrayList<>();

	private static File paises = new File("src/main/resources/paises.xml");
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

	
	public static void crearCredenciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
			
		} else if (fichero.exists()) {
			try (FileWriter writer = new FileWriter(rutaCredenciales)) {
				for (String credencial : listaCredenciales) {
					writer.write(credencial + System.lineSeparator());
					
				}
				
			} catch (IOException e) {
				System.err.println("Error al crear las credenciales en credendiales.dat " + e.getMessage());
				
			}
		}
	}
	
	public static void aniadirCredenciales() {
		listaCredenciales.add("1|luisdbb|miP@ss|luisdbb@educastur.org|Luis de Blas|España|coordinacion");
		listaCredenciales.add("2|camila|cam1las|camilas@circo.es|Camila Sánchez|Bolivia|artista");
	}


	public static void leerCredenciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
			
		} else if (fichero.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(rutaCredenciales))) {
				
				String linea;
				
				while ((linea = br.readLine()) != null) {
					listaCredenciales.add(linea);
					
				}
			} catch (IOException e) {
				System.err.println("Error al crear las credenciales en credendiales.txt " + e.getMessage());
				
			}
		}
	}

	// CU1 ver espectaculos
	public static void leerEspectaculos() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaEspectaculo))) {
			Object espectaculoOis = ois.readObject();
			
			System.out.println(espectaculoOis + "\n");
			
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error al leer Espectaculos.dat" + e.getMessage());
			
		}
	}
	
	public static void leerXml() {
		try {
			listaIdPais.clear();
	        listaNombrePais.clear();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			
			Document doc = dBuilder.parse(paises);
			
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("pais");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elementContent = (Element) nNode;
					
					String id = elementContent.getElementsByTagName("id").item(0).getTextContent().trim();
					String nombre = elementContent.getElementsByTagName("nombre").item(0).getTextContent().trim();
					
					listaIdPais.add(id);
					listaNombrePais.add(nombre);
					
				}
			}
		} catch (Exception e) {
			System.err.println("Error al cargar el documento" + e.getLocalizedMessage());
			
		}
	}

	// CU2 Login
	public static boolean verificarLogin(String usuario, String contrasenia) {
		
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioEncontrado = partes[1].trim();
			String contraseniaEncontrada = partes[2].trim();
			
			if (usuarioEncontrado.equals(usuario) && contraseniaEncontrada.equals(contrasenia)) {
				return true;
				
			}
		}
		
		System.err.println("Error al iniciar sesion");
		System.out.println("Credenciales incorrectas, intentelo de nuevo\n");
		
		return false;
	}

	public static boolean verificarAdmin(String usuario, String contrasenia) {
		Properties propiedad = new Properties();
		
		try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")){
			propiedad.load(input);
			
		} catch (IOException e) {
			System.err.println("Error al cargar el archivo");
			System.out.println("El archivo application.properties no se cargo");
			
		}
		
		String usuarioAdmin = propiedad.getProperty("usuarioAdmin");
        String contraseniaAdmin = propiedad.getProperty("contraseniaAdmin");
        
		if (usuarioAdmin.equals(usuario) && contraseniaAdmin.equals(contrasenia)) {
			return true;
			
		}
		
		return false;
	}
	
	public static Sesion asignarAdmin(Sesion nuevaSesion, boolean esAdmin) {
			if (esAdmin) {
				nuevaSesion.setNombre("admin");
				nuevaSesion.setPerfil(Perfil.ADMIN);
				return nuevaSesion;
			}
			return null;
	}
	
	public static Sesion asignarPerfil(Sesion nuevaSesion) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioActual = partes[1].trim();
			String perfilEncontrado = partes[6].trim().toUpperCase();
			
			nuevaSesion.setNombre(perfilEncontrado);
			
			}
		if (nuevaSesion.getNombre().equalsIgnoreCase("artista")) {
			nuevaSesion.setPerfil(Perfil.ARTISTA);
			
			return nuevaSesion;
			
		}
		
		if (nuevaSesion.getNombre().equalsIgnoreCase("coordinacion")) {
			nuevaSesion.setPerfil(Perfil.COORDINACION);
			
			return nuevaSesion;
			
		}
		
		return nuevaSesion;
	}
	
	
	// CU2 LogOut
	public static void validarlogOut(Sesion nuevaSesion, Scanner sc) {
		boolean esValido = false;
		
		String opcionSalir = null;
		
		do {
			System.out.println("Desea cerrar la sesion? Y para si, N para no");
			
			opcionSalir = sc.nextLine();
			
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

	public static String validarNombreReal(Scanner sc) {
		boolean esValido = false;
		
		String nombre;
		
		do {
			System.out.println("Introduzca el nombre de la persona");
			
			 nombre = sc.nextLine().trim();
			 
			 if (!nombre.matches("^[a-zA-Z ]+$")) {
				System.err.println("Error al introducir el nombre");
				System.out.println("Asegurese de usar caracteres validos! (caracteres de la A a la Z y espacios) \n");
				esValido = false;
			} else {
				esValido = true;
			}
		} while (!esValido);
		return nombre;
	}
	// CU3 Registrar persona
	public static String validarEmail(Scanner sc) {
		boolean esValido = false;
		
		String email;
		
		do {
			System.out.println("Introduzca un Email");
			
			email = sc.nextLine().trim();
			
			if (email.matches("^^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
				esValido = true;
				
			} else {
				System.err.println("Error al introducir el email");
				System.out.println("Asegurese de usar un formato valido (estandar RFC 5322)");
				
			}
			
		} while (!esValido);
		return email;
	}

	public static String validarNombreUsuario(Scanner sc) {
		boolean esValido = false;
		
		String nombreUsuario = null;

		do {
			System.out.println("Introduzca nombre de usuario");
			
			nombreUsuario = sc.nextLine().trim();

			if (!nombreUsuario.matches("[a-zA-Z]*")) {
				System.err.println("Error al introducir el nombre");
				System.out.println("Asegurese de usar caracteres validos! (caracteres de la A a la Z) \n");
				
				esValido = true;

			} else if (nombreUsuario.length() < 2) {
				System.err.println("Error al introducier el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
				
			}
			
		} while (!esValido);
		
		return nombreUsuario;
	}

	public static String validarContrasenia(Scanner sc) {
		boolean esValido = false;
		
		String contrasenia;
		
		do {
			System.out.println("Introduzca una contrasenia");
			
			contrasenia = sc.nextLine().trim();
			
			if (!contrasenia.isEmpty()) {
				esValido = false;
				
			} else if (contrasenia.length() < 2) {
				System.err.println("Error al introducier el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
				
				esValido = false;
				
			} else {
				esValido = true;
				
			}
			
		} while (!esValido);
		return contrasenia;
	}

	public static String validarNacionalidad(Scanner sc) {
		boolean esValido = false;
		boolean encontrado = false;
		
		String nacionalidad = null;
		
		do {
			System.out.println("Introduzca el nombre o codigo del pais");
			
			nacionalidad = sc.nextLine().trim();
			
			if (nacionalidad.isEmpty()) {
				System.err.println("Error al introducir la nacionalidad");
				
				System.out.println("Asegurese de no dejar en blanco el campo");
				
				esValido = false;
			
			} else if (!nacionalidad.matches("^[\\p{L}\\p{N}\\s'\\-\\.]+$")) {
				System.err.println("Error al introducir la nacionalidad");
				
				System.out.println("Asegurese de usar caracteres validos! (letras unicode, espacios, comillas simples y guiones) \n");
				
				esValido = false;
				
			} else {
				encontrado = buscarPais(nacionalidad);
				
				if (encontrado) {
					esValido = true;
					
				} else {
					System.err.println("Error al introducir el pais");
					System.out.println("No se ha encontrado el pais introducido");
					
				}
			}

		} while (!esValido);
		return nacionalidad;
	}

	// CU5 a.Crear espectaculo
	public static void validarNombreEspectaculo(Scanner sc) {
		String nombre;
		
		do {
			System.out.println("Introduzca un nombre para el espectaculo entre 1 y 25 caracteres:");
			
			nombre = sc.nextLine().trim();
			
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

	public static String validarPerfilPersona(Scanner sc) {
		String perfil = null;
		
		boolean esValido = false;
		
		do {
			try {
				System.out.println("Introduzca el perfil de la persona");
				
				 perfil = sc.nextLine().trim();
				
				if (perfil.equalsIgnoreCase("ADMIN") || perfil.equalsIgnoreCase("COORDINACION") || perfil.equalsIgnoreCase("ARTISTA")) {
					esValido = true;
					
				} else {
					System.out.println("No se ha encontrado un perfil");
					
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al introducir el perfil");
				System.out.println("Asegurese de usar caracteres validos! (letras de la a a la z) \n");
				
			}
		
		} while (esValido);
		
		return perfil;
	}

	public static boolean buscarNombreReal(String nombre) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioActual = partes[1].trim();
			String nombreRealEncontrado = partes[4].trim().toUpperCase();
			
			if (nombreRealEncontrado.equalsIgnoreCase(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean buscarEmail(String email) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioActual = partes[1].trim();
			String emailEncontrado = partes[4].trim().toUpperCase();
			
			if (emailEncontrado.equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean buscarPais(String nacionalidad) {
		for (String nombre : listaNombrePais) {
			if (nombre.equalsIgnoreCase(nacionalidad)) {
				System.out.println("Pais seleccionado: " + nombre);
				
				return true;
			}
		}

		for (String id : listaIdPais) {
			if (id.equalsIgnoreCase(nacionalidad)) {
				System.out.println("Pais seleccionado: " + id);
				
				return true;
				
			}
		}

		return false;
	}

	public static boolean buscarNombreUsuario(String nombre) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioActual = partes[1].trim();
			String nombreUsuarioEncontrado = partes[4].trim().toUpperCase();
			
			if (nombreUsuarioEncontrado.equalsIgnoreCase(nombre)) {
				return true;
				
			}
		}
		return false;
	}
	
	public static void consumirLinea(Scanner sc) {
		if (sc.hasNextLine()) {
			sc.nextLine();
			
		}
	}

	public static void nuevoIdPersona() {
		int contador = 2;
		for(String id : listaCredenciales) {
//			String[] linea = id[1].trim()
		}

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

	public static Sesion menuInvitadoLogin(Scanner sc, Sesion nuevaSesion) {
		boolean sesionIniciada = false;
		boolean esAdmin = false;
		
		String usuario = null;
		String contrasenia = null;
		
		do {
			try {
				System.out.println("Inicio de Sesion");
				System.out.println("Introduzca el nombre usuario:");
				
				usuario = sc.nextLine().trim();
				
				System.out.println("Introduzca la contrasenia:");
				
				contrasenia = sc.nextLine().trim();
				
				esAdmin = verificarAdmin(usuario, contrasenia);
				
				if (esAdmin) {
					sesionIniciada = true;
					
					nuevaSesion = asignarAdmin(nuevaSesion, esAdmin);
					
				} else if (!esAdmin){
					sesionIniciada = verificarLogin(usuario, contrasenia);
					
	                nuevaSesion = asignarPerfil(nuevaSesion);
	                
	                System.out.println("Sesión iniciada correctamente.\n");
	                
				} else {
					System.err.println("Credenciales incorrectas. Inténtelo de nuevo.\n");
					
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				
				System.out.println("Asegurese de introducir caracteres validos! (cualquier caracter?)");
				
				sc.nextLine();
				
			}
			
		} while (!sesionIniciada);
		
		return nuevaSesion;
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

	public static void menuSeleccionPerfil(Scanner sc, String perfil) {
		if (perfil.equalsIgnoreCase("ARTISTA")) {
			System.out.println("Introduzca el apodo de la persona artista");
			
			String apodo = sc.nextLine();
			
			System.out.println("Introduzca las especialidades del artista");
			
			String especialidades = sc.nextLine();
			
		}
		
		if (perfil.equalsIgnoreCase("COORDINACION")) {
			System.out.println("Es el la persona coordinadora senior? Y para si, N para no");
			
			String esSenior = sc.nextLine();
			
			switch (esSenior) {
			case "Y": {
				System.out.println("Introduzca la fecha en la que empezo a ser senior");
				break;
				
			}
			case "N": {
				break;
				
			}

			case " ": {
				System.out.println("No puedes dejar en blanco este espacio");
				
			}
			default:
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de usar caracteres validos! (Y o N.) \n");
				
			}
		}
	}
	public static void menuAdminGestionaPersona(Scanner sc) {
		System.out.println("Registro de una persona\n");
		System.out.println("Datos personales:\n");

		String nombreReal = validarNombreReal(sc);
		
		String email = validarEmail(sc);
		
		String nacionalidad = validarNacionalidad(sc);
		
		String perfil = validarPerfilPersona(sc);
		
		menuSeleccionPerfil(sc, perfil);
		
		String nombreUsuario = validarNombreUsuario(sc);

		String contrasenia = validarContrasenia(sc);
	}
	
	public static void menuAdminGestionaEspectaculo(Scanner sc) {
		validarNombreEspectaculo(sc);
		
		validarFechaEspectaculo(sc);
	}

	public static int menuArtista(Scanner sc, int opcion) {
		boolean esValido = false;
		
		do {
			try {
				System.out.println("Introduzca el numero al lado de la opcion para seleccionarla");
				System.out.println("1. Ver ficha");
				System.out.println("2. Logout");
				
				opcion = sc.nextInt();
				
				if (opcion > 0 && opcion <= 2) {
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
				
				if (opcion > 0 && opcion <= 2) {
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

	public static int menuCoordinacionGestion(Scanner sc, int segundaOpcion) {
		boolean esValido = false;
		
		do {
			try {
				System.out.println("Gestion de espectaculos\n");
				System.out.println("1. Crear espectaculo");
				System.out.println("2. Modificar espectaculo existente");
				
				segundaOpcion = sc.nextInt();
				
				if (segundaOpcion > 0 && segundaOpcion <= 2) {
					
					esValido = true;
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 2)");
				
				sc.nextLine();
			}
			
		} while (!esValido);
		
		return segundaOpcion;

	}

	// Agrupacion de metodos
	public static void iniciarPrograma() {
		crearFichero();
		crearEspectaculosIniciales();
		aniadirCredenciales();
		crearCredenciales();
		leerCredenciales();
		leerXml();
	}

	public static void crearespectaculo(Scanner sc) {
		validarNombreEspectaculo(sc);
		validarFechaEspectaculo(sc);
	}
}
