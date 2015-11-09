package Controller;
import java.util.ArrayList;

import GUI.Graph;
import Model.*;
public class Main {

	public static void main(String[] args) {
		boolean play = true;
		Jogo currentGame  = new Jogo();   
		Graph geniusForm = new Graph();
	    
		geniusForm.setVisible(true);
	    currentGame.reiniciar();
	    
	    while (play){
	    	if (currentGame.getStatus() == Status.DEMONSTRANDO){
	    		ArrayList<Cor> colorList = currentGame.getSequencia();
	    		for (int i = 0; i < colorList.size(); i++) {
	    			geniusForm.feedback(colorList.get(i));
	    		}
	    		currentGame.setStatus(Status.JOGAR);
	    		geniusForm.setWasButtonClicked(false);
	    	}
	    	while (currentGame.getStatus() == Status.JOGAR){
	    		if (geniusForm.getWasButtonClicked() == true) {
	    			geniusForm.setWasButtonClicked(false);
    				currentGame.comando(geniusForm.getButtonClicked());
	    		}
	    	}

	    }
	}
}