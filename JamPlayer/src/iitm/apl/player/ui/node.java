package iitm.apl.player.ui;

import java.util.LinkedList;

public class node {
    char letter;
    node parent;
    LinkedList<songPtr> songs;
    LinkedList<node> children;

    public node(char letter_, node parent_) {
	this.letter = letter_;
	this.parent = parent_;
	this.songs = new LinkedList<songPtr>();
	this.children = new LinkedList<node>();
    }

    public node(char letter_) {
	this(letter_, null);
    }

    public node addNode(char letter) {
	return addNode(letter, null);
    }

    /*
     * This finds if a node is present with the character
     * and if it is present then it directly returns it
     * Otherwise it creates a new one and returns the node
     * It also places the node in respective position
     */
    public node addNode(char letter, node parent) {
	node temp = getChildAt(letter);
	if (temp == null) {
	    int idx;
	    for (idx = 0; idx < children.size(); idx++)
		if (letter < children.get(idx).letter)
		    break;
	    temp = new node(letter, parent);
	    children.add(idx, temp);
	}
	return temp;
    }

    /*
     * This function checks if a node is present. 
     * if it is present then returns the node
     * and a null otherwise
     */
    public node getChildAt(char letter) {
	node temp = null;
	int flag = 0;
	for (int idx = 0; idx < children.size() && flag == 0; idx++)
	{
	    if (letter == children.get(idx).letter) {
		temp = children.get(idx);
		flag = 1;
	    }
	    if( letter < children.get(idx).letter)
		flag = 1;
	}
	return temp;
    }

    public node getParent() {
	return this.parent;
    }

    public void addSong(songPtr song) {
	songs.add(song);
    }

    public songPtr[] getSongs() {
	return (songPtr[]) this.songs.toArray();
    }
}
