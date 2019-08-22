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
import source.Files.*;

/**
 * This class contains the method required to execute the Cd command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Cd extends Command {
  /**
   * "Cd"s into a given path.
   * 
   * @param fs is the FileSystem object to be evoked on.
   * @param path is the Path to Cd into.
   * @param current is the current directory, may or may not be used depending on the path.
   * @return any error messages required.
   */
  public static String execute(FileSystem fs, String path, Directory current) {
    String[] directories;
    String output = "";
    if (path.charAt(0) == '/') {
      fs.getPath().subList(1, fs.getPath().size()).clear();
    }
    // Contains all the directories
    directories = path.split("/");

    for (String directory : directories) {
      if (directory.equals("..") && fs.getPath().size() > 1) {
        fs.getPath().remove(fs.getPath().size() - 1);
      } else if (directory.equals(".")) {
        output = fs.traverse(current.toString());
      } else if (!directory.equals("")) {
        output = fs.traverse(directory);
      }
    }
    return output;
  }



  /**
   * 
   * @return manual for Cd
   */
  public static String man() {
    return "Change directory to DIR, which may be relative to the current directory or\n"
        + "may be a full path";
  }
}
