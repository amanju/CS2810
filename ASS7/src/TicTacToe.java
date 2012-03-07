/*
 * A.Manjunath, CS10B001
 * */
import javax.swing.*;

public class TicTacToe {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MyGameWindow game = new MyGameWindow();
				game.createBoardAndSetVisible();
			}

		});
	}
}
