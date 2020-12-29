package com.ndr.free.java18.main;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.ndr.free.java18.model.SampleModel;

public class Lecture02Streams {

	
	
	public static void main(String[] args) {
		
		creatingStreams();
		
		List<SampleModel> sampleModelList = new ArrayList<>();
		sampleModelList.add(new SampleModel(1));
		sampleModelList.add(new SampleModel(3));
		sampleModelList.add(new SampleModel(2));
		sampleModelList.add(new SampleModel(4));
		
		operationsOnStreams(sampleModelList);
		
		parallelStreams(sampleModelList);
		
		traversingStreams(sampleModelList);
		
		listingStreamElements(sampleModelList);
		
		findingCountOfElementsInAStream(sampleModelList);
		
		truncatingStreams(sampleModelList);
		
		findingInStreams(sampleModelList);
		
		matchingInStreams(sampleModelList);
		
		sortingStreams(sampleModelList);
		
		slicingStreams(sampleModelList);
				
		convertingStreamsInToList(sampleModelList);
		
		mappingElementsOfStream(sampleModelList);
	
		flatteningStreams(sampleModelList);
		
		concatenatingStreamElements(sampleModelList);
		
		convertingStreams(sampleModelList);
		
		summarizingStreams(sampleModelList);
		
		groupingStreamsByProperty(sampleModelList);
		
		partitioningStreams(sampleModelList);
	}


	private static void partitioningStreams(List<SampleModel> sampleModelList) {
		Map<Boolean, List<SampleModel>> sampleModelListByBoolProperty1 = sampleModelList.stream().collect(Collectors.partitioningBy(SampleModel::isBoolProperty1));
		
		
		Map<Boolean, Map<String, List<SampleModel>>> sampleModelListByBoolProperty1ByStrProperty1 = sampleModelList.stream()
																									.collect(
																											Collectors.partitioningBy(SampleModel::isBoolProperty1, 
																																	  Collectors.groupingBy(SampleModel::getStrProperty1)
																																	 )
																											);
		
		Map<Boolean, SampleModel> sampleModelListPartitionedByHighestIntProperty1 = sampleModelList.stream()
						.collect(
								Collectors.partitioningBy(SampleModel::isBoolProperty1, 
														  Collectors.collectingAndThen(
																  					  Collectors.maxBy(
																  							  		   Comparator.comparingInt(SampleModel::getIntProperty1)
																  							  		   )
																  					  , Optional::get
																  					  )
														  )
								);
		
	}


	private static void summarizingStreams(List<SampleModel> sampleModelList) {
		int totalIntProperties = sampleModelList.stream().collect(Collectors.summingInt(SampleModel::getIntProperty1));
		
		double avgIntProperties = sampleModelList.stream().collect(Collectors.averagingInt(SampleModel::getIntProperty1));
		
		IntSummaryStatistics intPropertyStatistics = sampleModelList.stream().collect(Collectors.summarizingInt(SampleModel::getIntProperty1));
		
		int totalIntProperties2 = sampleModelList.stream().collect(Collectors.reducing(0, SampleModel::getIntProperty1, (intProperty1, intProperty2) -> intProperty1 + intProperty2));

		int totalIntProperties3 = sampleModelList.stream().collect(Collectors.reducing(0, SampleModel::getIntProperty1, Integer::sum));

		int totalIntProperties4 = sampleModelList.stream().map(SampleModel::getIntProperty1).reduce(Integer::sum).get();

		int totalIntProperties5 = sampleModelList.stream().mapToInt(SampleModel::getIntProperty1).sum(); //IntStream avoids auto-unboxing 

		
	}


	private static void creatingStreams() {
		//Streams from values
		Stream<String> stringStream = Stream.of("AAA ", "BBB", "CCC", "DDD");
		
		//Creating an empty stream
		Stream<String> emptyStream = Stream.empty();
		
		//Stream from nullable
		String sampleString = null;
		Stream<String> homeValueStream = Stream.ofNullable(sampleString); // Java 1.9
		
		//Creating Streams from arrays
		int[] numbers = {2, 3, 5, 7, 11, 13};
		IntStream intStream = Arrays.stream(numbers);
		
		//Creating Streams from files
		try(Stream<String> lines = Files.lines(Paths.get("<fileName"), Charset.defaultCharset())){
			//TODO 
		}
		catch(IOException e){
		}
		
		//Numeric Streams
		IntStream evenNumbers = IntStream.rangeClosed(1, 100)
				.filter(n -> n % 2 == 0); //Represents stream of even numbers
		System.out.println(evenNumbers.count());

		//Creating Streams from functions
		Stream.iterate(0, n -> n + 2)
				.limit(10)
				.forEach(System.out::println);
		
		Stream.generate(Math::random)
						.limit(5)
						.forEach(System.out::println);
	}


	private static void convertingStreams(List<SampleModel> sampleModelList) {
		//CONVERTING BACK TO A STREAM OF OBJECTS
		IntStream intStream = sampleModelList.stream().mapToInt(SampleModel::getIntProperty1);
		Stream<Integer> stream = intStream.boxed();

	}


	private static void listingStreamElements(List<SampleModel> sampleModelList) {
		sampleModelList.stream()
			.forEach(System.out::println);
	}


	private static void concatenatingStreamElements(List<SampleModel> sampleModelList) {
		
		String sortedUniqueStrProperties = sampleModelList.stream()
															.map(sampleModel -> sampleModel.getStrProperty1() )
															.distinct()
															.sorted()
															.reduce("", (str1, str2) -> str1 + str2);

		String sortedUniqueStrProperties2 = sampleModelList.stream()
															.map(sampleModel -> sampleModel.getStrProperty1() )
															.distinct()
															.sorted()
															.collect(joining());
		
		String allStrProperties = sampleModelList.stream().map(SampleModel::getStrProperty1).collect(Collectors.joining());
		
		String allStrPropertiesWithComma = sampleModelList.stream().map(SampleModel::getStrProperty1).collect(Collectors.joining(", "));
		
	}


	private static void sortingStreams(List<SampleModel> sampleModelList) {
		//sort by value (small to high)
		List<SampleModel> sampleModelList2 = sampleModelList.stream()
															.filter(sampleModel -> sampleModel.getIntProperty1() >= 50)
															.sorted(comparing(SampleModel::getIntProperty1))
															.collect(toList());
		
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


		//The highest int property
		Optional<Integer> highestIntProperty = sampleModelList.stream()
				.map(SampleModel::getIntProperty1)
				.reduce(Integer::max);

		//The SampleModel object with the highet int property
		Comparator<SampleModel> sampleModel15Comparator = Comparator.comparingInt(SampleModel::getIntProperty1);
		
		Optional<SampleModel> sampleModelWithHighestIntProperty = sampleModelList.stream()
											.collect(Collectors.maxBy(sampleModel15Comparator));
		
		Optional<SampleModel> sampleModelWithHighestIntProperty2 = sampleModelList.stream()
				.collect(Collectors.reducing( (sampleModel_1, sampleModel_2) -> sampleModel_1.getIntProperty1() > sampleModel_2.getIntProperty1() ? sampleModel_1 : sampleModel_2));
		
		//The SampleModel object with the smallest int property
		Optional<SampleModel> optSampleModelWithSmallestIntProperty = sampleModelList.stream()
				.reduce((sampleModel_1, sampleModel_2) -> sampleModel_1.getIntProperty1() < sampleModel_2.getIntProperty1() ? sampleModel_1 : sampleModel_2);

		Optional<SampleModel> optSampleModelWithSmallestIntProperty2 = sampleModelList.stream()
				.min(comparing(SampleModel::getIntProperty1));

		OptionalInt maxCalories = sampleModelList.stream()
				.mapToInt(SampleModel::getIntProperty1)
				.max();

		int max = maxCalories.orElse(1); // default maximum if there is no value
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
		
		List<String> uniqueStrPropertyList = sampleModelList.stream()
				.map(sampleModel -> sampleModel.getStrProperty1())
				.distinct()
				.collect(toList());
		
		Set<String> uniqueStrPropertySet = sampleModelList.stream()
				.map(sampleModel -> sampleModel.getStrProperty1())
				.collect(toSet());
		
		//MAPPING TO A NUMERIC STREAM
		int minIntProperty = sampleModelList.stream()
									.mapToInt(SampleModel::getIntProperty1)	
									.sum();
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
		List<SampleModel> sampleModelList2 = sampleModelList.stream()
				.filter(SampleModel::isBoolProperty1) 
				.distinct() //uses hashcode and equals methods of the stream's object
				.collect(toList());
		
		List<SampleModel> sampleModelList3 = sampleModelList.stream()
				.filter(sampleModel -> sampleModel.getIntProperty1() > 50 )
				.collect(Collectors.toList());
	}


	private static void findingCountOfElementsInAStream(List<SampleModel> sampleModelList) {
		long count1 = sampleModelList.stream().count();
		
		long count2 = sampleModelList.stream()
				.filter(sampleModel -> sampleModel.getIntProperty1() > 100)
				.distinct()
				.limit(3)
				.count();
		
		long count3 = sampleModelList.stream().collect(Collectors.counting());
		
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
		Map<String, List<SampleModel>> sampleModelListByStrProperty = sampleModelList.stream().collect(Collectors.groupingBy(SampleModel::getStrProperty1));
		
		Map<String, List<SampleModel>> sampleModelListByStrProperty2 = sampleModelList.stream()
																						.collect(groupingBy(SampleModel::getStrProperty1));
		
		
		
		Map<String, List<SampleModel>> sampleModel15ByGroup = sampleModelList.stream().collect( 		
											Collectors.groupingBy(sampleModel -> { if (sampleModel.getIntProperty1() < 50) 
																						return "GROUP1";
																						else return "GROUP2";
																					} 
											));
		
		//Manipulating grouped elements
		Map<String, List<SampleModel>> sampleModelByStrPropertyFiltered = sampleModelList.stream()
									.collect(Collectors.groupingBy(SampleModel::getStrProperty1, Collectors.filtering(sampleModel -> sampleModel.getIntProperty1() > 50), Collectors.toList()));
				
		Map<String, List<String>> strProperty2ListByStrProperty = sampleModelList.stream()
								.collect(Collectors.groupingBy(SampleModel::getStrProperty2, Collectors.mapping(SampleModel::getStrProperty2, Collectors.toList())));
				
		Map<String, Set<String>> strPropertyListByStrProperty = sampleModelList.stream()
								.collect(Collectors.groupingBy(SampleModel::getStrProperty, Collectors.flatMapping(sampleModel15 -> sampleModel15.getStrListProperty().stream(), Collectors.toSet())));
				
		
		//Multilevel grouping
		Map<String, Map<String, List<SampleModel>>> sampleModel15ByStrPropertyGroup = sampleModelList.stream()
												.collect(Collectors.groupingBy(SampleModel::getStrProperty1, Collectors.groupingBy(sampleModel -> {
																													if (sampleModel.getIntProperty1() < 50) 
																														return "GROUP1";
																													else return "GROUP2";
																											} )
																				)
														);
		
		//Collecting data in subgroups
		Map<String, Long> strPropertyCount = sampleModelList.stream().collect( Collectors.groupingBy(SampleModel::getStrProperty1, Collectors.counting()));
		
		
		Map<String, SampleModel> highestIntPropertyByStrProperty = sampleModelList.stream()
				.collect(Collectors.groupingBy(SampleModel::getStrProperty1
						, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(SampleModel::getIntProperty1))
								, Optional::get)));
		
		
		Map<String, Integer> totalIntPropertiesByStrProperty = sampleModelList.stream()
				.collect(Collectors.groupingBy(SampleModel::getStrProperty1
						, Collectors.summingInt(SampleModel::getIntProperty1)));


		Map<String, Set<String>> sampleModel15GroupBycaloricLevelsByType = sampleModelList.stream()
				.collect( Collectors.groupingBy(SampleModel::getStrProperty1
						, Collectors.mapping(sampleModel -> {
							if (sampleModel.getIntProperty1() < 50) 
								return "GROUP1";
							else return "GROUP2"; }
						, Collectors.toSet() )));

		Map<String, Set<String>> caloricLevelsByType = sampleModelList.stream()
				.collect( groupingBy(SampleModel::getStrProperty, sampleModel15(dish -> {
					if (sampleModel15.getIntProperty() < 50) 
						return "GROUP1";
					else return "GROUP2"; 
				},
						toCollection(HashSet::new) )));
		
	}
	
	private static void traversingStreams(List<SampleModel> sampleModelList) {
		Stream<SampleModel> sampleModelStream = sampleModelList.stream();
		sampleModelStream.forEach(System.out::println); 
		sampleModelStream.forEach(System.out::println); // stream is consumed, i.e. processed and closed
	}



	
}
