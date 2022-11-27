package application.model;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class CDTimer implements Runnable {
	@FXML private Text timerMin;
	@FXML private Text timerSec;
	@FXML private ListView<String> tasks;

	
	public CDTimer (Text min, Text sec, ListView<String> tasks) {
		this.timerMin = min;
		this.timerSec = sec;
		this.tasks = tasks;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.countDown();
		
	}
	public void countDown() 
	{
		String minutes = timerMin.getText();
		String seconds = timerSec.getText();
		
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
			//final int counter = count;
			
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					timerMin.setText(fmin);
	            	timerSec.setText(fsec);
	            	//removeTask();
				}		
			});
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
			//tasks.getItems().remove(0);
			System.out.println("Tasks list has something.");
		}
	}

}
