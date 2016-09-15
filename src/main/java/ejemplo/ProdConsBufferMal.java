package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class ProdConsBufferMal {

	private static int[] datos = new int[10];
	private static int posInser = 0;
	private static int posSacar = 0;

	public static void insertarBuffer(int dato) {
		datos[posInser] = dato;
		posInser = (posInser + 1) % datos.length;
	}

	public static int sacarBuffer() {
		int dato = datos[posSacar];
		posSacar = (posSacar + 1) % datos.length;
		return dato;
	}

	public static void productor() {
		for (int i = 0; i < 20; i++) {
			sleepRandom(500);
			insertarBuffer(i);
		}
	}

	public static void consumidor() {
		while (true) {
			int data = sacarBuffer();
			sleepRandom(500);
			print(data + " ");
		}
	}

	public static void main(String[] args) {		
		createThreads(5, "productor");
		createThreads(3, "consumidor");
		startThreadsAndWait();
	}
}
