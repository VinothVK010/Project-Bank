package projectbank;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity {

	public static void main(String[] args) 
	{
		Scanner command = new Scanner(System.in);
		CreateAccount ca = new CreateAccount();
		System.out.println(" Press \" 1 \" to Create an Account "
				+ "\n Press \" 2 \" to Exit");
		
		boolean programControl = true;
		while(programControl)
		{
			int input = 0;
			try
			{
				input = command.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("please enter something ! ");
			}
			switch(input)
			{
				case 1:
					ca.createAccounts();
					//programControl = false;
					break;
				case 2:
					programControl = false;
					break;
					
				default: System.out.println("Follow the above Instructions"
						+ " strictly ");
					break;
			
			}
			
		}	
		
	}

}
