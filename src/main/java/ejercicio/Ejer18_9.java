package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_9 {

	private static SimpleSemaphore semA;
	private static SimpleSemaphore semC;
	private static SimpleSemaphore semD;

	public static void proceso1() {		
		semA.acquire();
		print("A");		
		semC.release();
	}
	
	public static void proceso2() {
		print("B");
		semA.release();
		semD.release();
		semC.acquire();
		semC.acquire();
		print("C");
	}
	
	public static void proceso3() {		
		semD.acquire();
		print("D");
		semC.release();
	}

	public static void main(String[] args) {
		
		semA = new SimpleSemaphore(0);
		semC = new SimpleSemaphore(0);
		semD = new SimpleSemaphore(0);
		
		createThread("proceso1");
		createThread("proceso2");
		createThread("proceso3");
		
		startThreadsAndWait();
	}
}
