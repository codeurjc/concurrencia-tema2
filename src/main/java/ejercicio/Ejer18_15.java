package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer18_15 {

	private static final int N_PROCESOS = 4;
	private static final int K = 2;
	
	private static SimpleSemaphore emGen;
	
	public static void p() {		
		while(true){
			emGen.acquire();
			// Usar recurso
			emGen.release();
			// Hacer otras cosas
		}
	}

	public static void main(String[] args) {
		emGen = new SimpleSemaphore(K);
		createThreads(N_PROCESOS,"p");
		startThreadsAndWait();
	}
}
