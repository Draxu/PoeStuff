package CTRL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Model.LifeGlobe;
import application.Start;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class FunctionController {

	
	public FunctionController(){
		
	}

	public void playSound(){
	 URL location = Start.class.getProtectionDomain().getCodeSource().getLocation();
	 
	 String Path = location.toString() + "/Sounds/Optagelse1.mp3";;
	 Media pick = new Media(Path);
     MediaPlayer player = new MediaPlayer(pick);

     player.play();

	}
	
	//Write to "Database File"
	//Need this function if other screen size is wanted.
	/*
	public void writeToFile(LifeGlobe data) throws IOException{
		BufferedWriter writer = null;
		
		URL location = Start.class.getProtectionDomain().getCodeSource().getLocation();
		String locationSub = location.toString().substring(6, location.toString().length());
		File storeData = new File(locationSub + "Database/StoredData.txt");

		writer = new BufferedWriter(new FileWriter(storeData));
		for(ArrayList<Double> array: data.getPosition()){
			for(Double value : array){
				writer.write(value.toString());
				writer.newLine();
			}
		}
		
		writer.close();
		
	}
	*/
	//Read from "StoreData.txt" file 
	public ArrayList<ArrayList<Double>> readFromFile() throws FileNotFoundException {
		URL location = Start.class.getProtectionDomain().getCodeSource().getLocation();
		String locationSub = location.toString().substring(6, location.toString().length());
		File storeData = new File(locationSub + "Database/StoredData.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(storeData));
		     ArrayList<ArrayList<Double>> arrayReturn = new ArrayList<ArrayList<Double>>();
			 String line;
			    
			      for(int i = 0; i < 10; i++){
			    	  ArrayList<Double> tempArray = new ArrayList<Double>();
			    	  for(int x = 0; x < 5; x++){
			    		  if ((line = br.readLine()) != null) {
			    			  tempArray.add(Double.parseDouble(line));
			    			  
			    		  }
			    		 
			    	  }
			    	  arrayReturn.add(tempArray);
			    		  
			      }

			    return arrayReturn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
