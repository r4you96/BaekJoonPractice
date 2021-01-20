import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SevenDwarf2309 {
	ArrayList<Integer> dwarfList = new ArrayList<>();
	int[] dwarfArray = new int[9];
	
	public boolean dwarfSearch(int count, int sum) {
		int i;
		
		if(dwarfList.size() == 7) {
			/*for(int height : dwarfList)
				System.out.print(height+ " ");
			System.out.println();
			System.out.println(sum);*/
			
			if(sum==100) {
				dwarfList.sort(Comparator.naturalOrder());
				for(int height : dwarfList)
					System.out.println(height);
				
				return true;
			}else
				return false;
		}
		
		for(i=count+1; i<9; i++) {
			dwarfList.add(dwarfArray[i]);
			
			if(dwarfSearch(count+1, sum + dwarfArray[i]))
				return true;
			
			dwarfList.remove(count+1);
		}
		
		return false;
	}
	public void setNum() {
		int i;
		Scanner sc = new Scanner(System.in);
		
		for(i= 0; i<9; i++) {
			dwarfArray[i] = sc.nextInt();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SevenDwarf2309 main = new SevenDwarf2309();
		main.setNum();
		main.dwarfSearch(-1, 0);
	}

}
