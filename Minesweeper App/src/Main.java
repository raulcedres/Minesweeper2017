//Dariel J. Ramos
//dariel.ramos@upr.edu
//Raul G. Cedres
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setSize(400, 400); 
		myFrame.setLocationRelativeTo(null);// centra el minesweeper
		
		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myMouseAdapter.Mines();
		myFrame.setVisible(true);
	}
}