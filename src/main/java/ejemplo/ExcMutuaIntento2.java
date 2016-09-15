package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutuaIntento2 {

	private static volatile boolean p1p;
	private static volatile boolean p2p;

	public static void p1() {
		
		for (int i = 0; i < 5; i++) {
			
			p1p = true;
			while (p2p);

			// Sección bajo EM
			printlnI("P1_EM1");
			printlnI("P1_EM2");

			p1p = false;

			// Sección sin EM
			printlnI("P1_1");
			printlnI("P1_2");
		}
	}

	public static void p2() {

		for (int i = 0; i < 5; i++) {
			
			p2p = true;
			while (p1p);

			// Sección bajo EM
			printlnI("P2_EM1");
			printlnI("P2_EM2");
			
			p2p = false;

			// Sección sin EM
			printlnI("P2_1");
			printlnI("P2_2");
		}
	}

	public static void main(String[] args) {

		p1p = false;
		p2p = false;

		createThread("p1");
		createThread("p2");

		startThreadsAndWait();

	}
}
