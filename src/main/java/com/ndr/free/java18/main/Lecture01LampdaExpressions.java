package com.ndr.free.java18.main;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;

import com.ndr.free.java18.model.BiFunctionFunctionalInterface;
import com.ndr.free.java18.model.ComsumerFunctionalInterface;
import com.ndr.free.java18.model.FunctionFunctionalInterface;
import com.ndr.free.java18.model.SupplierFunctionalInterface;

public class Lecture01LampdaExpressions {

	
	public static void main(String[] args) {
		
		builtInFunctionalInterfaces();
		
		customFunctionalIntefaces();
		
		passingFunctionalInterface( (nValue1, nValue2) -> {return nValue1 > nValue2;} );
	}
	
	private static void customFunctionalIntefaces() {
		SupplierFunctionalInterface supplier = () -> Math.PI;
		System.out.println(supplier.get());
		
		ComsumerFunctionalInterface consumer = (strValue) -> System.out.println(strValue);
		consumer.accept("Hello World!");
		
		FunctionFunctionalInterface function = (int nValue) -> nValue * nValue;
		System.out.println(function.apply(2));
		
	}

	private static void builtInFunctionalInterfaces() {
		Runnable runnable = () -> System.out.println("Hello World!");
		runnable.run();
		
		Predicate<Integer> predicate1 = nValue -> nValue > 10;
		System.out.println(predicate1.test(3));
		
		Supplier<Double> supplier1 = () -> Math.PI;
		System.out.println(supplier1.get());
		
		Consumer<Double> consumer1 = (x) -> System.out.println(x);
		consumer1.accept(Math.PI);
		
		Comparator<String> comparator1 = (strValue1, strValue2) -> strValue1.compareTo(strValue2);
		System.out.println(comparator1.compare("ABC", "abc"));
		
		Function<Double, Double> function1 = (x) -> x * x;
		System.out.println(function1.apply((double) 3));
		
		ToDoubleBiFunction<Double, Double> biFunction1 = (x,y) -> x * y;
		System.out.println(biFunction1.applyAsDouble(new Double(3), new Double(4) ));
		
	}

	private static void passingFunctionalInterface(BiFunctionFunctionalInterface biFunction){
		System.out.println(biFunction.apply(3,5));
	}
}
