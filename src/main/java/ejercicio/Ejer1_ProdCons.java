package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer1_ProdCons {

	static volatile boolean producido;
	static volatile double producto;

	public static void productor() {
		
		producto = Math.random();
		producido = true;
	}

	public static void consumidor() {
		
		while (!producido);
		print("Producto: "+producto);
	}

	public static void main(String[] args) {

		producido = false;

		createThread("productor");
		createThread("consumidor");

		startThreadsAndWait();
	}
}
