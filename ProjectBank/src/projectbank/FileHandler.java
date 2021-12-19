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
		currentDirectory = System.getProperty("user.dir"); //returns the current working dir.
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
			fileWriter.write(s+"\n");//writing stuff with \n character.
			fileWriter.flush();//This has to be called to write the stuff on the file.otherwise it doesn't work
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
	
	public ArrayList<ArrayList<String>> splitStringFromFile()//better name would be CSVParser I guess.
	{
		ArrayList<ArrayList<String>> strings = new ArrayList<>();
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
				e.printStackTrace();
			}
			strings.add(parseString(line.split(",")));//usual CSV stuff nothing big.
			//strings.add(getTokens("[a-zA-Z1-9^, ]*",line)); //Slight Flexing Don't judge me!! These are regular expressions.
		}
		return strings;
	}
	
	public void closeFiles()//Always close the files from the code.
	{
		try
		{
			fileWriter.close();
			fileReader.close();
		} 
		catch (IOException e)//This won't run LOL but java wants it.
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		//example 
		FileHandler fh = new FileHandler("data.csv");//Any better name for the file??
		System.out.println(fh.splitStringFromFile());//testing stuff
		fh.closeFiles();
	}

}
