import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc;
		String inputLine;
		int curX1 = 0;
		int curX2 = 0;
		int curX3 = 0;
		int curX4 = 0;
		int curX5 = 0;
		int lineWidth;
		int numTrees1 = 0;
		int numTrees2 = 0;
		int numTrees3 = 0;
		int numTrees4 = 0;
		int numTrees5 = 0;
		
		int rowNum = 0;
		
		boolean do5 = false;
		
		try {
			//Read from file
			sc = new Scanner(new File("input.txt"));
			
			//Need to hard code the first pass to get the line width
			inputLine = sc.nextLine();
			lineWidth = inputLine.length();
			
			while(sc.hasNextLine()) {
				//inputLine holds the current line of info
				inputLine = sc.nextLine();
				
				rowNum++;
				
				//Need to do (curX +3) % width every row to get the proper x position on the looping slope
				curX1 = (curX1 + 1) % lineWidth;
				curX2 = (curX2 + 3) % lineWidth;
				curX3 = (curX3 + 5) % lineWidth;
				curX4 = (curX4 + 7) % lineWidth;
				
				//Only do this every other line, so have a boolean toggle
				if(do5) {
					curX5 = (curX5 + 1) % lineWidth;
					System.out.println("Checking (" + curX5 + ", " + rowNum + "). Found: " + inputLine.charAt(curX5));
					if(inputLine.charAt(curX5) == '#')
						numTrees5++;
					do5 = false;
				}
				else {
					do5 = true;
				}
				
				if(inputLine.charAt(curX1) == '#')
					numTrees1++;
								
				if(inputLine.charAt(curX2) == '#')
					numTrees2++;
								
				if(inputLine.charAt(curX3) == '#')
					numTrees3++;
								
				if(inputLine.charAt(curX4) == '#')
					numTrees4++;
								

								
			}
			
			sc.close();
			System.out.println(numTrees1);
			System.out.println(numTrees2);
			System.out.println(numTrees3);
			System.out.println(numTrees4);
			System.out.println(numTrees5);
			
			System.out.println(numTrees1 * numTrees2 * numTrees3 * numTrees4 * numTrees5);
			System.out.println("Num rows: " + rowNum);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
