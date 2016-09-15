package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_7_Enun {

	private static final int NPROCESOS = 3;

	public static void proceso() {		
		print("A");
		puntoSincronizacion();		
		print("B");
	}

	private static void puntoSincronizacion() {

	}

	public static void main(String[] args) {
		createThreads(NPROCESOS, "proceso");
		startThreadsAndWait();
	}
}
