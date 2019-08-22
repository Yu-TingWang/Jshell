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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import source.Files.ContentFile;
import source.Files.Directory;

/**
 * This class contains the method required to execute the Echo command as well as the manual.
 * 
 * @author hongxia8
 *
 */
public class Echo {
  /**
   * This helper function uses regex to strip a command input from between double quotes.
   * 
   * @param input is the entire input command string.
   * @return content stripped in between double quotes.
   */
  public static String stripContent(String input) {
    String content = "";
    Pattern regex = Pattern.compile("\"([^\"]*)\"");
    Matcher matcher = regex.matcher(input);
    while (matcher.find()) {
      content += matcher.group(1);
    }
    return content;
  }



  /**
   * Strips the content string then uses cases to determine the right Echo options.
   * 
   * @param command is the entire input command string.
   * @param current is the current directory
   * @return any error messages that is required.
   */
  public static String execute(String command, Directory current, Directory root) {
    String content = stripContent(command);
    String commandargs = command.replace("\"" + content + "\"", "");
    String[] inputarray = commandargs.split("\\s+");
    if (content.isEmpty()) {
      return "String not found, please put them between double quotes \n";
    }
    if (inputarray.length == 1) {
      return content + "\n";
    } else if (inputarray.length == 3) {
      String option = inputarray[1];
      String path = inputarray[2];
      if (path.endsWith("/")) {
        return "A directory cannot be passed on as an argument for outfile! \n";
      }
      Directory parent = (Directory) FileSystem.getParentFromPath(root, current, path);
      inputarray = path.split("/");
      String filename = inputarray[inputarray.length - 1];
      if (parent == null) {
        return "Path is invalid \n";
      }
      switch (option) {
        case ">":
          return writeFile(content, filename, parent);
        case ">>":
          return appendFile(content, filename, parent);
      }
    }
    return "Invalid number of arguments used, see 'man echo'. The string must be contained"
        + " by a single set of double quotes. \n";
  }



  /**
   * This function appends to a given file, used by Echo.execute().
   * 
   * @param content is a string to be written to file.
   * @param file is the name of the file.
   * @param current is the current directory.
   * @return
   */
  public static String appendFile(String content, String file, Directory current) {
    int index = FileSystem.getIndexFile(file, current);
    if (index == -1) {
      ContentFile newFile = new ContentFile(file, content);
      current.addFile(newFile);
    } else {
      ((ContentFile) current.getFiles().get(index)).appendFile(content);
    }
    return "";
  }



  /**
   * This function writes to a given file, used by Echo.execute().
   * 
   * @param content is a string to be written to file.
   * @param file is the name of the file.
   * @param current is the current directory.
   * @return
   */
  public static String writeFile(String content, String file, Directory current) {
    int index = FileSystem.getIndexFile(file, current);
    if (index == -1) {
      ContentFile newFile = new ContentFile(file, content);
      current.addFile(newFile);
    } else {
      ((ContentFile) current.getFiles().get(index)).writeFile(content);
    }
    return "";
  }



  /**
   * 
   * @return manual for Echo.
   */
  public static String man() {
    return "echo STRING [> OUTFILE]\n"
        + "If OUTFILE is not provided, print STRING on the shell. Otherwise, put\n"
        + "STRING into file OUTFILE. STRING is a string of characters surrounded by\n"
        + "double quotation marks. This creates a new file if OUTFILE does not exists and\n"
        + "erases the old contents if OUTFILE already exists. In either case, the only thing\n"
        + "in OUTFILE should be STRING. ";
  }
}
