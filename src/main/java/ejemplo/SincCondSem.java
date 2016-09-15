package ejemplo;


import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class SincCondSem {

	static SimpleSemaphore continuar;

	public static void a() {
		print("PA1 ");
		continuar.release();
		print("PA2 ");
	}

	public static void b() {
		print("PB1 ");
		continuar.acquire();
		print("PB2 ");
	}

	public static void main(String[] args) {

		continuar = new SimpleSemaphore(0);

		createThread("a");
		createThread("b");

		startThreadsAndWait();
	}
}
