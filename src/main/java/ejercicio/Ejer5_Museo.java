package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer5_Museo {

	public static void persona() {

		while (true) {
			
			enterMutex();
			printlnI("hola!");
			printlnI("qué bonito!");
			printlnI("alucinante!");
			printlnI("adiós");
			exitMutex();
			
			printlnI("paseo");
		}
	}

	public static void main(String[] args) {
		createThreads(3, "persona");
		startThreadsAndWait();
	}
}
