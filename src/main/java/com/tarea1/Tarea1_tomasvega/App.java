package com.tarea1.Tarea1_tomasvega;

import entidades.Perfil;
import entidades.Sesion;
import utiles.Metodos;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);

		Metodos.crearFichero();
	}
//		if (!carpetaFicheros.exists()) {
//			carpetaFicheros.mkdir();
//			System.out.println("Carpeta ficheros creada en: " + carpetaFicheros.getAbsolutePath());
//		} else {
//			try (ObjectOutputStream oos = new ObjectOutputStream(
//					new FileOutputStream("ficheros/espectaculos.dat"))) {
//				oos.writeObject(espectaculo1);
//				System.out.println(
//						"Archivo espectaculo.dat creado dentro de la carpeta ficheros.");
//			} catch (IOException e) {
//				System.out.println("Error");
//			}
//		}

//	System.out.println("============================================");
//	System.out.println(" ");
//	System.out.println("	Programa de gestion de circo");
//	System.out.println(" ");
//	System.out.println(" ");
//	System.out.println("	Por Tomas Jesus Vega Leiva");
//	System.out.println(" ");
//	System.out.println("============================================");
//	System.out.println(" ");

//	public static void mostrarMenuInvitado(Sesion nuevaSesion) {
//		System.out.println("Sesion: " + nuevaSesion.toString());
//		System.out.println("1. Login");
//		System.out.println("2. Ver espectaculos");
//		System.out.println("3. Salir");
//
//	}
//
//	public static void mostrarMenuCoordinacion(Sesion nuevaSesion) {
//		System.out.println("Sesion: " + nuevaSesion.toString());
//		System.out.println("1. Gestionar Espectaculos");
//		System.out.println("2. Logout");
//	}
//
//	//tener en cuenta si el usuario hace altf4
//	
//	public static void mostrarMenuAdmin(Sesion nuevaSesion) {
//		System.out.println("Sesion: " + nuevaSesion.toString());
//		System.out.println("1. Gestionar personas y credenciales");
//		System.out.println("2. Gestionar espectaculos");
//		System.out.println("3. Logout");
//	}
//
//	public static void mostrarMenuArtista(Sesion nuevaSesion) {
//		System.out.println("Sesion: " + nuevaSesion.toString());
//		System.out.println("1. Ver ficha");
//		System.out.println("2. Logout");
//	}

}
