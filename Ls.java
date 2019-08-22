package source;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ArrayList;
import source.Pwd;
// **********************************************************
// Assignment2:
// Student1: Yu-Ting Wang
// UTORID user_name: wangy964
// UT Student #: 1004340331
// Author: Yu-Ting Wang
//
// Student2: Runjie Zhang
// UTORID user_name: zhan6322
// UT Student #: 1005002952
// Author:Runjie Zhang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
import java.util.Arrays;
import source.Files.*;

/**
 * This class contains the method required to execute the Ls command as well as the manual.
 * @author wangy964
 */
public class Ls extends Command {

  /**
   * Executes the Ls command on a list of paths, if no path is found, an error message will be
   * printed
   * 
   * @param root is the root directory.
   * @param curr is the current working directory.
   * @param path is for pwd
   * @param inputs is an array of input arguments for Ls.
   * @return the contents of paths accordingly.
   */
  public static String execute(Directory root, Directory curr,ArrayList<Directory> path, String[] inputs) {	  
	  String output ="";
	// the case ls
    if (inputs.length == 1) {
      //output += Pwd.execute(path)+":\n"+FileSystem.printSub(curr) + "\n";
    	output += Pwd.execute(path)+":\n"+printContent(curr) + "\n";
    	}
    else {
    	// recursive case
    	if(inputs[1].equals("-R")) {
    		switch (inputs.length) {
    		case 2:// the case ls -R
    			output += Pwd.execute(path)+":\n"+printContent(curr);
    			// get all the sub-directories and files
    			ArrayList<File> files = curr.getFiles();
    			if (! files.isEmpty()) {
    				for(int i=0;i<files.size();i++) {
    					File file = files.get(i);
    					if(file instanceof Directory) {
    						output += "\n";
    						// update the ArrayList
    						ArrayList<Directory> newpath= (ArrayList<Directory>) path.clone();
        					newpath.add((Directory)file);
    						output += execute(root,(Directory)file,newpath,inputs);}
    					}
    				}
    			break;
    		// the case ls -R [path]
    		case 3:
    			File currdir;
    			ArrayList<Directory> newpath;
    			String[] recurinputs = {"ls","-R"};
    			// if given absolute path
    			if (inputs[2].startsWith("/")) {
					currdir = FileSystem.getFileFromPath(root, root, inputs[2]);
					if (currdir == null) {
	    				output += "The specified path could not be found: " + inputs[2] + "\n";}
	    			else {
	    				if (currdir instanceof ContentFile) {
	    					output += ((ContentFile)currdir).toString();
	    				}
	    				if (currdir instanceof Directory) {
	    					newpath = FileSystem.CreatePath(root,inputs[2]);
	    					if (newpath==null) {
        						return "The specified path could not be found:" +inputs[2]+"\n";
        					}
        					else {
	    					// recursion call on ls -R
	    					output += execute(root,(Directory)currdir,newpath,recurinputs);
        						}
							}
	    			}
    			}
    			else {// if given relative path
    				currdir = FileSystem.getFileFromPath(root, curr, inputs[2]);
    				if (currdir == null) {
        				output += "The specified path could not be found: " + inputs[2] + "\n";}
        			else {
        				if (currdir instanceof ContentFile) {
        					output += ((ContentFile)currdir).toString();
        					}
        				if(currdir instanceof Directory) {
        					String input = inputs[2];
        					newpath = FileSystem.updatepath(root, curr, path, input);
        					if (newpath==null) {
        						return "The specified path could not be found:" +inputs[2]+"\n";}
        					else {
        						//recursion call on ls -R
            					output += execute(root,(Directory)currdir,newpath,recurinputs);
        						}
        					}
    					}
    
    			}
    			break;
    		default:
    			return "invalid number of operands. see'man ls' \n";
    		}
    	}	
    	// the case ls [path]
    	else {
    		if (inputs.length == 2) {
    			// get the path and loop through each sub
            	for (String subdir : Arrays.copyOfRange(inputs, 1, inputs.length)) {
            		File file = FileSystem.getFileFromPath(root, curr, subdir);
            		//output = subdir;
            		if (file == null) {
            			output += "The specified path could not be found: " + subdir + "\n";}
            		else {
            			output = subdir;
            			if (file instanceof ContentFile) {
            				output += ((ContentFile) file).toString();}
            			if (file instanceof Directory) {
            				//((Directory) file).toString()
            				output += ":\n"+ printContent((Directory) file) + "\n";}
            				}
            		}
    		}
    		else {
    			return "invalid number of operands. see'man ls' \n";}
    			}
    }
    
    return output;
  }

  

  /***
   * Prints the contents of a directory, first level only.
   * 
   * @param directory is the directory to print on.
   * @return all of it's contents in string format.
   */
  public static String printContent(Directory directory) {
    String output = "";

    for (int i = 0; i < directory.getFiles().size(); i++) {
      output += directory.getFiles().get(i).toString() + " ";
    }
    //output += "\n";
    //System.out.println("pc: " + output);
    //System.out.println("end  pc");
    return output;
  }

  /**
   * 
   * @return manual for Ls
   */
  public static String man() {
    // TODO Auto-generated method stub
    return "If no paths are given, print the contents (file or directory) of the current\n"
        + "directory, with a new line following each of the content (file or directory)\n"
    +"If -R is given print the contents(file of directory) recursively.";
  }
}