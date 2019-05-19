package Bot;

import Model.Board;
import Other.Const;

import java.util.ArrayList;
import java.util.List;

public class BoardState implements State {
	private int operator;
	private Board board;
	private int turn;
	private int h;

	public BoardState(int operator, Board board, int turn) {
		this.operator = operator;
		this.board = board;
		this.turn = turn;
	}

	public BoardState(Board board, int turn) {
		this.operator = 0;
		this.board = board;
		this.turn = turn;
	}

	@Override
	public List<State> operator() {
		List<State> list = new ArrayList<State>();
		if (this.board.checkWin() == -1) {
			for (int i = 0; i < this.board.getBoard()[0].length; i++) {
				Board board2 = new Board(this.board.getBoard()[0].length, this.board.getBoard().length, this.board.getSequnce());
				Board.copyBoard(this.board, board2);
				boolean f = board2.move(turn, i + 1);
				int p = turn;
				if (f == false)
					continue;
				p = turn == Const.YELLOW ? Const.RED : Const.YELLOW;
				list.add(new BoardState(i, board2, p));
			}
			return list;
		}
		return null;

	}

	@Override
	public boolean equals(State s) {
		Board board2 = null;
		try {
			board2 = (Board) s;
		} catch (Exception e) {
			return false;
		}
		for (int i = 0; i < this.board.getBoard().length; i++) {
			for (int j = 0; j < this.board.getBoard()[0].length; j++) {
				if (this.board.getBoard()[i][j] != board2.getBoard()[i][j])
					return false;
			}
		}
		return true;
	}

	@Override
	public void setH() {
		if (this.board.checkWin() == Const.YELLOW) {
			this.h = Integer.MAX_VALUE;
		} else if (this.board.checkWin() == Const.RED) {
			this.h = Integer.MIN_VALUE;
		} else {
			int val = 0;
			for (int i = 1; i < this.board.getSequnce(); i++) {
				if (this.board.CheckAlmost() == Const.YELLOW)
					val += Math.pow(10, i - 1) * (this.board.checkNInARow(Const.YELLOW, i)) * 2;
				else
					val += Math.pow(10, i - 1) * (this.board.checkNInARow(Const.YELLOW, i));

				if (this.board.CheckAlmost() == Const.RED)
					val -= Math.pow(10, i - 1) * (this.board.checkNInARow(Const.RED, i)) * 2;
				else
					val -= Math.pow(10, i - 1) * (this.board.checkNInARow(Const.RED, i));

			}
			this.h = val;
		}
	}

	@Override
	public int h() {
		return h;
	}

	@Override
	public int getOperation() {
		return operator;
	}

}

