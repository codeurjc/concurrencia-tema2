package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutua1 {

	private static volatile int turno;

	public static void p1() {
		
		for (int i = 0; i < 5; i++) {
			// Preprotocolo
			while (turno != 1) {}

			// Sección Crítica
			printlnI("P1_SC1 ");
			printlnI("P1_SC2 ");

			// Postprotocolo
			turno = 2;

			// Sección No Crítica
			printlnI("P1_SNC1 ");
			printlnI("P1_SNC2 ");
		}
	}

	public static void p2() {

		for (int i = 0; i < 5; i++) {
			// Preprotocolo
			while (turno != 2) {}

			// Sección Crítica
			printlnI("P2_SC1 ");
			printlnI("P2_SC2 ");

			// Postprotocolo
			turno = 1;

			// Sección No Crítica
			printlnI("P2_SNC1 ");
			printlnI("P2_SNC2 ");
		}
	}

	public static void main(String[] args) {

		turno = 1;

		createThread("p1");
		createThread("p2");

		startThreadsAndWait();

	}
}
