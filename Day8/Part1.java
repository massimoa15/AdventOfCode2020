import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		try {
			int acc = 0;
			Scanner sc = new Scanner(new File("input.txt"));
			LinkedList<String> input = new LinkedList<String>();
			int curLine = 0;
			
			String curOp;
			int curNum;
			Scanner lineScanner;
			
			//Save input as text
			while(sc.hasNextLine()) {
				input.add(sc.nextLine());
			}
			sc.close();
			
			//Create boolean array to keep track of whether this line has been accessed already or not
			boolean[] lineHasBeenVisited = new boolean[input.size()];
			
			for(int i = 0; i < lineHasBeenVisited.length; i++) {
				lineHasBeenVisited[i] = false;				
			}
			
			while(true) {
				lineScanner = new Scanner(input.get(curLine));
				curOp = lineScanner.next();
				curNum = Integer.parseInt(lineScanner.next());
				
				if(lineHasBeenVisited[curLine]) {
					break;
				}
				lineHasBeenVisited[curLine] = true;
				if(curOp.equalsIgnoreCase("acc")) {
					acc += curNum;
					curLine++;
				}
				else if(curOp.equalsIgnoreCase("jmp")) {
					curLine += curNum;
				}
				else if(curOp.equalsIgnoreCase("nop")) {
					curLine++;
				}
				if(curLine == input.size()-1) {
					System.out.println("Reached end of input!");
					break;
				}
			}
			
			lineScanner.close();
			
			System.out.println("Acc: " + acc);

		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
