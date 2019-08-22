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
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * This class contains the required method of the Load command as well as the manual
 * @author zhan6322
 */
public class Load {
	/**
	 * @param c is the console object
	 * @param path is the path where the data to load is stored
	 * @return void
	 * @throws IOException
	 */
	public static void execute(Console c, String path) throws IOException  {
		
		try {
			FileReader fr;
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line!=null) {
				String input = line;
				c.history.addHistory(input.trim());
				if (!input.isEmpty()) {
					System.out.printf(c.shell.input(input));
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
/**
 * 	
 * @return the manual of load
 */
	public static String man() {
		return "load the contents of the FileName and reinitialize everything" + 
				" that was saved previously into the FileName.\n";
	}
	
}
