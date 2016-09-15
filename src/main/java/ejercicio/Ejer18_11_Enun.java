package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_11_Enun {

	private static int x;
	private static int y;
	private static SimpleSemaphore sY;
	private static SimpleSemaphore sX;

	public static void pA() {
		sX.acquire();
		x++;
		sY.acquire();
		y *= x;
		sY.release();
		sX.release();
	}

	public static void pB() {
		sY.acquire();
		y++;
		sX.acquire();
		x *= y;
		sX.release();
		sY.release();
	}

	public static void main(String[] args) {

		x = 2;
		y = 3;

		sX = new SimpleSemaphore(1);
		sY = new SimpleSemaphore(1);

		createThread("pA");
		createThread("pB");

		startThreadsAndWait();
	}
}
