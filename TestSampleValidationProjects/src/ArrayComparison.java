import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArrayComparison {

	public static void main(String[] args) {
		Integer [] a1 = {1,2,3,4,5};
		Integer [] a2 = {1,2,3,4,5,4,5};
		Integer [] a3 = {1,2,3,4};
		
		Set<Object> a1S = new HashSet<>(Arrays.asList(a1));
		Set<Object> a2S = new HashSet<>(Arrays.asList(a2));
		Set<Object> a3S = new HashSet<>(Arrays.asList(a3));
		
		List<Object> output = a1S
        .stream()
		.filter(temp -> !a3S.contains(temp))
        .collect(Collectors.toList());
		
		System.out.println(output);

		if(output.size() == 0) {
			System.out.println("Matches");
		}
	}

}

