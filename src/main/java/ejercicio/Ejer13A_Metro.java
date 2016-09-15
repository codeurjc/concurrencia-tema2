package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.createThread;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.printlnI;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.sleepRandom;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.startThreadsAndWait;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer13A_Metro {

	private static final int NUM_TRENES = 5;
	private static SimpleSemaphore emTramoA;
	private static SimpleSemaphore emTramoB;
	private static SimpleSemaphore emTramoC;
	
	public static void tren(int numTren) {
		
		sleepRandom(500);
		
		emTramoA.acquire();
		recorrerTramoA(numTren);
		
		sleepRandom(500);
		
		emTramoB.acquire();
		emTramoA.release();
		recorrerTramoB(numTren);
		
		sleepRandom(500);
		
		emTramoC.acquire();
		emTramoB.release();
		recorrerTramoC(numTren);
		
		emTramoC.release();
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


	public static void main(String args[]){
		
		emTramoA = new SimpleSemaphore(1);
		emTramoB = new SimpleSemaphore(1);
		emTramoC = new SimpleSemaphore(1);
		
		for(int i=0; i<NUM_TRENES; i++){
			createThread("tren", i);
		}
		startThreadsAndWait();
	}
}
