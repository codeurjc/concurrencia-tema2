package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer2_ProdConsN_2 {

	static volatile boolean produciendo;
	static volatile double producto;

	public static void productor() {
		double num = 0;
		for(int i=0; i<5; i++){
			producto = num++;
			sleep(500); //Simula tiempo de producción
			produciendo = false;			
			while(!produciendo);
		}
	}

	public static void consumidor() {

		for(int i=0; i<5; i++){
			while (produciendo);
			println("Producto: " + producto);
			sleep(500); //Simula tiempo de consumo
			produciendo = true;
		}
	}

	public static void main(String[] args) {

		produciendo = true;
		
		createThread("productor");
		createThread("consumidor");

		startThreadsAndWait();
	}
}
