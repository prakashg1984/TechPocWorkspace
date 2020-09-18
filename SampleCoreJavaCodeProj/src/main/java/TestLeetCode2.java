import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestLeetCode2 {

	public static void main(String[] args) {
		
//https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/
		List<List<String>> orderTables = new ArrayList<List<String>>();
		
		List<String> table1 = new ArrayList<String>();
		table1.add("David");
		table1.add("3");
		table1.add("Ceviche");
		
		List<String> table2 = new ArrayList<String>();
		table2.add("Corina");
		table2.add("10");
		table2.add("Beef Burrito");

		List<String> table3 = new ArrayList<String>();
		table3.add("David");
		table3.add("3");
		table3.add("Fried Chicken");

		List<String> table4 = new ArrayList<String>();
		table4.add("Rous");
		table4.add("3");
		table4.add("Ceviche");
		
		List<String> table5 = new ArrayList<String>();
		table5.add("Carla");
		table5.add("5");
		table5.add("Water");

		orderTables.add(table1);
		orderTables.add(table2);
		orderTables.add(table3);
		orderTables.add(table4);
		orderTables.add(table5);

		System.out.println("orderTables "+orderTables);
		
		findOutPut(orderTables);
	}
	
	
	private static List<List<String>> findOutPut(List<List<String>> orderTables) {
		List<List<String>> outPut = new ArrayList<List<String>>();
		
		List<String> tableDish = new ArrayList<String>();
		Map<Integer,List<String>> tableCountMap = new HashMap<Integer,List<String>>();

		
		orderTables.forEach(orderTable->{
			orderTable.forEach(table->{
				if(orderTable.indexOf(table) == 2){
					if(tableDish.indexOf(table) == -1) {
						System.out.println("table "+table);
						tableDish.add(table);
					}
				}
			});
		});
		
		Collections.sort(tableDish);
		tableDish.add(0,"Table");

		
		outPut.add(tableDish);
		
		for(List<String> orderTable : orderTables) {
			String tableNr = orderTable.get(1).toString();
			String dish = orderTable.get(2).toString();
			int index = tableDish.indexOf(dish)-1;
			List<String> countTable;

			System.out.println("Table Nr "+tableNr);
			System.out.println("dish "+dish);
			System.out.println("index "+index);
			System.out.println("tableDish.size() "+tableDish.size());
			
			if(tableCountMap.containsKey(Integer.valueOf(tableNr))) {
				countTable = tableCountMap.get(Integer.valueOf(tableNr));
				System.out.println("countTable "+countTable);
			}else {
				countTable = new ArrayList<String>();
				for(int i=0;i<tableDish.size()-1;i++) {
					countTable.add(i, 0 + "");
				}
			}
			
			System.out.println("countTable.size() "+countTable.size());

			if(countTable.size() >= index && countTable.get(index) != null) {
				int count = Integer.valueOf(countTable.get(index).toString());
				System.out.println("count before "+count);
				countTable.remove(index);
				countTable.add(index, ++count + "");
			}else {
				countTable.remove(index);
				countTable.add(index, 1 + "");
			}
			
			System.out.println("Adding count table for "+tableNr + " : "+countTable);
			tableCountMap.put(Integer.valueOf(tableNr), countTable);
			
		}
		
		System.out.println("tableCountMap "+tableCountMap);

		
		Map<Integer, List<String>> sortedMap = new TreeMap<Integer, List<String>>(tableCountMap);

		
		System.out.println("tableCountMap "+sortedMap);
		
		sortedMap.forEach((key,value)->{
			value.add(0, key.toString());
			outPut.add(value);
		});
		
		System.out.println("outPut "+outPut);
		
		return outPut;
	}

}
