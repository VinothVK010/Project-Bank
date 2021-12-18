package projectbank;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class CreateAccount {

	private ArrayList<String> printData = new ArrayList<>();
	//private HashMap<String,String> userInput = new HashMap<>();
	private ArrayList<String> userInput = new ArrayList<>();
	private String name;
	
	
	public void getUserInformation()
	{
		Scanner scanner = new Scanner(System.in);
		String userData = "";
		
		printData.add("Enter your Name: ");
		printData.add("Enter Father Name: ");
		printData.add("Enter the AadharNumber: ");
		printData.add("Enter PAN Number: ");
		
		
		for(String s : printData)
		{
			System.out.println(s);	
			userData = scanner.nextLine();
			userInput.add(userData);
			
		}	
	}
	
	public void createAccount()
	{
		
		AccountHolder newAccount = new AccountHolder(userInput);
		System.out.println(newAccount);
	}
	
	public static void main(String[] args) 
	{
		CreateAccount ca = new CreateAccount();
		ca.getUserInformation();
		
		
	}

}
