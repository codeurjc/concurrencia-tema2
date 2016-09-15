package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer17_Filosofos_Mal {

	public static final int N_FILOSOFOS = 5;

	public static SimpleSemaphore[] tenedores = new SimpleSemaphore[N_FILOSOFOS];

	public static void filosofo(int numFilosofo) {

		while(true){
			printlnI("Pensar");
			
			int tIzq = numFilosofo; 
			int tDer = (numFilosofo+1) % N_FILOSOFOS;
			
		    tenedores[tIzq].acquire();
		    sleepRandom(500); //Simular interbloqueo
		    tenedores[tDer].acquire();
		    		    
		    printlnI("Comer");      
		    
		    tenedores[tIzq].release();
		    tenedores[tDer].release();
		}		
	}

	public static void main(String[] args) {

		for (int i = 0; i < N_FILOSOFOS; i++) {
			tenedores[i] = new SimpleSemaphore(1);
		}
		
		for (int i = 0; i < N_FILOSOFOS; i++) {
			createThread("filosofo", i);
		}
		startThreadsAndWait();
	}
}
