package iitm.apl.MazeGenerator;

import java.util.Vector;
import java.util.HashSet;

/**
 *
 * @author Sachin and Pankaj
 */
public abstract class Maze implements StringRepresentable {
	protected int rows ;			// number of rows in maze
	protected int cols ;			// number of columns in maze
	private String state ;		// indicates the current state of your maze in string format
    
	/**
	 * 
	 * @param rows indicates number of rows in maze
	 * @param cols indicates number of columns in maze
	 */
	// constructor to create an instance of maze with rows and columns
    public Maze(int rows, int cols) {
    	this.rows = rows ;
    	this.cols = cols ;
        this.state = new Integer(rows).toString() + " " + new Integer(cols).toString() + " $"; 
    }

    // return current status of maze
    public String getStringRepresentation() {
        return state ;
    }

    // return current status of maze
    public String toString() {
    	return state ;
    }
    
    /**
     * 
     * @param room1
     * @param room2
     */
    // to break wall between room1 and room2
    protected final void breakWall(int room1, int room2) {
    	// rooms must be between 1 and rows * columns
    	if ( room1 <= 0 || room1 > rows * cols || room2 <= 0 || room2 > rows * cols ) {
    		System.out.println("Room(s) out of range") ;
    	}
    	// rooms must be horizontally or vertically adjacent
    	else if ( Math.abs(room1 - room2) != 1 && Math.abs(room1 - room2) != cols ) {
    		System.out.println("Rooms " + room1 + ", " + room2 + " " + "are not adjacent") ;
    	}
    	// now break the wall
    	else {
    		this.state = this.state.replace('$', ' ') ;
    		this.state += new Integer(room1).toString() + " " + new Integer(room2).toString() + " $" ;
    	}
    }
    
    // must be implemented by subclass
    public abstract void generate(Recorder recorder) ;
    
    // check maze generated is valid or not
    public final boolean validMaze() {
    	Vector<HashSet<Integer>> sets = new Vector<HashSet<Integer>>(rows * cols) ;
    	for ( int i = 1 ; i <= rows * cols ; i++ ) {
    		HashSet<Integer> hashset = new HashSet<Integer>() ;
    		hashset.add(new Integer(i)) ;
    		sets.add(i - 1, hashset) ;
    	}
    	
    	String[] str = this.state.split("[ $]+") ;
    	int v1, v2 ;
    	
    	for ( int i = 2 ; i < str.length ; i++ ) {
    		v1 = Integer.parseInt(str[i]) ;
    		v2 = Integer.parseInt(str[++i]) ;
    		
    		HashSet<Integer> set1 = null, set2 = null ;    		
    		for ( HashSet<Integer> x : sets ) {
    			if ( x.contains(v1) == true )
    				set1 = x ;
    			
    			if ( x.contains(v2) == true )
    				set2 = x ;
    		}
    		
    		if ( set1.equals(set2) ) {
    			System.out.println("Multiple paths detected in your maze") ;
    			return false ;
    		}
    		else {
    			if ( set1.size() < set2.size() ) {
    				set2.addAll(set1) ;
    				set1.clear() ;
    			}
    			else {
    				set1.addAll(set2) ;
    				set2.clear() ;
    			}
    		}
    	}
    	
    	int components = 0 ;
    	for ( HashSet<Integer> x : sets ) {
    		if ( x.isEmpty() )
    			continue ;
    		else
    			components++ ;
    	}
    	if ( components == 1 )
    		return true ;
    	
    	return false ;
    }
}
