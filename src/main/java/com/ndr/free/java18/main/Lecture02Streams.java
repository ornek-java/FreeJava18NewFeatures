package com.ndr.free.java18.main;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ndr.free.java18.model.SampleModel;

public class Lecture02Streams {

	
	
	public static void main(String[] args) {
		List<SampleModel> sampleModelList = new ArrayList<>();
		
		operationsOnStreams(sampleModelList);
		
		parallelStreams(sampleModelList);
		
		groupingStreamsByProperty(sampleModelList);
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
	
	
}
