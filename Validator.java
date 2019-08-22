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
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
/**
 * This class contains the method to check the validity of commands.
 * 
 * @author wangy964
 *
 */
public class Validator {
  public static boolean isValid(String[] inputarray) {
    switch (inputarray[0]) {
      case "man":
        if (inputarray.length != 2) {
          System.out.println("man: accepts a single CMD argument");
          return false;
        }
        return true;
      case "mkdir":
        if (inputarray.length < 2) {
          System.out.println("mkdir: missing operand");
          return false;
        }
        return true;
      case "pwd":
        if (inputarray.length > 1) {
          System.out.println("pwd: accepts no arguments");
          return false;
        }
        return true;
      case "cd":
        if (inputarray.length < 2) {
          System.out.println("cd: missing operand");
          return false;
        }
        return true;
      case "tree":
    	  if(inputarray.length >1) {
    		System.out.println("tree: accepts no arguments");
            return false;
    	  }
    	 return true;
      case "mv":
    	  if(inputarray.length != 3) {
      		System.out.println("mv: require an old path and a new path");
              return false;
      	  }
      	 return true;
      case "cp":
    	  if(inputarray.length != 3) {
        		System.out.println("cp: require an old path and a new path");
                return false;
        	  }
        	 return true;
      case "get":
    	  if(inputarray.length < 2 ) {
        		System.out.println("get: missing operand");
                return false;
        	  }
        	 return true;
      case "save":
    	  if(inputarray.length != 2 ) {
      		System.out.println("save: accepts a fileName");
              return false;
      	  }
      	 return true;
      case "load":
    	  if(inputarray.length != 2 ) {
      		System.out.println("load: accepts a fileName");
              return false;
      	  }
      	 return true;
      case "find":
    	  if(inputarray.length < 6 ) {
      		System.out.println("the syntax of find is:\n"+
    	  "find path ... -type [f|d] -name expression");
              return false;
      	  }
      	 return true;
    	  
      default:
        return true;
    }
  }
}
