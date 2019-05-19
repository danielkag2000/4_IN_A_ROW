package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Observe.Observable;

import javax.swing.JButton;

import Other.Const;

public class GameView extends Observable implements ActionListener, IView{
	private int player;
	private int rows, cols;
	private int mode;
	private Frame frame;
	private boolean isGameEnded = false;
	private boolean botFirst;

	public GameView(int row, int col, int mode) {
		this.rows = row;
		this.cols = col;
		this.mode = mode;
	}

	@Override
	public void displayData(int[][] data) {
		this.frame.displayData(data);
	}


	@Override
	public int getUserCommand() {
		if(mode == 0) {
			int temp = this.player;
			this.player = this.player == Const.RED ? Const.YELLOW : Const.RED;
			return temp;
		}
		if (botFirst) {
			botFirst = false;
			return Const.YELLOW;
		}
		return Const.RED;
	}

	private void addListeners() {
		JButton jb[][] = frame.getButtons();
		for(int i = 0 ; i < jb.length ; i ++) {
			for(int j = 0 ; j < jb[0].length ; j ++) {
				jb[i][j].addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isGameEnded) {
			return;
		}
		
		JButton source = (JButton) e.getSource();

		int place = playerMove(source);
		if(frame.getButtons()[0][place].getIcon() == Const.imageIcon)
			this.notifyObservers(place);
	}

	private int playerMove(final JButton source) {
		for(int i = 0 ; i < frame.getButtons().length ; i++) {
			for(int j = 0 ; j < frame.getButtons()[0].length ; j++) {
				if(frame.getButtons()[i][j].equals(source)) {
					return j;
				}
			}
		}
		return -1;
	}

	@Override
	public void notifyWinner(int winner) {
		isGameEnded = true;
		System.out.println("The Winner is: " + winner);
	}

	@Override
	public void notifyDraw() {
		isGameEnded = true;
		System.out.println("It's a draw!!!");
	}

	
	
	@Override
	public void start() {
		this.player = Const.RED;
		this.frame = new Frame(this.rows, this.cols);
		addListeners();
		
		if (mode == 1) {
			Random rnd = new Random();
			this.player = rnd.nextInt(2) == 1 ? Const.RED : Const.YELLOW;
			
			//if (this.player == Const.YELLOW) {
				botFirst = true;
				this.notifyObservers(-1);
				this.player = Const.RED;
			//}
		}
	}

}
