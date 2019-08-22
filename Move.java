package source;
import java.util.ArrayList;
import java.util.Arrays;
import source.Copy;
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
 * This class contained all the required method of the Command mv
 * @author wangy964
 *
 */
public class Move {
	/**
	 * Executes the Mv command on an oldpath and newpath, 
	 * if no path is found, an error message will be printed
	 * 
	 * @param root is the root directory 
	 * @param current is the current working directory
	 * @param paths records all the parent directories of current
	 * @param oldDir is the old path
	 * @param newDir is the newpath
	 * @return an empty string if no errors 
	 */
	public static String execute(Directory root, Directory current, ArrayList<Directory> paths,
			String oldDir, String newDir) {
		if(current == root) {
			if(oldDir.equals(".")||newDir.equals(".")) {
				return " cannot move the root directory";
			}
		}
		// cut the oldpath
		int len = oldDir.length();
		int index = oldDir.lastIndexOf('/');
		String old = oldDir.substring(0, index);
		// name is the name of the file pointed by oldDir
		String name = oldDir.substring(index+1,len);
		ArrayList<Directory> parents;// initialize
		///////// get oldpath//////////////
		if(oldDir.charAt(0)=='/') {
			parents = FileSystem.CreatePath(root, old);
			}
		else {
			parents = FileSystem.updatepath(root, current, paths, old);
			}
		Directory parent = parents.get(parents.size()-1);
		ArrayList<File> files = parent.getFiles();
		File oldfile =null;
		for(int i=0; i<parents.size();i++) {
			File test = parents.get(i);
			if(test.toString().equals(name)) {
					oldfile =test;;break;
				}
		}
		/////////// end of get oldpath////////
		////////if NEWPATH is a File
		File toadd = FileSystem.getFileFromPath(root, current, newDir);
		if (toadd instanceof ContentFile) {
			//// if OLDPATH is a directory
			if(oldfile instanceof Directory) {
				return "cannot remove a directory to a file\\n";
			}
			else {//// if OLDPATH is a file
				// check if the name already exist in newpath
				// if the name of newDir already existed in the directory of toadd
				Directory newparent = (Directory)FileSystem.getParentFromPath(root, current, oldDir);
				ArrayList<File> checks =newparent.getFiles();
				File duplicate =null;
				File check = null;
				for(int j=0;j<checks.size();j++) {
					check = checks.get(j);
					if(check.toString().equals(name)) {
						duplicate = check;
					}
				}
				if(duplicate == null) {
					Copy.execute(root, current,paths, oldDir, newDir);
					// remove 
					File temp = null;
					for(int i=0; i<parents.size();i++) {
						temp = parents.get(i);
						if(temp.toString().equals(name)) {
							 parents.remove(i);break;
						}
						}
					}
				else {
					return "A directory cannot contain two files with the same name\n";
				}
			}
		}
		else {if(toadd instanceof Directory) {
				///////// call copy
				Copy.execute(root, current,paths, oldDir, newDir);
				// remove 
				File temp = null;
				for(int i=0; i<files.size();i++) {
					temp = files.get(i);
					if(temp.toString().equals(name)) {
						 files.remove(i);break;
					}
				}	
			}
		}
		
		return "";
	}
	/**
	 * 
	 * @return manual for Mv
	 */
	public static String man() {
		// TODO Auto-generated method stub
		return "Move OLDPATH to NEWPATH, if oldpath is \n";
	}

}
