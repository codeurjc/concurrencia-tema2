package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejemplo1 {

	public static void repeat(String text) {
		for (int i = 0; i < 5; i++) {
			println(text);
		}
	}

	public static void printText() {
		println("B1");
		println("B2");
		println("B3");
	}

	public static void main(String[] args) {

		createThread("repeat", "XXXXX");
		createThread("repeat", "-----");
		createThread("printText");

		startThreadsAndWait();
	}
}