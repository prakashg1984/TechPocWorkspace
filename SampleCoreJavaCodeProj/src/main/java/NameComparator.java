import java.util.Comparator;


public class NameComparator implements Comparator {

	public int compare(Object arg0, Object arg1) {
		Player p1 = (Player) arg0;
		Player p2 = (Player) arg1;
		
		if(p1.getName().equalsIgnoreCase(p2.getName())){
			return 0;
		}else{
			return p2.getName().compareTo(p1.getName());
		}
	}

}
