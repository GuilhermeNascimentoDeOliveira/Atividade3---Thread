package controller;

import java.util.concurrent.Semaphore;

public class ativa extends Thread {

	private int id;
	private Semaphore semaforo;
	private static int ingresso = 100;

	public ativa(int idPessoa, Semaphore semaforo) {
		this.id = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		login();
	}

	private void login() {
		System.out.println("Pessoa " + id + " Efetue seu login");
		System.out.println("Pessoa " + id + " Digite o nome do �suario : ");
		System.out.println("Pessoa " + id + " Digite sua senha : ");
		try {
			int tempo = (int) (Math.random() * 1950) + 51;
			System.out.println("Pessoa " + id + " Aguade...");
			sleep(tempo);
			if (tempo >= 1000) {
				System.out.println("Pessoa " + id + "Login realizado com sucesso!");
				compra();
			} else {
				System.out.println(
						"Pessoa " + id + " Erro de carregamento \n Tente novamente");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void compra() {
		System.out.println("Pessoa " + id + " Bem-vindo ao processo de compra");
		try {
			int tempo = (int) (Math.random() * 3001) + 1001;
			System.out.println("Autenticando compra");
			sleep(tempo);
			if (tempo <= 2500) {
				System.out.println("Autentifica��o realizada com sucesso! ");
				finalizar();
			} else {
				System.out.println(
						"Pessoa " + id + " Seu tempo de sess�o expirou, Tente novamente");
			}
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	private void finalizar() {
		try {
			semaforo.acquire();
			int compras = (int) (Math.random() * 4) + 1;
			if (compras <= ingresso) {
				System.out.println("Pessoa" + id + " Voc� comprou " + compras + " ingressos");
				ingresso -= compras;
				System.out.println("Restam " + ingresso + " ingressos");
			} else {
				System.out.println(
						"Infelizmente n�o temos essa quantidade de ingressos disponivel. N�o ser� possivel realizar a compra ");
			}
			if (ingresso == 0) {
				System.out.println("Os ingressos acabaram :-(");
			}

		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

}