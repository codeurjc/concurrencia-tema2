package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer16_Downloader {

	private static final int N_FRAGMENTOS = 10;
	private static final int N_HILOS = 3;

	private static volatile int[] fichero = new int[N_FRAGMENTOS];
	private static volatile int fragPendiente = 0;
	private static volatile int hilosTerminados = 0;

	private static SimpleSemaphore emFragPendiente;
	
	private static SimpleSemaphore emHilosTerminados;
	private static SimpleSemaphore sb;
	private static SimpleSemaphore desbloqueo;
	
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

		for (int i = 0; i < 10; i++) {

			descargaFragmentos();

			emHilosTerminados.acquire();
			hilosTerminados++;
			if (hilosTerminados < N_HILOS) {
			
				emHilosTerminados.release();				
				sb.acquire();
				desbloqueo.release();
			
			} else {

				mostrarFichero();
				fragPendiente = 0;
				hilosTerminados = 0;

				sb.release(N_HILOS-1);
				desbloqueo.acquire(N_HILOS-1);
				emHilosTerminados.release();
			}
		}
	}

	private static void descargaFragmentos() {
		while (true) {

			emFragPendiente.acquire();
			if (fragPendiente == N_FRAGMENTOS) {
				emFragPendiente.release();
				break;
			}

			int fragDescargar = fragPendiente;
			fragPendiente++;
			emFragPendiente.release();

			println(getThreadName() + ": Descargando fragmento " + fragDescargar);

			int downloadedData = descargarDatos(fragDescargar);

			println(getThreadName() + ": Escribiendo fragmento " + fragDescargar);

			fichero[fragDescargar] = downloadedData;
		}
	}

	public static void main(String[] args) {

		emFragPendiente = new SimpleSemaphore(1);
		
		emHilosTerminados = new SimpleSemaphore(1);
		sb = new SimpleSemaphore(0);
		desbloqueo = new SimpleSemaphore(0);
		
		createThreads(N_HILOS, "downloader");

		startThreadsAndWait();
	}
}
