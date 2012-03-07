import java.util.Random;

public class gameBoard {
	private int size;
	private int player = 1;
	private int[][] cells;
	private boolean status = false;
	public boolean AIset = false;
	public boolean AIwin = false;

	public gameBoard(int n) {
		size = n;
		cells = new int[n][n];
		AIwin = false;
	}

	public void setPlayer(int p) {
		player = p;
	}

	public int getPlayer() {
		return player;
	}

	public void setCellValue(int i, int j) {
		cells[i][j] = player;
		setStatus(checkWin(i, j));
		if(AIset&&!status){
			AIRandom();
		}
	}

	public Integer getCellValue(int i, int j) {
		return cells[i][j];
	}

	private boolean checkWin(int i, int j) {
		int jRow = 0, iCol = 0, diagonalRight = 0, diagonalLeft = size - 1;
		int check = cells[i][j], temp;
		boolean win = true;

		while (iCol < size) {
			temp = check ^ cells[i][iCol++];
			if (temp != 0)
				win = false;
		}

		if (win)
			return win;
		win = true;
		while (jRow < size) {
			temp = check ^ cells[jRow++][j];
			if (temp != 0)
				win = false;
		}

		if (win)
			return win;
		win = true;
		while (diagonalRight < size) {
			temp = check ^ cells[diagonalRight][diagonalRight++];
			if (temp != 0)
				win = false;
		}

		if (win)
			return win;
		win = true;
		diagonalRight = 0;
		while (diagonalLeft >= 0) {
			temp = check ^ cells[diagonalLeft--][diagonalRight++];
			if (temp != 0)
				win = false;
		}

		return win;
	}

	private void AIRandom(){
		Random rand = new Random();
		int i=rand.nextInt()*size,j=rand.nextInt()*size;
		while(cells[i][j]!=0){
			i=rand.nextInt()*size;
			j=rand.nextInt()*size;
		}
		cells[i][j]=2;
		setStatus(checkWin(i,j));
	}
	
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public class cellCoords{
		int i;
		int j;
	}
}
