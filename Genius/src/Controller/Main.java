package Controller;
import java.util.ArrayList;

import GUI.Graph;
import Model.*;
public class Main {
	public static Jogo currentGame  = new Jogo();   
	public static Graph geniusForm = new Graph();
			
	public static void main(String[] args) {	    
		geniusForm.setVisible(true);
	    currentGame.reiniciar();
	    
	    showMove();
	}
	public static void showMove() {
		while (currentGame.getStatus() == Status.DEMONSTRANDO){
    		ArrayList<Cor> colorList = currentGame.getSequencia();
    		for (int i = 0; i < colorList.size(); i++) {
    			geniusForm.feedback(colorList.get(i));
    		}
    		currentGame.setStatus(Status.JOGAR);
	    }
	}
	
	public static void computesMove(){
		currentGame.comando(geniusForm.getButtonClicked());
		}
	}
