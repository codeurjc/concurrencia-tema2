package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer11_ClienteServidorProxy {

	static volatile boolean pedido;
	static volatile boolean respondido;

	static volatile double peticion;
	static volatile double respuesta;

	static volatile boolean pedidoProxy;
	static volatile boolean respondidoProxy;

	static volatile double peticionProxy;
	static volatile double respuestaProxy;

	public static void client() {

		peticionProxy = Math.random();
		pedidoProxy = true;
		while (!respondidoProxy);
		print("Response: "+respuestaProxy);
	}

	public static void proxy() {

		while (!pedidoProxy);		
		
		peticion = peticionProxy + 1;
		pedido = true;
		
		while (!respondido);
		
		respuestaProxy = respuesta;
		respondidoProxy = true;
	}

	public static void server() {

		while(!pedido);
		respuesta = peticion + 1;
		respondido = true;
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
