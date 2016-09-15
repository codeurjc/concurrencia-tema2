package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer19_LectoresEscritores_1 {

	// Número de escritores que han conseguido el acceso a la BD
	private static int escritoresBD = 0;

	// Número de lectores que han conseguido el acceso a la BD
	private static int lectoresBD = 0;
	
	// Lectores esperando a que finalicen los escritores
	private static int lectoresEspera;

	// Escritores esperando a que finalicen los lectores 
	private static int escritoresEspera;

	// Exclusión mutura de las variables de control
	private static SimpleSemaphore emControl;
	
	// Exclusión mutura para el acceso a los escritores 
	private static SimpleSemaphore emEscritura;
	
	// Bloqueo de los lectores cuando hay escritores
	private static SimpleSemaphore esperaFinEscritores;
	
	// Bloqueo de los escritores cuando hay lectores
	private static SimpleSemaphore esperaFinLectores;

	public static void inicioLectura() {
		emControl.acquire();
		printlnI("inicioLectura");
		if (escritoresBD == 0 && escritoresEspera == 0) {
			lectoresBD++;
			printlnI("lectTrab="+lectoresBD);
			emControl.release();
		} else {
			lectoresEspera++;
			printlnI("lectEspera="+lectoresEspera);
			emControl.release();			
			esperaFinEscritores.acquire();
		}
	}

	public static void finLectura() {
		emControl.acquire();
		lectoresBD--;
		printlnI("finLectura");
		printlnI("lectTrab="+lectoresBD);
		if (lectoresBD == 0) {
			printlnI("escriEspera="+escritoresEspera);
			for(int i=0; i<escritoresEspera; i++) {
				escritoresBD++;
				esperaFinLectores.release();
			}
			escritoresEspera = 0;
		}
		emControl.release();
	}

	public static void inicioEscritura() {
		emControl.acquire();
		printlnI("inicioEscritura");
		if (lectoresBD == 0) {
			escritoresBD++;
			printlnI("escTrab="+escritoresBD);
			emControl.release();
		} else {
			escritoresEspera++;
			printlnI("escEspera="+escritoresEspera);
			emControl.release();
			esperaFinLectores.acquire();
		}
		
		emEscritura.acquire();
		printlnI("Escribiendo...");
	}

	public static void finEscritura() {
		printlnI("FinEscribiendo...");
		emEscritura.release();
		
		emControl.acquire();
		escritoresBD--;
		printlnI("finEscritura");
		printlnI("escriTrab="+escritoresBD);
		if (escritoresBD == 0) {
			printlnI("lectEspera="+lectoresEspera);
			for(int i=0; i<lectoresEspera; i++){
				lectoresBD++;
				esperaFinEscritores.release();
			}
			lectoresEspera=0;
		}
		emControl.release();		
	}

	public static void lector() {
		while (true) {
			inicioLectura();
			printlnI("Leer datos");
			sleepRandom(300);
			finLectura();
			printlnI("Procesar datos");
			sleepRandom(500);
		}
	}

	public static void escritor() {
		while (true) {
			printlnI("Generar datos");
			sleepRandom(2000);
			inicioEscritura();
			printlnI("Escribir datos");
			sleepRandom(500);
			finEscritura();
		}
	}

	public static void main(String[] args) {

		emControl = new SimpleSemaphore(1);
		emEscritura = new SimpleSemaphore(1);
		esperaFinEscritores = new SimpleSemaphore(0);
		esperaFinLectores = new SimpleSemaphore(0);

		createThreads(5, "lector");
		createThreads(3, "escritor");
		startThreadsAndWait();
	}
}
