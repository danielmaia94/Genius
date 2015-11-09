package Model;

import java.util.ArrayList;		//Provê funcionalidades da lista de cores.
import java.util.Random;		//Provê funcionalidade de geração de valores aleatórios.

import GUI.Graph;

public class Jogo {
	
	//Lista de cores, contém a sequência gerada pelo jogo.
	private ArrayList<Cor> sequencia;
	
	//Status do jogo, pode ser DEMONSTRANDO (bloqueia certo comandos), ou DEMONSTRANDO (aceita qualquer comando).
	private Status status;
	
	//Apontador para o item da sequência que está sendo digitado agora pelo usuário.
	private int indice;
	
	public void setStatus(Status status) {
		this.status = status;
	}

	//Reinicia o jogo.
	public void reiniciar() {
		this.sequencia = new ArrayList<Cor>();
		this.proxima();
	}
	
	public Status getStatus(){
		return this.status;
	}
	
	//Gera uma cor aleatoriamente.
	private Cor geraCor() {
		Random random = new Random();
		Cor[] cores = Cor.values();
		int numCores = cores.length;
		int aleatorio = random.nextInt(numCores);
		return cores[aleatorio]; 
	}
	
	//Avança o ciclo do jogo, gerando uma nova cor na sequência e executando a demonstração.
	private void proxima() {
		Cor novaCor = geraCor();
		this.sequencia.add(novaCor);
		this.demonstrar();
	}

	//Chama o método que dá o feedback visual/sonoro para cada item da sequência, bloqueando os comandos no processo.
	private void demonstrar() {
		this.status = Status.DEMONSTRANDO;
		this.indice = 0;
	}

	//Dá o feedback sonoro apropriado para o erro do usuário.
	private void feedbackErro() {
		System.out.println("ERRRRROOU");
	}

	//Trata apropriadamente o comando recebido (pressionamento dos botões).
	public void comando(Botao botao) {
		switch (botao) {
			case AMARELO:
				if (this.status == Status.JOGAR) {
					this.computarJogada(Cor.AMARELA);
				}
				break;
			case VERMELHO:
				if (this.status == Status.JOGAR) {
					this.computarJogada(Cor.VERMELHA);
				}
		        break;
			case AZUL:
				if (this.status == Status.JOGAR) {
					this.computarJogada(Cor.AZUL);
				}
		        break;
			case VERDE:
				if (this.status == Status.JOGAR) {
					this.computarJogada(Cor.VERDE);
				}
		        break;
			case REPETIR:
				if (this.status == Status.JOGAR) {
					this.demonstrar();
		        }
				break;
			case REINICIAR:
				this.reiniciar();
				this.redesenhar();
		    	break;
		  }
	}
	
	public ArrayList<Cor> getSequencia() {
		return sequencia;
	}

	public void setSequencia(ArrayList<Cor> sequencia) {
		this.sequencia = sequencia;
	}

	//Avalia uma jogada do usuário, que corresponde ao pressionamento de um botão colorido:
	// - Verifica se a cor informada corresponde à cor apontada na sequência e neste caso avança o apontador.
	// - Se o apontador alcançou o fim da sequência, chama o método que gera uma próxima sequência.
	// - Se a cor informada não corresponde à cor apontada, chama o feedback sonoro de erro e reinicia o jogo.
	private void computarJogada(Cor cor) {
		if (cor == this.sequencia.get(this.indice)) {
			this.indice++;
			if (this.indice == this.sequencia.size()) {
				this.proxima();
			}
		} else {
			this.feedbackErro();
			this.reiniciar();
		}
	}

	//Atualiza interface visual do jogo.
	public void redesenhar() {
		//...
	}

}

