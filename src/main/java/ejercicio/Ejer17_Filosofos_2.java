package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer17_Filosofos_2 {

	public static final int N_FILOSOFOS = 5;

	public static SimpleSemaphore[] tenedores = new SimpleSemaphore[N_FILOSOFOS];
	public static SimpleSemaphore comedor;

	public static void filosofo(int numFilosofo, boolean diestro) {

		while (true) {
			printlnI("Pensar");

			int tIzq = numFilosofo;
			int tDer = (numFilosofo + 1) % N_FILOSOFOS;

			if (diestro) {
				tenedores[tIzq].acquire();
				tenedores[tDer].acquire();
			} else {
				tenedores[tDer].acquire();
				tenedores[tIzq].acquire();
			}

			printlnI("Comer");

			tenedores[tIzq].release();
			tenedores[tDer].release();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < N_FILOSOFOS; i++) {
			tenedores[i] = new SimpleSemaphore(1);
		}

		for (int i = 0; i < N_FILOSOFOS - 1; i++) {
			createThread("filosofo", i, true);
		}
		createThread("filosofo", N_FILOSOFOS - 1, false);

		startThreadsAndWait();
	}
}
