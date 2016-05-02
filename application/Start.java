package application;
	

import java.io.File;
import java.net.URL;

import javax.swing.Timer;

import com.sun.glass.ui.Robot;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;

import CTRL.ScreenController;
import CTRL.TimerController;
import Model.LifeGlobe;
import Model.Minion;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;



public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
		
	}
	
	private static Minion minion;
	private LifeGlobe lifeGlobe;
	private  TimerController Tctrl;
	private ScreenController sCtrl;
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();

			Parent root = fxmlLoader.load(getClass().getResource("Sample.fxml").openStream());
			
			Tctrl = fxmlLoader.getController();
			sCtrl = new ScreenController();
			Scene scene = new Scene(root,600,400);
			
			primaryStage.setScene(scene);
			
			
			URL location = Start.class.getProtectionDomain().getCodeSource().getLocation();
			System.out.println( location.toString()  + "Img" + "/Icon.png");

			primaryStage.getIcons().add(new Image( location.toString()  + "Img" + "/Icon.png"));
			primaryStage.setTitle("POE program UI");
			
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Tctrl.fillDdlGlobeType();
			Tctrl.fillDdlAlertAt();
			Tctrl.timer();
			//root.add(Tctrl.testPane());
			
	
			primaryStage.show();
			
			
			
			
		
			

		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/* 
	 * This method gets the screen size of the current user
	 */



	private static void checkGolemOrMinions(){
		int[]a  = {1,2};
		minion = new Minion(a);
	}
}
