package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer9A_Downloader_Plantilla {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	
	//Add the attributes you need

	private static int descargaDatos(int numFragmento) {
		sleepRandom(1000);
		return numFragmento * 2;
	}
	
	private static void mostrarFichero() {
		println("--------------------------------------------------");
		print("File = [");
		for (int i = 0; i < N_FRAGMENTOS; i++) {
			print(fichero[i] + ",");
		}
		println("]");
	}

	public static void downloader() {

		// Mientras hay fragmentos que descargar...
		
			//Descargar los datos del siguiente fragmento
			
			//Almacenar los datos en el array		
	}

	public static void main(String[] args) {

		createThreads(N_HILOS, "downloader");

		startThreadsAndWait();

		mostrarFichero();
	}

}
