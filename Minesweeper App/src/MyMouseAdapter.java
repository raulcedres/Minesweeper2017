import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter 
 {
	
	int counter = 15;
	public static  Mines MINE = new Mines(15);
	public void mousePressed(MouseEvent e) 
	 {
		switch (e.getButton()) 
		 {
			case 1:        //Left mouse button
			Component c = e.getComponent();
			
			while (!(c instanceof JFrame)) 
			 {
				c = c.getParent();
				if (c == null) 
			     {
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
	    
			case 2:        //Right mouse button paints color red if user thinks it is a mine
			Component d = e.getComponent();
			while (!(d instanceof JFrame)) 
			{
				d = d.getParent();
				if (d == null)
				{
					return;
				}
			}
			
			JFrame Right_Click_Event_Frame = (JFrame) d;
			MyPanel my_Panel = (MyPanel) Right_Click_Event_Frame.getContentPane().getComponent(0);
			Insets my_Insets = Right_Click_Event_Frame.getInsets();
			int x_1 = my_Insets.left;
			int y_1 = my_Insets.top;
			e.translatePoint(-x_1, -y_1);
			int X1 = e.getX();
			int Y1 = e.getY();
			my_Panel.x = X1;
			my_Panel.y = Y1;
			my_Panel.mouseDownGridX = my_Panel.getGridX(X1, Y1);
			my_Panel.mouseDownGridY = my_Panel.getGridY(X1, Y1);
			my_Panel.repaint();
			break;
		    default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	public void mouseReleased(MouseEvent e) 
	 {
		switch (e.getButton()) 
		{
		case 1:        //Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) 
			 {
				c = c.getParent();
				if (c == null) 
				 {
					 return;
				 }
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
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						if(gridX >= 0 && gridX <= 8 && gridY >= 0 && gridY <= 8)
						 { if(MINE.CellCompare(gridX, gridY)){ // Paints a grid black if a mine is on the target cell.

									myPanel.colorArray[gridX][gridY] = Color.BLACK;
									myPanel.GameLost = true;
									myPanel.repaint();
									
									JOptionPane.showMessageDialog(myFrame, "You touched a mine","GAME OVER!", JOptionPane.ERROR_MESSAGE);
									
									System.exit(0);
						 } else{
							if(MINE.Neighborhood(gridX, gridY))
							 {
								// Verifies how many mines are around the clicked cell
								int counter = MINE.Neigborhoodcount(gridX, gridY);

								Color newColor = Color.GRAY;
								myPanel.colorArray[gridX][gridY] = newColor;
								myPanel.countMines[gridX][gridY] = counter;
								myPanel.counter++;
								myPanel.repaint();
							} 

							
							// Paints grid as gray when clicked on an empty spot.
							else if(!MINE.CellCompare(gridX, gridY))
							 {
								 myPanel.RevealNextCell(gridX, gridY);

							 }				
						 }
					}
				}
			}
		}
				break;
			//On the grid other than on the left column and on the top row:
//			if(gridX >= 0 && gridX <= 8 && gridY >= 0 && gridY <= 8)
//			 {
//				if(MINE.Neighborhood(gridX, gridY))
//				 {
//					// Verifies how many mines are around the clicked cell
//					int counter = MINE.Neigborhoodcount(gridX, gridY);
//
//					Color newColor = Color.GRAY;
//					myPanel.colorArray[gridX][gridY] = newColor;
//					myPanel.countMines[gridX][gridY] = counter;
//					myPanel.counter++;
//					myPanel.repaint();
//				} 
//
//				
//				// Paints grid as gray when clicked on an empty spot.
//				else if(!MINE.CellCompare(gridX, gridY))
//				 {
//					 myPanel.RevealNextCell(gridX, gridY);
//
//				 }
//				
//				// Paints a grid black if a mine is on the target cell.
//				
//				if(MINE.CellCompare(gridX, gridY))
//				 {
//					myPanel.colorArray[gridX][gridY] = Color.BLACK;
//					myPanel.GameLost = true;
//					myPanel.repaint();
//					
//					JOptionPane.showMessageDialog(myFrame, "You touched a mine","GAME OVER!", JOptionPane.ERROR_MESSAGE);
//					
//					System.exit(0);
//			}	
//		 }
			
//			break;
	
			case 3:        //Right mouse button
			Component d = e.getComponent();
			while (!(d instanceof JFrame))
			 {
				 d = d.getParent();
				 if (d == null) 
				  {
					  return;
				  }
			 }
			
			JFrame Right_Click_Event_Frame = (JFrame)d;
			MyPanel my_Panel = (MyPanel) Right_Click_Event_Frame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets my_Insets = Right_Click_Event_Frame.getInsets();
			int x_1 = my_Insets.left;
			int y_1 = my_Insets.top;
			e.translatePoint(-x_1, -y_1);
			int X1 = e.getX();
			int Y1 = e.getY();
			my_Panel.x = X1;
			my_Panel.y = Y1;
			int grid_X = my_Panel.getGridX(X1, Y1);
			int grid_Y = my_Panel.getGridY(X1, Y1);

			my_Panel.repaint();

			if(grid_X >= 0 && grid_X <= 8 && grid_Y >= 0 && grid_Y <= 8) 
			 {
				if (my_Panel.counter>=71) 
				 {
					 JOptionPane.showMessageDialog(null, "You actually won at Minesweeper. Not bad.");
					 System.exit(0);
				 }

				if(my_Panel.colorArray[grid_X][grid_Y].equals(Color.WHITE))
				 {
					if(counter>0)
					 {
						 my_Panel.colorArray[grid_X][grid_Y] = Color.RED;
						 my_Panel.repaint();
						 counter--;
					 }
					
					else if(counter == 0)
					 {
						//Limits the amount of flags to 10.
						JOptionPane.showMessageDialog(d, "Maximum amount of flags used.");
					 }
				 }

					else if(my_Panel.colorArray[grid_X][grid_Y].equals(Color.BLACK) || my_Panel.colorArray[grid_X][grid_Y].equals(Color.GRAY))
				     {
					// Do nothing.
				     }

			    else 
				 {
					my_Panel.colorArray[grid_X][grid_Y] = Color.WHITE;
					my_Panel.repaint();
					
				    if(counter<10)
				     {	
					    counter++;	
					 }
				 }
			 }

			break;
			default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
			 
		}
	 }

	public void Mines()
	 {
		MINE.setMinesCoordinates();
	 }
 }


