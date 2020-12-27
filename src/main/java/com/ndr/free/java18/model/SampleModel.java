package com.ndr.free.java18.model;

public class SampleModel {

	private String strProperty1;
	private String strProperty2;
	
	public SampleModel() {
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

	@Override
	public String toString() {
		return "SampleModel [strProperty1=" + strProperty1 + ", strProperty2=" + strProperty2 + "]";
	}
	
	
	
	
}
