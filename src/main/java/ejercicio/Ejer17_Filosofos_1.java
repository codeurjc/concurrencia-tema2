package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer17_Filosofos_1 {

	public static final int N_FILOSOFOS = 5;

	public static SimpleSemaphore[] tenedores = new SimpleSemaphore[N_FILOSOFOS];
	public static SimpleSemaphore comedor;

	public static void filosofo(int numFilosofo) {

		while (true) {
			printlnI("Pensar");

			int tIzq = numFilosofo;
			int tDer = (numFilosofo + 1) % N_FILOSOFOS;

			comedor.acquire();

			tenedores[tIzq].acquire();
			tenedores[tDer].acquire();

			printlnI("Comer");

			tenedores[tIzq].release();
			tenedores[tDer].release();
			
			comedor.release();

		}
	}

	public static void main(String[] args) {

		comedor = new SimpleSemaphore(N_FILOSOFOS - 1);
		for (int i = 0; i < N_FILOSOFOS; i++) {
			tenedores[i] = new SimpleSemaphore(1);
		}

		for (int i = 0; i < N_FILOSOFOS; i++) {
			createThread("filosofo", i);
		}
		startThreadsAndWait();
	}
}
