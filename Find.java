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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import source.Files.*;

//////// find path -type [f|d] -name expression
/**
 * This class contains all the required method for the Command find
 * @author wangy964
 *
 */
public class Find {
	/**
	 * Execute Find method on the given paths, return the address of the files
	 * that matches the specified type and name
	 * 
	 * @param root is the root directory
	 * @param curr is the current working directory
	 * @param path is the ArrayList<Directory> of the current working directory
	 * @param inputs is the user input
	 * @return the address of the files or directories
	 */
	public static String execute(Directory root,Directory curr,
			ArrayList<Directory> path,String[]inputs) {
		String output =""; 
		File current;
		// get the size of inputs
		int size = inputs.length;
		// get the paths that are to be searched
		String[] paths = Arrays.copyOfRange(inputs, 1, size-4);
		// get the format of this input
		String[] format = Arrays.copyOfRange(inputs, size-4, size);
		// check if the format of input is correct
		if (inputs[size-4].equals("-type")&& inputs[size-2].equals("-name")) {
			ArrayList<File> files;ArrayList<Directory> newpath;
			String[] newinput;
			// loop through each path
			for(int i=0;i<paths.length;i++) {
				// if absolute path
				if (paths[i].startsWith("/")){
					current = FileSystem.getFileFromPath(root, root, paths[i]);
					// get the ArrayList<Directory> of current
					newpath = FileSystem.CreatePath(root, paths[i]);
					//output += execute(root,current,newpath,update);
					}
				// if relative path	
				else {
					current = FileSystem.getFileFromPath(root, curr, paths[i]);
					// get the arraylist of paths[i]
					newpath = FileSystem.updatepath(root, curr, path, paths[i]);
					}
				// construct the inputs for the recursion call
				String[] newinputs = {"find","",format[0],format[1],format[2],format[3]};
				// get the files 
				if (current == null) {
					return "invalid path. see 'man find'\n";
				}
				else if (current instanceof Directory) {
					files =((Directory) current).getFiles();
				// get the type we are going to search
				switch(inputs[size-3]) {
				case "d":
					if (current instanceof ContentFile) {
						// stop searching
						return output;
					}
					else if(current instanceof Directory) {
						if (current.toString().equals(inputs[size-1])) {
							// append the address of current to output
							output += Pwd.execute(path)+"\n";
						}
						ArrayList<File> subfiles = ((Directory)current).getFiles();
						
						for(int k=0;k<subfiles.size();k++) {
							ArrayList<Directory> recurpath = (ArrayList<Directory>) newpath.clone();
							File file = subfiles.get(k);
							//System.out.println("file["+k+"]:"+file.toString());
							// only do the recursion call on directory
							if(file instanceof Directory) {
								//ArrayList<File> sub = ((Directory)file).getFiles();
								recurpath.add((Directory)file);
								newinputs[1] = Pwd.execute(recurpath);
								output += execute(root,(Directory)curr,recurpath,newinputs);
							}
						}
					}
					break;
				case "f":
					// get the fullpath of path(user input)
					String pwd = Pwd.execute(newpath);
					// if current is a file
					if (current instanceof ContentFile) {
						if(current.toString().equals(inputs[size-1])) {
							// append the address of current to output
							output += pwd+"\n";}
						}
					else if (current instanceof Directory) {
						for(int j=0;j<files.size();j++) {
							File now = files.get(j);
							String fullpath = Pwd.execute(path);
							
							// if this is a file
							if(now instanceof ContentFile) {
								if (now.toString().equals(inputs[size-1])) {
									// append the address of current to output
									output += pwd+"\n";}
							}// if this is a directory
							else if (now instanceof Directory) {
								// update the ArrayList of directory
								newpath.add((Directory)now);
								// update the path that is given as inputs
								newinputs[1] = fullpath;
								// recursion call on this directory
								output += execute(root,(Directory)now,newpath,newinputs);
							}	
						}
					}
					break;
				default: return "invalid type. see 'man find'\n";}
			}
		}
		}
		else {
			return "invalid format of operand. see 'man find'\n";
		}
		return output;
	}
	public static String man() {
		return "if -type d, then find all the directory whose name is -name in the given paths\n"
				+ "if -type f, then find all the files whose name is -name in the given paths\n.";
	}
}
