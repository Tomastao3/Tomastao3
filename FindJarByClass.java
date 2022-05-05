package tool;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.regex.*;

public class FindJarByClass {

  public static String className;

  static String dir = "C:\\test";  //file directory

  public static HashSet<String> jarFiles = new HashSet();
  public static HashSet<String> checkSet = new HashSet();
  public static HashSet<String> classSet = new HashSet();
  public static HashSet<String> jarSet = new HashSet();

  public static void main(String args[]) {
    System.out.println(new Date());
    System.out.println("Start:=======================================================================================");
    arrayToSet();
    searchDir(dir, true);
    showJarFoundWithAll();
    System.out.println("=============================================================================================");
    showJarFound();
    showJarNotFound();
    System.out.println("End:=========================================================================================");
    System.out.println("Found:" + jarSet.size());
    System.out.println("Size:" + classSet.size());
    System.out.println(new Date());
  }

  public static void showJarFound(){
    for (String s : jarSet) {
      String[] modules = s.split("-");
      String name = modules[0] + "-" + modules[1];
      String moduleInfo = Module.setData(name);
      //if(!moduleInfo.contains("platform") && !moduleInfo.contains("apps-core-libs"))
        System.out.println("Jar Found :" + s + " :" + moduleInfo);
    }
  }

  static String[] workClass = {
    "java.util.*",
    "java.util.String",
  };

  public static void findJarByClass(String filename) throws IOException{
    ZipFile zip = new ZipFile(filename);
    Enumeration entries = zip.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      for (String className : classSet) {
        String thisClassName = getClassName(entry);
        if (thisClassName.equals(className + ".class")) {
            Matcher m1 = Pattern.compile("[^/]+\\.jar").matcher(filename);
            if (m1.find()) {
              String jarName = m1.group(0);
              jarFiles.add(jarName + " :" + className + " :" + thisClassName);
              checkSet.add(className);
              jarSet.add(jarName + " :" + className);
            }
        }
        else if(className.contains(".*") &&!thisClassName.contains("$") && thisClassName.contains(".class")
          && thisClassName.startsWith(className.replace(".*",""))
          && (thisClassName.split("\\.").length == (className + ".class").split("\\.").length)) {

          Matcher m1 = Pattern.compile("[^/]+\\.jar").matcher(filename);
          if (m1.find()) {
            String jarName = m1.group(0);
            jarFiles.add(jarName + " :" + className + " :" + thisClassName);
            checkSet.add(className);
            jarSet.add(jarName + " :" + className);

          }
        }
      }//for
    }//while
  }

  protected static void searchDir(String dir, boolean recurse) {
    try {
      File d = new File(dir);
      if (!d.isDirectory()) {
        return;
      }
      File[] files = d.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (recurse && files[i].isDirectory()) {
          searchDir(files[i].getAbsolutePath(), true);
        } else {
          String filename = files[i].getAbsolutePath();
          if (filename.endsWith(".jar") || filename.endsWith(".zip")) {
            findJarByClass(filename);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected static String getClassName(ZipEntry entry) {
    StringBuffer className = new StringBuffer(entry.getName().replace("/", "."));
    return className.toString();
  }

  public static void showJarFoundWithAll() {
    Iterator it = jarFiles.iterator();
    while (it.hasNext()) {
      String s = (String) it.next();
      System.out.println("Jar Found: " + s);
    }
  }

  public static void arrayToSet(){
    //List<String> list = Arrays.asList(workClass);
    for (int i = 0; i < workClass.length; i++) {
      classSet.add(workClass[i]);
    }
  }

  public static void showJarNotFound(){
    for (String s : classSet) {
      if(!checkSet.contains(s))
        System.out.println("Jar Not Found: " + s);
    }
  }

}
