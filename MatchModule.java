package tool;

public class MatchModule {
  static int i = 0;
  public static void main(String[] args) {
    setData("au-usermanagement");
  }

  public static String[] arr;
  public static String str;
  public static int module_name = 0;
  public static int module_portfolio = 1;
  public static int moudle_product = 2;
  public static int moudle_layer = 3;

  public static String setData(String name) {
    for (String moudule : moudules) {
      arr = moudule.split("#");
      if (arr[0].startsWith(name)) {
        str = moudule;
        return arr[3]+ ":" + arr[0];
      }
    }
    return "";
  }

  public static Boolean setDataPrint(String name) {
    for (String moudule : moudules) {
      arr = moudule.split("#");
      if (arr[0].startsWith(name)) {
        str = moudule;
        System.out.println( arr[3]+ ":" + arr[0] + ":" + String.valueOf(++i) );
        return true;
      }
    }
    return false;
  }

  public static String[] moudules = {
    "section1#section2#section3#layer#end",
  };
}
