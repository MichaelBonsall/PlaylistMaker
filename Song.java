import java.io.File;

public class Song {
	
	private String title;
	private String artist;
	private File songFile;
	
	/**
	 * 
	 * Song object
	 * @param title the title of the song
	 * @param artist the artist of the song
	 */
	public Song(String title, String artist, File songFile) {
		this.title = title;
		this.artist = artist;
		this.songFile = songFile;
	}
	
	/**
	 * equals method for the song object
	 * 
	 * @param obj the song you want to compare
	 * 
	 * @return true if the same song and artist, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		Song s = (Song)obj;
		return title.equals(s.title) && artist.equals(s.artist);
	}
	
	/**
	 * hashcode for the song object
	 * 
	 * @return int the hashcode of the song
	 */
	@Override
	public int hashCode() {
		return title.hashCode()+artist.hashCode();
	}
	
	/**
	 * toString method for the song object
	 * 
	 * @return the song object in format "title by artist"
	 */
	@Override
	public String toString() {
		return title + " by " + artist;
	}
	
	/**
	 * Artist getter 
	 * @return the artist of the song 
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Title getter 
	 * @return the title of the song
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * artist setter 
	 * @param artist the new name of the artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	/**
	 * title setter 
	 * @param title the new name of the title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	
	public File getSong(){
		return songFile;
	}

	public void setSongFile(File songFile){
		this.songFile = songFile;
	}

}
