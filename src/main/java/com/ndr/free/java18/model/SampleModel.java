package com.ndr.free.java18.model;

public class SampleModel {

	private Integer intProperty1;
	private String strProperty1;
	private String strProperty2;
	private boolean boolProperty1;
	
	
	public SampleModel() {
	}
	
	public SampleModel(Integer intProperty1) {
		this.intProperty1 = intProperty1;
	}
	
	public SampleModel(String strParam1) {
		this.strProperty1 = strParam1;
	}
	
	public SampleModel(String strParam1, String strParam2 ) {
		this.strProperty1 = strParam1;
		this.strProperty2 = strParam2;
	}
	

	public String getStrProperty1() {
		return strProperty1;
	}

	public String getStrProperty2() {
		return strProperty2;
	}

	public Integer getIntProperty1() {
		return intProperty1;
	}

	public boolean isBoolProperty1() {
		return boolProperty1;
	}

	public void setBoolProperty1(boolean boolProperty1) {
		this.boolProperty1 = boolProperty1;
	}

	@Override
	public String toString() {
		return "SampleModel [intProperty1=" + intProperty1 + ", strProperty1=" + strProperty1 + ", strProperty2="
				+ strProperty2 + ", boolProperty1=" + boolProperty1 + "]";
	}

	
	
	
	
}
