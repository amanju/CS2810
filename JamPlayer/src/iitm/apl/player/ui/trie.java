package iitm.apl.player.ui;

import iitm.apl.player.Song;

import java.util.Stack;
import java.util.Vector;

public class trie {

    // head of trie .. this does not contain any character
    public node head;
    // while searching we update this pointer
    public node currentPtr;
    // to check the new characters
    public String prevString = new String("");
    // total no. of songs
    public int size = 0;
    // for finding intersection
    private boolean[] present;
    // contains the songs for indexing
    Vector<Song> songs = new Vector<Song>();

    public trie() {
	head = new node('\0');
	currentPtr = head;
    }

    /*
     * Add all the substrings which are in the song in-order to support
     * substring search
     */
    public void addSong(songPtr song) {
	// just ensuring the song
	if (song != null)
	    size++;

	songs.add(song.song);
	// getting the song name
	String name = song.song.getTitle();

	// splitting the name of song
	String[] temp = name.split(" ", name.length() / 2 + 1);
	// inserting all the names of song
	for (int idx = 0; idx < temp.length; idx++)
	    insert(temp[idx], song);

	// adding the album for easy searching
	name = song.song.getAlbum();
	temp = name.split(" ", name.length() / 2 + 1);
	for (int idx = 0; idx < temp.length; idx++)
	    insert(temp[idx], song);
    }

    /*
     * this function takes the name and song as input inserts the song along
     * with all sub-strings into the trie
     */
    public void insert(String name, songPtr song) {
	// converting the name into array of chars
	char[] array = name.toLowerCase().toCharArray();
	node temp;

	int idx1, idx2;
	idx1 = 0;
	// for adding all substrings we are incrementing idx1 and adding the
	// remaining string
	while (idx1 < array.length) {
	    temp = head;
	    idx2 = idx1;
	    // adding all the chars in the array
	    while (idx2 < array.length)
		temp = temp.addNode(array[idx2++], temp);
	    temp.addSong(song);
	    idx1++;
	}
    }

    /*
     * This function is called if there are multiple words in the search This
     * finds the results for all the words and takes their intersection
     */
    public Vector<Song> multiSearch(String input) {
	Vector<Song> songs = new Vector<Song>();

	// used for finding the intersection of songs
	boolean[] localArray = new boolean[size];

	// Initializing all the variables to true so that we get the results for
	// the search of first word easily
	for (int idx = 0; idx < size; idx++)
	    localArray[idx] = true;

	String[] temp = input.split(" ", input.length() / 2 + 1);
	
	// looping through all the words
	for (int idx = 0; idx < temp.length; idx++) {
	    
	    // initializing the pointers before searching
	    prevString = "";
	    currentPtr = head;
	    
	    // searching for the word
	    search(temp[idx]);
	    
	    // taking intersection
	    for (int idx1 = 0; idx1 < size; idx1++)
		if (localArray[idx1] == true) {
		    localArray[idx1] = present[idx1];
		}
	}
	
	// adding the songs which should appear finally
	for (int idx = 0; idx < size; idx++)
	    if (localArray[idx] == true)
		songs.add(this.songs.get(idx));
	return songs;
    }

    /*
     * This function just updates the search results for an input string by
     * passing through the tree and updating the current_ptr.
     */
    public Vector<Song> search(String input) {
	// if the input is a multi-word then call the function
	if (input.contains(" "))
	    return multiSearch(input);

	// declaring the array used for removing duplicates in songs, not more
	// than one in the results
	if (present == null)
	    present = new boolean[size];

	String temp;
	// we have to start searching from the head if the previous search is
	// nothing
	if (prevString == "")
	    currentPtr = head;

	if (input.length() >= prevString.length()) {
	    if (input.startsWith(prevString)) {
		// we are getting the newly added chars in our search
		temp = (String) input.subSequence(prevString.length(), input
			.length());
	    } else {
		// if it does not start with the prev string then go to the head
		currentPtr = head;
		temp = input;
	    }
	    // save every char in lower case
	    char[] charArr = temp.toLowerCase().toCharArray();

	    int idx = 0;
	    // looping through the characters and if the result is nothing then
	    // the result becomes null and so we come out of the loop
	    while (idx < charArr.length && currentPtr != null)
		currentPtr = currentPtr.getChildAt(charArr[idx++]);
	} else {
	    
	    if (prevString.startsWith(input))
		// getting the no. of backspaces and thus moving up the trie
		backSearch(prevString.length() - input.length());
	    else {
		currentPtr = head;
		prevString = "";
	    }
	}
	prevString = input;
	// updating the string if the search is really bad: nothing much
	// important
	if (currentPtr == null)
	    prevString = "";
	// return the result of this function
	return getSongs();
    }

    // Updates the current_ptr by moving upper in the tree
    public Vector<Song> backSearch(int num) {
	while (num-- > 0 && currentPtr != null && currentPtr.parent != null)
	    currentPtr = currentPtr.parent;
	return getSongs();
    }

    /*
     * This function returns a vector containing all the songs which are present
     * in the sub-tree for which current_ptr is the parent. This returns in form
     * of a vector for easy updating in the library model
     */
    public Vector<Song> getSongs() {
	Vector<Song> songVect = new Vector<Song>();
	if (currentPtr == null) {
	    // this means that the result is nothing and so we are making the
	    // array completely false
	    for (int idx = 0; idx < size; idx++)
		present[idx] = false;
	    return songVect;
	}
	songPtr tempSong;
	node temp;
	// this is used for parsing through out the tree and so get all the
	// songs
	Stack<node> nodeStack = new Stack<node>();
	int idx1;
	for (idx1 = 0; idx1 < size; idx1++)
	    present[idx1] = false;
	nodeStack.push(currentPtr);
	while (nodeStack.size() > 0) {
	    temp = nodeStack.pop();
	    // pushing all the children into the stack
	    for (idx1 = 0; idx1 < temp.children.size(); idx1++)
		nodeStack.push(temp.children.get(idx1));

	    for (idx1 = 0; idx1 < temp.songs.size(); idx1++) {
		tempSong = temp.songs.get(idx1);
		if (present[tempSong.index] == false) {
		    // adding the song only if it was not previously added and
		    // also making the attribute true inorder
		    // not to add it again
		    songVect.add(tempSong.song);
		    present[tempSong.index] = true;
		}
	    }
	}
	return songVect;
    }
}
