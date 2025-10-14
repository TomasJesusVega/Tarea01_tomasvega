package com.tarea1.Tarea1_tomasvega;

import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import entidades.Espectaculo;
import entidades.Numero;
import entidades.Perfil;
import entidades.Sesion;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);
		boolean sesionIniciada = false;
		boolean sesionInvitado = true;

		Numero numero1 = new Numero(001L, 1, "apertura", 15.5);
		Set<Numero> listaNumeros = new HashSet<>();
		listaNumeros.add(numero1);

		Espectaculo espectaculo1 = new Espectaculo(10000L, "Espectaculo 1",
				LocalDate.of(2025, 6, 1), LocalDate.of(2025, 1, 30), 10000L,
				listaNumeros);
		switch (nuevaSesion.toString()) {
		case "INVITADO": {
			mostrarMenuInvitado(nuevaSesion);
			break;
		}

		case "COORDINACION": {
			mostrarMenuCoordinacion(nuevaSesion);
			break;
		}

		case "ARTISTA": {
			mostrarMenuArtista(nuevaSesion);
			break;
		}

		case "ADMIN": {
			mostrarMenuAdmin(nuevaSesion);
			break;
		}

		default:
			System.out.println("Valor invalido");

		}

//		try (FileWriter writer = new FileWriter("ficheros/espectaculos.dat")) {
//			writer.write("");
//		} catch (IOException e) {
//			// TODO: handle exception
//		}
		System.out.println("============================================");
		System.out.println(" ");
		System.out.println("	Programa de gestion de circo");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("	Por Tomas Jesus Vega Leiva");
		System.out.println(" ");
		System.out.println("============================================");
		System.out.println(" ");
	}

	public static void mostrarMenuInvitado(Sesion nuevaSesion) {
		System.out.println("Sesion: " + nuevaSesion.toString());
		System.out.println("1. Login");
		System.out.println("2. Ver espectaculos");
		System.out.println("3. Salir");

	}

	public static void mostrarMenuCoordinacion(Sesion nuevaSesion) {
		System.out.println("Sesion: " + nuevaSesion.toString());
		System.out.println("1. Gestionar Espectaculos");
		System.out.println("2. Logout");
	}

	public static void mostrarMenuAdmin(Sesion nuevaSesion) {
		System.out.println("Sesion: " + nuevaSesion.toString());
		System.out.println("1. Gestionar personas y credenciales");
		System.out.println("2. Gestionar espectaculos");
		System.out.println("3. Logout");
	}

	public static void mostrarMenuArtista(Sesion nuevaSesion) {
		System.out.println("Sesion: " + nuevaSesion.toString());
		System.out.println("1. Ver ficha");
		System.out.println("2. Logout");
	}
}
