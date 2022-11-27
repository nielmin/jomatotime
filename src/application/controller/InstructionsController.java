package application.controller;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InstructionsController implements EventHandler<ActionEvent>{
	
	public void handle(ActionEvent event) {
		
		try {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/Main.fxml"));

		Scene scene = new Scene((Parent) loader.load());

		Main.stage.setScene(scene);
		Main.stage.setTitle("Jomato Timer");
		Main.stage.show();
								
		} catch(Exception e) {
		e.printStackTrace();
		}
	}

}
