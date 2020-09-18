

public class Player implements Comparable{

	public Player(String name,int age){
		this.name=name;
		this.age=age;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private int age;
	
	public int compareTo(Object arg0) {
		Player obj = (Player) arg0;
		if(this.age == obj.age){
			return 0;
		}else{
			return obj.age - this.age ;
		}
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", age=" + age + "]";
	}
	

}
