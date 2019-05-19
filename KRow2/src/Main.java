import Control.Controller;
import Model.BotModel;
import Model.PvpModel;
import View.GameView;

public class Main {

	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		//int row = in.nextInt(), col = in.nextInt(), sequence = in.nextInt();
		int row = 8, col = 8, sequence = 4;
		
		GameView ui = new GameView(row, col, 0);
		PvpModel model = new PvpModel(row, col, sequence);
		//BotModel model = new BotModel(row, col, sequence);
		Controller c = new Controller(ui, model);
		
		ui.addObserver(c);
		model.addObserver(c);
		
		ui.start();
	}
}
