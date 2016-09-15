package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer17_Filosofos_Plantilla {

	public static final int N_FILOSOFOS = 5;

	public static void filosofo(int numFilosofo) {

		while (true) {
			printlnI("Pensar");
			// Obtener tenedores
			printlnI("Comer");
			// Liberar tenedores
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < N_FILOSOFOS; i++) {
			createThread("filosofo", i);
		}
		startThreadsAndWait();
	}
}
