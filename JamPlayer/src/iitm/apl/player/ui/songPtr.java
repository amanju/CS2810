package iitm.apl.player.ui;

import iitm.apl.player.Song;

public class songPtr {

    int index;
    Song song;

    public songPtr(Song song, int idx) {
	this.song = song;
	this.index = idx;
    }
}
