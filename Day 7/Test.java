public class Test {
  public static void main (String[] args) {
    String orig = "vibrant lime bags contain 3 faded gold bags, 3 plaid aqua bags, 2 clear black bags.";
    String orig2 = "dull gray bags contain 1 striped gold bag, 1 vibrant yellow bag.";
    String parsed = orig;

    parsed = ParseString(orig);
    System.out.println(parsed);

    parsed = ParseString(orig2);
    System.out.println(parsed);
    
  }

  public static String ParseString(String orig){
    orig = orig.replace(" bags", "");
    orig = orig.replace(" bag", "");
    orig = orig.replace(" contain ", ":");
    orig = orig.replace(", ", ",");
    orig = orig.replace(".", "");
    // orig = orig.replaceAll("[0-9] ", "");

    return orig;
  }

}