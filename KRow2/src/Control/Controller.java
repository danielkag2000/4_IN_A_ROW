package Control;

import Observe.Observable;
import Observe.Observer;

import Model.IModel;
import View.IView;

import Other.Const;

/**
 * the controller of the game
 */
public class Controller implements Observer {
	private IView ui;		// the view
	private IModel model;	// the model

	public Controller(IView ui, IModel model) {
		this.ui = ui;
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == ui) {
			int place = (Integer)arg;
			giveCommandToModdel(place);
			return;
		}
		
		if (o == model) {
			ui.displayData(model.getData());
			
			int winner = (Integer)arg;
			if (winner == Const.RED || winner == Const.YELLOW) {
				ui.notifyWinner(winner);
			}
			
			if (winner == Const.DRAW) {
				ui.notifyDraw();
			}
		}
	}

	/**
	 * give a command to the model after the view notified
	 * @param place 
	 */
	private void giveCommandToModdel(int place) {
		int input = ui.getUserCommand();
		switch (input) {

		case Const.YELLOW:
			model.yellowTurn(place);
			break;
			
		case Const.RED:
			model.redTurn(place);
			break;
		
		case Const.BOT_START:
			model.yellowTurn(place);
		}
	}
}
