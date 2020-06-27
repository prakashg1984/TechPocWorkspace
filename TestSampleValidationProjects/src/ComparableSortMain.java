import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ComparableSortMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<PlayerComparable> listPlayer = new ArrayList<PlayerComparable>();
		
		PlayerComparable p1 = new PlayerComparable(34,"PG");
		PlayerComparable p2 = new PlayerComparable(30,"PS");
		PlayerComparable p3 = new PlayerComparable(60,"AKG");
		
		listPlayer.add(p1);
		listPlayer.add(p2);
		listPlayer.add(p3);
		
		//Desc
		Collections.sort(listPlayer);
		System.out.println(listPlayer.toString());
		
		//Asc
		Collections.sort(listPlayer, new SortComparator());
		System.out.println(listPlayer.toString());
	}

}
