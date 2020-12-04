import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	static String byr = "";
	static String iyr = "";
	static String eyr = "";
	static String hgt = "";
	static String hcl = "";
	static String ecl = "";
	static String pid = "";
	static String cid;	//Not needed	
	public static void main(String[] args) {
		Scanner sc, lineScanner;
		String inputLine;
		String key;
		
		int numValid = 0;

		
		try {
			//Read from file
			sc = new Scanner(new File("input.txt"));
			//Now the computer will use whitespace as well as : as a delimiter
			
			//If there is a blank line, it's a new set of data
			
			while(sc.hasNextLine()) {
				//Need to separate the line into individual fields, but also need to check if it is empty because that means it is the end of the current data
				inputLine = sc.nextLine();
				lineScanner = new Scanner(inputLine);
				
				//Parse the line by the spaces and the : to separate by field
				lineScanner.useDelimiter(" |:");
				
				if (inputLine.isEmpty()) {
					//New line, reset dataset
//					System.out.println("End of set. Data: ");
//					printAllData();
					if(hasAllData() && hasValidData())
						numValid++;
					resetData();
				}
				else { //Now, alternate between checking what the tag is and then saving the value (next in scanner) into the proper variable
//					System.out.println("Current line: "  + inputLine);
					while(lineScanner.hasNext()) {
						key = lineScanner.next();
						saveInKey(key, lineScanner.next());
					}
				}
			}
			//Need to add the last one because it's ignored
//			System.out.println("End of set. Data: ");
//			printAllData();
			if(hasAllData() && hasValidData())
				numValid++;
			resetData();
			
						
			sc.close();
			
			System.out.println("Num valid: " + numValid);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetData() {
		byr = "";
		iyr = "";
		eyr = "";
		hgt = "";
		hcl = "";
		ecl = "";
		pid = "";
		cid = "";
	}
	
	public static boolean hasAllData() {
		//At least 1 field is empty
		if(byr == "" || iyr == "" || eyr == "" || hgt == "" || hcl == "" || ecl == "" || pid == "") {
			return false;
		}
		//No fields are empty
		return true;
	}
	
	public static void saveInKey(String key, String value) {
//		System.out.println("Saving " + value + " in " + key);
		if(key.equals("byr")) {
			byr = value;
		}
		else if(key.equals("iyr")) {
			iyr = value;
		}
		else if(key.equals("eyr")) {
			eyr = value;
		}
		else if(key.equals("hgt")) {
			hgt = value;
		}
		else if(key.equals("hcl")) {
			hcl = value;
		}
		else if(key.equals("ecl")) {
			ecl = value;
		}
		else if(key.equals("pid")) {
			pid = value;
		}
		else if(key.equals("cid")) {
			
		}
		else {
			System.out.println("Invalid key found: " + key);
		}
	}
	public static void printAllData() {
		System.out.println("~~~~~~~~~~~~~");
		System.out.println("byr: " + byr);
		System.out.println("iyr: " + iyr);
		System.out.println("eyr: " + eyr);
		System.out.println("hgt: " + hgt);
		System.out.println("hcl: " + hcl);
		System.out.println("ecl: " + ecl);
		System.out.println("pid: " + pid);
		System.out.println("cid: " + cid);
		System.out.println("~~~~~~~~~~~~~");
	}
	
	public static boolean hasValidData() {
		if(isValidBYR(byr) && isValidIYR(iyr) && isValidEYR(eyr) && isValidHGT(hgt) && isValidHCL(hcl) && isValidECL(ecl) && isValidPID(pid)) {
			return true;
		}
		return false;
	}
	//4 digits, [1920-2002]
	public static boolean isValidBYR(String input) {
		int year = Integer.parseInt(input);
		if(year >= 1920 && year <= 2002) {
			return true;
		}
//		System.out.println("Found invalid byr: " + input);
		return false;
	}
	//4 digits, [2010-2020]
	public static boolean isValidIYR(String input) {
		int year = Integer.parseInt(input);
		if(year >= 2010 && year <= 2020) {
			return true;
		}
//		System.out.println("Found invalid iyr: " + input);		
		return false;
	}
	//4 digits [2020-2030]
	public static boolean isValidEYR(String input) {
		int year = Integer.parseInt(input);
		if(year >= 2020 && year <= 2030) {
			return true;
		}
//		System.out.println("Found invalid eyr: " + input);
		return false;
	}
	//Number followed by "cm" or "in". cm must be [150-193]. in must be [59-76]
	public static boolean isValidHGT(String input) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(input);
		Pattern p2 = Pattern.compile("cm|in");
		Matcher m2 = p2.matcher(input);
		
		//Valid format, now need to check that the number is in range
		if(m.find() && m2.find()) {
			int height = Integer.parseInt(m.group());
			String unit = m2.group();
			
			//Determine if it is "in" or "cm"
			if(unit.equals("cm")) {
				if(height >= 150 && height <= 193) {
					return true;
				}
			}
			else if(unit.equals("in")) {
				if(height >= 59 && height <= 76) {
					return true;
				}
			}

		}
//		System.out.println("Found invalid hgt: " + input);
		return false;
	}
	//"#" followed by exactly 6 characters, 0-9 or a-f
	public static boolean isValidHCL(String input) {
		Pattern p = Pattern.compile("#[0-9a-f]{6}");
		Matcher m = p.matcher(input);
		
		if(m.find())
			return true;
//		System.out.println("Found invalid hcl: " + input);
		return false;
	}
	//amb|blu|brn|gry|grn|hzl|oth
	public static boolean isValidECL(String input) {
		Pattern p = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
		Matcher m = p.matcher(input);
		
		if(m.find()) {
			return true;
		}
//		System.out.println("Found invalid ecl: " + input);
		return false;		
	}
	//9 digits, including leading zeroes
	public static boolean isValidPID(String input) {
		Pattern p = Pattern.compile("[0-9]{9}");
		Matcher m = p.matcher(input);
		
		if(m.find())
			return true;
//		System.out.println("Found invalid pid: " + input);
		return false;
	}
}
