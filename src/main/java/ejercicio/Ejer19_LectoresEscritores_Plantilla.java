package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer19_LectoresEscritores_Plantilla {

	public static void inicioLectura() { }

	public static void finLectura() { }

	public static void inicioEscritura() { }

	public static void finEscritura() { }

	public static void lector() {
		while(true){
			inicioLectura();
			println("Leer datos");
			finLectura();		
			println("Procesar datos");
		}
	}

	public static void escritor() {
		while (true) {
			println("Generar datos");
			inicioEscritura();
			println("Escribir datos");
			finEscritura();			
		}
	}

	public static void main(String[] args) {		
		createThreads(5, "lector");
		createThreads(3, "escritor");
		startThreadsAndWait();
	}
}
