package ejemplo;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class ProdConsBuffer {

	private static final int BUFFER_SIZE = 10;
	
	private static int[] datos = new int[BUFFER_SIZE];
	private static int posInser = 0;
	private static int posSacar = 0;
	private static SimpleSemaphore nHuecos = new SimpleSemaphore(BUFFER_SIZE);
	private static SimpleSemaphore nProductos = new SimpleSemaphore(0);
	private static SimpleSemaphore emPosInser = new SimpleSemaphore(1);
	private static SimpleSemaphore emPosSacar = new SimpleSemaphore(1);
	
	public static void insertarBuffer(int dato) {		
		nHuecos.acquire();		
		
		emPosInser.acquire();
		datos[posInser] = dato;
		posInser = (posInser+1) % datos.length;
		emPosInser.release();
		
		nProductos.release();
	}

	public static int sacarBuffer() {		
		nProductos.acquire();
		
		emPosSacar.acquire();
		int dato = datos[posSacar];
		posSacar = (posSacar+1) % datos.length;
		emPosSacar.release();
		
		nHuecos.release();	
		
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
