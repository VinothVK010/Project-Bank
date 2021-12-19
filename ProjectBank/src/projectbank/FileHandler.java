package projectbank;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
//import java.io.FileWriter;

//TODO do this stuff LOL



public class FileHandler {
	
	private Path filePath;
	private BufferedReader fileReader;
	private static String currentDirectory;
	private BufferedWriter fileWriter;
	
	public FileHandler(String fileName)
	{
		currentDirectory = System.getProperty("user.dir"); //returns the current working dir.
		//String osName = System.getProperty("os.name");	//returns the os name.
		filePath = Paths.get(currentDirectory + "/data/" + fileName);//current working dir
		try
		{
			//FileWriter writer = new FileWriter("D:\\testout.txt");  
			//bufferedWriter = new BufferedWriter(writer);
			//bufferedWriter.write("Hello");
		    //FileWriter writer = new FileWriter("D:\\testout.txt");  
		    fileWriter = Files.newBufferedWriter(filePath,StandardOpenOption.APPEND,StandardOpenOption.CREATE); 
		    
		    //fileWriter.write("Kamrugilan,,,");  
		    //fileWriter.close();  
			fileReader = Files.newBufferedReader(filePath);
		} 
		catch (IOException e)
		{
			System.out.println("The specified file is not found! " + fileName);
			e.printStackTrace();
		}
		
	}
	
	public void writeString(String s)
	{
		try
		{
			fileWriter.write(s+"\n");
			fileWriter.flush();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void closeFiles()
	{
		try
		{
			fileWriter.close();
			fileReader.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected ArrayList<String> getTokens(String pattern,String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	private ArrayList<String> parseString(String[] lines)
	{
		ArrayList<String> userInputs = new ArrayList<>();
		//userInputs = Arrays.asList(lines); //not Working Idk why?
		for(String s : lines)
		{
			userInputs.add(s);
		}
		return userInputs;
	}
	
	public ArrayList<ArrayList<String>> splitStringFromFile()
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
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strings.add(parseString(line.split(",")));
			//strings.add(getTokens("[a-zA-z1-9^,]*",line)); //Slight Flexing Don't judge me!!
		}
		return strings;
	}
	
	public static void main(String[] args) 
	{
		FileHandler fh = new FileHandler("data.csv");
		//fh.writeString("Hello MFS hello");
		//fh.writeString("karmugilan,karm");
		
		System.out.println(fh.splitStringFromFile());
		
		fh.closeFiles();
	}

}
