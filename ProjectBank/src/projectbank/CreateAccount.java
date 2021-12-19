package projectbank;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class CreateAccount {

	private ArrayList<String> printData = new ArrayList<>();
	//private HashMap<String,String> userInput = new HashMap<>();
	private ArrayList<String> userInput = new ArrayList<>();
	//private ArrayList<String> generatedAccNos = new ArrayList<>();
	
	private void getUserInformation()
	{
		Scanner scanner = new Scanner(System.in);
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
		scanner.close();
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
	
	private String createPasswords()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Password: ");
		return scanner.nextLine();
		
	}
	
	public void createAccounts()
	{
		getUserInformation();
		createAccountNo();
		AccountHolder newAccount = new AccountHolder(userInput);
		//System.out.println(newAccount);
		
	}
	
}
