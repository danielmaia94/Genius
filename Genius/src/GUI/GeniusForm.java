package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Main;
import Model.Button;
import Model.Speed;
import Model.Status;

@SuppressWarnings("serial")
public class GeniusForm extends JFrame {
	
	private Drawer drawer = new Drawer();
	JPanel panel;

    public GeniusForm() {
        super("GENIUS");
        setSize(Drawer.GAME_WIDTH + Drawer.WINDOW_BORDER_WIDTH, Drawer.GAME_HEIGHT + Drawer.WINDOW_BORDER_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new GeniusPanel();
        add(panel);
    }
    
	public void playSequence(ArrayList<Button> colorSequence, int playDelay, int pauseDelay) {
		SequencePlayer sequencePlayer = new SequencePlayer(colorSequence, playDelay, pauseDelay);
		sequencePlayer.start();
	}
	public void playError(int errorDelay) {
		SequencePlayer sequencePlayer = new SequencePlayer(errorDelay);
		sequencePlayer.start();
	}
	
	public void enableButton(Button button) {
		drawer.getElementByButton(button).enable();
		Sound.playButtonSound(button);
		repaint();
	}
	public void disableButton(Button button) {
		drawer.getElementByButton(button).disable();
		Sound.stopSound();
		repaint();
	}
    
    public class GeniusPanel extends JPanel implements MouseListener {
        
        public GeniusPanel() {
        	this.pressedButton = null;
            addMouseListener(this);
        }
        private Button pressedButton;


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int currentScore = Main.getGame().getCurrentScore();
            int highScore = Main.getGame().getHighScore();
            Speed speed = Main.getGame().getSpeed();
            drawer.drawEverything(g, speed, currentScore, highScore);
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
        	if (Main.getGame().getStatus() == Status.PLAY) {
	        	this.pressedButton = drawer.getPressedButton(e.getPoint());
	        	if (this.pressedButton != null) {
	        		enableButton(this.pressedButton);
	        	}
	        	repaint();
        	} else {
        		this.pressedButton = null;
        	}
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        	if (this.pressedButton != null) {        	
	        	if (this.pressedButton != null) {
	            	disableButton(this.pressedButton);
	        		Main.getGame().command(this.pressedButton);
	        		this.pressedButton = null;
	        	}
	        	repaint();
        	}
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}