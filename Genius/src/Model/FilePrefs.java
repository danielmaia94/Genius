package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import GUI.Element;

public class FilePrefs {

    private String fileName;
    
    public FilePrefs(String fileName) {
    	this.fileName = fileName;
    }
   
    public void save(Prefs prefs) {
        try {
			FileOutputStream fileStream = new FileOutputStream(this.fileName);
			ObjectOutputStream objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(prefs);
			fileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public Prefs load() throws IOException, ClassNotFoundException {
        FileInputStream fileStream;
		try {
			fileStream = new FileInputStream(this.fileName);
		} catch (FileNotFoundException e) {
			return null;
		}
        ObjectInputStream objStream = new ObjectInputStream(fileStream);
        Object prefs = objStream.readObject();
        objStream.close();
        return (Prefs)prefs;
    }
    
}
class Prefs implements Serializable {
    private Speed speed;
    private int highScore;
    private ArrayList<Button> colorSequence;
    
    public Prefs(Speed speed, int highScore, ArrayList<Button> colorSequence) {
    	this.speed = speed;
    	this.highScore = highScore;
    	this.colorSequence = colorSequence;
    }
    public Speed getSpeed() {
    	return this.speed;
    }
    public int getHighScore() {
    	return this.highScore;
    }
    public ArrayList<Button> getColorSequence() {
    	return this.colorSequence;
    }
}