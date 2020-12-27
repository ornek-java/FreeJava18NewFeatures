package com.ndr.free.java18.main;

import com.ndr.free.java18.model.BiFunctionFunctionalInterface;
import com.ndr.free.java18.model.ComsumerFunctionalInterface;
import com.ndr.free.java18.model.FunctionFunctionalInterface;
import com.ndr.free.java18.model.SupplierFunctionalInterface;

public class Lecture01LampdaExpressions {

	public static void main(String[] args) {
		Runnable runnable = () -> System.out.println("Hello World!");
		runnable.run();
		
		SupplierFunctionalInterface supplier = () -> Math.PI;
		System.out.println(supplier.get());
		
		ComsumerFunctionalInterface consumer = (strValue) -> System.out.println(strValue);
		consumer.accept("Hello World!");
		
		FunctionFunctionalInterface function = (int nValue) -> nValue * nValue;
		System.out.println(function.apply(2));
		
		passingFunctionalInterface( (nValue1, nValue2) -> {return nValue1 > nValue2;} );
		
	}
	
	private static void passingFunctionalInterface(BiFunctionFunctionalInterface biFunction){
		System.out.println(biFunction.apply(3,5));
	}
}
