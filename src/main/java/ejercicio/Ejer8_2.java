package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer8_2 {

	static volatile boolean flag = false;

	public static void p1(){		
	
		while(true){
			print("A");
			flag = true;
			print("B");
		}
	}
	
	public static void p2(){
		while(true){
			print("C");
			while(!flag);
			print("D");
		}
	}

	public static void main(String[] args) {
		createThread("p1");
		createThread("p2");
		startThreadsAndWait();
	}
}
