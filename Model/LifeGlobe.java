package Model;

import java.util.ArrayList;

public class LifeGlobe {

	private ArrayList<ArrayList<Double>> storedPositions;
	
	
	private String globeType;
	
	public LifeGlobe()
	{
		// this array holds [x , y] * x
		storedPositions = new ArrayList<ArrayList<Double>>();
	}
	public void setStoredPosition(ArrayList<ArrayList<Double>> storedPositions){
		this.storedPositions = storedPositions;
	}
	
	public void addPosition(ArrayList<Double> test){
		storedPositions.add(test);
	}
	
	public ArrayList<ArrayList<Double>> getPosition(){
		return storedPositions;
	}
	
	
	//Sets the stored globeType
	public void setGlobeType(String type){
		this.globeType = type;
	}

	//Returns the stored globeType
	public String getGlobeType(){
		return globeType;
	}
	
}
