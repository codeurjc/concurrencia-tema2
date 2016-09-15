package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class ExcMutuaGenSem {

	private static SimpleSemaphore em;
	
	public static void p() {
		
		for (int i = 0; i < 5; i++) {

			em.acquire();
			printlnI("P_EM1");
			printlnI("P_EM2");
			em.release();

			// Sección No Crítica
			printlnI("P_1");
			printlnI("P_2");
		}
	}

	public static void main(String[] args) {

		em = new SimpleSemaphore(3);

		createThreads(5,"p");

		startThreadsAndWait();

	}
}
