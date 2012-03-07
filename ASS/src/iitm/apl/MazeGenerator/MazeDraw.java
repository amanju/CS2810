package iitm.apl.MazeGenerator;

import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author Sachin and Pankaj
 */
public class MazeDraw extends genericDraw {
	MazeDraw() {
		super() ;
		setXY(25, 100) ;
	}
	
    @Override
    void draw(Graphics g, String str) {
    	String[] state = str.split("[ $]+") ;
        int rows = Integer.parseInt(state[0]) ;
        int cols = Integer.parseInt(state[1]) ;
        int size = 40 ;				// size of each room in pixels
        
        // draw all rooms in maze with black colour
        g.setColor(Color.BLACK) ;
        for ( int i = 0 ; i < rows ; i++ ) {
        	for ( int j = 0 ; j < cols ; j++ ) {
        		g.drawRect(x + j * size, y + i * size, size, size) ;
        	}        	
        }
        
        // open up entrance and exit
        g.setColor(Color.WHITE) ;
        g.drawLine(x, y, x + size, y) ;
        g.drawLine(x + cols * size, y + rows * size, x + cols * size, y + rows * size - size) ;
        
        // now draw (overwrite) rooms which are connected
        g.setColor(Color.GREEN) ;        
        for ( int i = 2 ; i < state.length ; i++ ) {
        	int rindex1, rindex2 ;
        	int cindex1, cindex2 ;
        	
        	// graphically indexing of rooms start with zero
        	int room1 = Integer.parseInt(state[i]) - 1 ;
        	rindex1 = room1 / cols ;		// get row of the room
        	cindex1 = room1 % cols ;		// get column of the room
        	g.fillRect(x + cindex1 * size + 1, y + rindex1 * size + 1, size - 1, size - 1) ;
        	
        	int room2 = Integer.parseInt(state[++i]) - 1 ;
        	rindex2 = room2 / cols ;
        	cindex2 = room2 % cols ;
        	g.fillRect(x + cindex2 * size + 1, y + rindex2 * size + 1, size - 1, size - 1) ;
        	
        	// rooms are horizontally adjacent 
        	if ( rindex1 == rindex2 ) {
        		// room2 is to the left of room1
        		if ( cindex1 > cindex2 )
        			g.drawLine(x + cindex1 * size, y + rindex1 * size, x + cindex1 * size, y + rindex1 * size + size) ;
        		// room1 is to the left of room2
        		else
        			g.drawLine(x + cindex2 * size, y + rindex1 * size, x + cindex2 * size, y + rindex1 * size + size) ;
        	}
        	// rooms are vertically adjacent
        	else {
        		// room2 is above room1
        		if ( rindex1 > rindex2 )
        			g.drawLine(x + cindex1 * size, y + rindex1 * size, x + cindex1 * size + size, y + rindex1 * size) ;
        		// room1 is above room2
        		else
        			g.drawLine(x + cindex1 * size, y + rindex2 * size, x + cindex1 * size + size, y + rindex2 * size) ;
        	}
        }
    }
}
