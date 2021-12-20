package projectbank;

/**
 * Always sticking to csv files but why?.IDK but I like it.
 * @author karmugilan
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
//import java.io.FileWriter;

//TODO do this stuff LOL

public class FileHandler 
{
	private Path filePath;
	private BufferedReader fileReader;
	private static String currentDirectory;
	private BufferedWriter fileWriter;
	
	public FileHandler(String fileName)//TODO change the constructor and come up with a better idea.
	{
		//Because it's static
		currentDirectory = System.getProperty("user.dir"); //returns the current working dir.
		openFile(fileName);
	}
	
	public void openFile(String fileName)
	{
		filePath = Paths.get(currentDirectory + "/data/" + fileName);//current working dir
		try
		{
		    fileWriter = Files.newBufferedWriter(filePath,StandardOpenOption.APPEND,StandardOpenOption.CREATE); //Interesting one mmmm.
		    fileReader = Files.newBufferedReader(filePath);
		} 
		catch (IOException e)//This won't work for now
		{
			System.out.println("The specified file is not found! " + fileName);
			e.printStackTrace();
		}
	}
	
	public void writeString(String s)//Any better name?? for this fn.
	{
		try
		{
			fileWriter.write(s + "\n");//writing stuff with \n character.
			fileWriter.flush();//This has to be called to write the stuff on the file otherwise it doesn't work.
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void dumString(String s)
	{
		fileWriter = null;
		try
		{
			fileWriter = Files.newBufferedWriter(filePath, StandardOpenOption.WRITE,StandardOpenOption.CREATE);
			fileWriter.write(s);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<String> getTokens(String pattern,String text) //Just Flexing here don't judge me LOL.
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	private ArrayList<String> parseString(String[] lines) //java doesn't have one fn like this so I have to write it .
	{
		ArrayList<String> userInputs = new ArrayList<>();	
		//userInputs = Arrays.asList(lines); //not Working Idk why?
		for(String s : lines)
		{
			userInputs.add(s);
		}
		return userInputs;
	}
	
	public HashMap<String,ArrayList<String>> splitStringFromFile()//better name would be CSVParser I guess.
	{
		HashMap<String,ArrayList<String>> strings = new HashMap<>();
		while(true)
		{
			String line = "";
			try
			{
				line = fileReader.readLine();
				if(line == null)
					break;
			} 
			catch (IOException e)
			{
				System.out.println("Never close a file while your are reading it!");
				e.printStackTrace();
				break;
			}
			String[] strs =  line.split(",");
			strings.put(strs[0],parseString(strs));//usual CSV stuff nothing big.
			//strings.add(getTokens("[a-zA-Z1-9^, ]*",line)); //Slight Flexing Don't judge me!! These are regular expressions.
		}
		return strings;
	}
	
	public ArrayList<String> getPrintData()
	{
		ArrayList<String> printData = new ArrayList<>();
		while(true)
		{
			String line = "";
			try
			{
				line = fileReader.readLine();
				if(line == null)
					break;
			} 
			catch (IOException e)
			{
				System.out.println("Never close a file while your are reading it!");
				e.printStackTrace();
				break;
			}
			printData.add(line);
		}
		return printData;
	}
	
	public void closeFile()//Always close the files from the code.
	{
		try
		{
			fileWriter.close();
			fileReader.close();
			fileWriter = null;
			fileReader = null;
		} 
		catch (IOException e)//This won't run LOL but java wants it.
		{
			System.out.println("Don't delete the file while the App is running!");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		//example 
		FileHandler fh = new FileHandler("data.csv");//Any better name for the file??
		System.out.println(fh.splitStringFromFile());//testing stuff
		fh.closeFile();//always close one file before opening another one!.
		fh.openFile("data2.csv");
		//fh.writeString("vinoth,543,23489,8");//testing for if the writing the file works or not
		System.out.println(fh.splitStringFromFile());//always call before closing the file 
		//fh.dumString("karmugilan,karmugilan2002,rm,8825679623,8825679624,94165662083,0,Mon Dec 20 10:57:33 IST 2021,\n");
		fh.closeFile();
		fh.openFile("printdata.txt");
		System.out.println(fh.getPrintData());
		fh.closeFile();
	}

}
