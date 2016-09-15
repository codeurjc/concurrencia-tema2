package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer15_SincBarreraCicl {

	private static volatile int nProcesos;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore desbloqueo;
	private static SimpleSemaphore emNProcesos;

	public static void procesoA() {
		while (true) {
			print("A");
			sincronizacion();
		}
	}

	public static void procesoB() {
		while (true) {
			print("B");
			sincronizacion();
		}
	}

	public static void procesoC() {
		while (true) {
			print("C");
			sincronizacion();
		}
	}

	public static void procesoD() {
		while (true) {
			print("D");
			sincronizacion();
		}
	}

	public static void sincronizacion() {

		emNProcesos.acquire();
		nProcesos++;
		if (nProcesos < 4) {
			emNProcesos.release();
			sleepRandom(500); // Simular condiciones de carrera
			sb.acquire();
			desbloqueo.release();
		} else {

			println("-");
			nProcesos = 0;

			sb.release(3);
			desbloqueo.acquire(3);
			emNProcesos.release();
		}
	}

	public static void main(String[] args) {

		nProcesos = 0;
		sb = new SimpleSemaphore(0);
		desbloqueo = new SimpleSemaphore(0);
		emNProcesos = new SimpleSemaphore(1);

		createThread("procesoA");
		createThread("procesoB");
		createThread("procesoC");
		createThread("procesoD");

		startThreadsAndWait();
	}
}
