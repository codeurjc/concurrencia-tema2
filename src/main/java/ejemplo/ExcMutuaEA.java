package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutuaEA {

	public static void p1() {

		for (int i = 0; i < 5; i++) {

			enterMutex();

			// Sección bajo EM
			printlnI("P1_EM1 ");
			printlnI("P1_EM2 ");

			exitMutex();

			// Sección sin EM
			printlnI("P1_1 ");
			printlnI("P1_2 ");
		}
	}

	public static void p2() {

		for (int i = 0; i < 5; i++) {

			enterMutex();

			// Sección bajo EM
			printlnI("P2_EM1 ");
			printlnI("P2_EM2 ");

			exitMutex();

			// Sección sin EM
			printlnI("P2_1 ");
			printlnI("P2_2 ");
		}
	}

	public static void main(String[] args) {

		createThread("p1");
		createThread("p2");

		startThreadsAndWait();

	}
}
