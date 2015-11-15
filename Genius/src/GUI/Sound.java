package GUI;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import Model.Button;

public class Sound {
	
	private static final String YELLOW_SOUND = "Yellow.wav";
	private static final String BLUE_SOUND = "Blue.wav";
	private static final String GREEN_SOUND = "Green.wav";
	private static final String RED_SOUND = "Red.wav";
	private static final String ERROR_SOUND = "Error.wav";
	private static AudioClip audio;
	
	private static URL getFileSound(Button button){
		switch (button){
		case YELLOW : return Sound.class.getResource(YELLOW_SOUND);
		case BLUE 	: return Sound.class.getResource(BLUE_SOUND);
		case GREEN  : return Sound.class.getResource(GREEN_SOUND);
		case RED    : return Sound.class.getResource(RED_SOUND);
		default    		 : return null;
		}
	}
	public static void playButtonSound(Button button){
		URL url = getFileSound(button);
		if (url != null){
			audio = Applet.newAudioClip(url);
			audio.loop();
		}
	}
	
	public static void playErrorSound(){
		URL url = Sound.class.getResource(ERROR_SOUND);
		if (url != null){
			audio = Applet.newAudioClip(url);
			audio.loop();
		}
	}
	
	public static void stopSound(){
		if (audio != null) {
			audio.stop();
		}
	}

}