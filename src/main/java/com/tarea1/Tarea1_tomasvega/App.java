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
		boolean a = true;
		Metodos.crearFichero();
		
		do {
			switch (nuevaSesion.getPerfil()) {
			case INVITADO: {
				Metodos.mostrarMenuInvitado(nuevaSesion);
				a = false;
				break;
			}
			default:
				System.out.println("");
			}
		} while (a);
	}




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
