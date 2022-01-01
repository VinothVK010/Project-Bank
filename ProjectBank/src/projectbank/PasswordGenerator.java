package projectbank;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator 
{
	private Random random;
	private Scanner scanner;
	private String password;
	private FileHandler fh;
	
	public PasswordGenerator()
	{
		random = new Random();
		scanner = new Scanner(System.in);
		password = "";
		fh = new FileHandler("randomPassword.txt");
	}
	
	public void loop()
	{
		 boolean programcontrol = true;
		 while(programcontrol)
		 {
			 System.out.print("Enter \"0\" for exit \nEnter the length for your password: ");
			 try 
			 {
				 int input = scanner.nextInt();
				 process(input);
				 password = "";
				 if(input == 0)
				 {
					 System.out.println("Your brain is gonna EXPLODE IN: ");
					 for(int i=3; i>0; i--)
					 {
						 System.out.println(i);
					 }
					 System.out.println("KABOOM!!!");
					 fh.closeFile();
					 programcontrol = false;
				 }
			 } 
			 catch (Exception e) 
			 {
				System.out.println("We're asking for Integer but you entered this "
								+ ""+e.toString() +" you Moron :{ \nRerun the program"
								+ "\nFor god's sake enter some number ex:\"123\" YOU MORON !!!"); 
				//e.printStackTrace();
				//program(programcontrol);
				break;
			 }
		 }
	}
	
	public void process(int input)
	{
		  String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		  		+ "abcdefghijklmnopqrstuvwxyz"
		  		+ "1234567890"
		  		+ "!@#$%^&*_";
		  password = "";
		  for(int k = 0; k < input; k++)
		  {
			  int number = random.nextInt(alphabet.length());
			  char letter = alphabet.charAt(number);
			  password += letter;
		  }
		  
		  if(password.length()>4)
			  fh.writeString(password);
		  //if(password.length()>0)
			  //System.out.println(password);
	}

	public String getPassword()
	{
		return this.password;
	}
	
	public static void main(String[] args) 
	{
		PasswordGenerator result = new PasswordGenerator();
		result.loop();
		//result.process(8);
		////System.out.println(result.fh.getPrintData());
	}

}
