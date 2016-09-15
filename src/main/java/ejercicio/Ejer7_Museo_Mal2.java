package ejercicio;

import static es.urjc.etsii.code.concurrency.SimpleConcurrent.*;

public class Ejer7_Museo_Mal2 {

	static volatile int personas;
	
	public static void persona() {

		while (true) {
			
			enterMutex();
			personas++;
			printlnI("hola, somos "+personas);			
			exitMutex();
			
			if(personas == 1){
				printlnI("Tengo regalo");				
			} else {
				printlnI("No tengo regalo");
			}			
			
	        printlnI("qué bonito!");
			printlnI("alucinante!");
			
			enterMutex();
			personas--;
			printlnI("adiós a los "+personas);
			exitMutex();					
			
			printlnI("paseo");
		}
	}

	public static void main(String[] args) {
		personas = 0;
		createThreads(3, "persona");
		startThreadsAndWait();
	}
}
