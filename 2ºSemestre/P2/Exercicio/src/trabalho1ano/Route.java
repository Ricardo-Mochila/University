/*
public class Route {
	private String[] caminho;
	private Maze maze;
	private Pawn peao;
	private int tem = 0;
	
	
	public Route(Maze maze,Pawn peao,int size)
	{
		this.maze = maze;
		this.peao = peao;
		caminho = new String[size];
		
	}
	
	public int getCol()
	{
		return peao.getY();
	}
	public int getRow()
	{
		return peao.getX();
	}
	public int getCol(int i)
	{
		return maze.getNumCols() - 1 % i;
	}
	
	public int getRow(int i)
	{
		return maze.getNumRows() - 1 % i;
	}
	public int length()
	{
		return caminho.length;
	}
	void move(String move)
	{
		caminho[tem] = move;
		tem++;
	}
	
}
*/