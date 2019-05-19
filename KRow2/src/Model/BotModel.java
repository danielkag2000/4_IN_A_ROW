package Model;

import Observe.Observable;
import Other.Const;

public class BotModel extends Observable implements IModel {

	private Bot bot;
	private Board board;

	public BotModel(int col, int row, int sequence) {
		this.board = new Board(col, row, sequence);
		this.bot = new Bot();
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
				return;
			} else {
				int winner = this.board.checkWin();
				if (winner == Const.RED || winner == Const.YELLOW) {
					this.notifyObservers(winner);
					return;
				}
			}
		}
		yellowTurn(-1);
	}

	@Override
	public void yellowTurn(int place) {
		int play = this.bot.playTurn(this.board);
		if (board.move(Const.YELLOW, play)) {
			if (this.board.draw()) {
				this.notifyObservers(Const.DRAW);

			} else {
				int winner = this.board.checkWin();
				this.notifyObservers(winner);
			}
		}
	}

}
