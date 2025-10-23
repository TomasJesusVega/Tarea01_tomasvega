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
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import entidades.Espectaculo;
import entidades.Perfil;
import entidades.Sesion;

public class Metodos extends Objetos {

	private static Set<Espectaculo> espectaculos = new HashSet<>();
	
	private static ArrayList<String> listaCredenciales = new ArrayList<>();
	private static ArrayList<String> listaNombrePais = new ArrayList<>();
	private static ArrayList<String> listaIdPais = new ArrayList<>();

	private static File paises = new File("src/main/resources/paises.xml");
	private static File fichero = new File("ficheros");
	
	private static String rutaEspectaculo = "ficheros/espectaculo.dat";
	private static String rutaCredenciales = "ficheros/credenciales.txt";

	public static void aniadirCredenciales() {
		listaCredenciales.add("1|luisdbb|miP@ss|luisdbb@educastur.org|Luis de Blas|España|coordinacion");
		listaCredenciales.add("2|camila|cam1las|camilas@circo.es|Camila Sánchez|Bolivia|artista");
		
	}

	public static void aniadirEspectaculo() {
		espectaculos.add(espectaculo1);
		espectaculos.add(espectaculo2);
		espectaculos.add(espectaculo3);
		
	}
	
	public static void crearFichero() {
		if (!fichero.exists()) {
			fichero.mkdirs();
			
			System.out.println("Carpeta " + fichero.getName() + " creada en " + fichero.getAbsolutePath());
			
		} else {
			System.out.println("No se pudo crear la carpeta, compruebe si ya ha sido creada");
			
		}
	}

	/**
	 * Metodo que aniade 3 espectaculos de ejemplo 
	 */
	public static void crearEspectaculosIniciales() {
		if (!fichero.exists()) {
			System.out.println("No se encontro la carpeta ficheros, compruebe si ha sido creada");
			
		} else if (fichero.exists()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaEspectaculo))) {
				oos.writeObject(espectaculos);
				
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
				System.err.println("Error al crear las credenciales en credendiales.txt " + e.getMessage());
				
			}
		}
	}

	public static void mostrarCredenciales() {
	    if (listaCredenciales.isEmpty()) {
	        System.out.println("No hay credenciales registradas para mostrar.");
	    } else {
	        System.out.println("Lista de credenciales\n");
	        for (String credencial : listaCredenciales) {
	            System.out.println(credencial);
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

	/**
	 * Metodo que comprueba si las credenciales introducidas por el usuario coinciden con alguna credencial
	 * del archivo credenciales.txt
	 * @param usuario
	 * @param contrasenia
	 * @return booleano que indica si hay o no un inicio de sesion
	 */
	public static boolean verificarLogin(String usuario, String contrasenia) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioEncontrado = partes[1].trim();
			String contraseniaEncontrada = partes[2].trim();
			
			if (usuarioEncontrado.equals(usuario) && contraseniaEncontrada.equals(contrasenia)) {
				System.out.println("Sesion iniciada correctamente\n");
				return true;
				
			}
		}
		System.err.println("Error al iniciar sesion");
		System.out.println("Credenciales invalidas, vuelva a intentarlo");
		return false;
		
	}

	/**
	 * 
	 * @param usuario
	 * @param contrasenia
	 * @return
	 */
	public static boolean verificarAdmin(String usuario, String contrasenia) {
			Properties propiedad = new Properties();
			
			try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")){
				propiedad.load(input);
				
			} catch (IOException e) {
				System.err.println("Error al cargar el archivo");
				System.out.println("El archivo application.properties no se cargo");
				
			}
			
			String usuarioAdmin = propiedad.getProperty("usuarioAdmin").trim();
	        String contraseniaAdmin = propiedad.getProperty("contraseniaAdmin").trim();

			if (usuarioAdmin.equals(usuario) && contraseniaAdmin.equals(contrasenia)) {
				System.out.println("Sesion iniciada correctamente vA");
				return true;
				
				
			} else {
				
				return false;
			}
		
	}
	
	/**
	 * Metodo que asigna el nombre y perfil correspondientes al admin en caso de que se haya verificado
	 * que lo es
	 * @param nuevaSesion
	 * @param esAdmin
	 * @return retorna un objeto Sesion al que se le asigna el nombre y perfil correspondiente
	 */
	public static Sesion asignarAdmin(Sesion nuevaSesion, boolean esAdmin) {
			if (esAdmin) {
				nuevaSesion.setNombre("admin");
				nuevaSesion.setPerfil(Perfil.ADMIN);
				return nuevaSesion;
			}
			return null;
	}
	
	/**
	 * Metodo que asigna un perfil al objeto Sesion en funcion del valor encontrado dentro del archivo
	 * credenciales.txt
	 * @param nuevaSesion
	 * @return retorna un objeto Sesion al que se le asigna el nombre correspondiente
	 */
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
	
	/**
	 * 
	 * @param nuevaSesion
	 * @param sc
	 */
	public static void validarlogOut(Sesion nuevaSesion, Scanner sc) {
		boolean esValido = false;
		
		String opcionSalir = null;
		
		do {
			System.out.println("Desea cerrar la sesion? Y para si, N para no");
			
			opcionSalir = sc.nextLine().trim();
			
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
				System.err.println("Error al introducir la opcion");
				System.out.println("Asegurese de no dejar en blanco el campo\n");
				break;
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
				System.err.println("Error al introducir una opcion");
				System.out.println("Asegurese de no dejar en blanco el campo\n");
				break;
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
			} else if(nombre.isEmpty()) {
				System.err.println("Error al introducir el nombre");
				System.out.println("Asegurese de no dejar en blanco el campo");
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

	/**
	 * Metodo que comprueba si el nombre de usuario introducido es valido
	 * @param sc
	 * @return retorna una cadena de caracteres correspondiente al nombre de usuario de una persona
	 */
	public static String validarNombreUsuario(Scanner sc) {
		boolean esValido = false;
		
		String nombreUsuario = null;

		do {
			System.out.println("Introduzca nombre de usuario");
			
			nombreUsuario = sc.nextLine().trim();

			if (!nombreUsuario.matches("[a-zA-Z]*")) {
				System.err.println("Error al introducir el nombre");
				System.out.println("Asegurese de usar caracteres validos! (caracteres de la A a la Z) \n");
				
				esValido = false;

			} else if (nombreUsuario.length() < 2) {
				System.err.println("Error al introducier el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
				
				esValido = false;
				
			} else if(nombreUsuario.isEmpty()) {
				System.err.println("Error al introducir el usuario");
				System.out.println("Asegurese de no dejar en blanco el campo");
				
				esValido = false;
				
			} else if(buscarNombreUsuario(nombreUsuario) == true) {
				System.out.println("Lo sentimos, pero este nombre de usuario no esta disponible, introduzca otro");
				
				esValido = false;
				
			} else {
				esValido = true;
				
			}
			
		} while (!esValido);
		
		return nombreUsuario;
		
	}

	/**
	 * Metodo que comprueba si la contrasenia introducida es valida
	 * @param sc
	 * @return retona una cadena de caracteres correspondiente a la contrasenia de una persona
	 */
	public static String validarContrasenia(Scanner sc) {
		boolean esValido = false;
		
		String contrasenia;
		
		do {
			System.out.println("Introduzca una contrasenia");
			
			contrasenia = sc.nextLine().trim();
			
			if (contrasenia.isEmpty()) {
				System.err.println("Error al introducir el usuario");
				System.out.println("Asegurese de no dejar en blanco el campo");
				esValido = false;
				
			} else if (contrasenia.length() < 2) {
				System.err.println("Error al introducir el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
				
				esValido = false;
				
			} else {
				esValido = true;
				
			}
			
		} while (!esValido);
		
		return contrasenia;
		
	}

	/**
	 * Metodo que comprueba si la nacionalidad introducida es valida
	 * @param sc
	 * @return retorna una cadena de caracteres correspondiente a la nacionalidad de una persona
	 */
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

	/**
	 * Metodo que comprueba si el nombre del espectaculo introducido es valido
	 * @param sc
	 * @return retorna una cadena de caracteres correspondiente al nombre de un espectaculo
	 */
	public static String validarNombreEspectaculo(Scanner sc) {
		String nombreEspectaculo = null;
		boolean esValido = false;
		do {
			System.out.println("Introduzca un nombre para el espectaculo entre 1 y 25 caracteres:");
			
			nombreEspectaculo = sc.nextLine().trim();
			
			if (nombreEspectaculo.length() > 25 || nombreEspectaculo.length() == 0) {
				System.err.println("Error al registrar el nombre");
				System.out.println("Asegurese que la longitud del nombre sea la adecuada (entre 1 y 25 caracteres)\n");
				
			} else if(buscarNombreEspectaculo(nombreEspectaculo)) {
				System.err.println("Error al registrar el nombre");
				System.out.println("Ya existe un espectaculo con ese nombre");
			} else if (nombreEspectaculo.isEmpty()) {
				System.err.println("Error al introducir el nombre del espectaculo");
				System.out.println("Asegurese de no dejar en blanco el campo");
				
			} else {
				esValido = true;
			}
			
		} while (!esValido);
		
		return nombreEspectaculo;
	}

	/**
	 * Metodo que comprueba si las fechas introducidas para el espectaculo son validas
	 * @param sc
	 */
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
					esValido = true;
					
				}

			} catch (DateTimeParseException e) {
				System.err.println("Error en el formato de las fechas ");
				System.out.println("Asegurese de usar el formato correcto! (dd-mm-aaaa)\n");
				
			}
	
		} while (!esValido);
		
	}

	/**
	 * 
	 * @param sc
	 * @return
	 */
	public static String validarPerfilPersona(Scanner sc) {
		String perfil = null;
		
		boolean esValido = false;
		
		do {
			try {
				System.out.println("Introduzca el perfil de la persona");
				
				 perfil = sc.nextLine().trim();
				
				if (perfil.isEmpty()) {
					System.err.println("Error al introducir el perfil");
					System.out.println("Asegurese de no dejar en blanco el campo");
					
				} else if (perfil.equalsIgnoreCase("ADMIN") || perfil.equalsIgnoreCase("COORDINACION") || perfil.equalsIgnoreCase("ARTISTA")) {
					esValido = true;
					
				} else {
					System.out.println("No se ha encontrado un perfil");
					
					esValido = false;
					
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al introducir el perfil");
				System.out.println("Asegurese de usar caracteres validos! (letras de la a a la z) \n");
				
			}
		
		} while (!esValido);
		
		return perfil;
	}
	
	public static String validarApodo(Scanner sc) {
		boolean esValido = false;
		
		String apodo = null;
		
		do {
			System.out.println("Introduzca el apodo de la persona artista");
			
			apodo = sc.nextLine().trim();
			
			if (apodo.isEmpty()) {
				System.err.println("Error al introducir el apodo");
				System.out.println("Asegurese de no dejar en blanco el campo\n");
				
			} else if (!apodo.matches("[a-zA-Z]*")) {
				System.err.println("Error al introducir el nombre");
				System.out.println("Asegurese de usar caracteres validos! (caracteres de la A a la Z) \n");
				
				esValido = false;

			} else if (apodo.length() < 2) {
				System.err.println("Error al introducier el usuario");
				System.out.println("Asegurese que la longitud no sea inferior de 2 caracteres");
				
				esValido = false;
				
			} else {
				esValido = true;
				
			}
			
		} while (!esValido);
		
		return apodo;
		
	}
	
	public static String validarEspecialidad(Scanner sc) {
		boolean esValido = false;
		
		String especialidad = null;
		
		do {
			System.out.println("Introduzca las especialidades del artista");
			
			especialidad = sc.nextLine().trim();
			
			if (especialidad.equalsIgnoreCase("ACROBACIA") || especialidad.equalsIgnoreCase("MAGIA") || especialidad.equalsIgnoreCase("HUMOR") || 
				especialidad.equalsIgnoreCase("EQUILIBRISMO") || especialidad.equalsIgnoreCase("MALABARISMO")) {
				esValido = true;
				
			} else {
				System.err.println("Error al introducir la especialidad");
				
				System.out.println("No se ha encontrado la especialidad introducida, intentelo de nuevo");
				
				esValido = false;
				
			}
			
		} while (!esValido);

		return especialidad;
		
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

	public static boolean buscarNombreUsuario(String nombreUsuario) {
		for (String credencial : listaCredenciales) {
			String[] partes = credencial.split("\\s*\\|\\s*");
			String usuarioActual = partes[1].trim();
			String nombreUsuarioEncontrado = partes[4].trim().toUpperCase();
			
			if (nombreUsuarioEncontrado.equalsIgnoreCase(nombreUsuario)) {
				return true;
				
			}
		}
		return false;
	}
	
	private static boolean buscarNombreEspectaculo(String nombreEspectaculo) {
		for(Espectaculo esp:espectaculos) {
			if(esp.getNombre().equalsIgnoreCase(nombreEspectaculo)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Metodo que vacia el buffer del Scanner
	 * @param sc
	 */
	public static void consumirLinea(Scanner sc) {
		if (sc.hasNextLine()) {
			sc.nextLine();
			
		}
	}

	/**
	 * Metodo que genera ids autoincrementales para cada persona nueva creada
	 * @return
	 */
	public static long generarIdPersona() {
	    try {   
	        if (!fichero.exists()) {
	            return 1L;
	            
	        } else if(fichero.exists()){
	        	BufferedReader reader = new BufferedReader(new FileReader(rutaCredenciales));
	        	
		        String ultimaLinea = "";
		        String lineaActual;
		        
		        while ((lineaActual = reader.readLine()) != null) {
		            if (!lineaActual.trim().isEmpty()) {
		                ultimaLinea = lineaActual;
		                
		            }
		        }
		        reader.close();
		        
		        if (ultimaLinea.isEmpty()) {
		            return 1L;
		            
		        }
		        
		        String[] partes = ultimaLinea.split("\\|");
		        
		        if (partes.length > 0) {
		            try {
		                long ultimoId = Long.parseLong(partes[0].trim());
		                
		                return ultimoId + 1;
		                
		            } catch (NumberFormatException e) {
		               
		                return 1L;
		            }
		        }
	        }
	        
	        
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo de credenciales: " + e.getMessage());
	    }
	    
	    return 1L;
	}

	// Metodos menu
	// IMPORTANTE nextInt no consume linea de scanner, usar metodo consumirLinea
	// para que no salte exepcion despues de llamar a un metodo menu
	public static int menuInvitado(Scanner sc, int opcion) {
		boolean esValido = false;
		
		do {
			try {
				System.out.println("\nIntroduzca el numero al lado de la opcion para seleccionarla \n");
				System.out.println("1. Login");
				System.out.println("2. Ver espectaculos");
				System.out.println("3. Cerrar programa");
				
				opcion = sc.nextInt();
				
				if (opcion > 0 && opcion <= 3) {
					esValido = true;
					
				} else {
					System.err.println("Error al seleccionar una opcion");
					System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 3)");
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				System.out.println("Asegurese de introducir caracteres validos! (digitos del 1 al 3)");
				
				sc.nextLine();
			}
			
		} while (!esValido);
		
		return opcion;
	}

	/**
	 * Metodo que pide al usuario iniciar sesion con un nombre de usuario y una contrasenia
	 * @param sc
	 * @param nuevaSesion
	 * @return retorna un objeto Sesion con el nombre y el perfil correspondientes a las credenciales introducidas
	 */
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
	                
				} else {
					System.err.println("Error al iniciar sesion");
					System.out.println("Credenciales incorrectas, intentelo de nuevo");
					
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Error al seleccionar una opcion");
				
				System.out.println("Asegurese de introducir caracteres validos! (cualquier caracter?)");
				
				sc.nextLine();
				
			}
			
		} while (!sesionIniciada);
		
		return nuevaSesion;
	}

	/**
	 * 
	 * @param sc
	 * @param opcion
	 * @return
	 */
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
		boolean esValido = false;
		do {
			if (perfil.equalsIgnoreCase("ARTISTA")) {
				String apodo = validarApodo(sc);
				
				String especialidad = validarEspecialidad(sc);
				esValido = true;
				
			} else if (perfil.equalsIgnoreCase("COORDINACION")) {
				System.out.println("Es el la persona coordinadora senior? Y para si, N para no");
				
				String esSenior = sc.nextLine();
				
				switch (esSenior) {
				case "Y": {
					System.out.println("Introduzca la fecha en la que empezo a ser senior");
					
					String fecha = sc.nextLine();
					
					esValido = true;
					
					break;
					
				}
				case "N": {
					esValido = true;
					
					break;
					
				}

				case " ": {
					System.out.println("No puedes dejar en blanco este espacio");
					
					esValido = false;
					
				}
				default:
					System.err.println("Error al seleccionar una opcion");
					System.out.println("Asegurese de usar caracteres validos! (Y o N.) \n");
					
					esValido = false;
					
				}
			} else {
				System.out.println("No se ha encontrado el perfil");
			}
		} while (!esValido);
		
	}
	
	public static void menuAdminRegistraPersona(Scanner sc) {
		boolean esValido = false;
		do {
		System.out.println("Registro de una persona\n");
		System.out.println("Datos personales:\n");
		
		String nombreReal = validarNombreReal(sc);
		
		String email = validarEmail(sc);
		
		String nacionalidad = validarNacionalidad(sc);
		
		String perfil = validarPerfilPersona(sc);
		
		System.out.println(perfil);
		
		menuSeleccionPerfil(sc, perfil);
		
		String nombreUsuario = validarNombreUsuario(sc);

		String contrasenia = validarContrasenia(sc);
		
		Long id = generarIdPersona(); 
		
		String registroCredenciales = id + "|" + nombreUsuario + "|" + contrasenia + "|" + email + "|" + nombreReal + "|" + nacionalidad + "|" + perfil;
		
		esValido = true;
		
		listaCredenciales.add(registroCredenciales);
		
		} while (!esValido);
	}
	
	public static void menuAdminGestionaEspectaculo(Scanner sc) {
		boolean esValido = false;
		String nombreEspectaculo = null;
		do {
			nombreEspectaculo = validarNombreEspectaculo(sc);
			
			validarFechaEspectaculo(sc);
			
		} while (!esValido);
		
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
		leerXml();
	}

	public static void crearespectaculo(Scanner sc) {
		validarNombreEspectaculo(sc);
		validarFechaEspectaculo(sc);
	}
}
