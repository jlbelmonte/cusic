package music;

import org.jfugue.Pattern;
import org.jfugue.Player;
import org.jfugue.Rhythm;

public class Dummy {
Player player = new Player();

public static void main(String[] args) {
	Rhythm rhythm = new Rhythm();
rhythm.setLayer(1, "O..oO...O..oOO..");
	rhythm.setLayer(2, "..*...*...*...*.");
	rhythm.setLayer(3, "^^^^^^^^^^^^^^^^");
	rhythm.setLayer(4, "...............!");
	rhythm.addSubstitution('O', "[BASS_DRUM]i");
	rhythm.addSubstitution('o', "Rs [BASS_DRUM]s");
//	rhythm.addSubstitution('*', "[ACOUSTIC_SNARE]i");
//	rhythm.addSubstitution('^', "[PEDAL_HI_HAT]s Rs");
//	rhythm.addSubstitution('!', "[CRASH_CYMBAL_1]s Rs");
//	rhythm.addSubstitution('.', "Ri");
	Pattern pattern = rhythm.getPattern();
	pattern.repeat(4);
	Player player = new Player();
	player.play(pattern);
	}
}
