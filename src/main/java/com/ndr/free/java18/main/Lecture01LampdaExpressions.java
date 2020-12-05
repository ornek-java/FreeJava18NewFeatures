package com.ndr.free.java18.main;

import com.ndr.free.java18.model.SampleInterface01_3;
import com.ndr.free.java18.model.SampleInterface01_4;
import com.ndr.free.java18.model.SampleInterface01_5;
import com.ndr.free.java18.model.SupplierFunctionalInterface;

public class Lecture01LampdaExpressions {

	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("Hello World!");
		runnable.run();
		
		SupplierFunctionalInterface supplier = () -> Math.PI;
		System.out.println(supplier.get());
		
		SampleInterface01_3 sampleInterface1_3 = (strValue) -> System.out.println(strValue);
		sampleInterface1_3.sampleMethod1("Hello World!");
		
		SampleInterface01_4 sampleInterface1_4 = (int nValue) -> nValue * nValue;
		System.out.println(sampleInterface1_4.sampleMethod1(2));
		
		sampleMethod1( (nValue1, nValue2) -> {return nValue1 > nValue2;} );
		
	}
	
	private static void sampleMethod1(SampleInterface01_5 sampleInterface1_5){
		System.out.println(sampleInterface1_5.sampleMethod1(3,5));
	}
}
