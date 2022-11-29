package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.CDTask;
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
	private CDTask countdown;

	private Thread thread;
	private ObservableList<String> tasksList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

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

	private int flag;
	private int lbCount;
	private int i;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.pomodoro();
		flag = 0;
		lbCount = 0;
		tasks.setItems(tasksList);
	}
	
	/**
	 * Check for invalid user input.
	 */
	@FXML
	public void handleStartButton() {
		
		if (tasksList.isEmpty()) {
			warning.setText("Please enter a task...");
		}
		else {
			warning.setText("");
			if (countdown != null && countdown.isRunning()) {
				warning.setText("Timer is already running");
			}
			else {
				System.out.println("First run of count down timer.");
				startCountDown();
			}
		}
	}

	/**
	 * Main logic of the pomodoro timer.
	 */
	public void startCountDown() {
		countdown = new CDTask(timer, tasks);

		/**
		 * BAD CODE BELOW! Don't look. It'll scar you for life.
		 */
		for (i = 0; i < tasksList.size(); i++) {
			countdown.setOnSucceeded(e-> {
				if (flag == 0 || flag == 1) {
					removeTask();
					lbCount++;
//					System.out.println("LB count: " + lbCount);
//					System.out.println("Task #" + i + " removed.");
					if (lbCount % 4 == 0 && lbCount != 0) {
						this.longBreak();
						lbCount = 0;
					}
					else {
						flag = 2;
						this.shortBreak();
					}
				}
				else if (flag == 2) {
					this.pomodoro();
					flag = 1;
				}
				else {
					flag = 1;
					this.pomodoro();
				}
				startCountDown();
			});
		}

		thread = new Thread(countdown);
		thread.setDaemon(true);			
		thread.start();
}
	/**
	 * Cancels the timer and resets to the default working period time.
	 */
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
	
	/**
	 * Adds a task to the ListView.
	 * Checks for empty tasks.
	 */
	public void addTask() {
		if (task.getText() != "") {
			tasksList.add(task.getText());
			task.setText("");
		}
		else {
			warning.setText("No task was given...");
		}
	}
	
	/**
	 * Removes the oldest task frmo the ListView.
	 */
	public void removeTask() {
		if (tasksList.isEmpty()) {
			System.out.println("	Tasks List is empty");
		}
		else {
			tasksList.remove(0);
			System.out.println("	Task completed. Removing task...");
		}
	}
	
	/**
	 * Sets working period time.
	 */
	public void pomodoro() {
		/**
		 * 25 min is the default value for a working period.
		 */
//		timer.setText("25:00");
		banner.setText("Time to focus!");

		/*
		 * Demo value for a working period.
		 */
		timer.setText("00:10");
	}
	
	/**
	 * Sets short break time.
	 */
	public void shortBreak() {
		/**
		 * 5 min is the default value for a short break.
		 */
//		timer.setText("05:00");
		banner.setText("Time for a short break.");

		/*
		 * Demo value for a short beeak;
		 */
		timer.setText("00:05");
	}
	
	/**
	 * Sets long break time.
	 */
	public void longBreak() {
		/**
		 * 15 minutes is the default value for a long break.
		 */
//		timer.setText("15:00");
		banner.setText("Time for a long break!");

		/**
		 * Demo value for a long break.
		 */
		timer.setText("00:08");

	}
	
	/**
	 * Switches scenes to instructions on how to use the app.
	 * @param event
	 * @throws IOException
	 */
	public void switchToHelp(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Instructions.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Switches back to the Welcome view.
	 * @param event
	 * @throws IOException
	 */
	public void back(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Welcome.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Swithces to a credits view, where it lists the group members and GitHub link.
	 * @param event
	 * @throws IOException
	 */
	public void credit(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("../view/Credits.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

