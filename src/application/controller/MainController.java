package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.CDTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController implements Initializable, EventHandler<ActionEvent>{
	@FXML private Label timer;
	@FXML private Label banner;
	@FXML private Button pomodoro;
	@FXML private Button sBreak;
	@FXML private Button lBreak;
	@FXML private Button start;
	@FXML private Button addTask;
	@FXML private Text timerMin;
	@FXML private Text timerSec;
	
	@FXML private ListView<String> tasks;
	@FXML private ObservableList<String> taskList;
	@FXML private TextField task;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//timer.setText("25:00");
		timerMin.setText("00");
		timerSec.setText("05");
		banner.setText("Time to focus!");
		
	}
	
	public void handle(ActionEvent event) {
		CDTimer task01 = new CDTimer(timerMin, timerSec, tasks);
		Thread Timer = new Thread(task01);
		// init and run the new thread
		Timer.setDaemon(true);									
		Timer.start();
}
	
	public void addTask() {
		if (task.getText() != "") {
			tasks.getItems().add(task.getText());
			task.setText("");
		}
	}
	
	public void pomodoro() {
	//	timer.setText("25:00");
		timerMin.setText("25");
		timerSec.setText("00");
		banner.setText("Time to focus!");
	}
	
	public void shortBreak() {
	//	timer.setText("05:00");
		timerMin.setText("05");
		timerSec.setText("00");
		banner.setText("Time for a short break.");
	}
	
	public void longBreak() {
	//	timer.setText("15:00");
		timerMin.setText("15");
		timerSec.setText("00");
		banner.setText("Time for a long break!");
	}
	public void removeTask() {

		if (timerMin.getText().equals("0") && timerSec.getText().equals("0")) {
			System.out.println("Task remove");
			tasks.getItems().remove(0);
		}
		else {
			System.out.println("Task not removed");
		}
	}
}

