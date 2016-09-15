package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer3_ClienteServidor {

	static volatile boolean pedido;
	static volatile boolean respondido;
	
	static volatile double peticion;
	static volatile double respuesta;

	public static void server() {
		
		while(!pedido);
		respuesta = peticion + 1;
		respondido = true;
	}

	public static void client() {
		
		peticion = Math.random();
		pedido = true;
		while (!respondido);
		print("Response: "+respuesta);
	}

	public static void main(String[] args) {

		pedido = false;
		respondido = false;

		createThread("client");
		createThread("server");

		startThreadsAndWait();
	}	
}
