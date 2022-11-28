package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.CDTask;
import application.model.CDTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML private Label timer;
	@FXML private Label banner;
	@FXML private Label warning;
	@FXML private Button pomodoro;
	@FXML private Button sBreak;
	@FXML private Button lBreak;
	@FXML private Button start;
	@FXML private Button addTask;
	
	@FXML private ListView<String> tasks;
	@FXML private ObservableList<String> taskList;
	@FXML private TextField task;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	CDTask countdown;
	CDTimer countdownTime;
	
	private Thread thread;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		timer.setText("00:10");
		banner.setText("Time to focus!");
				
	}
	
	public void startTasks() {
		
		if (countdown != null && countdown.isRunning()) {
			countdown.cancel();
		}
		countdown = new CDTask(timer, tasks);
		countdown.setOnSucceeded(e -> {
			removeTask();
//			countdown.cancel(true);
			this.shortBreak();
			removeTask();
//			countdown.cancel(true);
			this.pomodoro();
			
			startTasks();
		});

		if (tasks.getItems().isEmpty()) {
			warning.setText("Please enter a task...");
		}
		else {
			warning.setText("");
			if (thread == null || !thread.isAlive()) {
				thread = new Thread(countdown);
				thread.setDaemon(true);			
				thread.start();
			}
			else {
				thread.interrupt();
			}
		}
}
	
	public void addTask() {
		if (task.getText() != "") {
			tasks.getItems().add(task.getText());
			task.setText("");
		}
		else {
			warning.setText("No task was given...");
		}
	}
	
	public void removeTask() {
		if (tasks.getItems().isEmpty()) {
			System.out.println("Tasks List is empty");
		}
		else {
			tasks.getItems().remove(0);
			System.out.println("Task completed. Removing task...");
		}
	}
	
	public void pomodoro() {
		timer.setText("25:00");
		banner.setText("Time to focus!");
	}
	
	public void shortBreak() {
		timer.setText("05:00");
		banner.setText("Time for a short break.");
	}
	
	public void longBreak() {
		timer.setText("15:00");
		banner.setText("Time for a long break!");
	}
	public void switchToHelp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Instructions.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void back(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Welcome.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void credit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Credits.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

