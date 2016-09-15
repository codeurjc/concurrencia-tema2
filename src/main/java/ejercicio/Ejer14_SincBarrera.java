package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer14_SincBarrera {

	private static final int NPROCESOS = 3;
	private static volatile int nProcesos;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore emNProcesos;

	public static void proceso() {
		print("A");

		emNProcesos.acquire();
		nProcesos++;
		if (nProcesos < NPROCESOS) {
			emNProcesos.release();
			sb.acquire();
		} else {
			emNProcesos.release();
			for (int i = 0; i < NPROCESOS - 1; i++) {
				sb.release();
			}			
		}
		
		print("B");
	}

	public static void main(String[] args) {
		
		nProcesos = 0;
		sb = new SimpleSemaphore(0);
		emNProcesos = new SimpleSemaphore(1);
		
		createThreads(NPROCESOS, "proceso");
		
		startThreadsAndWait();
	}
}
