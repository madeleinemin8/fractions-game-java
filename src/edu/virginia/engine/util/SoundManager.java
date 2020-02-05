package edu.virginia.engine.util;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.io.File;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundManager extends JFrame {

    private HashMap<String, File> hashmap;
    private static Sequencer midiPlayer;
    private Sequence song;
    // Constructor
    public SoundManager() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);
        hashmap = new HashMap<String, File>();
    }

    // Loads sound effect files into a hashmap of sound effects where the key is the String id and the value is the url.
    public void LoadSoundEffect(String id, String filename) {
        File wavFile = new File("resources/" + filename);
        hashmap.put(id, wavFile);
    }

    // Plays the sound effect
    public void PlaySoundEffect(String id) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(hashmap.get(id));
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Loads music file into a single Clip object
    public void LoadMusic(String filename) {
        try {
            File midiFile = new File("resources/" + filename);
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            song = MidiSystem.getSequence(midiFile);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Plays the music on a loop throughout the game
    public void PlayMusic() {
        try {
            midiPlayer = MidiSystem.getSequencer();
            midiPlayer.open();
            midiPlayer.setSequence(song);
            midiPlayer.setLoopCount(0);
            midiPlayer.start();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}