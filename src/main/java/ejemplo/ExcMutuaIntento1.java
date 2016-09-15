package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutuaIntento1 {

	private static volatile boolean ocupado;

	public static void p1() {
		
		for (int i = 0; i < 5; i++) {
			
			while (ocupado);
			ocupado = true;

			// Sección bajo EM
			printlnI("P1_EM1 ");
			printlnI("P1_EM2 ");

			ocupado = false;

			// Sección sin EM
			printlnI("P1_1 ");
			printlnI("P1_2 ");
		}
	}

	public static void p2() {

		for (int i = 0; i < 5; i++) {
			
			while (ocupado);
			ocupado = true;

			// Sección bajo EM
			printlnI("P1_EM1 ");
			printlnI("P1_EM2 ");

			ocupado = false;

			// Sección sin EM
			printlnI("P1_1 ");
			printlnI("P1_2 ");
		}
	}

	public static void main(String[] args) {

		ocupado = false;

		createThread("p1");
		createThread("p2");

		startThreadsAndWait();

	}
}
