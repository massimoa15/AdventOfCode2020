import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.*;

public class Part1 {
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
      String wantedBag = "shiny gold";
      int numValid = 0;

      // LinkedList<String> rules = new LinkedList<String>();
      Hashtable rules = new Hashtable();

      Scanner ruleScanner;
			
      //Need to get a list of all of the lines of this input
			while(sc.hasNextLine()) {
        //Sample: vibrant lime bags contain 3 faded gold bags, 3 plaid aqua bags, 2 clear black bags.
        //If I can get this split into a string like: "vibrant lime: faded gold, plaid aqua, clear black" then it will be easier to parse
        String parsedRule = ParseString(sc.nextLine());

        ruleScanner = new Scanner(parsedRule);
        ruleScanner.useDelimiter(":");
        String key = ruleScanner.next();
        String value = ruleScanner.next();

        //Now need to separate into the key and the value

				rules.put(key, value);
			}
			sc.close();
      
      Enumeration names = rules.keys();

      while(names.hasMoreElements()) {
         String str = (String) names.nextElement();
        //  System.out.println(str + ": " + rules.get(str));
        if(CanContainWanted(rules, str, wantedBag)){
          numValid++;
        }
      }
      
      System.out.println(numValid);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

  //Check if the given rule can contain the wantedBag, including if the bags within the rule can eventually contain the wanted bag
  public static boolean CanContainWanted(Hashtable rules, String key, String wantedBag){
    //First just check if this bag can contain the wanted bag directly
    String value = (String) rules.get(key);
    System.out.println("Checking: " + value);
    //Bag holds no other bags
    if(value.contains("no other")){
      return false;
    }
    if(value.contains(wantedBag)){
      return true;
    }
    else {
      //Need to parse the bags out of the value in this key and check those bags
      Scanner sc = new Scanner(value);
      sc.useDelimiter(",");
      while(sc.hasNext()){
        String bagToCheck = sc.next();
        if(CanContainWanted(rules, bagToCheck, wantedBag)){
          return true;
        }
      }
      
    }
    return false;
  }
  
  public static String ParseString(String orig){
    orig = orig.replace(" bags", "");
    orig = orig.replace(" bag", "");
    orig = orig.replace(" contain ", ":");
    orig = orig.replace(", ", ",");
    orig = orig.replace(".", "");
    orig = orig.replaceAll("[0-9] ", "");

    return orig;
  }

  public static int NumBagsInBag(){
    int numBags = 0;


    return numBags;
  }

}