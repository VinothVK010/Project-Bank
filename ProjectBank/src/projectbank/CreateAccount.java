package projectbank;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class CreateAccount 
{
	private ArrayList<String> printData;
	//private HashMap<String,String> userInput = new HashMap<>();
	private ArrayList<String> userInput;	
	private Scanner scanner;
	private FileHandler fh;
	private Date date;
	
	public CreateAccount()
	{
		 printData = new ArrayList<>();
		 userInput = new ArrayList<>();
		 date = new Date();
		 scanner = new Scanner(System.in);
		 fh = new FileHandler("printdata.txt");
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
	
	private void getUserInformation()
	{
		String userData = "";
		printData = fh.getPrintData();
		for(String s : printData)
		{
			System.out.print(s);	
			userData = scanner.nextLine();
			userInput.add(userData);
		}	
		createAccountNo();
		userInput.add("0");
		userInput.add(date.toString());
	}
	
	public void createAccount()
	{
		getUserInformation();
		AccountHolder newAccount = new AccountHolder(userInput);
		//System.out.println(newAccount);
		
	}
	
	
}
