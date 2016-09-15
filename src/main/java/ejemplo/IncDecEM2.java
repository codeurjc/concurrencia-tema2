package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class IncDecEM2 {

	static volatile double x;
	static volatile double y;

	public static void inc() {
		enterMutex("x");
		x = x + 1;
		exitMutex("x");
	}

	public static void dec() {
		enterMutex("x");
		x = x - 1;
		exitMutex("x");
	}

	public static void incY() {
		enterMutex("y");
		y = y + 1;
		exitMutex("y");
	}

	public static void decY() {
		enterMutex("y");
		y = y - 1;
		exitMutex("y");
	}

	public static void main(String[] args) {

		x = 0; y=0;		

		createThread("inc");
		createThread("dec");
		
		createThread("incY");
		createThread("decY");
		
		startThreadsAndWait();

		println("x:" + x);
		println("y:" + y);
	}
}