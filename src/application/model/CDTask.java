package application.model;

import java.text.DecimalFormat;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CDTask extends Task<String>{
	@FXML private Label timer;
	@FXML private ListView<String> tasks;
	
	private String[] parts;
	private String ddMin;
	private String ddSec;

	private int min;
	private int sec;
	
	public CDTask(Label timer, ListView<String> tasks) {
		this.timer = timer;
		this.tasks = tasks;
	}
	
	@Override
	protected String call() throws Exception {
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
			updateValue(ddMin + ":" + ddSec);
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					timer.setText(ddMin + ":" + ddSec);
				}		
			});

			try {
				Thread.sleep(1000);
				if (min == 0 && sec == 0) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//updateProgress(min*60+sec, min*60+sec);
		}
		return null;
	}

}
