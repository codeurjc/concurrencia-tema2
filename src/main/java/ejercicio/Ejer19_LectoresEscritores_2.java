package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;
import es.urjc.etsii.code.concurrency.SimpleSemaphore;

public class Ejer19_LectoresEscritores_2 {

	// Número de escritores que han iniciado inicioEscritura()
	// Están activos hasta que finalizan finEscritura()
	private static int escritoresActivos = 0;

	// Número de escritores que han conseguido el acceso
	// a la BD (Han terminado inicioEscritura())
	// Están trabajando hasta que finalizan finEscritura()
	// Siempre se cumple escritoresActivos >= escritoresTrabajando
	private static int escritoresBD = 0;

	// Número de lectores que han iniciado inicioLectura()
	// Están activos hasta que finalizan finLectura()
	// Un lector activo puede estar esperando a obtener el
	// acceso a los datos, que se le concederá cuando no
	// haya escritores activos
	private static int lectoresActivos = 0;

	// Número de lectores que han conseguido el acceso a
	// la BD (Han terminado inicioLectura())
	// Están trabajando hasta que finalizan finLectura()
	// Siempre se cumple lectoresActivos >= lectoresTrabajando
	private static int lectoresBD = 0;

	// Exclusión mutura de las variables de control
	private static SimpleSemaphore emControl;
	
	// Exclusión mutura para el acceso a los escritores 
	private static SimpleSemaphore emEscritura;
	
	// Bloqueo de los lectores cuando hay escritores
	private static SimpleSemaphore esperaFinEscritores;
	
	// Bloqueo de los escritores cuando hay lectores
	private static SimpleSemaphore esperaFinLectores;

	public static void inicioLectura() {
		emControl.acquire();
		lectoresActivos++;
		if (escritoresBD == 0) {
			lectoresBD++;
			emControl.release();
		} else {
			emControl.release();
			esperaFinEscritores.acquire();
		}
	}

	public static void finLectura() {
		emControl.acquire();
		lectoresActivos--;
		lectoresBD--;
		if (lectoresBD == 0) {
			while (escritoresActivos > escritoresBD) {
				escritoresBD++;
				esperaFinLectores.release();
			}
		}
		emControl.release();
	}

	public static void inicioEscritura() {
		emControl.acquire();
		escritoresActivos++;
		if (lectoresBD == 0) {
			escritoresBD++;
			emControl.release();
		} else {
			emControl.release();
			esperaFinLectores.acquire();
		}
		
		emEscritura.acquire();
	}

	public static void finEscritura() {
		emEscritura.release();
		
		emControl.acquire();
		escritoresActivos--;
		escritoresBD--;
		if (escritoresBD == 0) {
			while (lectoresActivos > lectoresBD) {
				lectoresBD++;
				esperaFinEscritores.release();
			}
		}
		emControl.release();		
	}

	public static void lector() {
		while (true) {
			inicioLectura();
			println("Leer datos");
			finLectura();
			println("Procesar datos");
		}
	}

	public static void escritor() {
		while (true) {
			println("Generar datos");
			inicioEscritura();
			println("Escribir datos");
			finEscritura();
		}
	}

	public static void main(String[] args) {

		emControl = new SimpleSemaphore(1);
		emEscritura = new SimpleSemaphore(1);
		esperaFinEscritores = new SimpleSemaphore(0);
		esperaFinLectores = new SimpleSemaphore(0);

		createThreads(5, "lector");
		createThreads(3, "escritor");
		startThreadsAndWait();
	}
}
