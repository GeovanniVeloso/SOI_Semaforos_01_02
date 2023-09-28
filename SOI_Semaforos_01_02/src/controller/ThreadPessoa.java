package controller;

import java.util.concurrent.Semaphore;

public class ThreadPessoa extends Thread {

	private int idPessoa;
	private Semaphore semaforo;

	public ThreadPessoa(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo = semaforo;
	}

	public void run() {
		CorredorPessoa();
		// Inicio da Seção Crítica;
		try {
			semaforo.acquire();
			PortaPessoa();
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			semaforo.release();
		}
		// Fim da Seção Crítica;
	}

	private void CorredorPessoa() {
		int distancia = 200;
		int andar;
		while (distancia > 0) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(e);
			}
			andar = ((int) (Math.random() * 2) + 4);
			distancia -= andar;
			try {
				semaforo.acquire();
				System.out.println("A pessoa #" + idPessoa + " andou " + andar + " metros.");
				if (distancia >= 1) {
					System.out.println("Faltam " + distancia + " metros até a porta!");
				}
				System.out.println(" ");
			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				semaforo.release();
			}
		}
		System.out.println("A pessoa #" + idPessoa + " chegou a porta");
	}

	private void PortaPessoa() {
		int tempo = ((int) (Math.random() * 1000) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		System.out.println("A pessoa #" + idPessoa + " cruzou a porta!");
	}

}
