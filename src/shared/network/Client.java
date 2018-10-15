package shared.network;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import shared.model.Player;

public class Client implements Player{

	private String localFolderPath = System.getProperty("user.dir") + "/src/resourses/local";
	private String currentMedia = "/";
	private MediaPlayer player;
	private static Client instance;


	private Client() {
			File file = new File(localFolderPath);
			if(!file.exists())
				file.mkdirs();
			
			System.out.println("Client Initialized");
	}



	public static Client getInstance() {

		if(instance == null)
			instance = new Client();

		return instance;
	}

	public String getLocalPath() {
		return localFolderPath;
	}

	public void setLocalPath(String path) {
		localFolderPath = path;
	}

	@Override
	public void play(String name) {
		
		if(currentMedia.equals(name)) 
			player.play();
		else {
			currentMedia = name;
			player = new MediaPlayer(new Media(
						new File(localFolderPath + "/" + name)
							.toURI().toString()));
			player.play();
		}
	}

	@Override
	public void stop() {
		//player.stop();
		player.pause();
	}
	
	public boolean exist(String name) {
		return new File(getLocalPath() + "/" + name).exists();
	}

}
