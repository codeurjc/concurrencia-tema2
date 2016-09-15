package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ExcMutuaEA2 {

	public static void p() {

		for (int i = 0; i < 5; i++) {

			enterMutex();
			printlnI("********");
			printlnI("********");
			printlnI("********");
			exitMutex();

			printlnI("--------");
			printlnI("--------");
			printlnI("--------");
			printlnI("--------");
			printlnI("--------");
		}
	}

	public static void main(String[] args) {
		createThreads(4, "p");
		startThreadsAndWait();
	}
}