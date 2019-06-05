import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import java.io.File;

public class Timer extends Application{

	int hour = 0;
	int minute = 0;
	int second = 0;
	int pressCount = 0;
	int startCount = 0;
	Text text = new Text(200,200,"");
	Timeline animation;
	Timeline animation2;

	String beep1 = "beep-09.mp3";
	String beep2 = "beep-07.mp3";

	//Media sound = new Media(new File(beep1).toURI().toString());
	//MediaPlayer playOne = new MediaPlayer(sound);

	//Media sound2 = new Media(new File(beep2).toURI().toString());
	//MediaPlayer playTwo = new MediaPlayer(sound2);

	@Override
	public void start(Stage primaryStage){

		text.setText(hour + ":" + minute + ":" + second);

		HBox buttonPane = new HBox(20);
		HBox buttonPane2 = new HBox(20);
		Button status = new Button("Start");
		Button clear = new Button("Clear");
		Button timer = new Button("Timer");
		Button stopWatch = new Button("Stop Watch");
		BorderPane pane = new BorderPane();
		Button start = new Button("Start");
		Button h = new Button("Hour");
		Button m = new Button("minute");
		Button s = new Button("Second");

		primaryStage.setTitle("Stop Watch");
		pane.setBottom(buttonPane);
		buttonPane2.getChildren().addAll(start, h, m, s, stopWatch);
		buttonPane.getChildren().addAll(status, clear, timer);
		buttonPane.setAlignment(Pos.CENTER);

		VBox textPane = new VBox();
		text.setFont(Font.font(40));
		textPane.getChildren().add(text);
		textPane.setAlignment(Pos.CENTER);
		pane.setCenter(textPane);

		animation = new Timeline(
					new KeyFrame(Duration.millis(1000), l -> time()));
				animation.setCycleCount(Timeline.INDEFINITE);
				animation.pause();

		start.setOnAction(e ->{

			if(startCount == 0){
				
				animation2.play();
			}

			startCount += 1;

			if(startCount % 2 == 0){
				animation2.pause();
				start.setText("Resume");
			}
			else{
				start.setText("Pause");
				animation2.play();
			}
			if(hour== 0 && minute == 0 && second == 0){
				startCount = 0;
				start.setText("Start");
				animation2.pause();
			}
		});

		stopWatch.setOnAction(e ->{
			primaryStage.setTitle("Stop Watch");
			animation2.stop();
			startCount = 0;
			hour = 0;
			second = 0;
			minute = 0;
			text.setText(hour + ":" + minute + ":" + second);
			pane.setBottom(buttonPane);
			buttonPane.setAlignment(Pos.CENTER);
		});

		h.setOnAction(e ->{
			hour += 1;
			text.setText(hour + ":" + minute + ":" + second);
		});

		m.setOnAction(e ->{
			minute +=1;
			text.setText(hour + ":" + minute + ":" + second);
		});

		s.setOnAction(e ->{
			second += 1;
			text.setText(hour + ":" + minute + ":" + second);
		});

		timer.setOnAction(e ->{
			primaryStage.setTitle("Timer");
			animation.stop();
			animation2 = new Timeline(
					new KeyFrame(Duration.millis(1000), l -> minusTime()));
				animation2.setCycleCount(Timeline.INDEFINITE);
			animation2.pause();
			start.setText("Start");
			status.setText("Start");
			pressCount = 0;
			hour = 0;
			second = 0;
			minute = 0;
			text.setText(hour + ":" + minute + ":" + second);
			pane.setBottom(buttonPane2);
			buttonPane2.setAlignment(Pos.CENTER);
		});

		status.setOnAction(e ->{
			if(pressCount == 0){
				animation.play();
			}

			pressCount += 1;

			if(pressCount % 2 == 0){
				status.setText("Resume");
				animation.pause();

			}
			else{
				status.setText("Pause");
				animation.play();
			}
		});

		clear.setOnAction(e ->{
			animation.pause();
			pressCount = 0;
			second = 0;
			minute = 0;
			hour = 0;
			text.setText(hour + ":" + minute + ":" + second);
			status.setText("Start");
		});

		Scene scene = new Scene(pane, 600,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void minusTime(){
		if(hour== 0 && minute == 0 && second == 0){
			//playTwo.play();
			animation2.pause();
		}
		else{
			second -= 1;
			if(second < 0){
				second = 59;
				minute -=1;
			}
			if(second > 59){
				second = 0;
				minute +=1;
			}
			if(minute < 0){
				minute = 59;
				hour -=1;
			}
			if(minute > 59){
				minute = 0;
				hour +=1;
			}
			if(hour == 0 && minute == 0 && second <= 5 && second > 0){
			//playOne.play();
		}
		}
		text.setText(hour + ":" + minute + ":" + second);
	}

	public void time(){
			second += 1;
			if(second == 60){
				second = 0;
				minute +=1;
			}
			if(minute == 60){
				minute = 0;
				hour += 1;
			}
			text.setText(hour + ":" + minute + ":" + second);
		}
	
}