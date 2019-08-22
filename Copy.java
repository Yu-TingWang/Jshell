package source;
import java.util.ArrayList;
import java.util.Arrays;
import source.Files.Directory;
import source.Files.File;
import source.Files.*;
//**********************************************************
//Assignment2:
//Student1: Yu-Ting Wang
//UTORID user_name: wangy964
//UT Student #: 1004340331
//Author: Yu-Ting Wang
//
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

/**
 * This class contains all the required methods that the Command Cp required
 * @author wangy964
 *
 */
public class Copy {
	/**
	 * Executes the Cp command on on an oldpath and newpath,
	 * if no path is found, an error message will be printed
	 * 
	 * @param root is the root directory
	 * @param current is the working directory
	 * @param paths records all the parent directories of current
	 * @param oldDir is the oldpath to be copied
	 * @param newDir is the newpath that oldDir is copied to
	 * @return
	 */
	public static String execute(Directory root, Directory current, ArrayList<Directory> paths,
			String oldDir, String newDir) {
		if(current == root) {
			if(oldDir.equals(".")||newDir.equals(".")) {
				return " cannot copy the root directory";
			}
		}
		////////////////// oldpath
		int len = oldDir.length();
		int index = oldDir.lastIndexOf('/');
		String old = oldDir.substring(0, index);
		String name = oldDir.substring(index+1,len);
		// get the Arraylist that records all the parents
		ArrayList<Directory> parents;
		if(oldDir.charAt(0)=='/') {
			parents = FileSystem.CreatePath(root, old);}
		else {
			parents = FileSystem.updatepath(root, current, paths, old);}
		// get the parents of the file whom pointed by the oldpath
		Directory parent = parents.get(parents.size()-1);
		ArrayList<File> files = parent.getFiles();
		if (files.isEmpty()) {
			return "oldpathis invalid\n";
		}
		// get the file that oldpath is pointing to
		File oldfile =null;
		for(int i=0; i<files.size();i++) {
			File test = files.get(i);
			if(test.toString().equals(name)) {
					oldfile =test;;break;
				}
		}
		////////////////////// oldpath
		if (oldfile == null) {
			return "oldpath is invalid\n";
		}
		else {
			// get the new directory
			File toadd =FileSystem.getFileFromPath(root, current, newDir);
			if(oldfile instanceof ContentFile) {
				if (toadd instanceof Directory) {
					((Directory)toadd).addFile(oldfile);}
				}
			if(oldfile instanceof Directory) {
				// if old is dir, new is file, return error
				if(toadd instanceof ContentFile) {
					return "cannot copy a directory to a file\n";
				}
				if (toadd instanceof Directory) {
					((Directory)toadd).addFile(oldfile);}
			}
			
		}
		return "";
		
	}
	/**
	 * 
	 * @return manual for Cp
	 */
	public static String man() {
		// TODO Auto-generated method stub
		return "Copy OLDPATH to NEWPATH\n+"
				+ "if oldpath is a directory but newpath is a file, return error\n";
	}
		

}
