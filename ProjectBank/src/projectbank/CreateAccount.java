package projectbank;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class CreateAccount {

	private ArrayList<String> printData = new ArrayList<>();
	//private HashMap<String,String> userInput = new HashMap<>();
	private ArrayList<String> userInput = new ArrayList<>();
	//private ArrayList<String> generatedAccNos = new ArrayList<>();	
	private Scanner scanner = new Scanner(System.in);
	
	private void getUserInformation()
	{
		String userData = "";
		
		printData.add("Enter your Name: ");
		printData.add("Enter Father Name: ");
		printData.add("Enter the AadharNumber: ");
		printData.add("Enter PAN Number: ");
		
		for(String s : printData)
		{
			System.out.print(s);	
			userData = scanner.nextLine();
			userInput.add(userData);
			
		}	
		
	}
	
	private void createAccountNo()
	{
		Random random = new Random();
		String accountNo = "9416";
		int number = 0;
		
		for(int k = 5; k <= 11; k++)
		{
			 number = random.nextInt(9);
			 accountNo += number;
		}
		
		userInput.add(accountNo);
		System.out.println("Account Number : " + accountNo);
	}
	
	public void createPasswords()
	{
		
		System.out.println("Enter your Password: ");
		String password = scanner.nextLine();
		
		System.out.println(password);
		userInput.add(password);
		
		scanner.close();
	}
		
	
	public void createAccounts()
	{
		getUserInformation();
		createAccountNo();
		createPasswords();
		AccountHolder newAccount = new AccountHolder(userInput);
		//System.out.println(newAccount);
		
	}
	
	
}
