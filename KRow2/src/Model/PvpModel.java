package Model;

import Observe.Observable;
import Other.Const;

public class PvpModel extends Observable implements IModel {
	private Board board;

	public PvpModel(int col, int row, int sequence) {
		this.board = new Board(col, row, sequence);
	}

	@Override
	public int[][] getData() {
		return this.board.getBoard();
	}

	@Override
	public void redTurn(int place) {
		if (board.move(Const.RED, place)) {
			if (this.board.draw()) {
				this.notifyObservers(Const.DRAW);
			} else {
				int winner = this.board.checkWin();
				this.notifyObservers(winner);
			}
		}
	}

	@Override
	public void yellowTurn(int place) {
		if (board.move(Const.YELLOW, place)) {
			if (this.board.draw()) {
				this.notifyObservers(Const.DRAW);
			} else {
				int winner = this.board.checkWin();
				this.notifyObservers(winner);
			}
		}
	}

}
