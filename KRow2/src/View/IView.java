package View;

public interface IView {

	/**
	 * display the board with the new data
	 * @param data the board
	 */
	void displayData(int[][] data);
	
	/**
	 * get the user command
	 * @return the command number
	 */
	int getUserCommand();
	
	void notifyWinner(int winner);
	
	void notifyDraw();
	
	void start();
}
