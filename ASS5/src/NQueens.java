/*
 * A. Manjunath, CS10B001 Date:3/2/2012
 * */
import java.util.ArrayList;
import java.util.Scanner;

public class NQueens {
	private static ArrayList<Integer> givenRows;// stores input rows
	private static ArrayList<Integer> givenColumns;// store input colnumn
													// numbers
	private static int[] positions;// actual positions of NQueens
	private static int n;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N, k;
		N = in.nextInt();
		k = in.nextInt();
		n = N;
		givenRows = new ArrayList<Integer>();
		givenColumns = new ArrayList<Integer>();
		positions = new int[N];
		for (int i = 0; i < k; i++) {
			givenRows.add(in.nextInt());
			givenColumns.add(in.nextInt());
			positions[givenRows.get(i) - 1] = givenColumns.get(i);// all
			// elements
			// are from 1 to
			// N
		}
		positions = queenPositions(N);
		// Print(positions);

	}

	private static void Print(int[] positions) {
		for (int i = 0; i < n; i++) {
			System.out.printf("%c%d ", i + 'a', positions[i]);
		}
		System.out.println();
	}

	private static int[] queenPositions(int N) {
		int i = 0, j;
		int k = 0;
		while (givenRows.contains(k + 1))
			k++;
		while (i < N && positions[k] <= N && i >= 0) {
			/* checks if queens is in ith row if so goes to next row */
			if (givenRows.contains(i + 1)) {
				/*
				 * this is the last term check, if so positions has valid
				 * positions print it and backtrace
				 */
				if (i == N - 1) {
					Print(positions);
					while (givenRows.contains(i + 1) == true)
						i--;
				} else
					i++;
			} else {
				for (j = positions[i] + 1; j <= N; j++) {
					positions[i] = j;
					if (checkPosition(i, j)) {
						if (i == N - 1)
							Print(positions);
						else
							break;
					}
				}
				if (j > N) {
					if (k != i)
						positions[i] = 0;
					i--;
					while (givenRows.contains(i + 1) == true)
						i--;
				} else
					i++;
			}
		}
		return positions;
	}

	private static boolean checkPosition(int row, int column) {
		int i, j, l;
		for (i = row + 1, j = column + 1, l = column - 1; i < n; i++, j++, l--) {

			if (positions[i] == column)
				return false;

			if (j <= n) {
				if (positions[i] == j)
					return false;
			}
			if (l > 0)
				if (positions[i] == l)
					return false;

		}
		for (i = row - 1, j = column + 1, l = column - 1; i >= 0; i--, j++, l--) {
			if (positions[i] == column)
				return false;

			if (j <= n) {
				if (positions[i] == j)
					return false;
			}
			if (l > 0)
				if (positions[i] == l)
					return false;
		}
		return true;
	}
}
