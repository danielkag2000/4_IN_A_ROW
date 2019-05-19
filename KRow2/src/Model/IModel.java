package Model;

public interface IModel {

	/**
	 * get the board
	 * @return the board
	 */
	int[][] getData();
	
	void redTurn(int place);
	
	void yellowTurn(int place);
}
