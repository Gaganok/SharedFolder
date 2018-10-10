package shared.network;

import shared.model.FileMonitor;
import shared.model.FileService;

public class Client implements FileMonitor{
	
	private static String localFolderPath = System.getProperty("user.dir") + "/src/resourses/local";
	private static Client instance;
	
	private Client() {System.out.println("Client Initialized");}
	
	public static Client getInstance() {
		
		if(instance == null)
			instance = new Client();
		
		return instance;
	}
	
	@Override
	public boolean monitor(FileService f) {
		return false;
	}
	
	public static String getLocalPath() {
		return localFolderPath;
	}
	
	public static void setLocalPath(String path) {
		localFolderPath = path;
	}
}
