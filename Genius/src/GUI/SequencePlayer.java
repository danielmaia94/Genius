package GUI;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Controller.Main;
import Model.Button;
import Model.Status;

public class SequencePlayer {

	private boolean isErrorPlay;
	private ArrayList<Button> colorSequence;
	private int index;
	private Timer timer;
	private int playDelay;
	private int pauseDelay;
	
	public SequencePlayer(ArrayList<Button> colorSequence, int playDelay, int pauseDelay) {
		this.isErrorPlay = false;
		this.colorSequence = colorSequence;
		this.index = 0;
		this.timer = new Timer();
		this.playDelay = playDelay;
		this.pauseDelay = pauseDelay;
	}
	
	public SequencePlayer(int errorDelay) {
		this.isErrorPlay = true;
		this.colorSequence = null;
		this.index = 0;
		this.timer = new Timer();
		this.playDelay = errorDelay;
		this.pauseDelay = 0;
	}
	
	public void start() {
		Main.getGame().setStatus(Status.RUNNING);
        timer.schedule(new Play(), pauseDelay);
	}
	
	private class Play extends TimerTask {
		public void run() {
			if (isErrorPlay) {
				Sound.playErrorSound();
			} else {
				Main.getForm().enableButton(colorSequence.get(index));
			}
			timer.schedule(new Pause(), playDelay);
		}
	}
	
	private class Pause extends TimerTask {
		public void run() {
			if (isErrorPlay) {
				Sound.stopSound();
				timer.cancel();
				Main.getGame().reset();
			} else {
				Main.getForm().disableButton(colorSequence.get(index));		
				index++;
				if (index < colorSequence.size()) {
					timer.schedule(new Play(), pauseDelay);
				} else {
					end();
				}
			}
		}
	}
	
	public void end() {
		timer.cancel();
		Main.getGame().setStatus(Status.PLAY);
	}
}
