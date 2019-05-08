import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class openfile {
	
	private int S_x;
	private int S_y;
	
	public ArrayList<String> open_file(String location)
	{
		try
		{
			FileReader file = new FileReader(location);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(file);
			
			String text = "";
			String line = reader.readLine();
			while(line != null){
				text += line;
				line = reader.readLine();
				text.replace("\\", "n");
				
			}
			return new ArrayList<String>(Arrays.asList(text.split("n")));
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	
	public ArrayList<ArrayList<String>> get_tabuleiro(ArrayList<String> myList)
	{
		ArrayList<ArrayList<String>> myList1 = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i< myList.size(); i++){
			ArrayList<String> listinha = new ArrayList<String>();
			int c = 0;
			
			while(myList.get(i).charAt(c) != '\\'){
				listinha.add(""+myList.get(i).charAt(c));
				c++;
				
				if(myList.get(i).charAt(c) == 'S')
				{
					S_x = i;
					S_y = c;
				}
			}
			
			myList1.add(listinha);
		}
		return myList1;
	}
	
	public int get_S_x()
	{
		return S_x;
	}
	public int get_S_y()
	{
		return S_y;
	}

}
