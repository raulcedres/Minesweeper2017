import java.util.ArrayList;
import java.util.Random;

public class Mines {
	public int[][] numbers = new int [9][9];
	public int[][] Mines = new int[9][9];
	Random random = new Random();
	public int Mine = 10;
	//generate random mines
	public void randomMines(){
		ArrayList<Integer> possible = new ArrayList<Integer>(); //Array list to be assigned all possible mines
		for (int x=0; x<numbers.length; x++){
			 for (int y=0; y<numbers.length; y++){
				 possible.add(x+y);
			 }
		}
		
		//choose random Mine from array list
		for (int a=0; a<20; a++){
			int xCord = random.nextInt(possible.size()); //get a random number for mine xCord from array list
			int yCord = random.nextInt(possible.size()); //get a random number for mine yCord from array list
			numbers[possible.get(xCord)][possible.get(yCord)] = Mine;
		}
	}
}
