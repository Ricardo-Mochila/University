/*package flappyBird;

import java.util.Map;
import java.util.HashMap;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;


public class AudioPlayer 
{
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load()
	{
		try {
			
			musicMap.put("music", new Music("res/music1.ogg") );
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
	}
}
*/