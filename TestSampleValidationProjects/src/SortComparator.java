import java.util.Comparator;


public class SortComparator implements Comparator<PlayerComparable> {

	@Override
	public int compare(PlayerComparable arg0, PlayerComparable arg1) {
		PlayerComparable p1 = arg0;
		PlayerComparable p2 = arg1;
		
		if(p1 == p2){
			return 0;
		}else{
			return p1.getAge() - p2.getAge();
		}
	}

}
