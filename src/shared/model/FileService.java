package shared.model;

public interface FileService {
	
	boolean openFile();
	boolean closeFile();
	boolean isModified();
	String[] getNames();
	Byte getByte();
}
