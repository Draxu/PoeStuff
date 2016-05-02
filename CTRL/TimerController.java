package CTRL;


import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.awt.List;
import java.awt.event.InputEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.Robot;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TimerController implements Initializable{
	
	private static AnimationTimer aTimer; 
	@FXML
	private Label lblX;
	@FXML 
	private Label lblY;
	@FXML
	private ChoiceBox<String> ddlGlobeType;
	
	@FXML 
	private ChoiceBox<String> ddlAlertAt;
	
	@FXML
	private Label lblColor;
	
	@FXML
	private Canvas myCanvas;
	
	@FXML 
	private Button add;
	
	@FXML
	private ColorPicker colorPicker;
	
	@FXML 
	private ListView<String> ListView;
	ObservableList<String> list = FXCollections.observableArrayList();
	
	private boolean type = true;
	
	
	private static FunctionController fc = new FunctionController();
	
	private static ScreenController sc = new ScreenController();
	


	//private ArrayList<LifeGlobe> storedGlobe;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void btnStopTimer(ActionEvent event){
		timerStop();
	}
	  
	public void btnStartTimer(ActionEvent evnet){
		timerStart();
		
	}
	
	public void StoreValue(ActionEvent event){
		
			list.add(ddlAlertAt.getValue());
			
		
	
		System.out.println(colorPicker.getValue());
		
		ListView.setItems(list);
		
	
		//fc.writeToFile(sc.getData());
		try {
			sc.setGlobe(fc.readFromFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void KeyPressTest(KeyEvent ke){
		System.out.println(ke.getCode() +  " test test test test ");
	}
	
	public void timer(){
		setaTimer(new AnimationTimer() {
		
			@Override
			public void handle(long now) {
				getScreenColor();				
				sc.checkColorValue(list.get((int) Math.floor(Math.random()*list.size())));
			}
			
		});
		}
	public void timerStart(){
		aTimer.start();
	}

	
	public void timerStop(){
		aTimer.stop();
	}

	public AnimationTimer getaTimer() {
		return aTimer;
	}

	public void setaTimer(AnimationTimer aTimer) {
		this.aTimer = aTimer;
	}
	
	public void fillDdlGlobeType(){
	
		ddlGlobeType.getItems().addAll(
				"Life",
			    "Ci",
			    "Hybrid"
			);
	}
	
	public void fillDdlAlertAt(){
		ddlAlertAt.getItems().addAll(
				"10%",
			    "20%",
			    "30%",
			    "40%",
			    "50%",
			    "60%",
			    "70%",
			    "80%",
			    "90%"
				);
	}
	
	
	public void getScreenColor()
	{
		Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
		
		
		int  test = robot.getPixelColor(robot.getMouseX(), robot.getMouseY());
		

		int red =   (test >> 16) & 0xFF;
		int green = (test >>  8) & 0xFF;
		int blue =  (test      ) & 0xFF;
		
		
	
		lblColor.setText("R:" + red + " G:"  + green + " b:"  + blue);
		lblX.setText("X: " + Integer.toString(robot.getMouseX()));
		lblY.setText("Y: " + Integer.toString(robot.getMouseY()));
		
		
	}
	
	public Canvas testPane(){
		
		myCanvas.setVisible(true);
		myCanvas.setHeight(1080);
		myCanvas.setWidth(1920);
		
		//myCanvas.setPickOnBounds(false);
		GraphicsContext gc = myCanvas.getGraphicsContext2D();
        gc.rect(20, 20, 20, 20);
        return myCanvas;
	}



	
	
}


