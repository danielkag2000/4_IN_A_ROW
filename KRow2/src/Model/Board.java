package Model;

import Other.Const;

public class Board {
	private int board[][];
	private int sequence;
	private int counter[];

	public Board(int row, int col, int sequence) {
		this.board = new int[row][col];
		this.counter = new int[col];
		this.sequence = sequence;
		for (int i = 0; i < col; i++) {
			counter[i] = row - 1;
		}
	}

	public int checkWin() {
		for (int i = 0; i < this.board[0].length; i++)
			for (int j = 0; j < this.board.length; j++) {
				if (getWin(Const.RED, i, j)) {
					return Const.RED;
				}
				if (getWin(Const.YELLOW, i, j)) {
					return Const.YELLOW;
				}
			}
		return -1;
	}

	public boolean getWin(final int c, final int x, final int y) {
		// int count=0;
		if (this.board[y][x] == c) {
			if (x + this.sequence <= this.board[0].length) {
				boolean won = true;
				for (int k = 1; k < this.sequence; k++) {
					if (this.board[y][x + k] != c) {
						won = false;
						break;
					}
				}
				if (won)
					return true;
			}
			if (y + this.sequence <= this.board.length) {
				boolean won = true;
				for (int k = 1; k < this.sequence; k++) {
					if (this.board[y + k][x] != c) {
						won = false;
						break;
					}
				}
				if (won)
					return true;
			}

			if (x + this.sequence <= this.board[0].length) {
				boolean won = true;
				if (y + this.sequence <= this.board.length) {
					for (int k = 1; k < this.sequence; k++) {
						if (this.board[y + k][x + k] != c) {
							won = false;
							break;
						}
					}
					if (won)
						return true;
				}

				if (y - this.sequence >= 0) {
					for (int k = 1; k < this.sequence; k++) {
						if (this.board[y - k][x + k] != c) {
							won = false;
							break;
						}
					}
					if (won)
						return true;
				}
			}

			if (x - this.sequence >= 0) {
				boolean won = true;
				if (y + this.sequence <= this.board.length) {
					for (int k = 1; k < this.sequence; k++) {
						if (this.board[y + k][x - k] != c) {
							won = false;
							break;
						}
					}
					if (won)
						return true;
				}

				if (y - this.sequence >= 0) {
					for (int k = 1; k < this.sequence; k++) {
						if (this.board[y - k][x - k] != c) {
							won = false;
							break;
						}
					}
					if (won)
						return true;
				}

			}
		}

		return false;
	}

	public boolean canMove(int row, int col) {
		if ((row <= -1) || (col <= -1) || (row > this.board.length - 1) || (col > this.board[0].length - 1)) {
			return false;
		}
		return true;
	}

	public boolean draw() {
		if (checkWin() != -1) {
			return false;
		}

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean move(final int player, final int place) {
		if (place < this.board[0].length && place >= 0) {
			if (this.counter[place] < 0) {
				return false;
			}
			if (player == Const.RED) {
				if (this.counter[place] >= 0) {
					this.board[this.counter[place]][place] = Const.RED;
					this.counter[place]--;
				}
			}
			if (player == Const.YELLOW) {
				if (this.counter[place] >= 0) {
					this.board[this.counter[place]][place] = Const.YELLOW;
					this.counter[place]--;
				}
			}
			return true;
		} 
		return false;
	}

	public int[][] getBoard(){
		return this.board;
	}

	public int getSequnce() {
		return this.sequence;
	}

	public static void copyBoard(Board src, Board dest) {
		for (int i = 0; i < src.board.length; i++) {
			for (int j = 0; j < src.board[0].length; j++) {
				dest.board[i][j] = src.board[i][j];
			}
		}
	}

	public int CheckAlmost() {
		for (int i = 0; i < this.board.length; i++) {
			Board temp = new Board(this.board.length, this.board[0].length, this.sequence);
			
			copyBoard(this, temp);
			temp.move(Const.RED, i);
			if (checkWin() == Const.RED) {
				return Const.RED;
			}
			
			copyBoard(this, temp);
			temp.move(Const.YELLOW, i);
			if (checkWin() == Const.YELLOW) {
				return Const.YELLOW;
			}
		}
		return -1;
	}


	public int checkNInARow(final int playerSymbol, int n) {

		int times = 0;
		boolean flag = true;
		// Check for n consecutive checkers in a row, horizontally.
		for (int i = this.board.length - 1; i >= 0; i--) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (canMove(i, j + n - 1)) {
					flag = true;
					int counter = 1;
					for (int k = 1; k < n; k++)
						if (this.board[i][j] == this.board[i][j + k] && this.board[i][j] == playerSymbol && flag) {
							counter++;
						} else
							flag = false;
					if (counter == n - 1 && this.board[i][j] == playerSymbol)
						times++;
				}
			}
		}

		// Check for n consecutive checkers in a row, vertically.
		for (int i = this.board.length - 1; i >= 0; i--) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (canMove(i - (n - 1), j)) {
					flag = true;
					int counter = 1;
					for (int k = 1; k < n; k++)
						if (this.board[i][j] == this.board[i - k][j] && this.board[i][j] == playerSymbol && flag) {
							counter++;
						} else
							flag = false;
					if (counter == n - 1 && this.board[i][j] == playerSymbol)
						times++;
				}
			}
		}

		// Check for n consecutive checkers in a row, in descending diagonal.
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (canMove(i + n - 1, j + n - 1)) {
					flag = true;
					int counter = 1;
					for (int k = 1; k < n; k++)
						if (this.board[i][j] == this.board[i + k][j + k] && this.board[i][j] == playerSymbol && flag) {
							counter++;
						} else
							flag = false;
					if (counter == n - 1 && this.board[i][j] == playerSymbol)
						times++;
				}
			}
		}

		// Check for n consecutive checkers in a row, in ascending diagonal.
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (canMove(i - (n - 1), j + (n - 1))) {
					flag = true;
					int counter = 1;
					for (int k = 1; k < n; k++)
						if (this.board[i][j] == this.board[i - k][j + k] && this.board[i][j] == playerSymbol && flag) {
							counter++;
						} else
							flag = false;
					if (counter == n - 1 && this.board[i][j] == playerSymbol)
						times++;
				}
			}
		}

		return times;
	}
}
