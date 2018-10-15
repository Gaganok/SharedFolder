package shared.network;

import java.io.File;

import javafx.stage.DirectoryChooser;
import shared.core.Main;
import shared.model.FileService;

public class Server implements FileService{
	
	private String sharedFolderPath = System.getProperty("user.dir") + "/src/resourses/shared";
	private File[] currentFiles;
	private static Server instance;
	
	
	private Server(){
			File file = new File(sharedFolderPath);
			if(!file.exists())
				file.mkdirs();
			
			System.out.println("Server Initialized");
	}
	
	public static Server getInstance() {
		if(instance == null)
			instance = new Server();
		
		return instance;
	}
	
	private File[] getFiles() {
		
		return new File(getSharedPath())
				.listFiles((f, n) -> {return n.endsWith(".mp3");});
	}
	
	public String[] getFileNames() {
		currentFiles = getFiles();
		
		String[] names = new String[currentFiles.length];
		for(int i = 0; i < currentFiles.length; i++)
			names[i] = currentFiles[i].getName();
		
		return names;
	}
	
	public String selectFolder() {
		DirectoryChooser dirCh = new DirectoryChooser();
		dirCh.setInitialDirectory(new File(System.getProperty("user.dir")));

		File directory = dirCh.showDialog(Main.getStage());

		if(directory != null) 
			setSharedPath(directory.getAbsolutePath());
			
		return sharedFolderPath;
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
		File[] newFiles = getFiles();
		if(newFiles.length == currentFiles.length) {
			for(int i = 0; i < newFiles.length; i++) {
				boolean ok = false;
				for(int j = 0; j < currentFiles.length; j++) {
					if(newFiles[i].getName().equals(currentFiles[j].getName()) && 
							newFiles[i].lastModified() == currentFiles[j].lastModified()) {
						ok = true;
						break;
					}
				}
				if(!ok)
					return true; 
			}
			return false;
		}
		return true;
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
	
	public String getSharedPath() {
		return sharedFolderPath;
	}
	
	public void setSharedPath(String path) {
		sharedFolderPath = path;
	}

}
