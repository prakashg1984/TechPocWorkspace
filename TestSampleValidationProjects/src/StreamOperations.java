import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamOperations {

	public static void main(String[] args) {
		
		//###Int Operations####
		List<Integer> list = new ArrayList<Integer>(); 
        for(int i = 1; i< 10; i++){
            list.add(i);
        }
        
        //FOREACH
        list.stream().forEach(data -> System.out.println(data));
        
        //COLLECT
        List<Integer> evenNumbersList = list.stream().filter(i -> i%2 == 0)
                .collect(Collectors.toList());
        System.out.print(evenNumbersList);
        
        list.stream().filter(i -> i%2 == 0).reduce((a,b)->a+b)
        .ifPresent(s -> System.out.println("Sum of all Even Nrs : "+s));
        
        //FILTER
        Integer[] evenNumbersArr = list.stream().filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
        
        //USING PARALLELSTREAM
        Integer[] evenNumbersArrParallel = list.parallelStream().filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArrParallel);

        //###String Operations####
        
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        
        //Filter
        memberNames.stream().filter((s) -> s.startsWith("A"))
        .forEach(System.out::println);
        
        //MAP
        memberNames.stream().filter((s) -> s.startsWith("A"))
        .map(String::toUpperCase)
        .forEach(System.out::println);         
        
        //SORTED
        memberNames.stream().sorted()
        .map(String::toUpperCase)
        .forEach(System.out::println);
        
        //COLLECT
        List<String> memNamesInUppercase = memberNames.stream().sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.print(memNamesInUppercase);
        
        //MATCH - ANY, ALL, NONE
        boolean matchedResult = memberNames.stream()
                .anyMatch((s) -> s.startsWith("A"));
        System.out.println(matchedResult);
         
        matchedResult = memberNames.stream()
                .allMatch((s) -> s.startsWith("A"));
        System.out.println(matchedResult);
         
        matchedResult = memberNames.stream()
                .noneMatch((s) -> s.startsWith("A"));
        System.out.println(matchedResult);
        
        //COUNT
        long totalMatched = memberNames.stream()
        	    .filter((s) -> s.startsWith("A"))
        	    .count();
       System.out.println(totalMatched);
       
       //REDUCE
       Optional<String> reduced = memberNames.stream().sorted().map(String::toUpperCase)
    	        .reduce((s1,s2) -> s1 + "#" + s2);
    	reduced.ifPresent(System.out::println);
    	
    	//FINDFIRST
    	String firstMatchedName = memberNames.stream()
                .filter((s) -> s.startsWith("L"))
                .findFirst().get();
    	System.out.println(firstMatchedName);
    	
    	//FINDFIRST-Optional
    	Optional<String> firstMatchedNameOp = memberNames.stream().map(String::toLowerCase)
                .filter((s) -> s.startsWith("l"))
                .findFirst();
    	firstMatchedNameOp.ifPresent(System.out::println);
	}

}
