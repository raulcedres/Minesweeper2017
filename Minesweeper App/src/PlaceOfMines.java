
public class PlaceOfMines {
	public static int[] XCoord;
	public static int[] YCoord;
	private  MinesCoordinates[]  mine;
	
	public PlaceOfMines(int location)
	 {
		 XCoord = new int[location];
		 YCoord = new int[location];
		 mine= new MinesCoordinates[location];
	 }
	public boolean Coord_Comparison(int x, int y, MinesCoordinates c) 
	 { // Method to compare two different coordinates	
		 return x == c.getX() && y == c.getY();			
	 }
	
	public boolean Coordinate_Comparision(int x, int y ) 
	{
		for(int i=0; i < mine.length; i++)
		{ 
			if(Coord_Comparison(x, y, mine[i]))
			{
				return true;
			}	
		}
		return false;	
	}

	public boolean Nearby_Mines( int a, int b)   
	{
		if(Coordinate_Comparision(a+1,b)|| 
				Coordinate_Comparision(a-1,b)||
				Coordinate_Comparision(a,b+1)||
				Coordinate_Comparision(a,b-1)||
				Coordinate_Comparision(a-1,b-1)||
				Coordinate_Comparision(a+1,b+1)||
				Coordinate_Comparision(a-1,b+1)||
				Coordinate_Comparision(a+1,b-1))

		{
			return true;
		}
		return false;
	}
}
