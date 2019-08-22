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

/**
 * This class represents the History command, it retains all inputs from console and is capable of
 * returning them.
 * 
 * @author hongxia8
 *
 */
public class History extends Command {

  private ArrayList<String> commandsArray;

  /**
   * Constructor, creates an instance of the History class.
   */
  
  public ArrayList<String> getcommandsArray(){
	  return commandsArray;
  }
  
  public History() {
    commandsArray = new ArrayList<String>();
  }

  /**
   * adds command to commandsArray.
   * 
   * @param command is a string representing the last command entered.
   */
  public void addHistory(String command) {
    this.commandsArray.add(command);
  }

  /**
   * Takes an array of command arguments, and returns the last n-number of commands if specified,
   * otherwise return the complete list of commands.
   * 
   * @param inputarray
   * @return the last n-number of commands or the entirety of it.
   */
  public String execute(String[] inputarray) {
    String output = "";
    int arraySize = this.commandsArray.size();
    int i;
    if (inputarray.length == 1) {
      i = arraySize - 10;
    } else {
      i = arraySize - Integer.parseInt(inputarray[1]);
    }
    while (i < arraySize) {
      output += (i + 1 + ". " + this.commandsArray.get(i) + "\n");
      i++;
    }
    return output;
  }

  /**
   * 
   * @return manual for History
   */
  public static String man() {
    return "Given a number n, print out the recent n commands";
  }
}
