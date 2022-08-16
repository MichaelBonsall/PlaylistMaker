# PlaylistMaker
Music playlist maker and player using .wav format.

## How to add songs
To create a new song object, place the wav in the same folder as everything else, and then add these three lines to the main method. 

File fileName = new File("YOURMUSICHERE.wav");

Song NAME = new Song("SONG NAME","ARTIST", fileName);

playlist.append(NAME);

## How to play playlist

Run LinkedPlayList.java to play the playlist. Instructions are printed to the terminal if you want to skip, pause, unpause, or quit the playlist.
