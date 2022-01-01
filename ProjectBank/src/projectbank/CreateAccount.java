package projectbank;

import java.util.Scanner;

import imgui.type.ImString;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class CreateAccount 
{
	private ArrayList<String> printData;
	//private HashMap<String,String> userInput = new HashMap<>();
	private ArrayList<String> userInput;	
	private Scanner scanner;
	//private FileHandler fh;
	private Date date;
	private boolean flag = true;
	private String accountNO;
	
	public CreateAccount()
	{
		 printData = new ArrayList<>();
		 userInput = new ArrayList<>();
		 date = new Date();
		 scanner = new Scanner(System.in);
		 //fh = new FileHandler("printdata.txt");
		 //printData = fh.getPrintData();
	}
	
	private String createAccountNo()
	{
		Random random = new Random();
		int number = 0;
		String accountNo = "9416";
		for(int k = 5; k <= 11; k++)
		{
			
			 number = random.nextInt(9);
			 accountNo += number;
		}
		
		userInput.add(accountNo);
		System.out.println("Account Number : " + accountNo);
		if(flag)
		{
			return accountNo;
		}
		return "";
	}
	
	private void getUserInformation()
	{
		String userData = "";
		//printData = fh.getPrintData();
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
		if(flag)
		{
			AccountHolder newAccount = new AccountHolder(userInput,true);
		}
		//System.out.println(newAccount);
		
	}
	
	

	public void createAccount(ArrayList<ImString> userdata) {
		getUserInformation(userdata);
		if(flag)
		{
			AccountHolder newAccount = new AccountHolder(userInput,true);
		}
				
	}

	private void getUserInformation(ArrayList<ImString> userdata) 
	{
		userInput.clear();
		for(ImString s : userdata)
		{
			//System.out.print(s);
			if(s.isNotEmpty())
				userInput.add(s.get());
			else
				flag = false;
		}	
		if(flag)
		{
			createAccountNo();
			userInput.add("0");
			userInput.add(date.toString());
		}
		
	}
	
	public ArrayList<String> getPrintData()
	{
		return this.printData;
	}

	public String getAccountNO() 
	{
		return accountNO = createAccountNo();
	}
	
}
