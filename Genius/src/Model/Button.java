package Model;

import java.util.EnumSet;  //Provê funcionalidade de subconjuntos.
import java.util.Random;   //Provê funcionalidade de geração de valores aleatórios.

public enum Button {
	YELLOW, RED, BLUE, GREEN, SPEED, REPEAT, RESET;
	
	public static EnumSet<Button> ColorButton = EnumSet.of(Button.YELLOW, Button.RED, Button.BLUE, Button.GREEN);

	//Testa se o botão é um botão de cor.
	public boolean isColorButton() {
		return ColorButton.contains(this);
	}
	
	//Gera uma cor aleatoriamente.
	public static Button getRandomColorButton() {
		Random randomizer = new Random();
		Object[] colors = Button.ColorButton.toArray();
		int numColors = colors.length;
		int randomIndex = randomizer.nextInt(numColors);
		return (Button)colors[randomIndex]; 
	}

}