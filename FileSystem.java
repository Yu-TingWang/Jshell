package source;

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
import java.util.ArrayList;
import java.util.Stack;
import source.Files.*;
import source.Files.File;
import java.util.Collections;
import java.lang.*; 
import java.io.*; 
import java.util.*;
/**
 * This class represents a FileSystem object, and contains the methods to manipulate the Files.
 * 
 * @author hongxia8
 *
 */
public class FileSystem {

  // Instance Variables
  private Directory root;
  private ArrayList<Directory> path;
  private Stack<String> stack;


  /* SETTERS AND GETTERS */

  public Directory getRoot() {
    return root;
  }

  public void setRoot(Directory root) {
    this.root = root;
  }

  public ArrayList<Directory> getPath() {
	  return path;
  }
  
  public String getStringPath() {
	  String result = "";
	  for(int i=0; i<path.size(); i++) {
		  result += "/"+path.get(i);
	  }
	  return result;
  }
  
  public void setPath(ArrayList<Directory> path) {
    this.path = path;
  }

  public Stack<String> getStack() {
    return stack;
  }

  public void setStack(Stack<String> stack) {
    this.stack = stack;
  }

  public FileSystem() {
    root = new Directory("");
    path = new ArrayList<Directory>();
    stack = new Stack<String>();
    path.add(root);
  }

  /**
   * Adds a given directory to the path if found, else return an error message.
   * 
   * @param directory is a String of the directory name.
   */
  public String traverse(String directory) {
    for (File file : path.get(path.size() - 1).getFiles()) {
      if (file instanceof Directory && file.toString().equals(directory)) {
        path.add((Directory) file);
        return "";
      }
    }
    return "The system cannot find the path specified.\n";
  }



  /**
   * Return a string that represents all of the files in the given directory.
   * 
   * @param directory is a String of the directory name.
   * @return string that represents all of the files in the given directory.
   */
  public static String printSub(Directory directory) {
    String output = "";

    for (File files : directory.getFiles()) {
      output = output + files.toString() + " ";
    }
    return output;
  }



  /**
   * Return whether a name is valid in terms of containing invalid characters.
   * 
   * @param name is the String to be checked.
   * @return the validity of the name.
   */
  public static boolean isValid(String name) {
    if (name.matches(".*[\\s/.!@#$%^&*(){}<>?~|].*")) {
      return false;
    }
    return true;
  }



  /**
   * Return whether a name for a file already exists in a given directory.
   * 
   * @param name is the String to be checked.
   * @param directory that requires the filename check.
   * @return if the name is unique in directory.
   */
  public static boolean isUnique(String name, Directory directory) {
    for (File file : directory.getFiles()) {
      if (file.toString().equals(name)) {
        return false;
      }
    }
    return true;
  }



  /**
   * Return -1 if no such file with name exists in the given directory, otherwise return the
   * index that it appears in the directory's ArrayList.
   * 
   * @param name is the name of the directory.
   * @return an integer of the index of the file or -1 in the even that file does not exist.
   */
  public static int getIndex(String name, Directory directory) {
    int i = 0;
    for (File file : directory.getFiles()) {
      if (file.toString().equals(name)) {
        return i;
      }
      i++;
    }
    return -1;
  }



  /**
   * Return -1 if no such directory with name exists in the given directory, otherwise return the
   * index that it appears in the directory's ArrayList.
   * 
   * @param name is the name of the directory.
   * @return an integer of the index of the file or -1 in the even that file does not exist.
   */
  public static int getIndexDirectory(String name, Directory directory) {
    int i = 0;
    for (File file : directory.getFiles()) {
      if (file instanceof Directory && file.toString().equals(name)) {
        return i;
      }
      i++;
    }
    return -1;
  }



  /**
   * Return -1 if no such ContentFile with name exists in the directory, otherwise return the index
   * that it appears in the directory's ArrayList. directory given by its name.
   * 
   * @param name is the name of the file.
   * @return an integer of the index of the file or -1 in the even that file does not exist.
   */
  public static int getIndexFile(String name, Directory directory) {
    int i = 0;
    for (File file : directory.getFiles()) {
      if (file instanceof ContentFile && file.toString().equals(name)) {
        return i;
      }
      i++;
    }
    return -1;
  }

/**
 * Given a path relative to the given directory, return the ArrayList<Directory>
 * that record all of its parent directory to the root
 * @param curr is the current working directory
 * @param root is the root directory
 * @param paths is the ArrayList that records all the parent directory of curr
 * @param path is the relative path
 * @return the ArrayList the records all the parent directory of path up to root
 */
  public static ArrayList<Directory> updatepath(Directory root, Directory curr,
		  ArrayList<Directory> paths,String path){
	  // get the index of the last "/"
	  int index = path.lastIndexOf("/");
	  // get the parent folder of path
	  String[] directories = path.split("/");
	  int size = directories.length;
	  // update is to store the parent directory of path up to current directory
	  ArrayList<Directory> update = new ArrayList<Directory> (size);
	  Directory toadd = (Directory)FileSystem.getFileFromPath(root, curr, path);
	  if (index != -1) {
		// construct the arraylist of paths
		  while((FileSystem.getFileFromPath(root, curr, path)!= curr)&& (index>0)) {
			  // add this to update
			  update.add(toadd);
			  // update the path
			  path = path.substring(0, index);
			  // update the directory toadd
			  toadd = (Directory)FileSystem.getFileFromPath(root, curr, path);
			  if (toadd==null) {
				  return null;
			  }
			  else {
				  // update the index
				  index = path.lastIndexOf("/");
			  }
		  }
		  // add the last directory into the arraylist
		  Directory last = (Directory) FileSystem.getFileFromPath(root, curr, path);
		  update.add(last);
		  // reverse update to the right order
		  Collections.reverse(update);
		  paths.addAll(update);
	  }
	  else {// if index ==-1, inputs has no "/", which means this path resides just
		  // under curr
		  paths.add(toadd);
	  }
	  return paths;
  }
  
  /**
   * Given an absolute path,return the ArrayList<Directory> that record all of its
   * parent directory up to the root.
   * 
   * @param root is the root directory
   * @param path is the fullpath of the leaf directory whose parents are to be returned
   * @return the ArrayList of the parent directory 
   */
  public static ArrayList<Directory> CreatePath(Directory root, String path){
	  // get the index of the last "/"
	  int index = path.lastIndexOf("/");
	  // get the parent folder of path
	  String[] directories = path.split("/");
	  int size = directories.length;
	  ArrayList<Directory> reverse = new ArrayList<Directory>(size);
	  Directory curr = (Directory)FileSystem.getFileFromPath(root, root, path);
	  if (index !=-1){
		// construct the arraylist of path
		  while(curr!=root||curr!=null) {
			  reverse.add(curr);
			  // reach the last directory
			  if(index==0) {
				  break;}
			  else {
				  // update the path
				  String newpath = path.substring(0, index);
				  // update the directory curr
				  curr = (Directory)FileSystem.getFileFromPath(root, root, newpath);
				  // update the index
				  index = newpath.lastIndexOf("/");
			  	}
			  }
		  // check which exit condition is met, if the loop terminate because
		  // curr==null, return null.
		  if (curr==null) {
			  return null;}
		  reverse.add(root);
		  Collections.reverse(reverse);
		  return reverse;
	  	}
	  else {
		  return null; }
	  
  }
  

  /**
   * Return a file from a given path, relative to the given directories.
   * 
   * @param root is the root of a file system.
   * @param directory is the current working director.
   * @param path is the path that the file sits on.
   * @return file if file exists in path, otherwise return null.
   */
  public static File getFileFromPath(Directory root, Directory directory, 
		  String path) {
    Directory current = null;
    String[] directories = path.split("/");
    int i, index;
    if (directories.length == 0) {
      return root;
    }
    // if given absolute path
    if (path.charAt(0) == '/') {
      current = root;}
    else {
    	current = directory;
    }
    /*
    for (i = 0; i < directories.length; i++) {
    	System.out.println("directories["+i+"]:"+directories[i]);
    }
    System.out.println("the length is"+ directories.length);
    */
    
    // loop through the directory
    for (i = 0; i < directories.length - 1; i++) {
      if (directories[i].isEmpty()) {
        continue;
      }
      index = FileSystem.getIndexDirectory(directories[i], current);
      // if found, then update current
      if (index != -1) {
          current = (Directory) directory.getFiles().get(index);
          // update directory
          directory = current;
        } 
        else {
          return null;
        }
    }
    index = FileSystem.getIndex(directories[i], current);
    if (index != -1) {
      return current.getFiles().get(index);
    	}
    System.out.println("cannot found\n");
    return null;
  }



  /**
   * Return the parent folder from a given path, relative to the given directories.
   * 
   * @param root is the root of a file system.
   * @param directory is the current working director.
   * @param path is the path that the file sits on.
   * @return parent if parent exists in path, otherwise return null.
   */
  public static File getParentFromPath(Directory root, Directory directory, 
		  String path) {
    Directory current = null;
    String[] directories = path.split("/");
    int i, index;
    if (directories.length == 0) {
      return root;
    }
    if (path.charAt(0) == '/') {
      current = root;
    } else {// if given relative path
    	current = directory;
    }
    for (i = 0; i < directories.length - 1; i++) {
      if (directories[i].isEmpty()) {
        continue;
      }
      index = FileSystem.getIndexDirectory(directories[i], current);
      if (index != -1) {
        current = (Directory) directory.getFiles().get(index);
      } else {
        return null;
      }
    }
    return current;
  }
}

