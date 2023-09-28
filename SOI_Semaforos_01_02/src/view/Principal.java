package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPessoa;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 4; i++) {
			Thread tPessoa = new ThreadPessoa(i, semaforo);
			tPessoa.start();
		}

	}

}
