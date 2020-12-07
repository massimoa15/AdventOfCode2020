import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			String curLine;
			boolean[] groupAnswers = new boolean[26];
			boolean[] curPersonsAnswers = new boolean[26];
			int numTotalAnswers = 0;
			
			/*
			 * The way that this algorithm will work is that you assume every question is = true for the group before you start. Then go through and figure out how many 
			 * answers are true for the current person. Then compare the group answers with the current person answers and for any answer for the current person that is false,
			 * set the answer for the group = false. Since if one person doesn't have it, the whole group cannot have it
			 */
			SetAnswers(groupAnswers, true);
			
			//Loop until end of file
			while(sc.hasNextLine()) {
				curLine = sc.nextLine();
				SetAnswers(curPersonsAnswers, false);
				
				//End of group
				if(curLine.isEmpty()) {
					numTotalAnswers += GetNumAnswers(groupAnswers);
					SetAnswers(groupAnswers, true);
				}
				else {
					//Loop through the current line and set true to all of the letters that appear
					for(int i = 0; i < curLine.length(); i++) {
						char c = curLine.charAt(i);
						c -= 97;	//Since a=97, if you subtract the char by 97 you will get it's position in the alphabet starting at index 0
						curPersonsAnswers[c] = true;
					}
					//Now go through every answer in the group answers and remove everything that this person doesn't currently have
					MergeAnswers(groupAnswers, curPersonsAnswers);
				}
			}
			sc.close();

			//End of file, increment numTotalAnswers and reset
			numTotalAnswers += GetNumAnswers(groupAnswers);
			SetAnswers(groupAnswers, true);
			
			System.out.println(numTotalAnswers);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void MergeAnswers(boolean[] groupAnswers, boolean[] curPersonsAnswers) {
		for(int i = 0; i < groupAnswers.length; i++) {
			if(!curPersonsAnswers[i]) {
				groupAnswers[i] = false;
			}
		}
	}
	
	public static void SetAnswers(boolean[] answers, boolean val) {
		for(int i = 0; i < answers.length; i++) {
			answers[i] = val;
		}
	}
	public static int GetNumAnswers(boolean[] answers) {
		int count = 0;
		for(int i = 0; i < answers.length; i++) {
			if (answers[i]){
				count++;
			}
		}
		return count;
	}
}
