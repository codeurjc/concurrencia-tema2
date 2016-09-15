package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer6_Museo_Mal {

	static volatile int personas;
	
	public static void persona() {

		while (true) {
			
			enterMutex();
			personas++;
			exitMutex();
						
			printlnI("hola, somos "+personas);
			printlnI("qué bonito!");
			printlnI("alucinante!");
			
			enterMutex();
			personas--;
			exitMutex();
			
			printlnI("adiós a los "+personas);			
			
			printlnI("paseo");
		}
	}

	public static void main(String[] args) {
		personas = 0;
		createThreads(3, "persona");
		startThreadsAndWait();
	}
}
