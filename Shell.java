package source;

import java.io.IOException;
import java.util.ArrayList;
// **********************************************************
// Assignment2:
// Student1: Yu-Ting Wang
// UTORID user_name: wangy964
// UT Student #: 1004340331
// Author: Yu-Ting Wang
//
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
import source.Files.*;

/**
 * This class represents a Shell, capable of parsing commands and returning output.
 * @author wangy964
 *
 */
public class Shell {
  Console console;

  public Shell(Console console) {
    this.console = console;
  }

  public String input(String command) throws IOException {
    Output output = new Output();
    String[] inputarray = command.trim().split("\\s+");
    Directory current = console.fs.getPath().get(console.fs.getPath().size() - 1);
    Directory root = console.fs.getPath().get(0);
    ArrayList<Directory> paths = console.fs.getPath();
    
    /* COMMAND CASES */
    if (Validator.isValid(inputarray)) {
      switch (inputarray[0]) {
        case "exit": Exit.execute(console); break;
        case "ls": output.appendOutput(Ls.execute(root, current,paths,inputarray));break;
        case "mkdir": output.appendOutput(Mkdir.execute(root, current, inputarray)); break;
        case "cd": output.appendOutput(Cd.execute(console.fs, inputarray[1], current)); break;
        case "pwd": output.appendOutput(Pwd.execute(paths) + "\n"); break;
        case "echo": output.appendOutput(Echo.execute(command, current, root)); break;
        case "cat": output.appendOutput(Cat.execute(root, current, inputarray)); break;
        case "history": output.appendOutput(console.history.execute(inputarray)); break;
        case "pushd": output.appendOutput(Pushd.execute(console, inputarray[1])); break;
        case "popd": output.appendOutput(Popd.execute(console)); break;
        case "man": output.appendOutput(Man.execute(inputarray)); break;
        case "tree": output.appendOutput(Tree.execute(root, current,0));break;
        case "save": Save.execute(console.history, inputarray[1]); break;
        case "load": Load.execute(console, inputarray[1]);break;
        case "get": Get.execute(root,current,paths,inputarray[1]);break;
        case "find": output.appendOutput(Find.execute(root, current,paths,inputarray));break;
        case "mv": output.appendOutput(Move.execute(root, current,paths, inputarray[1], inputarray[2])); break;
        case "cp": output.appendOutput(Copy.execute(root,current, paths,inputarray[1], inputarray[2])); break;
        default: output.appendOutput("Command '" + inputarray[0] + "' not found %n"); break;
      }
    }
    return output.getOutput();
  }
}
