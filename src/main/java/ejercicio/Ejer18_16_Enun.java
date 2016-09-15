package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer18_16_Enun {

	private static SimpleSemaphore[] sinc = new SimpleSemaphore[2];

	public static void puntoSinc(int numProc) {

		if (numProc == 0) {
			sinc[0].release();
			sinc[1].release();
		} else {
			if (numProc == 1) {
				sinc[1].release();
				sinc[0].acquire();
			} else {
				sinc[1].acquire();
				sinc[1].acquire();
			}
		}
	}

	public static void proceso(int numProc) {
		print("A"+numProc+" ");
		puntoSinc(numProc);
		print("B"+numProc+" ");
	}

	public static void main(String[] args) {
		sinc[0] = new SimpleSemaphore(0);
		sinc[1] = new SimpleSemaphore(0);

		for (int i = 0; i < 3; i++) {
			createThread("proceso", i);
		}

		startThreadsAndWait();
	}
}
