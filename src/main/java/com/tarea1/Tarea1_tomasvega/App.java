package com.tarea1.Tarea1_tomasvega;

import java.util.Scanner;

import entidades.Perfil;
import entidades.Sesion;
import utiles.Metodos;
import utiles.Objetos;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);
		boolean ejecucion = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("============================================\n");
		System.out.println("	Programa de gestion de circo\n\n");
		System.out.println("	Por Tomas Jesus Vega Leiva\n");
		System.out.println("============================================\n");

		do {
			switch (nuevaSesion.getPerfil()) {
			case INVITADO: {
				Metodos.menuInvitado(nuevaSesion);
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1: {
					System.out.println("Inicio de Sesion");
					System.out.println("Usuario: ");
					System.out.println("Contrasenia: ");
					break;
				}

				case 2: {
					System.out.println("Lista de espectaculos");
					break;
				}

				case 3: {
					sc.nextLine();
					System.out.println("Desea cerrar el programa? Y para si, N para no");
					String opcionSalir = sc.nextLine();
					ejecucion = Metodos.cerrarPrograma(opcionSalir);
					break;

				}
				default:
					System.out.println("Caso default");
				}
				break;
			}

			case ARTISTA: {
				Metodos.menuArtista(nuevaSesion);
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1: {
					System.out.println("Lista de espectaculos");
					Metodos.guardarEspectaculo();
					break;
				}
				case 2: {
					sc.nextLine();
					System.out.println("Desea cerrar la sesion y volver al perfil de invitado? Y para si, N para no");
					String opcionSalir = sc.nextLine();
					if (opcionSalir.equalsIgnoreCase("Y")) {
						nuevaSesion.setNombre("Invitado");
						nuevaSesion.setPerfil(Perfil.INVITADO);
						break;
					} else if (opcionSalir.equalsIgnoreCase("N")) {
						break;
					} else {
						System.out.println("Opcion invalida");
						sc.nextLine();
						break;
					}
				}
				default:
					System.out.println("Valores Invalidos");
				}
				break;
			}

			case COORDINACION: {
				Metodos.menuCoordinacion(nuevaSesion);
				int opcion = sc.nextInt();
				switch (opcion) {
				case 1: {
					sc.nextLine();
					int opcionCoord = sc.nextInt();
					System.out.println("Gestion de espectaculos");
					System.out.println("1.Crear espoectaculo");
					System.out.println("2. Gestionar espectaculo");
					switch (opcionCoord) {
					case 1: {

						break;
					}
					case 2: {
						break;
					}
					case 3: {
						sc.nextLine();
						String opcionSalir = sc.nextLine();
						Metodos.logOut(nuevaSesion, opcionSalir);

						break;
					}
					default:
						System.out.println("Valor invalido");
					}

				}

				case 2: {
					sc.nextLine();
					System.out.println("Desea cerrar la sesion y volver al perfil de invitado? Y para si, N para no");
					String opcionSalir = sc.nextLine();
					if (opcionSalir.equalsIgnoreCase("Y")) {
						nuevaSesion.setNombre("Invitado");
						nuevaSesion.setPerfil(Perfil.INVITADO);
						break;
					} else if (opcionSalir.equalsIgnoreCase("N")) {
						break;
					} else {
						System.out.println("Opcion invalida");
						sc.nextLine();
						break;
					}
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + opcion);
				}
				break;
			}

			case ADMIN: {
				Metodos.menuAdmin(nuevaSesion);

				break;
			}
			default:
				System.out.println("Caso default");
			}

		} while (ejecucion);
		sc.close();
	}
}
