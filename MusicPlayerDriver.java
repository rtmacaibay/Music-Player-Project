/**
	Runs a music player
	@Author: Robert Macaibay
	@Assignment: Project 5
**/

import java.io.File;
import java.util.ArrayList;

public class MusicPlayerDriver {
	public static void main(String[] args) {
		//checks if there is an argument and is a directory
		if (args.length == 0) {
			System.out.println("Please input a directory.");
			System.out.println("Proper usage: java MusicPlayerDriver /Users/srollins/mymusic");
			System.exit(0);
		}
		if (!new File(args[0]).isDirectory()) {
			System.out.println("Please input a directory.");
			System.out.println("Proper usage: java MusicPlayerDriver /Users/srollins/mymusic");
			System.exit(0);
		}

		try {
			//finds all the song files
			ArrayList<Song> files = findAudioFiles(args[0]);
			//displays menu
			MusicPlayerMenu.displayMenu(files);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
		grabs the song files - initial method
		@param: String for directory
		@return: ArrayList of songs
	**/
	public static ArrayList<Song> findAudioFiles(String dir) {
		//create an arraylist of songs
		ArrayList<Song> files = new ArrayList<Song>();
		//use helper method
		findAudioFiles(new File(dir).listFiles(), files, 0);
		return files;
	}

	/**
		grabs the song files from directories and inner-directories - helper method
		@param: array of Files, ArrayList of songs, int for a counter
	**/
	private static void findAudioFiles(File[] input, ArrayList<Song> files, int count) {
		//base case - when you reach the end
		if (count == input.length) {
			return;
		}

		//checks if the found file is a file is an mp3
		if (input[count].isFile() && input[count].toString().endsWith(".mp3")) {
			try {
				//add to list
				files.add(new Song(input[count]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//checks if directory
		if (input[count].isDirectory()) {
			//recall itself but input the directory
			findAudioFiles(input[count].listFiles(), files, 0);
		}

		//recall itself, increment count
		findAudioFiles(input, files, count + 1);
	}
}