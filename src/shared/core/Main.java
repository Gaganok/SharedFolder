package shared.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

	private static Stage primaryStage;
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
		
		primaryStage.setScene(new Scene(root, 1280, 800));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	
	public static Stage getStage() {
		return primaryStage;
	}
}
