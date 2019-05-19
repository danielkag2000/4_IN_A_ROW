package Model;

import Bot.BoardState;
import Bot.DecisionTree;
import Bot.State;
import Other.Const;

public class Bot {
	public Bot() {
		
	}
	
	public int playTurn(Board board) {
		State play = new BoardState(board, Const.YELLOW);
		State place = DecisionTree.getTheNextMove(play, 3);
		return place.getOperation();
	}
}
