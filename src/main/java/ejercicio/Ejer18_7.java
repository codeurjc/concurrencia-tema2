package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_7 {

	private static final int NPROCESOS = 3;
	private static volatile int nProcesos;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore emNProcesos;

	public static void proceso() {		
		print("A");
		puntoSincronizacion();		
		print("B");
	}

	private static void puntoSincronizacion() {
		emNProcesos.acquire();
		nProcesos++;
		if (nProcesos == NPROCESOS) {			
			for (int i = 0; i < NPROCESOS; i++) {
				sb.release();
			}			
		}
		emNProcesos.release();
		sb.acquire();
	}

	public static void main(String[] args) {
		nProcesos = 0;
		sb = new SimpleSemaphore(0);
		emNProcesos = new SimpleSemaphore(1);
		createThreads(NPROCESOS, "proceso");
		startThreadsAndWait();
	}
}
