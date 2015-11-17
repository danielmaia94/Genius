package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Model.Button;
import Model.Speed;

public class Drawer {
	
	public static final Color COLOR_BODY = new Color(51, 51, 51);

	
	public static final Color COLOR_BUTTON_BLUE_OFF = new Color(0, 0, 127);
    public static final Color COLOR_BUTTON_BLUE_ON = new Color(0, 0, 255);
    
	public static final Color COLOR_BUTTON_GREEN_OFF = new Color(0, 127, 0);
	public static final Color COLOR_BUTTON_GREEN_ON = new Color(0, 255, 0);
	
	public static final Color COLOR_BUTTON_RED_OFF = new Color(127, 0, 0);
	public static final Color COLOR_BUTTON_RED_ON = new Color(255, 0, 0);
	
	public static final Color COLOR_BUTTON_YELLOW_OFF = new Color(127, 127, 0);
	public static final Color COLOR_BUTTON_YELLOW_ON = new Color(255, 255, 0);
	
	
	public static final Color COLOR_LABELS = new Color(0, 0, 0);
	
	
	public static final Color COLOR_LCD_BACKGROUND = new Color(48, 0, 0);
	
	
	public static final Color COLOR_LED_OFF = new Color(80, 0, 0);
    public static final Color COLOR_LED_ON = new Color(255, 66, 14);
    
    
    public static final Color COLOR_MINIBUTTON_BORDER_SPEED = new Color(96, 96, 96);
	public static final Color COLOR_MINIBUTTON_BORDER_REPEAT = new Color(96, 96, 0);
	public static final Color COLOR_MINIBUTTON_BORDER_RESET = new Color(96, 0, 0);
	
	
	public static final Color COLOR_MINIBUTTON_SPEED = new Color(255, 255, 255);
	public static final Color COLOR_MINIBUTTON_REPEAT = new Color(255, 255, 0);
	public static final Color COLOR_MINIBUTTON_RESET = new Color(255, 0, 0);
	
	
	public static final Color COLOR_PANEL = new Color(192, 192, 192);

	
	public static final String FONT_LCD_FILENAME = "DSEG7ClassicMini-BoldItalic.ttf";
	
	
	public static final int GAME_WIDTH = 600;
	public static final int GAME_HEIGHT = 600;
	
	
	public static final String LABEL_BUTTON_SPEED = "SPEED";
	public static final String LABEL_BUTTON_REPEAT = "REPEAT";
	public static final String LABEL_BUTTON_RESET = "RESET";
	public static final String LABEL_HIGH_SCORE = "HIGH SCORE";
	public static final String LABEL_SCORE = "SCORE";
	
	
	public static final int WINDOW_BORDER_WIDTH = 16;
	public static final int WINDOW_BORDER_HEIGHT = 38;
	
	private Graphics2D g2d;
	
	private ArrayList<Element> elementList = new ArrayList<Element>();
	
	public Drawer() {
		createBody();
        createColorButtons();
        createSpacers();
        createPanel();
        createDisplays();
        createMiniButtons();
	}
	
	public void drawEverything(Graphics g, Speed speed, int currentScore, int highScore){
		this.g2d = (Graphics2D)g;
		this.g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		this.g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        this.drawElements();
        this.drawSpeedLeds(speed);
        this.drawLabels();
        this.drawScores(currentScore, highScore);
	}
	
	public Button getPressedButton(Point point) {

		for (int i = elementList.size()-1; i>=0;  i--) {
			if (elementList.get(i).shapeClicked(point)) {
					return elementList.get(i).getButton();
			}
		}
		return null;
	}
	
	private void createBody() {
		Shape shape = new Ellipse2D.Double(0, 0, Drawer.GAME_WIDTH, Drawer.GAME_HEIGHT);
		Color color = COLOR_BODY;
		Element element = new Element(shape, color);
		elementList.add(element);
	}
	
	private void createColorButtons() {
		Shape shape;
		Color color;
		Color colorEnabled;
		Button button;
		final double x = GAME_WIDTH * 0.05;
		final double y = GAME_HEIGHT * 0.05;
		final double width = GAME_WIDTH * 0.9;
		final double height = GAME_HEIGHT * 0.9;
		Element element;
		
		shape = new Arc2D.Double(x, y, width, height, 90, 90, Arc2D.PIE);
		color = COLOR_BUTTON_GREEN_OFF;
		colorEnabled = COLOR_BUTTON_GREEN_ON;
		button = Button.GREEN;
		element = new Element(shape, color, colorEnabled, button);
		elementList.add(element);
        
		shape = new Arc2D.Double(x, y, width, height, 0, 90, Arc2D.PIE);
		color = COLOR_BUTTON_RED_OFF;
		colorEnabled = COLOR_BUTTON_RED_ON;
		button = Button.RED;
		element = new Element(shape, color, colorEnabled, button);
		elementList.add(element);

		shape = new Arc2D.Double(x, y, width, height, 270, 90, Arc2D.PIE);
		color = COLOR_BUTTON_BLUE_OFF;
		colorEnabled = COLOR_BUTTON_BLUE_ON;
		button = Button.BLUE;
		element = new Element(shape, color, colorEnabled, button);
		elementList.add(element);
        
		shape = new Arc2D.Double(x, y, width, height, 180, 90, Arc2D.PIE);
		color = COLOR_BUTTON_YELLOW_OFF;
		colorEnabled = COLOR_BUTTON_YELLOW_ON;
		button = Button.YELLOW;
		element = new Element(shape, color, colorEnabled, button);
		elementList.add(element);
	}
	
	private void createSpacers() {
		Shape shape;
		Color color;
		Element element;
		
		shape = new Rectangle2D.Double(288, 15, 24, 570);
		color = COLOR_BODY;
		element = new Element(shape, color);
		elementList.add(element);
        
        shape = new Rectangle2D.Double(15, 288, 570, 24);
        color = COLOR_BODY;
        element = new Element(shape, color);
		elementList.add(element);

        shape = new Ellipse2D.Double(150, 150, 300, 300);
        color = COLOR_BODY;
        element = new Element(shape, color);
		elementList.add(element);
	}
	
	private void createPanel() {
		final double x = GAME_WIDTH * 0.3;
		final double y = GAME_HEIGHT * 0.3;
		final double width = GAME_WIDTH * 0.4;
		final double height = GAME_HEIGHT * 0.4;
		Shape shape = new Ellipse2D.Double(x, y, width, height);
        Color color = COLOR_PANEL;
        Element element = new Element(shape, color);
		elementList.add(element);
	}
	
	private void createDisplays() {
		final double xScore = GAME_WIDTH * 0.33;
		final double xHiScore = GAME_WIDTH * 0.52;
		final double y = GAME_HEIGHT * 0.4375;
		final double width = GAME_WIDTH * 0.15;
		final double height = GAME_HEIGHT * 0.0625;
		Shape shape;
		Color color;
		Element element;
		
		shape = new Rectangle2D.Double(xScore, y, width, height);
        color = COLOR_LCD_BACKGROUND;
        element = new Element(shape, color);
		elementList.add(element);
        
        shape = new Rectangle2D.Double(xHiScore, y, width, height);
        color = COLOR_LCD_BACKGROUND;
        element = new Element(shape, color);
		elementList.add(element);
	}
	
	private void createMiniButtons() {
		final double xSpeed = GAME_WIDTH * 0.375;
		final double xRepeat = GAME_WIDTH * 0.475;
		final double xReset = GAME_WIDTH * 0.575;
		final double y = GAME_HEIGHT * 0.55;
		final double width = GAME_WIDTH * 0.05;
		final double height = GAME_HEIGHT * 0.05;
		final double hBorder = GAME_HEIGHT * 0.005;
		final double vBorder = GAME_HEIGHT * 0.005;
		Shape shape;
		Color color;
		Element element;
		
		shape = new Ellipse2D.Double(xSpeed, y, width, height);
        color = COLOR_MINIBUTTON_BORDER_SPEED;
        element = new Element(shape, color, color, Button.SPEED);
		elementList.add(element);
        shape = new Ellipse2D.Double(xSpeed + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2);
        color = COLOR_MINIBUTTON_SPEED;
        element = new Element(shape, color, color, Button.SPEED);
		elementList.add(element);
        
        shape = new Ellipse2D.Double(xRepeat, y, width, height);
        color = COLOR_MINIBUTTON_BORDER_REPEAT;
        element = new Element(shape, color, color, Button.REPEAT);
		elementList.add(element);
        shape = new Ellipse2D.Double(xRepeat + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2);
        color = COLOR_MINIBUTTON_REPEAT;
        element = new Element(shape, color, color, Button.REPEAT);
		elementList.add(element);
        
        shape = new Ellipse2D.Double(xReset, y, width, height);
        color = COLOR_MINIBUTTON_BORDER_RESET;
        element = new Element(shape, color, color, Button.RESET);
		elementList.add(element);
        shape = new Ellipse2D.Double(xReset + hBorder, y + vBorder, width - hBorder * 2, height - vBorder * 2);
        color = COLOR_MINIBUTTON_RESET;
        element = new Element(shape, color, color, Button.RESET);
		elementList.add(element);
	}
	
	private void drawElements() {
		for (int i = 0; i < elementList.size(); i++) {
			elementList.get(i).draw(this.g2d);
		}
	}
	
	private void drawSpeedLeds(Speed speed) {
		final double xSlow = GAME_WIDTH * 0.375;
		final double xMedium = GAME_WIDTH * 0.395;
		final double xFast = GAME_WIDTH * 0.415;
		final double y = GAME_HEIGHT * 0.535;
		boolean ledSlow = false;
		boolean ledMedium = false;
		boolean ledFast = false;
		switch (speed) {
		case SLOW:
			ledSlow = true;
			break;
		case MEDIUM:
			ledMedium = true;
			break;
		case FAST:
			ledFast = true;
			break;
		}
		this.drawLed(xSlow, y, ledSlow);
		this.drawLed(xMedium, y, ledMedium);
		this.drawLed(xFast, y, ledFast);
	}
	
	private void drawLabels() {

		this.g2d.setColor(COLOR_LABELS);
		Font f = new Font("Sans", Font.BOLD, 10);
		this.g2d.setFont(f);
		
		final double xCurrentScore = GAME_WIDTH * 0.33;
		final double xHighScore = GAME_WIDTH * 0.52;
		final double yScores = GAME_HEIGHT * 0.43;
		final double widthScores = GAME_WIDTH * 0.15;
        
        this.drawCenteredString(LABEL_SCORE, widthScores, xCurrentScore, yScores);
        this.drawCenteredString(LABEL_HIGH_SCORE, widthScores, xHighScore, yScores);
		
        final double xButtonSpeed = GAME_WIDTH * 0.375;
        final double xButtonRepeat = GAME_WIDTH * 0.475;
        final double xButtonReset = GAME_WIDTH * 0.575;
        final double yButtons = GAME_HEIGHT * 0.625;
        final double widthButtons = GAME_WIDTH * 0.05;
		this.drawCenteredString(LABEL_BUTTON_SPEED, widthButtons, xButtonSpeed, yButtons);
		this.drawCenteredString(LABEL_BUTTON_REPEAT, widthButtons, xButtonRepeat, yButtons);
		this.drawCenteredString(LABEL_BUTTON_RESET, widthButtons, xButtonReset, yButtons);
	}

	private void drawScores(int currentScore, int highScore) {
		InputStream is = GeniusForm.class.getResourceAsStream(FONT_LCD_FILENAME);
        Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			f = new Font("Courier new", Font.PLAIN, 24);
		}
        f = f.deriveFont(36f);
        this.g2d.setFont(f);
        final double xCurrentScore = GAME_WIDTH * 0.33;
        final double xHighScore = GAME_WIDTH * 0.52;
        final double y = GAME_HEIGHT * 0.5;
        this.g2d.setColor(COLOR_LED_OFF);
        this.g2d.drawString("888", (int)xCurrentScore, (int)y);
        this.g2d.drawString("888", (int)xHighScore, (int)y);
        this.g2d.setColor(COLOR_LED_ON);
        this.g2d.drawString(getDisplayString(currentScore), (int)xCurrentScore, (int)y);
        this.g2d.drawString(getDisplayString(highScore), (int)xHighScore, (int)y);
	}
	
	private void drawCenteredString(String s, double width, double x, double y){
		int stringWidth = (int)this.g2d.getFontMetrics().getStringBounds(s, this.g2d).getWidth();
		final double start = width/2 - stringWidth/2;
		this.g2d.drawString(s, (int)start + (int)x, (int)y);
	}
	private void drawLed(double x, double y, boolean enabled) {
		final double width = GAME_WIDTH * 0.01;
		final double height = GAME_HEIGHT * 0.01;
		if (enabled) {
			this.g2d.setColor(COLOR_LED_ON);
		} else {
			this.g2d.setColor(COLOR_LED_OFF);
		}
		this.g2d.fillOval((int)x, (int)y, (int)width, (int)height);
	}
	
	private String getDisplayString(int n) {
		if (0 > n) {
			return "-LO";
		}
		if (n > 999) {
			return "-HI";
		}
		return String.format("%d", n); 
	}
	
	public Element getElementByIndex(int i) {
		return elementList.get(i);
	}
	
	public Element getElementByButton(Button button) {
		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).getButton() == button) {
				return elementList.get(i);
			}
		}
		return null;
	}

}
