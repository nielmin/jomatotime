package application.model;

import java.text.DecimalFormat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class CDTimer implements Runnable {
	@FXML private Label timer;
	@FXML private Text timerMin;
	@FXML private Text timerSec;
	@FXML private ListView<String> tasks;
	
	private String ddMin;
	private String ddSec;

	private int min;
	private int sec;
	private String[] parts;
	
	public CDTimer (Label timer, ListView<String> tasks) {
//		this.timerMin = min;
//		this.timerSec = sec;
		this.timer = timer;
		this.tasks = tasks;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.betterCountDown();
		
	}
	
	public void betterCountDown() {
		DecimalFormat dFormat = new DecimalFormat("00");

		while (true) {
			parts = timer.getText().split(":");
		
			ddMin = parts[0];
			ddSec = parts[1];
			min = Integer.parseInt(ddMin);
			sec = Integer.parseInt(ddSec);

			sec--;

			ddMin = dFormat.format(min);
			ddSec = dFormat.format(sec);
			if (sec == -1) {
				sec = 59;
				min--;
				ddMin = dFormat.format(min);
				ddSec = dFormat.format(sec);
			}
			if (min <= 0 && sec <= 0) {
				break;
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					timer.setText(ddMin + ":" + ddSec);
				}		
			});
			System.out.println(ddMin + ":" + ddSec);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	public void removeTask() {

		if (tasks.getItems().isEmpty()) {
			System.out.println("Task list is empty");

		}
		else {
			tasks.getItems().remove(0);
			System.out.println("Tasks list has something.");
		}
	}

}
