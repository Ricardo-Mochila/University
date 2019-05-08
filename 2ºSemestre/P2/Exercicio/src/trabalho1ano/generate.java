import java.util.ArrayList;
import java.util.Random;

public class generate {
	
	 public ArrayList<ArrayList <String>> Gerar(int x, int y){ 
		 
		 ArrayList<ArrayList<String>> gerador1 = new ArrayList<ArrayList<String>>();
		 for (int i = 0; i <= x-1; i++){
			 ArrayList<String> gerador = new ArrayList<String>();
			 for (int c = 0; c<= y-1; c++){
				 gerador.add("");
			}
			gerador1.add(gerador);
			
		 }
		 return gerador1;
		 
		 
	 }
	 public void randomizer(ArrayList<ArrayList<String>> gerador1 ){
		 
		 int S = gerador1.get(0).size();
		 Random random = new Random();
		 int value = random.nextInt(S);
		 gerador1.get(0).set(value, "S");
		 
		 for (int j = 0 ; j < gerador1.size() ; j++)
		 {
				System.out.println(gerador1.get(j));
		 }
		 
		 
		
	 
	 
	 
	 
	/* public class Maze
	 {
	 	public ArrayList <ArrayList <String>> lab = new ArrayList< ArrayList<String>>();
	 	private int numCols;
	 	private int numRows;

	 	public Maze(ArrayList <ArrayList <String>> file)
	 	{
	 		numRows = file.size();
	 		numCols = file.get(numRows-1).size();
	 		lab=file;
	 	}

	 	public int getNumCols()
	 	{
	 		return numCols;
	 	}

	 	public int getNumRows()
	 	{
	 		return numRows;
	 	}

	 	public boolean canMove(Pawn peao,String new_move)
	 	{
	 		String[] array = getOptions(peao);
	 		if (array != null)
	 		{
	 			for (int i = 0; i < array.length;i++)
	 			{
	 				if (new_move.equals(array[i]))
	 					return true;

	 			}
	 		}
	 		return false;
	 	}

	 	public String[] getOptions(Pawn peao)
	 	{
	 		int x = peao.getX();
	 		int y = peao.getY();
	 		
	 		String[] pos = new String[4];
	 		int i=0;
	 		if (x + 1 < numRows && lab.get(x+1).get(y).equals("_"))
	 		{
	 			pos[i]= "South";
	 			i++;
	 		}
	 		if (x - 1 >= 0 && lab.get(x-1).get(y).equals("_"))
	 		{
	 			pos[i]= "North";
	 			i++;
	 		}
	 		if (y + 1 < numCols && lab.get(x).get(y + 1).equals("_"))
	 		{
	 			pos[i] = "East";
	 			i++;
	 		}
	 		if (y - 1 >= 0 && lab.get(x).get(y - 1).equals("_"))
	 		{
	 			pos[i] = "West";
	 			i++;
	 		}
	 		
	 		if (i!=0)	
	 			return pos;
	 		System.out.println("Nao tem opcoes");
	 		return null;
	 		

	 	}
	 	
	 	private int atualiza_lab(Pawn peao,int x, int y)
	 	{
	 		if(lab.get(x).get(y).equals("E"))
	 		{
	 			System.out.println("Game Over!!");
	 			return -1;			
	 		}
	 		lab.get(x).set(y, "S");
	 		lab.get(peao.getX()).set(peao.getY(),"X");
	 		return 0;		
	 	}
	 	
	 	public void move(Pawn peao, String new_move)
	 	{
	 		int temp;
	 		if (canMove(peao,new_move))
	 		{
	 			if (new_move.equals("North"))
	 			{
	 				temp = atualiza_lab(peao,peao.getX() - 1, peao.getY());
	 				
	 				peao.move_peao("North");
	 			}
	 			else if (new_move.equals("South"))
	 			{
	 				temp = atualiza_lab(peao,peao.getX() + 1, peao.getY());
	 				if(temp != 0)
	 					return;
	 				peao.move_peao("South");
	 				
	 			}
	 			else if (new_move.equals("West"))
	 			{
	 				temp = atualiza_lab(peao,peao.getX(), peao.getY() - 1);
	 				if(temp != 0)
	 					return;
	 				peao.move_peao("West");
	 				
	 			}
	 			else if (new_move.equals("East"))
	 			{
	 				temp = atualiza_lab(peao,peao.getX(), peao.getY() + 1);
	 				if(temp != 0)
	 					return;
	 				peao.move_peao("East");
	 				
	 			}
	 			return;
	 		}
	 		System.out.println("Can't move");
	 			
	 		
	 	}
	 	
	 	public boolean isSolvedBy(Pawn peao)
	 	{
	 		if (lab.get(peao.getX()).get(peao.getY()).equals("E"))
	 		{
	 			return true;
	 		}
	 		return false;
	 	}
*/
	 }

}