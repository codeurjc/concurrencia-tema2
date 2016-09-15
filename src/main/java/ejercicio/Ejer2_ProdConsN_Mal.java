package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer2_ProdConsN_Mal {

	static volatile boolean producido;
	static volatile boolean consumido;
	static volatile double producto;

	public static void productor() {
		double num = 0;
		
		for(int i=0; i<5; i++){
			producto = num++;			
			producido = true;
			sleep(50); //Simula livelock			
			consumido = false;
			while(!consumido);
		}
	}

	public static void consumidor() {

		for(int i=0; i<5; i++){
			while (!producido);
			println("Producto: " + producto);
			producido = false;
			consumido = true;
		}
	}

	public static void main(String[] args) {

		producido = false;
		consumido = false;

		createThread("productor");
		createThread("consumidor");

		startThreadsAndWait();
	}
}
