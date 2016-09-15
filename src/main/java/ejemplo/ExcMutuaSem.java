package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class ExcMutuaSem {

	private static SimpleSemaphore em;
	
	public static void p() {
		
		for (int i = 0; i < 5; i++) {

			//Sección bajo EM
			em.acquire();
			printlnI("P_EM1");
			printlnI("P_EM2");
			em.release();

			// Sección sin EM
			printlnI("P_1");
			printlnI("P_2");
		}
	}

	public static void main(String[] args) {

		em = new SimpleSemaphore(1);

		createThreads(2,"p");

		startThreadsAndWait();

	}
}
