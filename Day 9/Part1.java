import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			LinkedList<Integer> prev25 = new LinkedList<Integer>();
			int curNum;
			
			
			//First read the first 25 lines to initialize the list
			for(int i = 0; i < 25; i++) {
				prev25.addLast(Integer.parseInt(sc.nextLine()));
			}
			
			//Now loop through the rest and check if there is a sum in the list prev25
			while(sc.hasNextLine()) {
				curNum = Integer.parseInt(sc.nextLine());
				if(NumHasSumInList(curNum, prev25)) {
					//Add this to the list
					prev25.removeFirst();
					prev25.add(curNum);
				}
				else {
					System.out.println("No property for number: " + curNum);
					break;
				}
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean NumHasSumInList(int num, LinkedList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = i+1; j < list.size(); j++) {
				if(list.get(i) + list.get(j) == num) {
					System.out.println("" + list.get(i) + " + " + list.get(j) + " = " + num);
					return true;
				}
			}
		}
		return false;
	}
}
