/*
 * A.Manjunath, CS10B001
 * Date: 10/2/2012
 * Maze Generator
 * */

package iitm.apl.MazeGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

public class MyMaze extends Maze {
	public MyMaze(int rows, int cols) {
		super(rows, cols);
	}

	private ArrayList<Integer> trail;// to store the rooms visited
	private ArrayList<Integer> temp;// to store temporarily rooms around a given
									// room in the maze
	private ArrayList<Integer> subTrail;// to store rooms visited but have one
										// or more valid adjacent rooms

	// creates a sublist of valid adjacent rooms of room 'cell' in the maze
	private void validAdjacentCells(int cell) {
		if ((cell - 1) % this.cols != 0)
			if (!trail.contains(cell - 1))
				temp.add(cell - 1);
		if (cell % this.cols != 0)
			if (!trail.contains(cell + 1))
				temp.add(cell + 1);
		if ((cell - cols) > 0)
			if (!trail.contains(cell - cols))
				temp.add(cell - cols);
		if ((cell + cols) <= this.rows * this.cols)
			if (!trail.contains(cell + cols))
				temp.add(cell + cols);
		return;
	}

	@Override
	public void generate(Recorder recorder) {
		Random rand = new Random();
		trail = new ArrayList<Integer>();
		subTrail = new ArrayList<Integer>();
		temp = new ArrayList<Integer>();
		// initialize both trails by adding first room
		int r = rand.nextInt(this.rows*this.cols-1)+1;
		trail.add(r);
		subTrail.add(r);
		int j;
		Integer cell = new Integer(0);

		/*
		 * For a given cell if it has any valid adjacent cell(s) then any one
		 * edge between them is selected randomly and broken else the cell is
		 * removed from sub Trail and another cell in sub Trail is selected
		 * randomly and the above operation is performed again
		 */

		while (trail.size() < this.rows * this.cols) {
			int size = subTrail.size();
			cell = subTrail.get((int) rand.nextFloat() * size);
			validAdjacentCells(cell);
			size = temp.size();
			if (size >= 0) {
				if (size == 0)
					subTrail.remove(cell);
				else {
					j = temp.get(rand.nextInt(size));
					trail.add(j);
					subTrail.add(j);
					breakWall(cell, j);
					recorder.takeSnap(this);
					temp.clear();
				}
			}
		}
	}
}