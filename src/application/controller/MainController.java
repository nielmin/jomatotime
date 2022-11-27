package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.CDTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MainController implements Initializable {
	@FXML private Label timer;
	@FXML private Label banner;
	@FXML private Label warning;
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
		timerSec.setText("10");
		banner.setText("Time to focus!");
		
//		String time = "00:05:00";
//	    LocalTime localtime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
//		int min = localtime.get(ChronoField.MINUTE_OF_HOUR);
//		int sec = localtime.get(ChronoField.SECOND_OF_MINUTE);
		
//		System.out.println(min + ":" + sec);
	}
	
	public void startTasks() {
		CDTimer task01 = new CDTimer(timerMin, timerSec, tasks);
		Thread Timer = new Thread(task01);
		// init and run the new thread
		Timer.setDaemon(true);							
		if (tasks.getItems().isEmpty()) {
			warning.setText("Please enter a task...");
		}
		else {
			warning.setText("");
			Timer.start();
		}
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
}

