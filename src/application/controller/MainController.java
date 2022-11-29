package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.CDTask;
import application.model.CDTimer;
import javafx.collections.FXCollections;
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

	
	private Stage stage;
	private Scene scene;
	private Parent root;
//	private CDTimer countdownTime;
	private CDTask countdown;

	private Thread thread;
	private ObservableList<String> tasksList = FXCollections.observableArrayList();
	private ObservableList<String> doneList = FXCollections.observableArrayList("Hello", "World");

	@FXML private Label timer;
	@FXML private Label banner;
	@FXML private Label warning;
	@FXML private Button pomodoro;
	@FXML private Button sBreak;
	@FXML private Button lBreak;
	@FXML private Button start;
	@FXML private Button cancel;
	@FXML private Button addTask;
	@FXML private TextField task;
	@FXML private ListView<String> tasks = new ListView<String>(tasksList);
	@FXML private ListView<String> done = new ListView<String>(doneList);

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//this.pomodoro();
		tasks.setItems(tasksList);
	}
	
	@FXML
	public void handleStartButton() {
		if (tasks.getItems().isEmpty()) {
			warning.setText("Please enter a task...");
		}
		else {
			warning.setText("");
			if (countdown != null && countdown.isRunning()) {
				warning.setText("Timer is already running");
			}
			else {
				startCountDown();
			}
		}
	}
	
	public void startCountDown() {
		
		countdown = new CDTask(timer, tasks);

		countdown.setOnSucceeded(e-> {
			removeTask();
		});
		
		thread = new Thread(countdown);
		thread.setDaemon(true);			
		thread.start();
}
	public void stopCountDown() {
		if (countdown == null) {
			warning.setText("Timer wasn't started");
		}
		else {
			warning.setText("");
			countdown.cancel();
			this.pomodoro();
		}
	}
	
	public void addTask() {
		if (task.getText() != "") {
			tasksList.add(task.getText());
			task.setText("");
		}
		else {
			warning.setText("No task was given...");
		}
	}
	
	public void removeTask() {
		if (tasksList.isEmpty()) {
			System.out.println("Tasks List is empty");
		}
		else {
			tasksList.remove(0);
			System.out.println("Task completed. Removing task...");
		}
	}
	
	public void pomodoro() {
		/**
		 * Default value for the timer.
		 */
		timer.setText("00:10");
		/*
		 * Demo value for the timer.
		 */
//		timer.setText("00:10");
//		banner.setText("Time to focus!");
		banner.setText("Testing!");



	}
	
	public void shortBreak() {
		timer.setText("05:00");
//		timer.setText("00:05");

		banner.setText("Time for a short break.");

	}
	
	public void longBreak() {
		timer.setText("15:00");
//		timer.setText("00:08");

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

