package iitm.apl.player.ui;

import iitm.apl.player.Song;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PlayListMakerDialog extends JDialog {
    private static final long serialVersionUID = -2891581224281215999L;
    private Vector<Song> songList;
    private PlaylistTableModel playlistModel;
    private int[][] sackValue;
    private int scale = 10;
    private int[] weight;

    public PlayListMakerDialog(JamPlayer parent) {
	super();
	songList = parent.getSongList();

	Container pane = getContentPane();
	pane.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
	pane.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
	pane.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
	pane.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
	// Create the dialog window
	GroupLayout layout = new GroupLayout(getContentPane());
	getContentPane().setLayout(layout);

	// Create the slider
	JLabel label0 = new JLabel("Play List Length: ");
	int totalTime = getTotalLength(songList);
	final JSlider contentSlider = new JSlider(0, totalTime, totalTime);
	final JLabel timeLabel = new JLabel();
	timeLabel.setText(String.format("%d:%02d:%02d", (totalTime / 3600),
		(totalTime / 60) % 60, totalTime % 60));
	contentSlider.addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent arg0) {
		JSlider contentSlider = (JSlider) arg0.getSource();
		int time = contentSlider.getValue();
		timeLabel.setText(String.format("%d:%02d:%02d", (time / 3600),
			(time / 60) % 60, time % 60));
	    }
	});
	// TODO: Connect to handler that will populate PlaylistTableModel
	// appropriately.
	JButton makeButton = new JButton("Make!");
	makeButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// System.out.println(contentSlider.getValue());
		createPlaylist(contentSlider.getValue() / scale);

	    }
	});
	JButton savePlaylist = new JButton("Save Playlist");
	JButton getPlaylist = new JButton("Get Previous List");

	savePlaylist.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		saveSongs();
	    }
	});

	getPlaylist.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		getSongs();
	    }
	});

	playlistModel = new PlaylistTableModel();
	playlistModel.set(songList);
	JTable playlistTable = new JTable(playlistModel);
	JScrollPane playlistPane = new JScrollPane(playlistTable);

	layout.setVerticalGroup(layout
		.createSequentialGroup()
		.addGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(label0)
				.addComponent(contentSlider)
				.addComponent(timeLabel)
				.addComponent(makeButton)
				.addComponent(savePlaylist)
				.addComponent(getPlaylist)).addContainerGap()
		.addComponent(playlistPane));

	layout.setHorizontalGroup(layout
		.createParallelGroup()
		.addGroup(
			layout.createSequentialGroup().addComponent(label0)
				.addComponent(contentSlider)
				.addComponent(timeLabel)
				.addComponent(makeButton)
				.addComponent(savePlaylist)
				.addComponent(getPlaylist))
		.addComponent(playlistPane));
	this.pack();
    }

    /*
     * TODO: Take all the songs from the list and save them into a specific file
     * Use object serialisation
     */
    public static void saveSongs() {

    }

    /*
     * TODO: Read all the songs from the file and get them into an array Then
     * check if the songs are present in the array or not If that is present
     * then add them into the list Otherwise don't add
     */
    public static void getSongs() {
	Vector<Song> temp = LibraryTableModel.songsTrie.search("");
    }

    public static int getTotalLength(Vector<Song> songs) {
	int time = 0;
	for (Song song : songs)
	    time += song.getDuration();
	return time;
    }

    private Vector<Song> createPlaylist(int MaxTime) {
	Vector<Song> songs = new Vector<Song>();
	// int minTime = minSongLength(songList);
	sackValue = new int[songList.size() + 1][MaxTime + 1];
	weight = new int[songList.size()];
	for (int i = 0; i < songList.size(); i++)
	    weight[i] = songList.get(i).getDuration() / scale;

	LinkedList<Integer> indices = knapsack(sackValue, MaxTime + 1);
	System.out.println(indices);

	return songs;

    }

    private LinkedList<Integer> knapsack(int[][] sackValues, int maxTime) {
	int i, w;
	LinkedList<Integer> idx = new LinkedList<Integer>();
	for (w = 0; w < maxTime; w++)
	    sackValues[0][w] = 0;
	for (i = 0; i < songList.size(); i++)
	    sackValues[i][0] = 0;
	for (i = 1; i < songList.size(); i++) {
	    for (w = 0; w < maxTime; w++) {
		if (weight[i] <= w) {
		    if (weight[i] + sackValues[i - 1][w - weight[i]] > sackValues[i - 1][w])
			sackValues[i][w] = weight[i]
				+ sackValues[i - 1][w - weight[i]];
		    else
			sackValues[i][w] = sackValues[i - 1][w];
		} else
		    sackValues[i][w] = sackValues[i - 1][w];
	    }
	}
	i = songList.size() - 1;
	w = maxTime - 1;
	while (i > 0 && w > 0) {
	    if (sackValues[i][w] != sackValues[i - 1][w]) {
		idx.add(i);
		w = w - weight[i];
		i--;
	    } else
		i--;
	}

	return idx;
    }

    private int minSongLength(Vector<Song> Songs) {
	int minTime = 0;
	for (Song song : Songs)
	    if (minTime > song.getDuration() / scale)
		minTime = song.getDuration() / scale;

	return minTime;
    }
}
