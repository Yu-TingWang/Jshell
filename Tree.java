package source;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import source.Files.*;
import source.Ls;
public class Tree extends Command{
	public static String execute(Directory root,Directory curr, int indent) {
		String output ="";
		if (curr == root) {
			output += "\\"+"\n";
		}
		// get all the sub-directories and files that sits on the current directory
		ArrayList<File> files =  curr.getFiles();
		
		if (! files.isEmpty()) {
			// check every sub-dir and file
			for(int i=0; i<files.size();i++) {
				File file = files.get(i);
				String name = file.toString();
				output += addIndent(name,indent+1);
				//output +=  file.toString() + "\n";
				if( file instanceof Directory) {
					// recursive call on this subdirectory
					//System.out.println(output);
					output += execute(root,(Directory)file,indent+1);
				}
			}
		}

	      return output;
	}
	
	public static String addIndent(String tree,int level) {
		int i=0;
		while(i<level) {
			tree = "	" + tree;
			i++;
		}
		return tree +"\n";
	}

/**
 * 
 * @return manual for tree.
 */
public static String man() {
    return "from the root directory (`\') display the entire file" + 
    		"system as a tree.";
  }



}
