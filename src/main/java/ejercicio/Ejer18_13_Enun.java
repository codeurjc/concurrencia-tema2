package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_13_Enun {

	private static final int N = 3;
	private static volatile int nProcesos;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore emNProcesos;

	public static void proceso() {
		while (true) {
			print("A");
			puntoSincronizacion();
			print("B");
		}
	}

	private static void puntoSincronizacion() {
		emNProcesos.acquire();
		nProcesos++;
		if (nProcesos == N) {
			nProcesos = 0;
			emNProcesos.release(); //I0
			for (int i = 0; i < N; i++) { 
				sb.release(); //I1
			}
		} else {
			emNProcesos.release();
			sb.acquire();
		}
	}

	public static void main(String[] args) {
		nProcesos = 0;

		sb = new SimpleSemaphore(0);
		emNProcesos = new SimpleSemaphore(1);

		createThreads(N, "proceso");
		startThreadsAndWait();
	}
}
