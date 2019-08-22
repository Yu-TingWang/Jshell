package source;

// **********************************************************
// Assignment2:
// Student1: Yu-Ting Wang
// UTORID user_name: wangy964
// UT Student #: 1004340331
// Author: Yu-Ting Wang
//
// Student2: Zhi Geng
// UTORID user_name: gengzhi1
// UT Student #: 1005042044
// Author: Zhi Geng
//
// Student3: Runjie Zhang
// UTORID user_name: zhan6322
// UT Student #: 1005002952
// Author:Runjie Zhang
//
// Student4: XiangQian "Richard" Hong
// UTORID user_name: hongxia8
// UT Student #: 1005456178
// Author: Richard Hong
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
import java.util.ArrayList;
import source.Files.Directory;

/**
 * This class contains the method required to execute the Pwd command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Pwd extends Command {

  /**
   * Executes the Pwd command on a path.
   * 
   * @param path is an Array list of directories that form the current path.
   * @return the string representation of the path.
   */
  public static String execute(ArrayList<Directory> path) {
    String output = "/";
    for (Directory directory : path) {
      output += directory.toString();
      if (!directory.equals(path.get(path.size() - 1)) && !directory.equals(path.get(0))) {
        output += "/";
      }
    }
    return output;
  }



  /**
   * 
   * @return manual for Pwd
   */
  public static String man() {
    return "Print the current working directory (including the whole path).";
  }
}
