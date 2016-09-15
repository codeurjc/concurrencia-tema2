package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer10_SincCond {

	static volatile boolean continuarA;
	static volatile boolean continuarD;
	static volatile boolean continuarE1;
	static volatile boolean continuarE2;

	public static void proc1() {
		while(!continuarA);
		print("A");
		print("B");
		continuarE1 = true;
	}

	public static void proc2() {
		print("C");
		continuarA = true;
		while(!continuarD);
		print("D");
		while(!continuarE1);
		while(!continuarE2);
		print("E");		
	}
	
	public static void proc3() {
		print("F");
		continuarD = true; 
		print("G");
		continuarE2 = true;
	}

	public static void main(String[] args) {

		continuarA = false;
		continuarD = false;
		continuarE1 = false;
		continuarE2 = false;

		createThread("proc1");
		createThread("proc2");
		createThread("proc3");
		
		startThreadsAndWait();
	}
}
