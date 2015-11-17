package Model;

import java.util.ArrayList;		//Prov� funcionalidades da lista de cores.
import Controller.Main;

public class Game {
	
	private static final String PREFS_FILE_NAME = "genius.dat";
	private static final int PLAY_DELAY = 1000;
	private static final int PAUSE_DELAY = 300;
	private static final int ERROR_DELAY = 2000;
	
	//Lista de cores, cont�m a sequ�ncia gerada pelo jogo.
	private ArrayList<Button> colorSequence;
	
	//Status do jogo, pode ser DEMONSTRANDO (bloqueia certo comandos), ou DEMONSTRANDO (aceita qualquer comando).
	private Status status;
	
	private Speed speed;
	
	//Apontador para o item da sequ�ncia que est� sendo digitado agora pelo usu�rio.
	private int colorIndex;
	
	private int highScore;
	
	public Game() {
		this.speed = Speed.SLOW;
		this.highScore = 0;
	}
	
	public void save() {
		Prefs prefs = new Prefs(this.speed, this.highScore, this.colorSequence);
		FilePrefs filePrefs = new FilePrefs(PREFS_FILE_NAME);
		filePrefs.save(prefs);
	}
	
	public int getPlayDelay() {
		switch (speed) {
		case SLOW:
			return PLAY_DELAY;
		case MEDIUM:
			return PLAY_DELAY / 2;
		case FAST:
			return PLAY_DELAY / 4;
		}
		return 0;
	}
	
	public int getPauseDelay() {
		switch (speed) {
		case SLOW:
			return PAUSE_DELAY;
		case MEDIUM:
			return PAUSE_DELAY / 2;
		case FAST:
			return PAUSE_DELAY / 4;
		}
		return 0;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	public Status getStatus() {
		return this.status;
	}
	public Speed getSpeed() {
		return this.speed;
	}
	
	public int getCurrentScore() {
		return this.colorSequence.size() - 1;
	}
	
	public int getHighScore() {
		return this.highScore;
	}
	
	public void start() {
		Prefs prefs;
		FilePrefs filePrefs = new FilePrefs(PREFS_FILE_NAME);
		try {
			prefs = filePrefs.load();
		} catch (Exception e) {
			prefs = null;
		}
		if (prefs == null) {
			reset();
			return;
		}
		this.speed = prefs.getSpeed();
		this.highScore = prefs.getHighScore();
		this.colorSequence = prefs.getColorSequence();
		this.colorIndex = 0;
		Main.getForm().playSequence(colorSequence, getPlayDelay(), getPauseDelay());
	}

	//Reinicia o jogo.
	public void reset() {
		this.colorSequence = new ArrayList<Button>();
		this.next();
	}
	
	//Avan�a o ciclo do jogo, gerando uma nova cor na sequ�ncia e executando a demonstra��o.
	private void next() {
		Button newColor = Button.getRandomColorButton();
		this.colorSequence.add(newColor);
		this.colorIndex = 0;
		Main.getForm().playSequence(colorSequence, getPlayDelay(), getPauseDelay());
	}

	//Trata apropriadamente o comando recebido (pressionamento dos bot�es).
	public void command(Button button) {
		if (button.isColorButton() && this.status == Status.PLAY) {
			this.computarJogada(button);
		} else if (button == Button.SPEED) {
			this.speed = this.speed.next();
			Main.getForm().repaint();
		} else if (button == Button.REPEAT) {
			Main.getForm().playSequence(colorSequence, getPlayDelay(), getPauseDelay());
		} else if (button == Button.RESET) {
			this.reset();
		}
	}

	//Avalia uma jogada do usu�rio, que corresponde ao pressionamento de um bot�o colorido:
	// - Verifica se a cor informada corresponde � cor apontada na sequ�ncia e neste caso avan�a o apontador.
	// - Se o apontador alcan�ou o fim da sequ�ncia, chama o m�todo que gera uma pr�xima sequ�ncia.
	// - Se a cor informada n�o corresponde � cor apontada, chama o feedback sonoro de erro e reinicia o jogo.
	private void computarJogada(Button cor) {
		if (cor == this.colorSequence.get(this.colorIndex)) {
			this.colorIndex++;
			if (this.colorIndex == this.colorSequence.size()) {
				this.next();
			}
		} else {
			this.saveHighScore(this.getCurrentScore());
			Main.getForm().playError(ERROR_DELAY);
		}
	}
	
	//Registra a pontua��o atual como recorde, se for maior do que ele.
	private void saveHighScore(int score) {
		if (score > highScore) {
			highScore = score;
		}
	}


}

