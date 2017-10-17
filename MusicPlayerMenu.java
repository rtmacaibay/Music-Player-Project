/**
	This class will create a menu for the user to navigate through.
	@Author: Robert Macaibay
	@Assignment: Project 5
**/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MusicPlayerMenu {
	/**
		displays the initial menu that'll get the user's input
		@param: ArrayList of songs
	**/
	public static void displayMenu(ArrayList<Song> songs) {
		//tries and catch any exceptions thrown
		try {
			boolean byArtist = true; //checks if the user asked for the mp3s listed in a way
			Scanner in = new Scanner(System.in); //for input
			char input = 'a'; //char input
			MusicPlayer mp = new MusicPlayer(); //music player

			//a loop that'll always run until the user exits out
			do {
				//display menu options
				System.out.println(">>Type in the letter of an option<<");
				System.out.println("a. List all MP3s sorted by song title");
				System.out.println("b. List all MP3s sorted by artist");
				System.out.println("c. Play an MP3");
				System.out.println("d. Stop playback");
				System.out.println("e. Exit");
				System.out.print("Input: ");

				//gets user input
				String temp = in.next();
				System.out.println();

				//if user input happens to be bigger than required, let the user know
				if (temp.length() > 1) {
					System.out.println("Please input a correct \"command\". For ex: a, b, c, d, or e.");
					System.out.println();
					continue;
				}

				//converts the input into our char variable
				input = temp.toLowerCase().charAt(0);

				//checks what the user wanted to do
				if (input == 'a') {
					byArtist = false;
					//sorts the list by title
					Collections.sort(songs, MyComparators.TITLE);
					//displays songs
					displaySongs(songs);
					System.out.println();
				} else if (input == 'b') {
					byArtist = true;
					//sorts the list by artist
					Collections.sort(songs, MyComparators.ARTIST);
					//displays songs
					displaySongs(songs);
					System.out.println();
				} else if (input == 'c') {
					//display songs and do the rest in other method
					displayPlayMenu(songs, byArtist, in, mp);
				} else if (input == 'd') {
					//checks if any music is playing
					if (mp.isPlaying()) {
						//stop music if so
						mp.stop();
					}
				} else if (input == 'e') {
					//checks if music is playing
					if (mp.isPlaying()) {
						//stop music if so
						mp.stop();
					}
					//go end the loop
					continue;
				} else {
					//checks if it was incorrect input
					System.out.println("Please input a correct \"command\". For ex: a, b, c, d, or e.");
					System.out.println();
				}
			} while (input != 'e');
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
		displays songs and then ask user for what song to play
		@param: ArrayList of songs, boolean for how the list is sorted
		Scanner for input, MusicPlayer for song playback
	**/
	public static void displayPlayMenu(ArrayList<Song> songs, boolean byArtist, Scanner in, MusicPlayer mp) {
		//just input variables
		char input = 'a';
		int option = 0;

		try {
			do {
				//checks how the list should be sorted
				if (byArtist) {
					Collections.sort(songs, MyComparators.ARTIST);
					displaySongs(songs);
				} else {
					Collections.sort(songs, MyComparators.TITLE);
					displaySongs(songs);
				}

				System.out.println();
				System.out.print("Type in the number of the song to play: ");

				//checks if the input contains an integer
				if (in.hasNextInt()) {
					option = in.nextInt();
				} else {
					//checks if the user wanted to exit out
					String temp = in.next();
					if (temp.toLowerCase().charAt(0) == 'e' && temp.length() == 1) {
						break;
					}
					//elsewise tell user incorrect input
					System.out.println("Please input a correct value or e to exit. For ex: 1, 2, 3, etc.");
					System.out.println();
					continue;
				}

				//check if the number that the user inputted exists in the list
				if (option < 1 || option > songs.size()) {
					System.out.println("Please input a correct value that is within the list.");
					System.out.println();
				} else {
					//check if any music is playing
					if (mp.isPlaying()) {
						//stop music if so
						mp.stop();
					}
					//play the song if within list
					mp.play(songs.get(option - 1).getFile());
				}
			} while (!(option > 0 && option < songs.size() + 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
		displays a list of songs
		@param: ArrayList of songs
	**/
	public static void displaySongs(ArrayList<Song> songs) {
		for (int x = 0; x < songs.size(); x++) {
			System.out.println((x + 1) + ": " + songs.get(x).getTitle() + " - " + songs.get(x).getArtist());
		}
	}
}