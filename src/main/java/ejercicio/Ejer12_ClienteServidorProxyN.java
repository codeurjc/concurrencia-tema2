package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer12_ClienteServidorProxyN {

	static volatile boolean pedido;
	static volatile boolean respondido;

	static volatile double peticion;
	static volatile double respuesta;

	static volatile boolean pedidoProxy;
	static volatile boolean respondidoProxy;

	static volatile double peticionProxy;
	static volatile double respuestaProxy;

	public static void client() {
		for (int i = 0; i < 10; i++) {
			peticionProxy = Math.random();
			pedidoProxy = true;
			while (!respondidoProxy);
			respondidoProxy = false;
			println("Response: " + respuestaProxy);
		}
	}

	public static void proxy() {

		for (int i = 0; i < 10; i++) {
			while (!pedidoProxy);
			pedidoProxy = false;
			
			peticion = peticionProxy + 1;
			pedido = true;

			while (!respondido);
			respondido = false;

			respuestaProxy = respuesta;
			respondidoProxy = true;
		}
	}

	public static void server() {
		for (int i = 0; i < 10; i++) {
			while (!pedido);
			pedido = false;
			respuesta = peticion + 1;
			respondido = true;
		}
	}

	public static void main(String[] args) {

		pedido = false;
		respondido = false;

		pedidoProxy = false;
		respondidoProxy = false;

		createThread("client");
		createThread("proxy");
		createThread("server");

		startThreadsAndWait();
	}
}
