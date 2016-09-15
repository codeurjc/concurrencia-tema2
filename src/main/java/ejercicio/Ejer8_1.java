package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer8_1 {

	static volatile boolean fin = false;

	public static void proceso() {
		int i = 0;           // I1
		while (!fin) {       // I2
			i++;             // I3
			if (i == 3) {    // I4
				fin = true;  // I5
			} else {
				fin = false; // I6
			}
		}
	}

	public static void main(String[] args) {
		createThreads(2, "proceso");
		startThreadsAndWait();
	}
}
