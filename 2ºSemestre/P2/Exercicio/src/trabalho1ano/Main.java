/*import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args)
	{
		System.out.println("Qual é a sua opção\n 1-Manual\n 2-Gerar\n 3-Terminar");
		@SuppressWarnings("resource")
		Scanner menu = new Scanner(System.in);
		String menu1 = menu.nextLine();
		
		if (menu1.equals("1"))
		{
			openfile cenas = new openfile();
			
			ArrayList <ArrayList <String>> temp = cenas.get_tabuleiro(cenas.open_file(""));
			
			Pawn peao = new Pawn(cenas.get_S_x(),cenas.get_S_y());
			
			Maze maze = new Maze(temp);
			
			for (int j = 0 ; j < temp.size() ; j++)
			{
				System.out.println(temp.get(j));
			}
			Route route = new Route(maze,peao,maze.getNumCols()*maze.getNumRows());
			
			System.out.println("Comandos : Opcoes ou qualquer movimento valido");
			while(true)
			{
				
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				String s = scanner.nextLine();
				if (maze.getOptions(peao) == null)
				{
					System.out.println("Sem mais caminhos");
				}
				else if (s.equals("Opcoes"))
				{
					String[] cenass = maze.getOptions(peao);
					for (int k = 0;k<cenass.length;k++)
					{
						if (cenass[k]!=null)
								System.out.println(cenass[k]);
					}
				}
				else if(s.equals("North") || s.equals("South") || s.equals("East") || s.equals("West") || s.equals("Noop")||s.equals("3"))
				{				
						
					maze.move(peao, s);
					route.move(s);
					for (int j = 0 ; j < temp.size() ; j++)
					{
						System.out.println(temp.get(j));
					}
				}
				else
				{
					System.out.println("Comando errado");
				}
				
			}
			
		}	
		else if(menu1.equals("2"))
		{
			generate g = new generate();
			
			 ArrayList<ArrayList<String>> tab = g.Gerar(5, 5);
			g.randomizer(tab);
			
		}
		else if(menu1.equals("3"))
		{
			System.out.println("Obrigado por jogar");
			System.exit(1);
		}
		
			
	}
		
		
}
*/