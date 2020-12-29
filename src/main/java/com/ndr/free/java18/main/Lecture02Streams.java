package com.ndr.free.java18.main;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ndr.free.java18.model.SampleModel;
import com.ndr.samples.java18.model.SampleModel12;
import com.ndr.samples.java18.model.SampleModel13;

public class Lecture02Streams {

	
	
	public static void main(String[] args) {
		List<SampleModel> sampleModelList = new ArrayList<>();
		sampleModelList.add(new SampleModel(1));
		sampleModelList.add(new SampleModel(3));
		sampleModelList.add(new SampleModel(2));
		sampleModelList.add(new SampleModel(4));
		
		operationsOnStreams(sampleModelList);
		
		parallelStreams(sampleModelList);
		
		traversingStreams(sampleModelList);
		
		findingCountOfElementsInAStream(sampleModelList);
		
		truncatingStreams(sampleModelList);
		
		findingInStreams(sampleModelList);
		
		matchingInStreams(sampleModelList);
				
		convertingStreamsInToList(sampleModelList);
		
		mappingElementsOfStream(sampleModelList);
				
		groupingStreamsByProperty(sampleModelList);
		
		slicingStreams(sampleModelList);
		
		flatteningStreams(sampleModelList);
		
	}


	private static void matchingInStreams(List<SampleModel> sampleModelList) {
		boolean isAllMatch = sampleModelList.stream().allMatch(sampleModel -> sampleModel.getIntProperty1() < 50); //checks if all the elements in the stream match the predicate
		
		boolean isNoneMatch = sampleModelList.stream().noneMatch(sampleModel -> sampleModel.getIntProperty1() >= 50); //checks if there are no elements in the stream that the predicate
		
	}


	private static void findingInStreams(List<SampleModel> sampleModelList) {
		boolean isAnyMatch = sampleModelList.stream().anyMatch(SampleModel::isBoolProperty1); // returns true if there is an element in the stream matching the predicate
		
		
		Optional<SampleModel> optSampleModel = sampleModelList.stream()
				.filter(SampleModel::isBoolProperty1)
				.findAny(); //returns the first element of the stream that matches the predicate(short-circuiting)
		
	}


	private static void flatteningStreams(List<SampleModel> sampleModelList) {
		List<String> uniqueCharacters = sampleModelList.stream()
				.map(sampleModel -> sampleModel.getStrProperty1().split("")) 
				.flatMap(Arrays::stream) 
				.distinct()
				.collect(toList());
		
		/*
		sampleModelList.stream()											-> 		Stream<SampleModel>
		map(sampleModel -> sampleModel.getStrProperty1().split("")) 		-> 		Stream<String[]>
		Arrays::stream														-> 		Stream<Stream<String>>
		flatMap(Arrays::stream) 											->		Stream<String>
		distinct() 															->		Stream<String>
		List<String> 														-> 		collect(toList())
	*/
	}


	private static void mappingElementsOfStream(List<SampleModel> sampleModelList) {
		List<String> sampleModelList2 = sampleModelList.stream()
				.map(SampleModel::getStrProperty1) //applies function to each element of the stream
				.collect(toList());
		
	}


	private static void truncatingStreams(List<SampleModel> sampleModelList) {
		List<SampleModel> sampleModelList2 = sampleModelList.stream()
				.filter(sampleModel -> sampleModel.getIntProperty1() < 100)
				.limit(3) 
				.collect(toList());
	}


	/**
	 * Java 1.9 new features 
	 */
	private static void slicingStreams(List<SampleModel> sampleModelList) {
		List<SampleModel> sampleModel12List2 = sampleModelList.stream()
				.takeWhile(sampleModel12 -> sampleModel12.getIntProperty() <= 100) //stops when an element is found to not match 
				.collect(Collectors.toList());

		List<SampleModel> sampleModel12List3 = sampleModelList.stream()
				.dropWhile(sampleModel12 -> sampleModel12.getIntProperty() > 100) //when an element is found to match stops and returns all remaining elements 
				.collect(Collectors.toList());

	}


	private static void convertingStreamsInToList(List<SampleModel> sampleModelList) {
		List<SampleModel> sampleModel1List2 = sampleModelList.stream()
				.filter(SampleModel::isBoolProperty1) //Method reference
				.distinct() //according to the hashcode and equals methods of the stream's object
				.collect(toList());
		
	}


	private static void findingCountOfElementsInAStream(List<SampleModel> sampleModelList) {
		long count = sampleModelList.stream()
				.filter(sampleModel -> sampleModel.getIntProperty1() > 100)
				.distinct()
				.limit(3)
				.count();
		
	}


	private static void operationsOnStreams(List<SampleModel> sampleModelList) {
		List<String> sampleModelList2 = sampleModelList.stream()
														.filter(sampleModel -> sampleModel.getIntProperty1() < 100) // Intermediate Operation
														.sorted(comparing(SampleModel::getIntProperty1)) // Intermediate Operation
														.map(SampleModel::getStrProperty1) // Intermediate Operation
														.limit(5) // Intermediate Operation
														.collect(toList()); // Terminal Operation : produce a result(non-stream value) from a stream pipeline
	}
	
	private static void parallelStreams(List<SampleModel> sampleModelList) {
		List<String> sampleModelList2 = sampleModelList.parallelStream() //to execute in parallel in multicore environment 
														.filter(sampleModel -> sampleModel.getIntProperty1() < 100)
														.sorted(comparing(SampleModel::getIntProperty1))
														.map(SampleModel::getStrProperty1)
														.limit(5)
														.collect(toList());
		
	}
	
	private static void groupingStreamsByProperty(List<SampleModel> sampleModelList) {
		Map<String, List<SampleModel>> sampleModelListByStrProperty = sampleModelList.stream()
																						.collect(groupingBy(SampleModel::getStrProperty1));
		
	}
	
	private static void traversingStreams(List<SampleModel> sampleModelList) {
		Stream<SampleModel> sampleModelStream = sampleModelList.stream();
		sampleModelStream.forEach(System.out::println); 
		sampleModelStream.forEach(System.out::println); // stream is consumed, i.e. processed and closed
	}



	
}
