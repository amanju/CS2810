/*
 * A .Manjunath
 * CS10B001
 */

import java.util.Scanner;

public class NQueens {
	private static int[] positions;

	/**
	 * Read 'N' from terminal and generate a valid arrangement of N queens on an
	 * NxN chess board.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		positions = new int[N];
		placeQueens(N);
		for (int i = 0; i < N; i++)
			System.out.println(positionString(i, positions[i]));
		printBoard(positions);
	}

	/**
	 * Find a valid arrangement of n queens on a nxn chess-board. Hint: Use a
	 * recursive solution
	 * 
	 * @param n
	 * @return
	 */
	private static int[] placeQueens(int n) {
		int i = positions.length - 1;
		while (i >= 0) {
			positions[n - 1] = i;
			if (placeQueens(positions, n - 1)) {
				if (n == 1)
					return positions;
				else if (placeQueens(n - 1) != null)
					return positions;
			}
			positions[n - 1] = 0;
			i--;
		}
		return null;
	}

	/**
	 * Place queen number 'row' so as not to conflict with any of the previous
	 * positions Return false if no such placement is possible
	 * 
	 * @param positions
	 * @param row
	 * @return
	 */
	private static boolean placeQueens(int[] positions, int row) {
		int N = positions.length;
		int i, j;
		for (i = row + 1; i <= N - 1; i++)
			if (positions[row] == positions[i])
				return false;

		for (i = row + 1, j = positions[row] + 1; i <= N - 1 && j <= N - 1; i++, j++)
			if (positions[i] == j)
				return false;

		for (i = row + 1, j = positions[row] - 1; i <= N - 1 && j >= 0; i++, j--)
			if (positions[i] == j)
				return false;
		return true;
	}

	/**
	 * Print the position of a piece in i-th row and j-th column in chess
	 * format.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	private static String positionString(int i, int j) {
		char ch = (char) (i + 97);
		String position = Character.toString(ch);
		return position + Integer.toString(j + 1);
	}

	/**
	 * Helper function that prints the position of all queens given the array
	 * 
	 * @param positions
	 */
	private static void printBoard(int[] positions) {
		int n = positions.length;

		System.err.print("XX|");
		for (int j = 0; j < n; j++) {
			System.err.format("%c|", 'a' + j);
		}
		System.err.println();
		for (int i = 0; i < n; i++) {
			System.err.format("%2d|", i + 1);
			for (int j = 0; j < n; j++) {
				if (j == positions[i])
					System.err.print("Q|");
				else
					System.err.print(" |");
			}
			System.err.println();
		}
	}
}
