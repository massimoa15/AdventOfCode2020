import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Part2 {
	static int addend1Pos, addend2Pos;
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			LinkedList<Long> input = new LinkedList<Long>();
			LinkedList<Long> prev25 = new LinkedList<Long>();
			long curNum = 0;
			long rollingSum = 0;
			long wantedNum = 0;
			
			while(sc.hasNextLine()) {
				input.add(Long.parseLong(sc.nextLine()));
			}
			
			//First read the first 25 lines to initialize the list
			for(int i = 0; i < 25; i++) {
				prev25.addLast(input.get(i));
			}
			
			//Now loop through the rest and check if there is a sum in the list prev25
			for(int i = 25; i < input.size(); i++) {
				curNum = input.get(i);
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
			
			//Now, curNum is the error number. Need to find x consecutive numbers that sum to curNum
			wantedNum = curNum;
			int startContSetPos = -1; 
			int endContSetPos = -1;
			for(int i = 0; i < input.size(); i++) {
				rollingSum = input.get(i);
				for(int j = i+1; j < input.size(); j++) {
					rollingSum += input.get(j);
					if(rollingSum == wantedNum) {
						startContSetPos = i;
						endContSetPos = j;
						break;
					}
					if(rollingSum > wantedNum) {
						break;
					}
				}
				if(endContSetPos != -1) {
					break;
				}
			}
			
			//Find min and max in that range
			long min = input.get(startContSetPos);
			long max = input.get(endContSetPos);
			
			for(int i = startContSetPos; i <= endContSetPos; i++) {
				if(input.get(i) < min)
					min = input.get(i);
				if(input.get(i) > max)
					max = input.get(i);
			}
			
			System.out.println(min + max);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean NumHasSumInList(long num, LinkedList<Long> list) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = i+1; j < list.size(); j++) {
				if(list.get(i) + list.get(j) == num) {
					addend1Pos = i;
					addend2Pos = j;
					System.out.println("" + list.get(i) + " + " + list.get(j) + " = " + num);
					return true;
				}
			}
		}
		return false;
	}
}
