package View;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Other.Const;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	private JButton jb[][];
	private JPanel jp;

	public Frame(int row, int col) {
		setTitle("Four in a Row");
		setVisible(true);
		setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jp = new JPanel();
		jb = new JButton[row][col];
		GridLayout layout = new GridLayout(row, col);
		jp.setLayout(layout);
		
		for(int i = 0 ; i < row ; i++) {
			for(int j = 0 ; j < col ; j++) {
				jb[i][j] = new JButton(Const.imageIcon);
				jp.add(jb[i][j]);
			}
		}
		add(jp);
		validate();
	}
	
	public void displayData(int[][] data) {
		int row, col;
		for(row = 0 ; row < data.length ; row++) {
			for(col = 0 ; col < data[0].length; col++) {
				int player = data[row][col];
				if(player == Const.RED) {
					jb[row][col].setIcon(new ImageIcon(Const.RED_PIC));
				}
				else if(player == Const.YELLOW){
					jb[row][col].setIcon(new ImageIcon(Const.YELLOW_PIC));
				}
				else
					jb[row][col].setIcon(Const.imageIcon);
			}
		}
		validate();
	}
	
	public JButton[][] getButtons(){
		return this.jb;
	}
}
