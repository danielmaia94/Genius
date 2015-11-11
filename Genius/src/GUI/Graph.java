package GUI;
import Controller.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import Model.*;

public class Graph extends JFrame {
	public static final int BUTTON_ID_BLUE = 3;
	public static final int BUTTON_ID_GREEN = 1;
	public static final int BUTTON_ID_RED = 2;
	public static final int BUTTON_ID_YELLOW = 4;
	public static final int MINIBUTTON_ID_REPEAT = 13;
	public static final int MINIBUTTON_ID_RESET = 15;
	public static final int MINIBUTTON_ID_SPEED = 11;
	public static final int PANEL_ID = 7;
	
	public static final Color COLOR_BODY = new Color(51, 51, 51);
    
	
	public static final Color COLOR_LCD_BACKGROUND = new Color(48, 0, 0);
    public static final Color COLOR_LCD_NUMBER = new Color(255, 66, 14);
    
    
    public static final Color COLOR_BUTTON_BLUE_OFF = new Color(0, 0, 127);
    public static final Color COLOR_BUTTON_BLUE_ON = new Color(0, 0, 255);
    
	public static final Color COLOR_BUTTON_GREEN_OFF = new Color(0, 127, 0);
	public static final Color COLOR_BUTTON_GREEN_ON = new Color(0, 255, 0);
	
	public static final Color COLOR_BUTTON_RED_OFF = new Color(127, 0, 0);
	public static final Color COLOR_BUTTON_RED_ON = new Color(255, 0, 0);
	
	public static final Color COLOR_BUTTON_YELLOW_OFF = new Color(127, 127, 0);
	public static final Color COLOR_BUTTON_YELLOW_ON = new Color(255, 255, 0);
	
	
	public static final Color COLOR_MINIBUTTON_BORDER_SPEED = new Color(96, 96, 96);
	public static final Color COLOR_MINIBUTTON_BORDER_REPEAT = new Color(96, 96, 0);
	public static final Color COLOR_MINIBUTTON_BORDER_RESET = new Color(96, 0, 0);
	
	
	public static final Color COLOR_MINIBUTTON_SPEED = new Color(255, 255, 255);
	public static final Color COLOR_MINIBUTTON_REPEAT = new Color(255, 255, 0);
	public static final Color COLOR_MINIBUTTON_RESET = new Color(255, 0, 0);
	
	
	public static final Color COLOR_PANEL = new Color(192, 192, 192);
	
	
	public static final String FONT_LCD_FILENAME = "Crysta.ttf";
	
	
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 600;
	
	
	public static final int WINDOW_BORDER_WIDTH = 16;
	public static final int WINDOW_BORDER_HEIGHT = 38;
	
	JPanel panel;

    public Graph() {
        super("GENIUS");
        setSize(GAME_WIDTH + WINDOW_BORDER_WIDTH, GAME_HEIGHT + WINDOW_BORDER_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new GeniusPanel();
        add(panel);
    }
    public void feedback(Cor Color) {
        ((GeniusPanel)panel).feedback(Color);
    }
    public Botao getButtonClicked(){
    	switch (((GeniusPanel)panel).buttonClicked){
    	case BUTTON_ID_BLUE   : return Botao.AZUL;
    	case BUTTON_ID_GREEN  : return Botao.VERDE;
    	case BUTTON_ID_RED    : return Botao.VERMELHO;
    	case BUTTON_ID_YELLOW : return Botao.AMARELO;
    	default : return null; 
    	}
    }
    public boolean getWasButtonClicked(){
    	return (((GeniusPanel)panel).getWasButtonClicked());
    }
    public void setWasButtonClicked(boolean state){
    	((GeniusPanel)panel).setWasButtonClicked(state);
    }
    public 
    
    class GeniusPanel extends JPanel implements MouseListener {

		private final ArrayList<Shape> shapes;
        private final ArrayList<Color> colors;
        public int buttonClicked;
        public boolean wasButtonClicked;
        
    	public boolean getWasButtonClicked() {
			return wasButtonClicked;
		}

		public void setWasButtonClicked(boolean wasButtonClicked) {
			this.wasButtonClicked = wasButtonClicked;
		}
        
        public GeniusPanel() {
            addMouseListener(this);

            shapes = new ArrayList<Shape>();
            colors = new ArrayList<Color>();
                      
            drawBody();

            drawButtons();
            
            drawSpacers();

            drawPanel();
            
            drawDisplays();
            
            drawMiniButtons();

        }

		private void drawPanel() {
			final double x = GAME_WIDTH * 0.3;
			final double y = GAME_HEIGHT * 0.3;
			final double width = GAME_WIDTH * 0.4;
			final double height = GAME_HEIGHT * 0.4;
			shapes.add(new Ellipse2D.Double(x, y, width, height));
            colors.add(COLOR_PANEL);
		}

		private void drawDisplays() {
			final double xScore = GAME_WIDTH * 0.36;
			final double xHiScore = GAME_WIDTH * 0.52;
			final double y = GAME_HEIGHT * 0.4375;
			final double width = GAME_WIDTH * 0.12;
			final double height = GAME_HEIGHT * 0.0625;
			
			shapes.add(new Rectangle2D.Double(xScore, y, width, height));
            colors.add(COLOR_LCD_BACKGROUND);
            
            shapes.add(new Rectangle2D.Double(xHiScore, y, width, height));
            colors.add(COLOR_LCD_BACKGROUND);
		}

		private void drawMiniButtons() {
			final double xSpeed = GAME_WIDTH * 0.375;
			final double xRepeat = GAME_WIDTH * 0.475;
			final double xReset = GAME_WIDTH * 0.575;
			final double y = GAME_HEIGHT * 0.55;
			final double width = GAME_WIDTH * 0.05;
			final double height = GAME_HEIGHT * 0.05;
			final double hBorder = GAME_HEIGHT * 0.005;
			final double vBorder = GAME_HEIGHT * 0.005;
			
			shapes.add(new Ellipse2D.Double(xSpeed, y, width, height));
            colors.add(COLOR_MINIBUTTON_BORDER_SPEED);
			shapes.add(new Ellipse2D.Double(xSpeed + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2));
            colors.add(COLOR_MINIBUTTON_SPEED);
            
            shapes.add(new Ellipse2D.Double(xRepeat, y, width, height));
            colors.add(COLOR_MINIBUTTON_BORDER_REPEAT);
            shapes.add(new Ellipse2D.Double(xRepeat + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2));
            colors.add(COLOR_MINIBUTTON_REPEAT);
            
            shapes.add(new Ellipse2D.Double(xReset, y, width, height));
            colors.add(COLOR_MINIBUTTON_BORDER_RESET);
            shapes.add(new Ellipse2D.Double(xReset + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2));
            colors.add(COLOR_MINIBUTTON_RESET);
            
		}

		private void drawButtons() {
			final double x = GAME_WIDTH * 0.05;
			final double y = GAME_HEIGHT * 0.05;
			final double width = GAME_WIDTH * 0.9;
			final double height = GAME_HEIGHT * 0.9;
			
			shapes.add(new Arc2D.Double(x, y, width, height, 90, 90, Arc2D.PIE));
			colors.add(COLOR_BUTTON_GREEN_OFF);
            
            shapes.add(new Arc2D.Double(x, y, width, height, 0, 90, Arc2D.PIE));
			colors.add(COLOR_BUTTON_RED_OFF);

            shapes.add(new Arc2D.Double(x, y, width, height, 270, 90, Arc2D.PIE));
			colors.add(COLOR_BUTTON_BLUE_OFF);
            
            shapes.add(new Arc2D.Double(x, y, width, height, 180, 90, Arc2D.PIE));
            colors.add(COLOR_BUTTON_YELLOW_OFF);
		}

		private void drawSpacers() {
			shapes.add(new Rectangle2D.Double(288, 15, 24, 570));
            colors.add(COLOR_BODY);
            
            shapes.add(new Rectangle2D.Double(15, 288, 570, 24));
            colors.add(COLOR_BODY);

            shapes.add(new Ellipse2D.Double(150, 150, 300, 300));
            colors.add(COLOR_BODY);
		}

		private void drawBody() {
			shapes.add(new Ellipse2D.Double(0, 0, GAME_WIDTH, GAME_HEIGHT));
            colors.add(COLOR_BODY);
		}

        @Override
        protected void paintComponent(Graphics gr) {
            super.paintComponent(gr);
            Graphics2D g = (Graphics2D) gr;

            for (int i = 0; i < shapes.size(); i++) {
                Shape shape = shapes.get(i);
                Color color = colors.get(i);
                g.setColor(color);
                g.fill(shape);
            }
            drawScores(g);
        }

		private void drawScores(Graphics2D g) {
			InputStream is = Graph.class.getResourceAsStream(FONT_LCD_FILENAME);
            Font f;
			try {
				f = Font.createFont(Font.TRUETYPE_FONT, is);
			} catch (FontFormatException | IOException e) {
				f = new Font("Courier new", Font.PLAIN, 24);
			}
            f = f.deriveFont(48f);
            g.setFont(f);
            g.setColor(COLOR_LCD_NUMBER);
            g.drawString(getCurrentScore(), 216, 300);
            g.drawString(getHighScore(), 312, 300);
		}

        @Override
        public void mouseClicked(MouseEvent e) {
        	if (shapeClicked(shapes.get(PANEL_ID), e)) {
        		if (shapeClicked(shapes.get(MINIBUTTON_ID_SPEED), e)) {
        			System.out.println("Clicked SPEED");
        		} else if (shapeClicked(shapes.get(MINIBUTTON_ID_REPEAT), e)) {
        			System.out.println("Clicked REPEAT");
        			
        		} else if (shapeClicked(shapes.get(MINIBUTTON_ID_RESET), e)) {
        			System.out.println("Clicked RESET");
        		}
        	} else {
        		if (shapeClicked(shapes.get(BUTTON_ID_GREEN), e)) {
        			System.out.println("Clicked GREEN");
        			buttonClicked = BUTTON_ID_GREEN;
        			wasButtonClicked = true;
        			Main.computesMove();
        		} else if (shapeClicked(shapes.get(BUTTON_ID_RED), e)) {
        			System.out.println("Clicked RED");
        			buttonClicked = BUTTON_ID_RED;
        			wasButtonClicked = true;
        			Main.computesMove();
        		} else if (shapeClicked(shapes.get(BUTTON_ID_YELLOW), e)) {
        			System.out.println("Clicked YELLOW");
        			buttonClicked = BUTTON_ID_YELLOW;
        			wasButtonClicked = true;
        			Main.computesMove();
        		} else if (shapeClicked(shapes.get(BUTTON_ID_BLUE), e)) {
        			System.out.println("Clicked BLUE");
        			buttonClicked = BUTTON_ID_BLUE;
        			wasButtonClicked = true;
        			Main.computesMove();
        		}
        	}
        }
        public void feedback(Cor cor) {
        	switch (cor) {
        	case AZUL:
        		colors.set(BUTTON_ID_BLUE, COLOR_BUTTON_BLUE_ON);
        		repaint();
//            	sleep(1000);
            	colors.set(BUTTON_ID_BLUE, COLOR_BUTTON_BLUE_OFF);
            	repaint();
            	System.out.println(" Blue");
        		break;
    		case AMARELA:
	    		colors.set(BUTTON_ID_YELLOW, COLOR_BUTTON_YELLOW_ON);
	    		repaint();
	//        	sleep(1000);
	        	colors.set(BUTTON_ID_YELLOW, COLOR_BUTTON_YELLOW_OFF);
	        	repaint();
	        	System.out.println(" yellow");
	    		break;
    		case VERDE:
	    		colors.set(BUTTON_ID_GREEN, COLOR_BUTTON_GREEN_ON);
	    		repaint();
	//        	sleep(1000);
	        	colors.set(BUTTON_ID_GREEN, COLOR_BUTTON_GREEN_OFF);
	        	repaint();
	        	System.out.println(" green");
	    		break;
    		case VERMELHA:
	    		colors.set(BUTTON_ID_RED, COLOR_BUTTON_RED_ON);
	    		repaint();
	//        	sleep(1000);
	        	colors.set(BUTTON_ID_RED, COLOR_BUTTON_RED_OFF);
	        	repaint();
	        	System.out.println(" red");
	    		break;		
    	}

        }
        
        private String getCurrentScore() {
        	return "000";
        }
        
        private String getHighScore() {
        	return "111";
        }
        
        private boolean shapeClicked(Shape shape, MouseEvent e) {
        	return shape.contains(e.getPoint());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}