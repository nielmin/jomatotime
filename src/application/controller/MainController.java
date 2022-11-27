package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
		//System.out.println("Start Button works");
		String minutes = timerMin.getText();
		String seconds = timerSec.getText();
		
		
			try {
			Thread Timer = new Thread( new Task(){                // put the task in its own thread
			@Override
			protected String call() throws Exception {
		// convert minutes to seconds and while i < total seconds add to the total time
				int j = Integer.valueOf(seconds) + (Integer.valueOf(minutes)*60);
				int count = 0;
				int b = 1;
				int secTime = Integer.valueOf(seconds);
				if (secTime == 0) {
					secTime = 60;
				}
				String elapsed;
				if(Integer.valueOf(minutes) > 0) {
				 elapsed = String.valueOf(Integer.valueOf(minutes)-1);
				}
				else {
					 elapsed = String.valueOf(Integer.valueOf(minutes));
				}
				//while(true) {
					for(int i = j; i >= 0; i--) {
						count++;
					if(count % 60 == 0) 
					{
						elapsed = String.valueOf(Integer.valueOf(elapsed)-b);
						b++;
						
						System.out.println(i + " " + b + " " + elapsed + " " + secTime);
						
						secTime = 60;
						
					}
					final String fsec = String.valueOf(secTime);
					secTime--;
					final String fmin = elapsed;
					final int counter = count;
					

					// update the label on the JavaFx Application Thread!
					Platform.runLater(new Runnable() {
			            @Override
			            public void run() {
			    
			            	timerMin.setText(fmin);
			            	
			            	timerSec.setText(fsec);
			            	//System.out.println(counter);
			            }
			        });
					Thread.sleep(1000);
				
			}
					return null;
			}
		});													
		
		// init and run the new thread
		Timer.setDaemon(true);									
		Timer.start();
		Thread.sleep(1000);
		}catch (InterruptedException e) {
			e.printStackTrace();
	}
	}
	
	public void addTask() {
		if (task.getText() != "") {
			tasks.getItems().add(task.getText());
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

