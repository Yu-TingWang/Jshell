package source;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ArrayList;
import source.Pwd;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
import source.Files.File;
import java.net.HttpURLConnection;

/**
 * This method would copy the file resides in the URL address to the current
 * working directory
 * @param root is the root directory
 * @param curr is the current working directory
 * @input is the URL address
 * @author wangy964
 *
 */
public class Get extends Command{
	public static void execute(Directory root, Directory curr,ArrayList<Directory> path, String input ) {
		// get the name of the file
		int index = input.lastIndexOf('/');
		String filename = input.substring(index+1, input.length());
		File file = new File(filename);
		if (FileSystem.isUnique(filename, curr)) {
			// add it to the current working directory
			String fullpath;
			//curr.addFile(file);
			if(curr == root) {
				fullpath= Pwd.execute(path)+filename;
			}
			else{
				fullpath= Pwd.execute(path)+"/"+filename;
			}
			BufferedInputStream inputs = null;
			FileOutputStream outputs = null;
			    try {
			        URL url = new URL(input);
			        /*
			        String htmlcontent = " ";
	                HttpURLConnection c = (HttpURLConnection) url.openConnection();
	                c.connect();
	                InputStream inputk = c.getInputStream();
	                int data;
	                InputStreamReader reader = new InputStreamReader(inputk);
	                data = reader.read();
	                while (data != -1)
	                {
	                    char content = (char) data;
	                    htmlcontent+=content;
	                    System.out.print(htmlcontent);
	                    data = reader.read();
	                }
	                */
			        //BufferedInputStream inputs = new BufferedInputStream(url.openStream());
			        inputs = new BufferedInputStream(url.openStream());
			        //FileOutputStream outputs = new FileOutputStream("/"+filename);
			        outputs = new FileOutputStream(System.getProperty("user.dir")+"/"+filename);
			        byte[] buffer = new byte[1024];
			        int count=0;
			        /*
			        int data = inputs.read();
			        while (data != -1) {
			        	outputs.write(data);
			        	data = inputs.read();
			        }
			        */			
			        StringBuilder sb = new StringBuilder();
			        while((count = inputs.read(buffer,0,1024)) != -1)
			        {
			        	sb.append(buffer);
			            outputs.write(buffer, 0, count);
			        }
			        outputs.close();
			        inputs.close();
			        /*
			        InputStream is = new FileInputStream(System.getProperty("user.dir")+"/"+filename);
			        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			        String line = buf.readLine();
			        while(line != null){
			        	   sb.append(line).append("\n");
			        	   line = buf.readLine();
			        }*/
			        ContentFile newFile = new ContentFile(filename, sb.toString());
			        curr.addFile(newFile);
			    } catch (FileNotFoundException e) {
			    	// catch 404
			    } catch (IOException e) {
					// catch it here
				}
		}
		else {
			System.out.println("file name is already used.\n");
		}
		
	}

	/**
	   * 
	   * @return manual for Get
	   */
	public static String man() {
		// TODO Auto-generated method stub
		return "Retrieve the \file at that URL\n" + 
				"and add it to the current working directory.";
	}
}
