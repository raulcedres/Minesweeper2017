//import java.awt.Color;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
                                                            
public class MyMouseAdapter extends MouseAdapter {
		
	public static Mines MINES = new Mines(15); //Use methods of class 
	int counter=15;
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button

		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Soltamos click
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Outside frame, do nothing..
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {//Released the mouse button on the same cell where it was pressed
						if (MINES.Neighborhood(gridX, gridY)) {
							//Reveals count of bombs around
							int counter = MINES.Neigborhoodcount(gridX, gridY);
							Color newColor = Color.GRAY;
							myPanel.colorArray[gridX][gridY] = newColor;
							myPanel.countMines[gridX][gridY] = counter;
							myPanel.counter++;
							myPanel.repaint();
						} else {
							if(MINES.CellCompare(gridX, gridY)){
							myPanel.RevealNextCell(gridX, gridY);
							} else{
								Color newColor = Color.BLACK;
								myPanel.colorArray[gridX][gridY] = newColor;
								myPanel.repaint();
								
								JOptionPane.showMessageDialog(myFrame, "You pressed a mine!", "You Lost!", JOptionPane.ERROR_MESSAGE);
								myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							  }
						  }
					}
				}
			}
						myPanel.repaint();
						break;

		case 3:		//Right mouse button   (Hacer lo de la bandera)
		
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void Mines(){
		MINES.setMinesCoordinates();
	}
}