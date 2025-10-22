package com.tarea1.Tarea1_tomasvega;

import java.util.Scanner;

import entidades.Perfil;
import entidades.Sesion;
import utiles.Metodos;
import utiles.Objetos;

/**
 * Hello world!
 */
public class Principalcirco {
	public static void main(String[] args) {
		Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);
		boolean ejecucion = true;
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================\n");
		System.out.println("	Programa de gestion de circo\n\n");
		System.out.println("	Por Tomas Jesus Vega Leiva\n");
		System.out.println("============================================\n");

		Metodos.iniciarPrograma();
		do {
//			switch (nuevaSesion.getPerfil()) {
//			case INVITADO: {
//				System.out.println("Sesion actual: " + nuevaSesion.getNombre());
//				opcion = Metodos.menuInvitado(sc, opcion);
//				switch (opcion) {
//				case 1: {
//					Metodos.consumirLinea(sc);
//					Metodos.menuInvitadoLogin(sc);
//					
//					break;
//				}
//
//				case 2: {
//					Metodos.consumirLinea(sc);
//					Metodos.leerEspectaculos();
//				}
//
//				case 3: {
//					Metodos.consumirLinea(sc);
//					ejecucion = Metodos.cerrarPrograma(sc);
//					break;
//
//				}
//				default:
//					System.out.println("Caso default");
//				}
//				break;
//			}
//
//			case ARTISTA: {
//				System.out.println("Sesion actual: " + nuevaSesion.getNombre());
//				opcion = Metodos.menuArtista(sc, opcion);
//				switch (opcion) {
//				case 1: {
//					System.out.println("Lista de espectaculos");
//					break;
//				}
//				case 2: {
//
//				}
//				default:
//					System.out.println("Valores Invalidos");
//				}
//				break;
//			}
//
//			case COORDINACION: {
//				System.out.println("Sesion actual: " + nuevaSesion.getNombre());
//				opcion = Metodos.menuCoordinacion(sc, opcion);
//				switch (opcion) {
//				case 1: {
//					sc.nextLine();
//					int opcionCoord = sc.nextInt();
//					System.out.println("Gestion de espectaculos");
//					System.out.println("1.Crear espoectaculo");
//					System.out.println("2. Gestionar espectaculo");
//					switch (opcionCoord) {
//					case 1: {
//						Metodos.validarNombreEspectaculo(sc);
//						Metodos.validarFechaEspectaculo(sc);
//
//						break;
//					}
//					case 2: {
//						break;
//					}
//					case 3: {
//						sc.nextLine();
//						String opcionSalir = sc.nextLine();
//						Metodos.validarlogOut(nuevaSesion, opcionSalir);
//						break;
//					}
//					default:
//						System.out.println("Valor invalido");
//					}
//				}
//				case 2: {
//				}
//				default:
//				}
//			}
//			case ADMIN: {
//				System.out.println("Sesion actual: " + nuevaSesion.getNombre());
//				opcion = Metodos.menuAdmin(sc, opcion);
//				switch (opcion) {
//				case 1: {
//				}
//				case 2: {
//					
//				}
//				default:
//					throw new IllegalArgumentException("Unexpected value: " + opcion);
//				}
//			}
//			default:
//				System.out.println("Caso default");
//			}
			
			if (nuevaSesion.getPerfil().equals(Perfil.INVITADO)) {
				System.out.println("Sesion actual: " + nuevaSesion.getNombre());
				opcion = Metodos.menuInvitado(sc, opcion);
				switch (opcion) {
				case 1: {
					Metodos.consumirLinea(sc);
					Metodos.menuInvitadoLogin(sc);
					break;
				}
				
				case 2: {
					Metodos.consumirLinea(sc);
					Metodos.menuInvitadoLogin(sc);
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
				
			}
		} while (ejecucion);
		sc.close();
	}
}
