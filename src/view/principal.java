package view;

import java.util.concurrent.Semaphore;

import controller.ativa;

public class principal {


	public class Principal {

		public static void main(String[] args) {
			int permissoes = 1;
			Semaphore semaforo = new Semaphore(permissoes);

			for (int idPessoa = 1; idPessoa <= 300; idPessoa++) {
				Thread pessoa = new ativa(idPessoa, semaforo);
				pessoa.start();
			}

		}
	}
	
	
}
