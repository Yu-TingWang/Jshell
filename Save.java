package source;
import java.io.FileNotFoundException;
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
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * This class contains the required method of the Save command as well as the manual
 * @author zhan6322
 */

/**
 * @param h is a history object in the console
 * @param path is a String of where the user want to store the file
 * @return void
 * @author zhan6322
 *
 */
public class Save extends Command{
	
	public static void execute(History h, String path) {
			FileWriter fw;
			try {
				fw = new FileWriter(path);
				PrintWriter pw = new PrintWriter(fw);
				ArrayList<String> arr = h.getcommandsArray();
				for(int i=0; i<arr.size()-1; i++) {
					pw.println(arr.get(i));
				}
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
	}
/**
* 	
* @return the manual of save
*/	
	public static String man() {
		return "save the session of the JShell\n";
	}
	
}
