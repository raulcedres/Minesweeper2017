//import java.awt.Color;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
                                                            
public class MyMouseAdapter extends MouseAdapter {
	
	
	public static PlaceOfMines mines = new PlaceOfMines(10);
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
			int[][] Mousebutton = new int[9][9];
			
			
			if(gridX >= 0 && gridX <= 8 && gridY >= 0 && gridY <= 8)
			 {
				if(mines.Nearby_Mines(gridX, gridY))
				 {
					// Verifies how many mines are around the target cell clicked.
					//int counter = mines.Nearby_Mines_Counter(gridX, gridY);

					Color newColor = Color.GRAY;
					myPanel.colorArray[gridX][gridY] = newColor;
					//myPanel.NearbyMines[gridX][gridY] = counter;
					myPanel.counter++;
					myPanel.repaint();
				} 

				
				// Paints grid as gray when clicked on an empty spot.
				else if(!mines.Coordinate_Comparision(gridX, gridY))
				 {
					 myPanel.NextToBlock_Display(gridX, gridY);

				 }
				
				// Paints a grid black if a mine is on the target cell.

				if(mines.Coordinate_Comparision(gridX, gridY))
				 {
					
					Color newColor = Color.BLACK;
					myPanel.colorArray[gridX][gridY] = newColor;
					myPanel.repaint();
					
					JOptionPane.showMessageDialog(myFrame,
							"You clicked on a mine, you lose!",
							"You lose!",
							JOptionPane.ERROR_MESSAGE);
					myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
				
				
					System.exit(0);

				}

			}

		case 3:		//Right mouse button   (Hacer lo de la bandera)
		
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}