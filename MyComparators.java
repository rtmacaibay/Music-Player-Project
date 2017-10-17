/**
	My comparators for sorting the songs by either title or artist
	@Author: Robert Macaibay
	@Assignment: Project 5
**/
import java.util.Comparator;

public class MyComparators {
	//sorts by title
	public static Comparator<Song> TITLE = new Comparator<Song>() {
		public int compare(Song s1, Song s2) {
			return s1.getTitle().compareTo(s2.getTitle());
		}
	};

	//sorts by artist
	public static Comparator<Song> ARTIST = new Comparator<Song>() {
		public int compare(Song s1, Song s2) {
			return s1.getArtist().compareTo(s2.getArtist());
		}
	};
}

