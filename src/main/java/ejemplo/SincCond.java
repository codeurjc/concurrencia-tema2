package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class SincCond {

	static volatile boolean continuar;

	public static void a() {
		print("PA1 ");
		continuar = true;
		print("PA2 ");
	}

	public static void b() {
		print("PB1 ");
		while (!continuar);
		print("PB2 ");
	}

	public static void main(String[] args) {

		continuar = false;

		createThread("a");
		createThread("b");

		startThreadsAndWait();
	}
}
