
public class PlayerComparable implements Comparable {

	private int age;
	private String name;
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerComparable(int age,String name){
		this.age = age;
		this.name = name;
	}
	
	@Override
	public int compareTo(Object arg0) {
		PlayerComparable newPlayer = (PlayerComparable) arg0;
		if(this.age == newPlayer.age){
			return 0;
		}else{
			//Desc sort
			return  newPlayer.age - this.age ;
		}
	}

	@Override
	public String toString() {
		return "PlayerComparable [age=" + age + ", name=" + name + "]";
	}

}
