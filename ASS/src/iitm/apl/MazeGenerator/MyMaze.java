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

	private ArrayList<Integer> trail = new ArrayList<Integer>();// to store the
																// rooms visited
	private ArrayList<Integer> temp = new ArrayList<Integer>();// to store
																// temporarily
																// rooms around
																// a given
	// room in the maze
	private ArrayList<Integer> subTrail = new ArrayList<Integer>();// to store
																	// rooms
																	// visited
																	// but have
																	// one

	Random rand = new Random();

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
		int cells = this.rows * this.cols;
		int cell = rand.nextInt(cells - 1) + 1;
		subTrail.add(cell);
		trail.add(cell);
		visit(cell, recorder);
	}

	/*
	 * this function visits room 'cell' and visits recursively the cells which
	 * are valid around it(not visited and rooms with non zero adjacent cells)
	 */
	private int visit(Integer cell, Recorder recorder) {
		temp.clear();
		validAdjacentCells(cell);
		int size = temp.size();
		while (size > 0) {
			int j = temp.get(rand.nextInt(size));
			trail.add(j);
			subTrail.add(j);
			breakWall(j, cell);
			recorder.takeSnap(this);
			visit(j, recorder);
			temp.clear();
			validAdjacentCells(cell);
			size = temp.size();
		}
		subTrail.remove(cell);
		return cell;
	}
}