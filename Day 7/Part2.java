import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.*;

public class Part2 {
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
      
      //Need to check the # of bags that must fit inside the wantedBag
      int numContained = ContainsXWanted(rules, wantedBag, wantedBag);

      System.out.println(numContained);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

  public static int ContainsXWanted(Hashtable rules, String key, String wantedBag) {
    String value = (String) rules.get(key);
    System.out.println("Checking: " + value);
    //value contains the list of all bags that can go inside 

    int totNumBags = 0;

    if(value.contains("no other")){
      System.out.println("++There were no bags inside of " + wantedBag);
      return 0;
    }

    //Separate this into the # of bags and what the colour is
    Scanner sc = new Scanner(value);
    sc.useDelimiter(",");
    while(sc.hasNext()){
      String bagToCheck = sc.next();
      int numBags = ExtractNum(bagToCheck);
      String bagColour = ExtractAlpha(bagToCheck);
      bagColour = bagColour.trim();

      //Need to now check inside of current bag
      System.out.println("~Need to check inside of " + wantedBag + ". Specifically: " + bagColour);

      //You need to count 1 bag for the actual bag type. For example if a bag contains 1 red bag and that red bag contains 0 other bags, then you need to count the 1 red bag being inside of the current bag. Hence the 1 + ...
      totNumBags += numBags * (1 + ContainsXWanted(rules, bagColour, bagToCheck));
    }
    System.out.println("++There were " + totNumBags + " inside of " + wantedBag);

    return totNumBags;

  }

  //Check if the given rule can contain the wantedBag, including if the bags within the rule can eventually contain the wanted bag
  public static boolean CanContainWanted(Hashtable rules, String key, String wantedBag){
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
    //Need to work with # of bags now
    // orig = orig.replaceAll("[0-9] ", "");

    return orig;
  }

  public static int ExtractNum(String str){
    return Integer.parseInt(str.replaceAll("[^0-9]",""));
  }

  public static String ExtractAlpha(String str){
    return str.replaceAll("[^a-z ]","");
  }
}