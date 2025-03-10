/**
 * Represents a song with a name, album, and artist. This class provides basic information about a
 * song and a string representation of it.
 */
public class Song {
  /**
   * The name of the song.
   */
  private String name;

  /**
   * The album that contains the song.
   */
  private Album album;

  /**
   * The name of the artist who performed the song.
   */
  private String artist;

  /**
   * Constructs a new Song object with the specified name, album, and artist.
   *
   * @param name   the name of the song
   * @param artist the artist who performed the song
   * @throws IllegalArgumentException if the any of the fields is null or empty
   */
  public Song(String name, String artist) {
    if (name == null || artist == null) {
      throw new IllegalArgumentException("Null input fields");
    }
    if (name.isBlank() || artist.isBlank()) {
      throw new IllegalArgumentException("Blank input fields");
    }
    this.name = name;
    this.artist = artist;
  }

  /**
   * Sets the album for this song.
   *
   * @param album the Album object to be associated with this song.
   */
  public void setAlbum(Album album) {
    this.album = album;
  }

  /**
   * Returns a string representation of the song, including its name, artist, and albumName. If the
   * album is null, use an empty string in the parentheses ()
   *
   * @return a string in the format "Name: Artist (Album)"
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    String albumName = album != null ? album.getAlbumName() : "";
    return name + ": " + artist + " (" + albumName + ")";
  }

  /**
   * Determines whether this song and anObject are copies (deep or shallow) of each other. If
   * anObject is not a Song object at all, they are not equal. If it IS a Song, then they are equal
   * if and only if this song and anObject have the same name, artist, and album (CASE INSENSITIVE).
   * Two albums are considered to be equal only if their names match or they are both null.
   */
  @Override
  public boolean equals(Object anObject) {
    if (anObject instanceof Song) {
      Song s = (Song) anObject;
      if (this.name.equalsIgnoreCase(s.name) && this.artist.equalsIgnoreCase(s.artist)) {
        if (this.album == null && s.album == null) {
          return true;
        }
        if (this.album != null && s.album != null) {
          return this.album.getAlbumName().equalsIgnoreCase(s.album.getAlbumName());
        }
      }
    }
    return false;
  }
}
