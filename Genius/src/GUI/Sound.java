package GUI;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
	private URL getFileSound(Model.Sound sound){
		switch (sound){
		case YELLOWSOUND : return getClass().getResource("Yellow.wav");
		case BLUESOUND 	 : return getClass().getResource("Blue.wav");
		case GREENSOUND  : return getClass().getResource("Blue.wav");
		case REDSOUND    : return getClass().getResource("Red.wav");
		case ERRORSOUND  : return getClass().getResource("Error.wav");
		default    		 : return null;
		}
	}
	public void playSound(Model.Sound sound){
		URL url = getFileSound(sound);
		if (url != null){
			AudioClip audio = Applet.newAudioClip(url);
			audio.play();
		}
	}

}
