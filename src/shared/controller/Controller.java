package shared.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import shared.core.Main;
import shared.network.Client;
import shared.network.Server;

public class Controller {

	@FXML ListView<String> listView;
	@FXML Label sharedFolderLabel;

	public Controller(){
		Platform.runLater(() -> {
			sharedFolderLabel.setText(Server.getSharedPath());
			displaySharedList();
		});
	}

	private void displaySharedList() {
		File sharedFolder = new File(Server.getSharedPath());
		File[] files = sharedFolder.listFiles((f, n) -> {return n.endsWith(".jpg");});
		
		listView.getItems().clear();
		
		for(File f : files)
			listView.getItems().add(f.getName());
	}

	public void selectFolder() {
		DirectoryChooser dirCh = new DirectoryChooser();
		dirCh.setInitialDirectory(new File(System.getProperty("user.dir")));
		
		File directory = dirCh.showDialog(Main.getStage());

		if(directory != null) {
			Server.setSharedPath(directory.getAbsolutePath());
			sharedFolderLabel.setText(directory.getAbsolutePath());
			displaySharedList();
		}
	}

	public void stop() {

	}

	public void play() {

	}

	public void download() throws IOException {
		String name = listView.getSelectionModel().getSelectedItem();
		
		InputStream input = null;
		OutputStream output = null; 
		
		try {
			input = new FileInputStream(new File(Server.getSharedPath() + "/" + name));
			output = new FileOutputStream(new File(Client.getLocalPath()  + "/" + name));

			byte[] buffer = new byte[1024];
			int length;
			while((length = input.read(buffer)) > 0)
				output.write(buffer, 0, length);

		} catch(Throwable t) {
			System.out.println(t);
		} finally {
			input.close();
			output.close();
		}
	}
}
