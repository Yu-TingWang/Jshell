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
import java.util.Arrays;
import source.Files.*;

/**
 * This class contains the method required to execute the Cat command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Cat extends Command {

  public static String execute(Directory root, Directory directory, String[] paths) {
    String output = "";
    int index = 1;
    for (String path : Arrays.copyOfRange(paths, 1, paths.length)) {
      File file = FileSystem.getFileFromPath(root, directory, path);
      if (file == null) {
        output += "The specified path could not be found: " + path + "\n";
      } else {
        if (file instanceof ContentFile) {
          output += ((ContentFile) file).printFile();
          if (paths.length > 2 && index != paths.length - 1) {
            output += "\n\n\n";
          }
        }
        if (file instanceof Directory) {
          output += "cat cannot be used on a directory file: " + path + "\n";
        }
      }
      index ++;
    }
    return output + "\n";
  }

  /**
   * 
   * @return manual for Cat.
   */
  public static String man() {
    return "Display the contents of FILE1 and other files (i.e. File2 ...) concatenated\n"
        + "in the shell.";
  }
}
