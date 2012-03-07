package iitm.apl.KnightsTour;
import java.util.ArrayList;
import java.util.List;

public class Move {
	/* this class represents a position in chess board */
	int row, col;

	Move(int r, int c) {
		row = r;
		col = c;
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}

	// verifies if the position is valid board position
	// with respect to the boardSize mentioned in ChessBoard class
	public static boolean isValid(Move m) {
		if (m.row < 0 || m.row >= ChessBoard.getBoardSize() || m.col < 0
				|| m.col >= ChessBoard.getBoardSize())
			return false;
		return true;
	}

	public boolean isValid() {
		return isValid(this);
	}

	public Move[] reachableMoves() {
		List<Move> moves = new ArrayList<Move>();
		// The various moves are +/-2,+/-1,+/-1,+/-2
		for (int i = 0; i < 2; i++) {
			int rinc, linc;
			if( i == 0 )
			{
				rinc = 2; linc = 1;
			}
			else
			{
				rinc = 1; linc = 2;
			}
			
			for (int j = 0; j < 2; j++) {
				int row_ = row + ((j == 0) ? rinc : -rinc);
				for (int k = 0; k < 2; k++) {
					int col_ = col + ((k == 0) ? linc : -linc);
					Move m = new Move(row_, col_);
					if (m.isValid())
						moves.add(m);
				}
			}
		}

		return moves.toArray(new Move[0]);
	}

	public boolean equals(Object o) {
		Move m = (Move) o;
		if (m.row == row && m.col == col)
			return true;
		return false;

	}
}
