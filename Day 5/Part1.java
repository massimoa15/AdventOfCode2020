import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		int rowNum = 0;
		int colNum = 0;
		int highestID = 0;
		String inputLine;
		
		int curMin, curMax, range;
		
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			
			//Loop through every line of code, for every seat on the plane
			while(sc.hasNextLine()) {
				inputLine = sc.nextLine();
				curMin = 0;
				curMax = 127;
				
				//Loop through chars in this input that relate to row number first
				for(int i = 0; i < 6; i++) {
					//The current range
					range = curMax - curMin + 1;
					
					//Front half of range, so bottom half. Subtract the half range from the max
					if(inputLine.charAt(i) == 'F') {
						curMax -= range/2;
					}
					//Back, so bottom half. Add the half range to the min
					else if (inputLine.charAt(i) == 'B'){
						curMin += range/2;
					}
					System.out.println(inputLine.charAt(i) + ": (" + curMin + ", " + curMax + ")");
				}
				//Now you are at the last F/B, so the row# is the max if the char is F, or the min if the char is B
				if(inputLine.charAt(6) == 'F') {
					rowNum = curMin;
				}
				else {
					rowNum = curMax;
				}
				curMin = 0;
				curMax = 7;
				//Loop through & determine column #
				for (int i = 7; i < inputLine.length()-1; i++) {
					//The current range
					range = curMax - curMin + 1;
					
					//Front half of range, so top half. Subtract the half range from the max
					if(inputLine.charAt(i) == 'R') {
						curMin += range/2;
					}
					//Back, so bottom half. Add the half range to the min
					else if (inputLine.charAt(i) == 'L'){
						curMax -= range/2;
					}
					System.out.println(inputLine.charAt(i) + ": (" + curMin + ", " + curMax + ")");
				}
				if(inputLine.charAt(9) == 'L') {
					colNum = curMin;
				}
				else {
					colNum = curMax;
				}
				
				//Check if this is new highest id
				if(rowNum * 8 + colNum > highestID) {
					highestID = rowNum * 8 + colNum;
				}
			}
			System.out.println("Row num: " + rowNum);
			System.out.println("Col num: " + colNum);
			System.out.println("Highest ID: " + highestID);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
