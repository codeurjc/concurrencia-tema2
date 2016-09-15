package ejercicio;

import es.urjc.etsii.code.concurrency.SimpleSemaphore;
import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer15_SincBarreraCicl_Mal2 {

	private static volatile int nProcesos;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore emNProcesos;

	public static void procesoA() {
		while(true){
			print("A");
			sincronizacion();
		}
	}
	
	public static void procesoB() {
		while(true){
			print("B");
			sincronizacion();
		}
	}
	
	public static void procesoC() {
		while(true){
			print("C");
			sincronizacion();
		}
	}
	
	public static void procesoD() {
		while(true){
			print("D");
			sincronizacion();
		}
	}
	
	public static void sincronizacion(){

		emNProcesos.acquire();
		nProcesos++;
		if (nProcesos < 4) {
			emNProcesos.release();
			//sleepRandom(500); Simular condiciones de carrera
			sb.acquire();
		} else {
			
			println("-");			
			nProcesos = 0;			
			
			for (int i = 0; i < 3; i++) {				
				sb.release();				
			}			
			emNProcesos.release();
		}
	}

	public static void main(String[] args) {
		
		nProcesos = 0;
		sb = new SimpleSemaphore(0);
		emNProcesos = new SimpleSemaphore(1);
		
		createThread("procesoA");
		createThread("procesoB");
		createThread("procesoC");
		createThread("procesoD");
		
		startThreadsAndWait();
	}
}
