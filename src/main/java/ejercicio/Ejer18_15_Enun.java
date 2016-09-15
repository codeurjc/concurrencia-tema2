package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_15_Enun {

	private static final int N_PROCESOS = 4;
	private static final int K = 2;
	
	public static void p() {		
		while(true){
			// Obtener recurso
			// Usar recurso
			// Liberar recurso
			// Hacer otras cosas
		}
	}

	public static void main(String[] args) {
		createThreads(N_PROCESOS,"p");
		startThreadsAndWait();
	}
}
