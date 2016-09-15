package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer18_10_2 {

	private static int x;
	private static SimpleSemaphore sincronizacion;
	private static SimpleSemaphore exclusion;

	public static void p1() {
		exclusion.acquire();
		x++;
		exclusion.release();
		sincronizacion.release();
	}

	public static void p2() {
		exclusion.acquire();
		x++;
		exclusion.release();		
		sincronizacion.acquire();
	}

	public static void main(String[] args) {

		x = 0;
		sincronizacion = new SimpleSemaphore(0);
		exclusion = new SimpleSemaphore(1);

		createThread("p1");
		createThread("p2");
		startThreadsAndWait();
	}
}
