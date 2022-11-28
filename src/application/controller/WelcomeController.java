package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController implements EventHandler<ActionEvent>{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void handle (ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Instructions");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
