import java.util.Random;

public class Mines {
	private MinesCoordinates [] MINE;
	public static int[] xCord;
	public static int[] yCord;
	
	public Mines(int x){
		xCord = new int[x];
		yCord = new int[x];
		MINE = new MinesCoordinates[x];
	}
	public MinesCoordinates [] getMinesCoordinates(){
		return MINE;
	}
	public void setMinesCoordinates(){
		Random generate = new Random();
		for (int i=0; i<15; i++){
			int xcoord = generate.nextInt(9);
			int ycoord = generate.nextInt(9);
			MINE[i] = new MinesCoordinates(xcoord, ycoord);
		}
		int XCORD [] = new int[9];
		int YCORD [] = new int[9];
		for(int j=0; j<9; j++){
			XCORD[j] = MINE[j].getX();
			YCORD[j] = MINE[j].getY();
		}
	}
	public boolean CellComparison(int x, int y, MinesCoordinates c) 
	 { // Method to compare two different coordinates	
		 return x == c.getX() && y == c.getY();			
	 }
	public boolean CellCompare(int x, int y){
		for (int i=0; i<MINE.length; i++){
			if(CellComparison(x, y, MINE[i])){
				return true;
			}
		}return false;
	}
	public boolean Neighborhood(int x, int y){ //recursive search
		if(CellCompare(x+1, y) || CellCompare(x-1,y) || CellCompare(x,y+1) || 
			CellCompare(x, y-1) || CellCompare(x+1,y+1) || CellCompare(x-1,y-1) || 
			CellCompare(x-1,y+1) || CellCompare(x+1,y-1)){
			return true;
		}return false;
	}
	public int Neigborhoodcount(int x, int y){
		int counter = 0;
		for (int a= x-1; a<= x+1; a++){
			for (int b= y-1; b<= y+1; b++){
				if(!(a == x && b==y)){
					if(CellCompare(a,b)){
						counter++;
					}
				}
			}
		} return counter;
	}
}
