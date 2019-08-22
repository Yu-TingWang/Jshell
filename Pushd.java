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
import source.Files.Directory;

/**
 * This class contains the method required to execute the Pushd command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Pushd extends Command {
  /**
   * add the current path to a stack, then cd into given path.
   * 
   * @param console is the console object that is running this command.
   * @param path is the path to cd into after adding current to stack.
   * @return any error messages required.
   */
  public static String execute(Console console, String path) {
    String cdpath = "/";
    for (Directory directory : console.fs.getPath()) {
      cdpath += directory.toString() + "/";
    }
    console.fs.getStack().push(cdpath);

    if(path.equals("..")) {
    	return Cd.execute(console.fs, "..", null);
    }
    else {
    	return Cd.execute(console.fs, path, null);
    }
    
  }


  /**
   * 
   * @return manual for Pushd
   */
  public static String man() {
    return "Saves the current working directory by pushing onto directory stack and then changes "
        + "the new current working directory to DIR.";
  }
}
