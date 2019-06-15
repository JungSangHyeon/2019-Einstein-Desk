package music;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Speaker {

	static Clip clip; 
	
	static boolean on = false;
	public static boolean isOn() {return on;}
	public static void off() {if(clip!=null) {clip.stop();}on=false;}
	
	public static void on(String fileName, boolean Loop) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			if (Loop) {clip.loop(-1);}
			on = true;
		} catch (Exception e) {System.out.println("파일이 없는데여");}
	}
	
}
