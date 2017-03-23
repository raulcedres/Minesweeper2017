import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
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
			Component r = e.getComponent();
			while (!(r instanceof JFrame)) {
				r = r.getParent();
				if (r == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame)r;
			MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets1 = myFrame1.getInsets();
			int x3 = myInsets1.left;
			int y3 = myInsets1.top;
			e.translatePoint(-x3, -y3);
			int x4 = e.getX();
			int y4= e.getY();
			myPanel1.x = x4;
			myPanel1.y = y4;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x4, y4);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x4, y4);
			myPanel1.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
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
						//Released the mouse button on the same cell where it was pressed
						if ((gridX==0) && (gridY == 0)) {
							Color newColor = null;
							for (int i=0; i<9; i++){
							switch (generator.nextInt(5)) {
							case 0:
								newColor = Color.YELLOW;
								break;
							case 1:
								newColor = Color.MAGENTA;
								break;
							case 2:
								newColor = Color.BLACK;
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							}
							myPanel.colorArray[i+1][i+1] = newColor;
							myPanel.repaint();
							}
						} else {
						if ((gridX == 0) && (gridY>=1 && gridY<10)) {
							//On the left column and on the top row... do nothing
							Color newColor = null;
							for (int i=0; i<9; i++){
							switch (generator.nextInt(5)) {
							case 0:
								newColor = Color.YELLOW;
								break;
							case 1:
								newColor = Color.MAGENTA;
								break;
							case 2:
								newColor = Color.BLACK;
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							}
							myPanel.colorArray[i+1][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
							}
						} else {
							if ((gridX==0) && (gridY == 10)){
								Color newColor = null;
								for(int i=4; i<7; i++){
									for (int j=4; j<7; j++){
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.RED;
										break;
									case 1:
										newColor = Color.GREEN;
										break;
									case 2:
										newColor = Color.CYAN;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
								}
									myPanel.colorArray[i][j] = newColor;
									myPanel.repaint();
									}
								}	
							} else {
							//On the top row and columns>0
							if ((gridX>=1) && (gridY==0)){
								Color newColor = null;
								for(int i=0; i<9; i++){
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
								}
									myPanel.colorArray[myPanel.mouseDownGridX][i+1] = newColor;
									myPanel.repaint();	
								}	
							}else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							do {
							switch (generator.nextInt(5)) {
							case 0:
								newColor = Color.YELLOW;
								break;
							case 1:
								newColor = Color.MAGENTA;
								break;
							case 2:
								newColor = Color.BLACK;
								break;
							case 3:
								newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							case 4:
								newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
								break;
							}
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint(); } while (!(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor)));
								}
							}
						}
					}
				}
			}
			myPanel.repaint();
			break;
			}
		case 3:		//Right mouse button
			Component r = e.getComponent();
			while (!(r instanceof JFrame)) {
				r = r.getParent();
				if (r == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame)r;
			MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets1 = myFrame1.getInsets();
			int x3 = myInsets1.left;
			int y3 = myInsets1.top;
			e.translatePoint(-x3, -y3);
			int x4 = e.getX();
			int y4= e.getY();
			myPanel1.x = x4;
			myPanel1.y = y4;
			int gridX1 = myPanel1.getGridX(x4, y4);
			int gridY1 = myPanel1.getGridY(x4, y4);
			if ((gridX1 == -1) || (gridY1 == -1)){
				Color newColor = null;
				for (int i=1; i<10; i++){
					for (int j=1; j<10; j++){
						switch (generator.nextInt(3)) {
						case 0:
							newColor = Color.RED;
							break;
						case 1:
							newColor = Color.GREEN;
							break;
						case 2:
							newColor = Color.CYAN;
							break;
					}
						myPanel1.colorArray[i][j] = newColor;
						myPanel1.repaint();
					}
				}
			}
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}