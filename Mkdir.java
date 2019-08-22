package source;
//**********************************************************
//Assignment2:
//Student1: Yu-Ting Wang
//UTORID user_name: wangy964
//UT Student #: 1004340331
//Author: Yu-Ting Wang
//
//Student2: Zhi Geng
//UTORID user_name: gengzhi1
//UT Student #: 1005042044
//Author: Zhi Geng
//
//Student3: Runjie Zhang
//UTORID user_name: zhan6322
//UT Student #: 1005002952
//Author:Runjie Zhang
//
//Student4: XiangQian "Richard" Hong
//UTORID user_name: hongxia8
//UT Student #: 1005456178
//Author: Richard Hong
//
//
//Honor Code: I pledge that this program represents my own
//program code and that I have coded on my own. I received
//help from no one in designing and debugging my program.
//I have also read the plagiarism section in the course info
//sheet of CSC B07 and understand the consequences.
//*********************************************************
import source.Files.*;

import java.util.Arrays;

/**
 * This class contains the method required to execute the Mkdir command as well as the manual.
 * @author hongxia8
 *
 */
public class Mkdir extends Command{
	
	/**
	 * Executes the Mkdir command.
	 * 
	 * @param root is the directory at the root of the File tree.
	 * @param directory is a current directory, may or may not be used.
	 * @param path is the path and/or name of the folder to be made.
	 * @return
	 */
	public static String execute(Directory root, Directory directory, String[] paths){
		String output = "";
		for(String path : Arrays.copyOfRange(paths, 1, paths.length)) {
			if(path.charAt(0) == '/') {
				output += createDir(root, directory, path);
			}
			else {
				output += createDir(directory, directory, path);
			}
		}
		return output;
	}
	
	
	
	/**
	 * The function that creates the directory, used by Mkdir.execute().
	 * 
	 * @param directory is the directory to create the new directory.
	 * @param path is the path and/or name of the folder to be made.
	 * @return any error messages that is required.
	 */
	public static String createDir(Directory directory, Directory current, String path) {
		String[] directories = path.split("/");
		String output = "";
		int i, index;
		if(directories.length == 0) {
			return output;
		}
		for(i = 0;i < directories.length - 1;i++) {
			//Splitting can cause empty strings to be left, this if continue takes care of it.
			if(directories[i].isEmpty()) {
				continue;
			}
			index = FileSystem.getIndex(directories[i], directory);
			if(index != -1) {
				directory = (Directory) directory.getFiles().get(index);
			}
			else {
				return "A directory in the path does not exist\n";
			}
		}
		index = FileSystem.getIndex(directories[i], directory);
		if(index == -1) {
			if(FileSystem.isValid(directories[i])) {
				Directory file = new Directory(directories[i]);
				directory.addFile(file);
			}
			else {
				output = "name: " + directories[i] + " is invalid\n";
			}
		}
		else {
			output = "directory: " + directories[i] + " already exists\n";
		}
		return output;
	}
	
	
	
	
	/**
	 * 
	 * @return manual for Mkdir.
	 */
	public static String man() {
		return "Create directories, each of which may be relative to the current directory\n" + 
				"or may be a full path. ";
	}
}
