package source;

import java.io.IOException;
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
import java.util.Scanner;

/**
 * This class manages output.
 * 
 * @author wangy964
 *
 */
public class Console {
	Shell shell;
	private Boolean run;
	FileSystem fs;
	History history;

	public Console(FileSystem fs) {
		shell = new Shell(this);
		setRun(true);
		this.fs = fs;
		this.history = new History();
	}

	public void io() throws IOException {
		Scanner scanner = new Scanner(System.in);

		while (getRun()) {
			System.out.print(Pwd.execute(fs.getPath()) + "#: ");

			String input = scanner.nextLine();

			history.addHistory(input.trim());

			if (!input.isEmpty()) {
				System.out.printf(shell.input(input));
			}
		}
		scanner.close();
	}

	public Boolean getRun() {
		return run;
	}

	public void setRun(Boolean run) {
		this.run = run;
	}
}
