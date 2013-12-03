package com.LogVisualization.Bean;

public class GraphFields {
	private String[] key;
	private String[] value;
	private int numOfFields;
	private int step;
	private String unit;
	
	public void setKey(String[] val){
		this.key = val;
	}
	public String[] getKey(){
		return this.key;
	}
	
	public void setValue(String[] val){
		this.value = val;
	}
	public String[] getValue(){
		return this.value;
	}
	
	public void setNumOfFields(int val){
		this.numOfFields = val;
	}
	public int getNumOfFields(){
		return this.numOfFields;
	}
	
	public void setStep(int val) {
		this.step = val;
	}
	public int getStep(){
		return this.step;
	}
	
	public void setUnit(String val){
		this.unit = val;
	}
	public String getUnit(){
		return this.unit;
	}
}
