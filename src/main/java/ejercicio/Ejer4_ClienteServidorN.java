package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer4_ClienteServidorN {

	static volatile boolean pedido;
	static volatile boolean respondido;

	static volatile double peticion;
	static volatile double respuesta;

	public static void server() {

		while (true) {
			while (!pedido);
			pedido = false;
			respuesta = peticion + 1;
			respondido = true;
		}
	}

	public static void client() {

		while (true) {
			peticion = Math.random();
			pedido = true;
			while (!respondido);
			respondido = false;
			println("Response: " + respuesta);
		}
	}

	public static void main(String[] args) {

		pedido = false;
		respondido = false;

		createThread("client");
		createThread("server");

		startThreadsAndWait();
	}
}
