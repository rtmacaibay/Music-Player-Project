/**
	Creates a Song object that holds title, artist, and the original file
	@Author: Robert Macaibay
	@Assignment: Project 5
**/

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Song {
	private String title;
	private String artist;
	private File file;

	/**	
		creates song object
		@param: File for inputted file
	**/
	public Song(File f) {
		try {
			file = f;
			//read file and find the needed tags
			title = AudioFileIO.read(f).getTag().getFirst(FieldKey.TITLE);
			artist = AudioFileIO.read(f).getTag().getFirst(FieldKey.ARTIST);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
		get title
		@return: String for title
	**/
	public String getTitle() {
		return title;
	}

	/**
		get artist
		@return: String for artist
	**/
	public String getArtist() {
		return artist;
	}

	/**
		get file
		@return: File for... file
	**/
	public File getFile() {
		return file;
	}
}