import java.util.ArrayList;

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
		ArrayList<Integer> list = new ArrayList<Integer>(); //Array to avoid repeted random mines
		for (int a=0; a<9; a++){
			for (int b=0; b<9; b++){
				list.add(a*100+b);
			}
		}
		for (int i=0; i<15; i++){
			int choice = (int)(Math.random()*list.size());
			MINE[i] = new MinesCoordinates(list.get(choice)/100, list.get(choice)%100);
		}
		int XCORD [] = new int[15];
		int YCORD [] = new int[15];
		for(int j=0; j<15; j++){
			XCORD[j] = MINE[j].getX();
			YCORD[j] = MINE[j].getY();
		}
	}

	public boolean CellComparison(int x, int y, MinesCoordinates c) 
	 { // Compares two coordinates
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
	public int Neigborhoodcount(int x, int y){ //counts mines around cell
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
