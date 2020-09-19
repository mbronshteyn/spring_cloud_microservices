package mbronshteyn.scratchpad;

public class Test {

  public static void main(String[] args) {
    String str = "abcd";
    String reversed = reverseString(str);
    System.out.println("The reversed string is: " + reversed);

    try {
      throw new RuntimeException("test");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static String reverseString(String str) {
    if (str.isEmpty()) return str;
    // Calling Function Recursively
    System.out.println(str.substring(1));
    System.out.println(str.charAt(0));
    return reverseString(str.substring(1)) + str.charAt(0);
  }
}
