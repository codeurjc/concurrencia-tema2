package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer13A_Metro_Plantilla {

	private static final int NUM_TRENES = 5;

	public static void tren(int numTren) {		
		
		sleepRandom(500);
		recorrerTramoA(numTren);
		
		sleepRandom(500);
		recorrerTramoB(numTren);
		
		sleepRandom(500);
		recorrerTramoC(numTren);
	}

	private static void recorrerTramoA(int numTren) {
		printlnI("Entra TA T" + numTren);
		sleepRandom(500);
		printlnI("Sale TA T" + numTren);
	}

	private static void recorrerTramoB(int numTren) {
		printlnI("Entra TB T" + numTren);
		sleepRandom(500);
		printlnI("Sale TB T" + numTren);
	}

	private static void recorrerTramoC(int numTren) {
		printlnI("Entra TC T" + numTren);
		sleepRandom(500);
		printlnI("Sale TC T" + numTren);
	}

	public static void main(String args[]) {
		for (int i = 0; i < NUM_TRENES; i++) {
			createThread("tren", i);
		}
		startThreadsAndWait();
	}
}
