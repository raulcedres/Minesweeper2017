
public class Mines {
	private MinesCoordinates [] MINE;
	public int[] xCord;
	public int[] yCord;
	
	public Mines(int x){
		xCord = new int[x];
		yCord = new int[x];
		MINE = new MinesCoordinates [x];
	}
	public MinesCoordinates [] getMinesCoordinates(){
		return MINE;
	}
}
