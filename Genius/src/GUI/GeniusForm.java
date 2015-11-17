package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        /*Some piece of code*/
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	try {
            		Main.getGame().save();
            	} catch (Exception e) {
            		System.exit(1);
            	}
            	System.exit(0);
            }
        });
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
        private long clickStart;


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
        	this.clickStart = System.currentTimeMillis();
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
        		long clickDuration = System.currentTimeMillis() - this.clickStart;
        		long extendedTime = Main.getGame().getPlayDelay() - clickDuration;
        		if ((extendedTime <= 0) || !this.pressedButton.isColorButton()) {
        			extendedTime = 0;
        		}
	        	Timer timer = new Timer();
	        	timer.schedule(new PostClick(), (int)extendedTime);
        	}
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
        
    	private class PostClick extends TimerTask {
    		public void run() {
            	disableButton(pressedButton);
        		Main.getGame().command(pressedButton);
        		pressedButton = null;
	        	repaint();
    		}
    	}
    }

}