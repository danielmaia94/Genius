package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import Model.Button;

public class Element {
	private Color color;
	private Color colorEnabled;
	private Shape shape;
	private Button button;
	private boolean enabled = false;
	
	public Button getButton() {
		return this.button;
	}
	
	public Element(Shape shape, Color color) {
		this.color = color;
		this.colorEnabled = null;
		this.shape = shape;
		this.button = null;
	}
	public Element(Shape shape, Color color, Button button) {
		this.color = color;
		this.colorEnabled = null;
		this.shape = shape;
		this.button = button;
	}
	public Element(Shape shape, Color color, Color colorEnabled, Button button) {
		this.color = color;
		this.colorEnabled = colorEnabled;
		this.shape = shape;
		this.button = button;
	}
	
	public void enable() {
		this.enabled = true;
	}
	public void disable() {
		this.enabled = false;
	}
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if (this.enabled && this.colorEnabled != null) {
			g2d.setColor(this.colorEnabled);
		} else {
			g2d.setColor(this.color);
		}
		g2d.fill(this.shape);
	}
	
    public boolean shapeClicked(Point point) {
    	return shape.contains(point);
    }
}
