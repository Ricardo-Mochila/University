
public class Pawn {
	private int x;
	private int y;

	public Pawn(int x1, int y1){
		x = x1;
		y = y1;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x1)
	{
		x = x1;
	}
	
	public void setY(int y1)
	{
		y = y1;
	}
	
	public void move_peao(String move)
	{
		if (move == "North")
		{
			x = x - 1;
			return;
		}
		if (move == "South")
		{
			x = x + 1;
			return;
		}
		if (move == "East")
		{
			y = y + 1;
			return;
		}
		if (move == "West")
		{
			y = y - 1;
			return;
		}
		
	}






}
