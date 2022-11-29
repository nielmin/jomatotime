package application.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InstructionsController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * Switches to the main view of the app.
	 * @param event
	 * @throws IOException
	 */
	public void switchToMain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Instructions");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
