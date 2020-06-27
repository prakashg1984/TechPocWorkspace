
public class RainWaterHarvest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] coordinates = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(findTrapWater(coordinates));
	}

	private static int findTrapWater(int[] coordinates){
		int totalTrap = 0;
		int size = coordinates.length;
		
		for(int i=0;i<size;i++){
			System.out.println(" i "+i);
			int maxLeft = 0;
			int maxRight = 0;
			
			//Search Left of I
			for(int j=i;j>=0;j--){
				maxLeft = Math.max(maxLeft, coordinates[j]);
			}
			
			//Search Right of I
			for(int j=i;j<size;j++){
				maxRight = Math.max(maxRight, coordinates[j]);
			}
			System.out.println(" coordinates[i] "+coordinates[i]);
			System.out.println(" maxLeft "+maxLeft);
			System.out.println(" maxRight "+maxRight);

			System.out.println("This trap : ");
			System.out.println(Math.min(maxLeft,maxRight) - coordinates[i]);
			
			totalTrap += Math.min(maxLeft,maxRight) - coordinates[i];
		}
		
		
		return totalTrap;
	}
}
