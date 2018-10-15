package shared.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import shared.model.FileMonitor;
import shared.network.Client;
import shared.network.Server;
import shared.service.Monitor;

public class Controller implements FileMonitor{

	@FXML ListView<String> listView;
	@FXML Label sharedFolderLabel;

	private Server server;
	private Client client;

	public Controller(){

		server = Server.getInstance();
		client = Client.getInstance();

		Platform.runLater(() -> {
			sharedFolderLabel.setText(server.getSharedPath());
			displaySharedList();

			Thread t = new Thread(new Monitor(this));
			t.setDaemon(true);
			t.start();
		});
	}

	private void displaySharedList() {
		listView.getItems().setAll(server.getFileNames());
	}

	public void selectFolder() {
		String newPath = server.selectFolder();
		sharedFolderLabel.setText(newPath);
		displaySharedList();
	}

	public void stop() {
		Client.getInstance().stop();
	}

	public void play() {
		String name = listView.getSelectionModel().getSelectedItem();

		if(name != null) {
			if(!client.exist(name)) 
				download();

			Client.getInstance().play(name);
		}
	}

	public void download() {
		String name = listView.getSelectionModel().getSelectedItem();

		if(!client.exist(name)) {
			InputStream input = null;
			OutputStream output = null; 

			try {
				input = new FileInputStream(new File(server.getSharedPath() + "/" + name));
				output = new FileOutputStream(new File(client.getLocalPath()  + "/" + name));

				byte[] buffer = new byte[1024];
				int length;

				System.out.println("Downloading: " + name);

				while((length = input.read(buffer)) > 0)
					output.write(buffer, 0, length);

			} catch(Throwable t) {
				System.out.println(t);
			} finally {
				try {
					input.close();
					output.close();
				} catch(Throwable t) {
					System.out.println(t);
				}
			}
		} else {
			System.out.println("Media File is already downloaded");
		}
	}


	@Override
	public void monitor() {
		if(server.isModified()) {
			System.out.println("SharedFolder modified");
			Platform.runLater(() -> displaySharedList());
		}
	}
}
