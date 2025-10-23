package com.tarea1.Tarea1_tomasvega;

import java.util.Scanner;

import entidades.Perfil;
import entidades.Sesion;
import utiles.Metodos;

/**
 * Hello world!
 */
public class Principalcirco {
	public static void main(String[] args) {
		Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);
		boolean ejecucion = true;
		int opcion = 0;
		int segundaOpcion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================\n");
		System.out.println("	Programa de gestion de circo\n\n");
		System.out.println("	Por Tomas Jesus Vega Leiva\n");
		System.out.println("============================================\n");
		Metodos.iniciarPrograma();
		do {
			if (nuevaSesion.getPerfil().equals(Perfil.INVITADO)) {
				System.out.println("\nSesion actual: " + nuevaSesion.getNombre() + "\n");
				
				opcion = Metodos.menuInvitado(sc, opcion);
				
				switch (opcion) {
				case 1: {
					Metodos.consumirLinea(sc);
					
					Metodos.menuInvitadoLogin(sc, nuevaSesion);
					
					break;
					
				}
				
				case 2: {
					Metodos.consumirLinea(sc);
					
					Metodos.leerEspectaculos();
					
					break;
					
				}
				
				case 3: {
					Metodos.consumirLinea(sc);
					
					ejecucion = Metodos.cerrarPrograma(sc);
					
					break;
					
				}
				default:
					System.err.println("Como llegaste hasta aqui?");
					
				}
			}
			
			if (nuevaSesion.getPerfil().equals(Perfil.ARTISTA)){
				System.out.println("\nSesion actual: " + nuevaSesion.getNombre() + "\n");
				
				opcion = Metodos.menuArtista(sc, opcion);
				
				switch (opcion) {
				case 1: {
					Metodos.consumirLinea(sc);
					
					System.out.println("Ficha del artista...");
					
					break;
					
				}
				case 2: {
					Metodos.consumirLinea(sc);
					
					Metodos.validarlogOut(nuevaSesion, sc);
					
					break;
					
				}
				default:
					System.err.println("Como llegaste hasta aqui?");
					
				}
			}
			
			if (nuevaSesion.getPerfil().equals(Perfil.COORDINACION)) {
				System.out.println("\nSesion actual: " + nuevaSesion.getNombre() + "\n");
				
				opcion = Metodos.menuCoordinacion(sc, opcion);
				
				switch (opcion) {
				case 1: {
					Metodos.consumirLinea(sc);
					
					segundaOpcion = Metodos.menuCoordinacionGestion(sc, segundaOpcion);
					
					switch (segundaOpcion) {
					case 1: {
						
						break;
						
					}
					case 2: {
						
						break;
						
					}
					default:
						throw new IllegalArgumentException("Unexpected value: " + segundaOpcion);
					}
					
				}
				case 2: {
					Metodos.consumirLinea(sc);
					
					Metodos.validarlogOut(nuevaSesion, sc);
					break;
					
				}
				default:
					System.err.println("Como llegaste hasta aqui?");
					
				}
			}
			
			if (nuevaSesion.getPerfil().equals(Perfil.ADMIN)) {
				
				System.out.println("\nSesion actual: " + nuevaSesion.getNombre() + "\n");
				
				opcion = Metodos.menuAdmin(sc, opcion);
				
				switch (opcion) {
				case 1: {
					Metodos.consumirLinea(sc);
					
					Metodos.menuAdminRegistraPersona(sc);
					
					Metodos.mostrarCredenciales();
					
					break;
				}
				case 2: {
					Metodos.consumirLinea(sc);
					
					Metodos.menuAdminGestionaEspectaculo(sc);
					
					break;
				}
				
				case 3: {
					Metodos.consumirLinea(sc);
					
					Metodos.validarlogOut(nuevaSesion, sc);
					
					break;
				}
				default:
					System.err.println("Como llegaste hasta aqui?");
					
				}
			}
			
		} while (ejecucion);
		
		sc.close();
	}
}
