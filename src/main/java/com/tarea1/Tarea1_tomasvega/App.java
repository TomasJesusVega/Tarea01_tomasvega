package com.tarea1.Tarea1_tomasvega;

import entidades.Perfil;
import entidades.Sesion;
import entidades.Perfil;
import entidades.Sesion;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	Sesion nuevaSesion = new Sesion("Invitado", Perfil.INVITADO);
    	boolean sesionIniciada = true;
    	do {
    		System.out.println("============================================\n");
    		System.out.println("	Programa de gestion de circo");
    		System.out.println(" ");
    		System.out.println(" ");
    		System.out.println("	Por Tomas Jesus Vega Leiva");
    		System.out.println(" ");
    		System.out.println("============================================");
    		showGuestMenu();
    		sesionIniciada = false;
		} while (sesionIniciada == true);
    }
    
    public static void nuevaSeleccion (int opcion) {
    	
    }
    
    public static void showGuestMenu () {
    	System.out.println("Sesion: ");
    	System.out.println("1. Login");
    	System.out.println("2. Ver espectaculos");
    }
    
    public static void showCoordMenu () {
    	
    }
    
    public static void showAdminMenu () {
    	
    }
}
