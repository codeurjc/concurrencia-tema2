package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer13B_Metro {

	private static final int NUM_TRAMOS = 6;
	private static final int NUM_TRENES = 3;
	private static SimpleSemaphore[] emTramos;

	public static void tren(int numTren) {
		for (int numTramo = 0; numTramo < NUM_TRAMOS; numTramo++) {
			
			sleepRandom(500);
			emTramos[numTramo].acquire();
			
			if(numTramo != 0){
				emTramos[numTramo-1].release();	
			}
			
			recorrerTramo(numTren, numTramo);
		}
		
		emTramos[NUM_TRAMOS-1].release();
	}
	
	private static void recorrerTramo(int numTren, int numTramo) {
		char tramo = (char)('A' + numTramo);
		printlnI("Entra T"+tramo+" T" + numTren);
		sleepRandom(500);
		printlnI("Sale T"+tramo+" T" + numTren);
	}

	public static void main(String args[]){
		
		emTramos = new SimpleSemaphore[NUM_TRAMOS];
		for(int i=0; i<NUM_TRAMOS; i++){
			emTramos[i] = new SimpleSemaphore(1);
		}
		
		for(int i=0; i<NUM_TRENES; i++){
			createThread("tren", i);
		}
		startThreadsAndWait();
	}
}
