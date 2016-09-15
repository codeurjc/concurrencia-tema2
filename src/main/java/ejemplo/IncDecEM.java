package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class IncDecEM {

	static volatile double x;

	public static void inc() {

		for (int i = 0; i < 10000000; i++) {
			enterMutex();
			x = x + 1;
			exitMutex();
		}
	}

	public static void dec() {

		for (int i = 0; i < 10000000; i++) {
			enterMutex();
			x = x - 1;
			exitMutex();
		}
	}

	public static void main(String[] args) {

		x = 0;

		createThread("inc");
		createThread("dec");
		startThreadsAndWait();

		println("x:" + x);
	}
}