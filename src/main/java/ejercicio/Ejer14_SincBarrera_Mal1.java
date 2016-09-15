package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer14_SincBarrera_Mal1 {

	private static final int NPROCESOS = 3;

	private static volatile int nProcesos;
	private static SimpleSemaphore sb;

	public static void proceso() {
		print("A");
		nProcesos++;
		if (nProcesos < NPROCESOS) {
			sb.acquire();
		} else {
			for (int i = 0; i < NPROCESOS - 1; i++) {
				sb.release();
			}
		}
		print("B");
	}

	public static void main(String[] args) {		
		nProcesos = 0;		
		sb = new SimpleSemaphore(0);		
		createThreads(NPROCESOS, "proceso");		
		startThreadsAndWait();
	}
}
