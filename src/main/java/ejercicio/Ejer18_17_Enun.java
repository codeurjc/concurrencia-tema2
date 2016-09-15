package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer18_17_Enun {

	private static SimpleSemaphore s;
	
	public static void p1(){
		print("A");
		s.release();
	}
	
	public static void p2(){
		s.acquire();
		print("B");
		s.release();
		s.release();
	}
						
	public static void p3(){
		s.acquire();
		s.acquire();
		print("C");
	}

	public static void main(String[] args){
		
		s = new SimpleSemaphore(0);
		
		createThread("p1");
		createThread("p2");
		createThread("p3");
		
		startThreadsAndWait();		
	}
}
