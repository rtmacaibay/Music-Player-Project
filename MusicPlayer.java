/**
	Creates a music player to play songs
	@Author: Robert Macaibay
	@Assignment: Project 5
**/

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayer {
	private Player player;
	private boolean playing;

	/**
		construct our music player object
		REMEMBER: player cannot be initialized without a file sooooo make it null
		because there is no file YET
	**/
	public MusicPlayer() {
		player = null;
		playing = false;
	}

	/**
		plays the song from a file
		@param: File for original song file
	**/
	public void play(File f) {
		try {
			//creates a new player with an inputted file
			player = new Player(new FileInputStream(f));
		
			//creates a thread to play the song
			Thread t = new Thread() {
				public void run() {
					try {
						//plays the song
						player.play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};

			//start thread
			t.start();
			//show that there is a song playing
			playing = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
		stops the song playing
	**/
	public void stop() {
		//close player
		player.close();
		playing = false;
	}

	/**
		gets whether or not there is something playing
	**/
	public boolean isPlaying() {
		return playing;
	}
}