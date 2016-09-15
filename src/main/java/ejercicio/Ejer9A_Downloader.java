package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer9A_Downloader {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	private static volatile int fragPendiente = 0;

	private static int descargarDatos(int numFragment) {
		sleepRandom(1000);
		return numFragment * 2;
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

		while (true) {

			enterMutex();
			if (fragPendiente == N_FRAGMENTOS) {
				exitMutex();
				break;
			}

			int fragDescargar = fragPendiente;
			fragPendiente++;
			exitMutex();

			println(getThreadName() + ": Descargando fragmento " + fragDescargar);

			int downloadedData = descargarDatos(fragDescargar);

			println(getThreadName() + ": Escribiendo fragmento " + fragDescargar);

			fichero[fragDescargar] = downloadedData;

		}
	}

	public static void main(String[] args) {

		createThreads(N_HILOS, "downloader");

		startThreadsAndWait();

		mostrarFichero();
	}

}
