
import java.io.File;

import javax.print.attribute.standard.Media;



public class LinkedPlayList {
    
    static class Node {
        private Song song;
        private Node next;
        
        /**
         * Node object
         * @param song the song you want to be in the linked list
         * @param next the next node in the list
         */
        public Node(Song song,Node next) {
            this.song = song;
            this.next = next;
        }
        
        /**
         * Song getter
         * 
         * @return the song object
         */
        public Song getSong() {
            return song;
        }
        
        /**gets next node
         * 
         * @return the next node in the list
         */
        public Node getNext() {
            return next;
        }
        
        /**
         * Song toString
         */
        public String toString() {
            return song.toString();
        }
        
    }
    
    private Node head;
    
    /**Gets head of the linked list
     * 
     * @return head of the linked list
     */
    public Node head() {
        return head;
    }
    
    /**Checks if the linked list is empty
     * 
     * @return false if empty, true if not empty
     */
    public boolean isEmpty() {
        return head==null;
    }
    
    /**
     * Returns the size of the linked list
     * 
     * @return size of the linked list
     */
    public int size() {
        int size=0;
        Node current = head;
        while(current!=null) {
            size++;
            current = current.next;
        }
        return size;
    }
    
    public void insertAtHead(Song song) {
        Node node = new Node(song,null);
        node.next = head;
        head = node;
    }
    
    /**
     * equals method for the linked list
     * 
     * @param object the linked list you want to compare
     * 
     * @return true if lists are equal, false otherwise
     */
    public boolean equals(Object o) {
        LinkedPlayList pl = (LinkedPlayList)o;
        if(pl.size() != size()) {return false;}
        Node current1 = pl.head;
        Node current2 = head;
        while(current1!=null) {
            if(!current1.song.equals(current2.song))
                return false;
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }
    
    /**hashcode method
     * 
     * @return the hashcode for the linked list
     * 
     */
    public int hashCode() {
        int hc = 0;
        Node current = head;
        while(current!=null) {
            hc = hc + (current.getSong()).hashCode();
        }
        return hc;
    }
    
    /**
     * Checks to see if a song is contained in the linked list
     * @param s Song to be checked 
     * @return false if no song found, true if song found
     */
    public boolean contains(Song s) {
        Node current = head;
        while(current !=null) {
            if(s.equals(current.song)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    /**
     * Adds song to beginning of the linked list  
     * 
     * @param song Song to be added
     */
    public void append(Song song) {
        Node node = new Node(song,null); 
        if(head==null) {
            head=node;
        }
        else {
            Node current = head;
            while(current.next!=null) {
                current = current.next;
            }
            current.next = node;
        }
    }
    


    /**
     * Returns node at specified index
     * 
     * @param index Index of the song you want to return
     * @return the Node at the given index
     */
    public Node get(int index) {
        if(index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        else {
            int currentIndex = 0;
            Node current = head;
            while(current!=null) {
                if(currentIndex == index) {
                    return current;
                }
                currentIndex++;
                current = current.next;
            }
            throw new IndexOutOfBoundsException(index);
        }
    }
    


    /**
     * Adds node to list at the given location
     * 
     * @param loc location for the song to be added to
     * @param song Song to be added
     */
    public void add(int loc,Song song) {
        if(loc<0) {
            throw new IndexOutOfBoundsException(loc);
        }
        if(loc==0) {
            Node n = new Node(song,null);
            n.next = head;
            head = n;
        }
        else {
            Node prevSong = get(loc-1);
            Node nextSong = get(loc);
            Node n = new Node(song,null);
            prevSong.next = n;
            n.next = nextSong;
        }
    }
    
    /**
     * Removes first instance of the specified song
     * 
     * @param song The song to be removed
     * @return false if the list doesn't contain the song, true if otherwise
     */
    public boolean removeOne(Song song) {
        boolean removedOne = false;
        if(head==null)
            throw new IllegalArgumentException("can't remove from an empty list");
        if(song.equals(head.song)) {
            head = head.next;
            removedOne = true;
        }
        else {
            Node current = head;
            while(current.next!=null && (!song.equals(current.next.song)))
                current = current.next;
            if(current.next!=null) {
                current.next = current.next.next;
                removedOne = true;
            }
            else
                removedOne = false;
        }
        
        return removedOne;
    }
    


    /**
     * Removes ALL instances of the given song 
     * @param song song to be removed 
     * @return false if song doesn't exist in list, true otherwise
     */
    public boolean removeAll(Song song) {
    	if (head == null) {
    		throw new IllegalArgumentException("Linked List is empty");
    	}
    	Node current = head;
    	boolean found = false;
    	while (current.next != null) {
    		if (song.equals(current.next.song)) {
    			current.next = current.next.next;
    			found = true;
    		}
    		else current = current.next;
    	}
    	if (head.song.equals(song)) {
    		head = head.next;
    		found = true;
    	}
    	if (!found) {
    		System.out.println("Song not found in list");
    	}
        return found;
    }
    
    /**
     * 
     * Inserts song after song in list
     * @param prevSong The song you want to add the given song after
     * @param songToAdd Song to add to the linked list
     * @return true if successful, false otherwise
     */
    public boolean insertAfter(Song prevSong, Song songToAdd) {
    	boolean inserted = false;
    	Node data = new Node(songToAdd, null);
    	Node current = head;
    	while (current != null) {
    		if (current.song.equals(prevSong)) {
    			current.next = data;
    			inserted = true;
    			break;
    		}
    		else current = current.next;
    	}
        
        return inserted;
    }
    
    
    /**
     * Reverses the linked list
     * 
     */
    public void reverse() {
    	Node prev = null;
    	Node current = head;
    	Node next = null;
    	while (current != null) {
    		next = current.next;
    		current.next = prev;
    		prev = current;
    		current = next;
    	}
    	head = prev;
    }
    
    /**
     * Swaps two songs in the list
     * 
     * @param i first song to swap
     * @param j second song to swap
     */
    public void swap(Song i, Song j) {
    	      Node node1 = null;
    	      Node node2 = null;
    	      Node n1 = head;
    	      Node n2 = head;
    	      
    	      if (head == null) {
    	    	  return;
    	      }
    	      
    	      if (i.equals(j)) {
    	    	  return;
    	      }
    	      
    	      while(n1 != null && !n1.song.equals(i)) {
    	    	  node1 = n1;
    	    	  n1 = n1.next;
    	      }
    	      
    	      while(n2 != null && !n2.song.equals(j)) {
    	    	  node2 = n2;
    	    	  n2 = n2.next;
    	      }
    	      
    	      if(n1 != null && n2 != null) {
    	    	  if (node1 != null) {
    	    		  node1.next = n2;
    	    	  }
    	    	  else {
    	    		  head = n2;
    	    	  }

    	    	  if(node2 != null) {
    	    		  node2.next = n1;
    	    	  }
    	    	  else {
    	    		  head = n1;
    	    	  }
    	    	  
    	    	  Node temp = n1.next;
    	    	  n1.next = n2.next;
    	    	  n2.next = temp;
    	      }

    }
 
    
    
    /**
     * toString for the list
     * 
     * @return the linked list in format "Song -> song -> ..."
     * 
     */
    public String toString() {
        String s = "";
        Node current = head;
        while(current != null) {
            s = s + current.song;
            current = current.next;
            s = s+"->";
        }
        return s;
    }

    public String getTitle(Node current){
        return current.song.getTitle();
    }

    public String getArtist(Node current){
        return current.song.getArtist();
    }
    
    public void playPlayList(){
        Node current = head;
        while (current != null){
            System.out.println("Now playing " + getTitle(current) + " by " + getArtist(current) + ".");
            MediaPlayer.play(current.getSong());
            current = current.next;
        }
        MediaPlayer.threadScanner.close();
        System.out.println();
        System.out.println("You have reached the end of the playlist.");
    }
    public static void main(String[] args) {
        LinkedPlayList playlist = new LinkedPlayList();
        File testSong = new File("sample.wav");
        File testSong2 = new File("sample2.wav");
		Song s1 = new Song("Bad Habits","Ed Sheeran", testSong);
		Song s2 = new Song("Tides", "Ed Sheeran",testSong2);
        playlist.append(s1);
        playlist.append(s2);
        playlist.append(s1);
        
        playlist.playPlayList();
    }
    
}
