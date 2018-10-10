package shared.network;

import java.nio.file.Path;
import java.nio.file.Paths;

import shared.model.FileService;

public class Server implements FileService{
	
	private static String sharedFolderPath = System.getProperty("user.dir") + "/src/resourses/shared";
	private static Server instance;
	
	
	private Server(){System.out.println("Server Initialized");}
	
	public static Server getInstance() {
		if(instance == null)
			instance = new Server();
		
		return instance;
	}
	
	@Override
	public boolean openFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeFile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Byte getByte() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String getSharedPath() {
		return sharedFolderPath;
	}
	
	public static void setSharedPath(String path) {
		sharedFolderPath = path;
	}

}
