package iitm.apl.MazeGenerator;

/**
 * 
 * @author Sachin and Pankaj
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		int rows, cols;
		// make sure that only two arguments are passed
		if (args.length != 2) {
			rows = 20;
			cols = 20;
		}
		// two arguments passed are number of rows and columns in maze
		else {
			rows = Integer.parseInt(args[0]);
			cols = Integer.parseInt(args[1]);
		}
		// create an instance of MyMaze but refer it through type Maze
		Maze maze = new MyMaze(rows, cols);

		// create an instance of Recorder that will record your solution
		Recorder recorder = new Recorder();

		// register your maze with recorder (MazeDraw is graphical component of
		// your maze)
		recorder.addRef(maze, new MazeDraw());

		// record the initial state of your solution
		recorder.takeSnap(maze);

		// generate the solution and let recorder save it
		maze.generate(recorder);

		// check if your solution is correct or not
		if (maze.validMaze() == false)
			System.out.println("Solution is not correct");
		else
			System.out.println("Solution is correct");

		// you can see your solution graphically
		recorder.play();
	}
}
