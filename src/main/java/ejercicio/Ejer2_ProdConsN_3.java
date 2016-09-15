package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer2_ProdConsN_3 {

	static volatile boolean produciendo;
	static volatile double producto;

	public static void productor() {
		double num = 0;
		for(int i=0; i<5; i++){			
			double almacen = num++; // Crea el producto
			sleep(500); //Simula tiempo de consumo
			while (!produciendo); // Espera para colocarlo
			producto = almacen; // Guardar el valor
			produciendo = false; // Se notifica que ya hay valor			
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
