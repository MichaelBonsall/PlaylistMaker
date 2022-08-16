import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class MediaPlayer implements Runnable {

    private static boolean isPaused = false;
    static long pausedTime = 0;
    static char response = ' ';
    static boolean responseFound = false;
    static Scanner threadScanner = new Scanner(System.in);


    /**
     * Plays the current song using javax. Includes unpause/pause, reset, skip, and quit (used for quitting a playlist)
     * 
     * @param song The song you want to play
     */
    public static void play(Song song){
        
        System.out.flush();
        try{
            File file = song.getSong();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            long length = clip.getMicrosecondLength();
            
            Runnable userInput = new MediaPlayer(); //thread used to read in user input while checking if the song is over
            Thread thread = new Thread(userInput);
            thread.start();
            while (length != clip.getMicrosecondPosition()){
                if (responseFound){
                    switch(response){
                        case ('P'):
                            if (!isPaused){
                                pausedTime = clip.getMicrosecondPosition();
                                clip.stop();
                                isPaused = true;
                                responseFound = false;
                                System.out.println("Song Paused.");
                                break;
                            }
                            else{
                                clip.setMicrosecondPosition(pausedTime);
                                clip.start();
                                isPaused = false;
                                responseFound = false;
                                System.out.println("Song Unpaused.");
                                break;        
                            }
    
                        case ('R'): 
                            clip.setMicrosecondPosition(0);
                            clip.start();
                            responseFound = false;
                            System.out.println("Song Restarted.");
                            break;
        
                        case ('S'):
                            clip.setMicrosecondPosition(length);
                            responseFound = false;
                            response = ' ';
                            System.out.println("Song Skipped.");
                            break;
        
                        case ('Q'):
                            clip.close();
                            System.out.println("Stopping Playlist.");
                            thread.interrupt();
                            System.exit(0);
            
                        default: 
                            System.out.println("Not a valid response");
                            responseFound = false;

                    }
                response = ' ';
                }
                
            }
            clip.close();
            return;
            
           
        } 
        catch (UnsupportedAudioFileException e) {    
            String fileName = song.getSong().toString();
            int dotIndex = fileName.lastIndexOf(".");
            String extension = fileName.substring(dotIndex); 
            System.out.println("The file format " + extension + " is not supported.");
            
        } 
        catch (IOException e) {
            System.out.println("Song " + song.getTitle() + " could not be found.");
        }
        catch (LineUnavailableException e) {
            System.out.println("I have no idea what this means. FIX");
            e.printStackTrace();
        }
    }

    /**
     * thread used to read in input while play() waits for a song to finish
     * 
     */
    @Override
    public void run()
    {
        try{
            while (true){
                System.out.println("P = Pause / UnPause, R = Reset, S = Skip, Q = Quit");
                System.out.println("Enter your choice: ");    
                response = Character.toUpperCase(threadScanner.next().charAt(0)); 
                System.out.println("response = " + response);
                responseFound = true;
            }
        }
        catch (IndexOutOfBoundsException e){
            //empty catch to silence indexoutofbounds i dont understand
        } 
        
    
    }
}