package Controller;
import GUI.GeniusForm;
import Model.Game;
public class Main {
	
	static Game currentGame;
	static GeniusForm geniusForm;
	
	public static Game getGame(){
		return currentGame;
	}

	public static GeniusForm getForm(){
		return geniusForm;
	}
	
	public static void main(String[] args) {
		geniusForm = new GeniusForm();
		currentGame = new Game();
	    currentGame.start();
		geniusForm.setVisible(true);

	}
}