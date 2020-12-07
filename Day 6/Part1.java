import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			String curLine;
			boolean[] answers = new boolean[26];
			int numTotalAnswers = 0;
			
			while(sc.hasNextLine()) {
				curLine = sc.nextLine();
				
				//End of group
				if(curLine.isEmpty()) {
					numTotalAnswers += GetNumAnswers(answers);
					ResetAnswers(answers);
				}
				
				//Loop through the current line and set true to all of the letters that appear
				for(int i = 0; i < curLine.length(); i++) {
					char c = curLine.charAt(i);
					c -= 97;	//Since a=97, if you subtract the char by 97 you will get it's position in the alphabet starting at index 0
					answers[c] = true;
				}

			}
			sc.close();

			//End of file, increment numTotalAnswers and reset
			numTotalAnswers += GetNumAnswers(answers);
			ResetAnswers(answers);
			
			System.out.println(numTotalAnswers);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void ResetAnswers(boolean[] answers) {
		for(int i = 0; i < answers.length; i++) {
			answers[i] = false;
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
