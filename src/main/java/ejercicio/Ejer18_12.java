package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_12 {

	private static SimpleSemaphore semD;
	private static SimpleSemaphore semF;	

	public static void proceso1() {		
		print("A");		
		semD.release();
		print("B");
	}
	
	public static void proceso2() {
		print("C");		
		semF.release();
		semD.acquire();
		print("D");
	}
	
	public static void proceso3() {		
		print("E");
		semF.acquire();
		semF.acquire();
		print("F");
	}

	public static void main(String[] args) {
		
		semD = new SimpleSemaphore(0);
		semF = new SimpleSemaphore(0);
		
		createThread("proceso1");
		createThread("proceso2");
		createThread("proceso3");
		
		startThreadsAndWait();
	}
}
