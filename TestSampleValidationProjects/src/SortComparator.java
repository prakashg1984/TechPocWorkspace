import java.util.Comparator;


public class SortComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		PlayerComparable p1 = (PlayerComparable) arg0;
		PlayerComparable p2 = (PlayerComparable) arg1;
		
		if(p1 == p2){
			return 0;
		}else{
			return p1.getAge() - p2.getAge();
		}
	}

}
