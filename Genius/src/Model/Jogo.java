package Model;

import java.util.ArrayList;		//Prov� funcionalidades da lista de cores.
import java.util.Random;		//Prov� funcionalidade de gera��o de valores aleat�rios.

import GUI.Graph;

public class Jogo {
	
	//Lista de cores, cont�m a sequ�ncia gerada pelo jogo.
	private ArrayList<Cor> sequencia;
	
	//Status do jogo, pode ser DEMONSTRANDO (bloqueia certo comandos), ou DEMONSTRANDO (aceita qualquer comando).
	private Status status;
	
	//Apontador para o item da sequ�ncia que est� sendo digitado agora pelo usu�rio.
	private int indice;
	
	//Gerencia a interface gr�fica do jogo
	private Graph geniusForm;
	
	//Reinicia o jogo.
	public void reiniciar() {
		this.sequencia = new ArrayList<Cor>();
		this.proxima();
	}
	
	public Status getStatus(){
		return this.status;
	}
	
	public void formCreate(){
        geniusForm = new Graph();
        geniusForm.setVisible(true);
        this.reiniciar();
	}
	//Gera uma cor aleatoriamente.
	private Cor geraCor() {
		Random random = new Random();
		Cor[] cores = Cor.values();
		int numCores = cores.length;
		int aleatorio = random.nextInt(numCores);
		return cores[aleatorio]; 
	}
	
	//Avan�a o ciclo do jogo, gerando uma nova cor na sequ�ncia e executando a demonstra��o.
	private void proxima() {
		Cor novaCor = geraCor();
		this.sequencia.add(novaCor);
		this.demonstrar();
	}

	//Chama o m�todo que d� o feedback visual/sonoro para cada item da sequ�ncia, bloqueando os comandos no processo.
	private void demonstrar() {
		this.status = Status.DEMONSTRANDO;
		for (int i = 0; i < this.sequencia.size(); i++) {
			geniusForm.feedback(this.sequencia.get(i));
		}
		this.indice = 0;
		this.status = Status.JOGAR;
	}

	//D� o feedback sonoro apropriado para o erro do usu�rio.
	private void feedbackErro() {
		//...
	}

	//Trata apropriadamente o comando recebido (pressionamento dos bot�es).
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
	
	//Avalia uma jogada do usu�rio, que corresponde ao pressionamento de um bot�o colorido:
	// - Verifica se a cor informada corresponde � cor apontada na sequ�ncia e neste caso avan�a o apontador.
	// - Se o apontador alcan�ou o fim da sequ�ncia, chama o m�todo que gera uma pr�xima sequ�ncia.
	// - Se a cor informada n�o corresponde � cor apontada, chama o feedback sonoro de erro e reinicia o jogo.
	private void computarJogada(Cor cor) {
		geniusForm.feedback(cor);
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

