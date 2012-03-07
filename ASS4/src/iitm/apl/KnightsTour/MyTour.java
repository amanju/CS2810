/*A.Manjunath, CS10B001	Date: 29/1/2012
 * Program to find knight's tour on nXn chess board
 * */
package iitm.apl.KnightsTour;

import java.util.ArrayList;
import java.util.List;

public class MyTour extends ChessBoard {
	private List<Move> KnightMoves = new ArrayList<Move>();

	public static void main(String[] args) {
		initialise(new MyTour());
	}

	@Override
	/*
	 * This function uses Warnsdorff's Heuristic to generate a Knight's tour on
	 * a nXn chessboard
	 * 
	 * @param m :Initial position of knight on the chess board
	 * 
	 * @return KnightMoves Sequence of steps for knight's tour:
	 */
	List<Move> tour(Move m) {
		if (makeMove(m) == false) {// check if the move m is valid or not
			return null;
		} else {
			// if m is move valid add it to KnightMoves
			Move[] AMoves = m.reachableMoves();
			KnightMoves.add(m);
			// check whether are positions are exhausted on chess board
			if (KnightMoves.size() == Math.pow(ChessBoard.getBoardSize(), 2)) {
				return KnightMoves;
			}
			for (int i = 0; i < AMoves.length; i++) {

				for (int j = i + 1; j < AMoves.length; j++) {
					/*
					 * loop to find reachable position with minimum degree in
					 * AMoves for index i to AMoves.length
					 */
					if (AMoves[j].reachableMoves().length < AMoves[i]
							.reachableMoves().length) {
						Move temp = AMoves[i];
						AMoves[i] = AMoves[j];
						AMoves[j] = temp;
					}
				}
				// proceed with next move
				if (tour(AMoves[i]) != null)
					return KnightMoves;
			}
			// if none of the positions satisfy undo the move and go to previous
			// step
			KnightMoves.remove(KnightMoves.size() - 1);
			undoMove();
			return null;
		}
	}
}
