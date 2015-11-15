package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;

import Model.Button;

public class Element {
	private Color color;
	private Color colorEnabled;
	private Shape shape;
	private Button button;
	private boolean enabled = false;
	private boolean coverButton = false;
	
	public static void createAndAdd(Shape shape, Color color, ArrayList<Element> elementList) {
		Element element = new Element(shape, color);
		element.testCoverButton(elementList);
		elementList.add(element);
	}
	
	public static void createAndAdd(Shape shape, Color color, Button button, ArrayList<Element> elementList) {
		Element element = new Element(shape, color, button);
		element.testCoverButton(elementList);
		elementList.add(element);
	}
	
	public Button getButton() {
		return this.button;
	}
	public boolean getCoverButton() {
		return this.coverButton;
	}
	
	public static void createAndAdd(Shape shape, Color color, Color colorEnabled, Button button, ArrayList<Element> elementList) {
		Element element = new Element(shape, color, colorEnabled, button);
		elementList.add(element);
	}
	
	private Element(Shape shape, Color color) {
		this.color = color;
		this.colorEnabled = null;
		this.shape = shape;
		this.button = null;
	}
	private Element(Shape shape, Color color, Button button) {
		this.color = color;
		this.colorEnabled = null;
		this.shape = shape;
		this.button = button;
	}
	private Element(Shape shape, Color color, Color colorEnabled, Button button) {
		this.color = color;
		this.colorEnabled = colorEnabled;
		this.shape = shape;
		this.button = button;
	}
	
	private void testCoverButton(ArrayList<Element> elementList) {
		Area areaA = new Area(shape);
		Area areaB;
		for (int i = 0; i < elementList.size(); i++) {
			areaB = new Area(elementList.get(i).shape);
			if (elementList.get(i).button == null) {
				continue;
			}
			if (!elementList.get(i).button.isColorButton()) {
				continue;
			}
			areaB.intersect(areaA);
			if (!areaA.isEmpty()) {
				this.coverButton = true;
				return;
			}
		}
		this.coverButton = false; 
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
