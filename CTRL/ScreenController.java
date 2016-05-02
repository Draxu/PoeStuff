package CTRL;

import java.util.ArrayList;

import com.sun.glass.ui.Robot;

import Model.LifeGlobe;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenController {
	private double screenWidth;
	private double screenHight;
	
	private LifeGlobe lifeGlobe;
	
	private FunctionController fc = new FunctionController();
	
	public ScreenController(){
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();

		setScreenWidth(primaryScreenBounds.getWidth());
		setScreenHight(primaryScreenBounds.getHeight());
		
		lifeGlobe = new LifeGlobe();
		screenMath();
		

	}


	private void setScreenHight(double height) {
		this.screenHight = height;
	}


	private void setScreenWidth(double width) {	
		this.screenWidth = width;
	}



	public double getScreenHight() {
		return screenHight;
	}



	public double getScreenWidth() {
		return screenWidth;
	}
	
	//Calculates the position and color of the globe and adds it to an arraylist
	public void screenMath(){
		//10.9375 H
		//12.037037037037036 W
		Double test1 = 0.12 * screenWidth / 2.2;
		Double test2 = 0.807 * screenHight;
		Double test3 = screenHight - test2;
		Double test4 = test3 / 10;
		for(int i = 0; i < 10; i++){

			ArrayList<Double> tempArray = new ArrayList<Double>();
			
			tempArray.add(screenHight - test3 + (test4 * i));
			tempArray.add(test1);
			int[] RGBArray = getScreenColor(test1, screenHight - test3 + (test4 * i));
			
			for(int x = 0; x < RGBArray.length; x++){
				tempArray.add((double) (RGBArray[x]));
			}
			
			lifeGlobe.addPosition(tempArray);
		}
	}
	
	
	
	//Gets the screen color of a screen position and returns it as an array 0-255
	public int[] getScreenColor(Double x, Double y)
	{
		Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
		

		int  test = robot.getPixelColor(x.intValue(), y.intValue());
		

		int red =   (test >> 16) & 0xFF;
		int green = (test >>  8) & 0xFF;
		int blue =  (test      ) & 0xFF;

		int[] ColorArray = {red,green,blue};
		return ColorArray ;
	}
	
	/*
	 * Color value checker.
	 * checks the color value of the globe against the new color value of the globe
	 */
	public boolean checkColorValue(String valueCombo) {
		String Storedvalue = valueCombo.substring(0,1);
		int storeValueInt = Integer.parseInt(Storedvalue);
		int[] colorValues = getScreenColor(lifeGlobe.getPosition().get(9 - storeValueInt).get(1), 
				lifeGlobe.getPosition().get(9 - storeValueInt).get(0));
		double dist = calcDist(colorValues);

		
		// I might wanna rework this because it dont work 100% of the times
		if(dist < 40){
			fc.playSound();
		}
		
	
		return false;
	
		/*
		if(colorValues[0] != 186.0 && colorValues[0] != 91.0){
			if(colorValues[1] != 149.0 && colorValues[1] != 70.0){
				if(colorValues[1] != 107.0 && colorValues[1] != 45.0){
					return true;
				}
			}
		
		
		}
		*/
	}
	
	/*
	 * Gets the color distance stuff i dont really understand this 
	 */
	public double calcDist(int [] colorList){
		
		double red = colorList[0] - 186;
		double green = colorList[1] - 149;
		double blue = colorList[2] - 107;
		double dist = Math.sqrt(red * red + green * green +
				blue * blue);
		
		double red1 = colorList[0] - 91.0;
		double green1 = colorList[1] - 70.0;
		double blue1 = colorList[2] - 45.0;
		double dist1 = Math.sqrt(red1 * red1 + green1 * green1 +
				blue1 * blue1);
		return Math.min(dist, dist1) ;
	}
	
	public LifeGlobe getData(){
		return lifeGlobe;
	}
	
	public void setGlobe(ArrayList<ArrayList<Double>> data){
		this.lifeGlobe.setStoredPosition(data);
	}
	
	
}

