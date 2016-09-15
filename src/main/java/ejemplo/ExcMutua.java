package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutua {

	public static void p1() {
		
		while(true) {

			// Sección bajo EM
			printlnI("P1_EM1 ");
			printlnI("P1_EM2 ");

			// Sección sin EM
			printlnI("P1_1 ");
			printlnI("P1_2 ");
		}
	}

	public static void main(String[] args) {

		createThreads(2, "p1");

		startThreadsAndWait();

	}
}
