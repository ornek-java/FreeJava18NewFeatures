package com.ndr.free.java18.main;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;

import com.ndr.free.java18.model.BiFunctionFunctionalInterface;
import com.ndr.free.java18.model.ComsumerFunctionalInterface;
import com.ndr.free.java18.model.FunctionFunctionalInterface;
import com.ndr.free.java18.model.SampleModel;
import com.ndr.free.java18.model.SupplierFunctionalInterface;

public class Lecture01LampdaExpressions {

	
	
	public static void main(String[] args) {
		
		builtInFunctionalInterfaces();
		
		customFunctionalIntefaces();
		
		passingFunctionalInterface( (nValue1, nValue2) -> {return nValue1 > nValue2;} );
		
		methodReferences();
		
		constructerReferences();
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
	
	private static void customFunctionalIntefaces() {
		SupplierFunctionalInterface supplier = () -> Math.PI;
		System.out.println(supplier.get());
		
		ComsumerFunctionalInterface consumer = (strValue) -> System.out.println(strValue);
		consumer.accept("Hello World!");
		
		FunctionFunctionalInterface function = (int nValue) -> nValue * nValue;
		System.out.println(function.apply(2));
		
	}

	private static void passingFunctionalInterface(BiFunctionFunctionalInterface biFunction){
		System.out.println(biFunction.apply(3,5));
		
		ArrayList<SampleModel> sampleModelList = new ArrayList<>();
		sampleModelList.add(new SampleModel(1));
		sampleModelList.add(new SampleModel(3));
		sampleModelList.add(new SampleModel(2));
		sampleModelList.add(new SampleModel(4));
		
		sampleModelList.sort( (s1,s2) -> s2.getIntProperty1().compareTo(s1.getIntProperty1())  );
		sampleModelList.forEach(s -> System.out.println("1. " + s.getIntProperty1()));
		
		sampleModelList.sort( comparing(s1 -> s1.getIntProperty1()) );
		sampleModelList.forEach(s -> System.out.println("2. " + s.getIntProperty1()));
		
		sampleModelList.sort( comparing(SampleModel::getIntProperty1).reversed() );
		sampleModelList.forEach(s -> System.out.println("3. " + s.getIntProperty1()));
	}
	
	private static void methodReferences() {
		BiFunction<Double,Double,Double> function1 = Math::pow;
		System.out.println(function1.apply(new Double(2), new Double(4)));
		
		function1 = (x,y) -> { double z = Math.pow(x, y); 
								return z; 
							};
		System.out.println(function1.apply(new Double(2), new Double(4)));
		
		
		
	}
	
	private static void constructerReferences() {
		Supplier<SampleModel> supplier3 = SampleModel::new;
		SampleModel sampleModel = supplier3.get();
		System.out.println(sampleModel);

		Function<String, SampleModel> function2 = SampleModel::new;
		sampleModel = function2.apply("Hello world-1!");
		System.out.println(sampleModel);

		BiFunction<String, String, SampleModel> function3 = SampleModel::new;
		sampleModel = function3.apply("Hello", "World-2");
		System.out.println(sampleModel);

	}
}
