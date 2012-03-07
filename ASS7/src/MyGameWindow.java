import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyGameWindow {
	private Integer size=3;
	private int imgWidth;
	private int imgHeight;
	gameBoard game;
	private boolean set = false;

	gameBoard myGame;
	JFrame mainFrame;
	Container mainPane;
	JLabel statusLabel;
	JPanel gamePanel;
	JButton[][] buttons;

	BufferedImage player1Img;
	BufferedImage player2Img;

	public MyGameWindow() {
	}

	public void createBoardAndSetVisible() {
		mainFrame = new JFrame("TicTacToe");
		mainFrame.setMinimumSize(new Dimension(500, 500));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPane = mainFrame.getContentPane();
		// mainPane.

		// menubar initialization and adding components into it
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("Game");
		JMenu menu2 = new JMenu("Options");
		JMenuItem sizeItem = new JMenuItem("Board Size");
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem quitItem = new JMenuItem("Quit");
//		JMenu players = new JMenu("Players");
//		JMenuItem twoPlayer = new JMenuItem("2 Players");
//		JMenuItem singlePlayer = new JMenuItem("single Player");
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					newGame();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		quitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.dispose();
			}
		});
		sizeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sizeDialogBox();
			}
		});

/*		twoPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				set = false;
			}
		});

		singlePlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				set = true;
			}
		});
*/		menu1.add(newItem);
		menu1.add(quitItem);
		menu2.add(sizeItem);
//		players.add(twoPlayer);
//		players.add(singlePlayer);
//		menu2.add(players);
		menuBar.add(menu1);
		menuBar.add(menu2);

		// setting up the label
		statusLabel = new JLabel(
				"Welcome to Tic-Tac-Toe Game! Click New for new game.");
		// creating JPanel to contain the game board

		gamePanel = new JPanel();
		gamePanel.setMinimumSize(new Dimension(200, 200));
		mainPane.add(gamePanel, BorderLayout.CENTER);

		mainPane.add(statusLabel, BorderLayout.PAGE_END);
		mainPane.add(menuBar, BorderLayout.PAGE_START);

		mainFrame.pack();
		mainFrame.setVisible(true);

	}

	protected void sizeDialogBox() {
		int i = 1, j = 0;
		Integer[] sizes = new Integer[100];
		while (i < 101)
			sizes[j++] = i++;
		JOptionPane pane = new JOptionPane();
		size = (Integer) JOptionPane.showInputDialog(mainPane,
				"Enter the size of Board", "Size Input",
				JOptionPane.PLAIN_MESSAGE, null, sizes, 3);
		mainPane.remove(pane);
		gamePanel.setLayout(new GridLayout(size, size));
	}

	private void setAndResizeImage() throws IOException {
		imgWidth = mainFrame.getWidth() / size;
		imgHeight = mainFrame.getHeight() / size;

		BufferedImage temp = ImageIO.read(new File("./index.jpeg"));
		player1Img = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D img = (Graphics2D) player1Img.createGraphics();
		img.drawImage(temp, 0, 0, imgWidth, imgHeight, null);
		img.dispose();

		temp = ImageIO.read(new File("./s2.jpeg"));
		player2Img = new BufferedImage(imgWidth, imgHeight,
				BufferedImage.TYPE_INT_ARGB);
		img = (Graphics2D) player2Img.createGraphics();
		img.drawImage(temp, 0, 0, imgWidth, imgHeight, null);
		img.dispose();
	}

	private void newGame() throws IOException {
		gamePanel.removeAll();
		setAndResizeImage();

		myGame = new gameBoard(size);
		myGame.AIset = set;
		buttons = new JButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final int ri = i;
				final int cj = j;
				buttons[i][j] = new JButton("");
				buttons[i][j].setMaximumSize(new Dimension(40, 40));
				buttons[i][j].addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							makeMove(ri, cj);
						}
					}

				});
				gamePanel.add(buttons[i][j]);
			}
		}
		mainFrame.pack();
	}

	private void showPlayerImage(int i, int j) {
		if (myGame.getPlayer() == 1) {
			buttons[i][j].setIcon(new ImageIcon(player1Img));
			buttons[i][j].setEnabled(true);
		} else {
			buttons[i][j].setIcon(new ImageIcon(player2Img));
			buttons[i][j].setEnabled(true);
		}

	}

	private void makeMove(int i, int j) {
		if (myGame.getCellValue(i, j) == 0) {
			int p = myGame.getPlayer();
			myGame.setCellValue(i, j);
			showPlayerImage(i, j);
			if (!set) {
				if (myGame.isStatus() == true)
					statusLabel.setText("Player " + myGame.getPlayer()
							+ " wins the game!");
				if (p == 1)
					myGame.setPlayer(2);
				else
					myGame.setPlayer(1);
			}
		} else {
			if (myGame.isStatus() == true) {
				if (!myGame.AIwin)
					statusLabel.setText("you won the game!");
				else
					statusLabel.setText("you won the game!");
	
			}else{
			}
		}

	}
}
