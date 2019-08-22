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
/**
 * This class contains the method required to execute the Popd command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Popd extends Command {

  /**
   * Executes the Popd command on a console, cding into the top of the directory stack.
   * 
   * @param console is the console object that contains directory stack.
   * @return any necessary error messages.
   */
  public static String execute(Console console) {
    String output = "";
    if (console.fs.getStack().isEmpty()) {
      output += "No files in stack";
      return output;
    }
    String newpath = console.fs.getStack().pop();
    Cd.execute(console.fs, newpath, null);
    return output;
  }

  public static String man() {
    return "Remove the top entry from the directory stack, and cd into it.";
  }
}
