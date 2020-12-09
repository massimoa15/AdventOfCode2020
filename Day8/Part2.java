import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) {
		try {
			int acc = 0;
			Scanner sc = new Scanner(new File("input.txt"));
			LinkedList<String> input = new LinkedList<String>();
			int curLine = 0;
			
			String curOp;
			int curNum;
			Scanner lineScanner;
			
			int lineSwappedNum = -1;
			
			int oldAcc = 0;
			boolean[] oldVisitedArray = null;
			
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

			
			//Create boolean array to keep track of whether this line has been accessed already or not
			while(true) {
				lineScanner = new Scanner(input.get(curLine));
				curOp = lineScanner.next();
				curNum = Integer.parseInt(lineScanner.next());
				
//				System.out.println("Reading line " + curLine + ": " + input.get(curLine));
				
				if(lineHasBeenVisited[curLine]) {
//					System.out.println("Repeated line " + curLine + ": " + input.get(curLine) + ". Restarting from " + lineSwappedNum + ".");
					//If line has been visited, restart back at the line that was swapped, revert it to original and continue from there
					curLine = lineSwappedNum;
					lineSwappedNum = -1;
					
					//Reset boolean array
					lineHasBeenVisited = oldVisitedArray.clone();
					
					//Reset acc
					acc = oldAcc;
					//We need to not swap the curOp since we are back in the place where we swapped it and it failed. So instead of swapping, just run it and continue, swap the next
//					System.out.println("----------------Trying to read line " + curLine);
					lineScanner = new Scanner(input.get(curLine));
					curOp = lineScanner.next();
					curNum = Integer.parseInt(lineScanner.next());
					lineScanner.close();
					
//					System.out.println("~~~~Exclusive operation~~~~. Line " + curLine + ": " + curOp + " " + curNum);
					
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
					if(curLine >= input.size()-1) {
						System.out.println("Reached end of input!");
						break;
					}
					
					continue;
					
					
//					break;
				}

				if((curOp.equalsIgnoreCase("jmp") || curOp.equalsIgnoreCase("nop")) && lineSwappedNum == -1) {
//					System.out.print("Swapped operation on line " + curLine + " from " + curOp + " to ");
					//Save this point in time
					lineSwappedNum = curLine;
					oldAcc = acc;
					curOp = SwapOp(curOp);
					oldVisitedArray = lineHasBeenVisited.clone();
//					System.out.println(curOp);
				}

				//Tell the computer that this line has now been visited, then perform the operation
				lineHasBeenVisited[curLine] = true;

//				System.out.println("Performing operation " + curOp + " " + curNum + " on line " + curLine);
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
				if(curLine >= input.size()-1) {
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
	
	public static String SwapOp(String op) {
		if(op.equals("jmp"))
			return "nop";
		if(op.equals("nop"))
			return "jmp";
		return "";
	}
}
