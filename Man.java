package source;
//**********************************************************
//Assignment2:
//Student1: Yu-Ting Wang
//UTORID user_name: wangy964
//UT Student #: 1004340331
//Author: Yu-Ting Wang
//
//Student2: Runjie Zhang
//UTORID user_name: zhan6322
//UT Student #: 1005002952
//Author:Runjie Zhang
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************
/**
 * This class contains the method required to execute the Man command as well as the manual.
 * @author hongxia8
 *
 */
public class Man extends Command{
  /**
   * Return the command manual for the command in inputarray.
   * @param inputarray is an array of commands.
   * @return the manual for the command in the inputarray.
   */
	public static String execute(String[] inputarray){
		String output = "";
		for (String command : inputarray) {
				switch(command) {
				case "exit": output = Exit.man(); break;
				case "man": output = Man.man(); break;
				case "mkdir": output = Mkdir.man(); break;
				case "ls": output = Ls.man(); break;
				case "pwd": output = Pwd.man(); break;
				case "cd": output = Cd.man(); break;
				case "echo": output = Echo.man(); break;
				case "cat": output = Cat.man(); break; 
				case "pushd": output = Pushd.man(); break;
				case "popd": output = Popd.man(); break;
				case "history": output = History.man(); break; 
				case "tree": output = Tree.man(); break;
				case "load": output = Load.man(); break;
				case "save": output = Save.man(); break;
				case "get": output = Get.man(); break;
				case "find": output = Find.man();break;
				case "cp": output = Copy.man();break;
				case "mv": output = Move.man(); break;
				default: output = "Command '" + inputarray[0] + "' not found";
			}
		}
		return output + "\n";
	}
	
	/**
	 * 
	 * @return manual for man
	 */
	public static String man() {
		return "Print documentation for CMD";
	}
}
