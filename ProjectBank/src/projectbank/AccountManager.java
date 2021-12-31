package projectbank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

//TODO Someone has to do this stuff.

public class AccountManager 
{
	private FileHandler fh;
	private HashMap<String,ArrayList<String>> accountsData;
	private Scanner scanner;
	private HashMap<String,AccountHolder> accountHolderObjs;
	
	public AccountManager()
	{
		fh = new FileHandler("data.csv");
		accountsData = new HashMap<>();
		scanner = new Scanner(System.in);
		loadExistingAccountsData();
		accountHolderObjs = getLoadedAccounts();
	}
	
	public void createAnAccount()
	{
		CreateAccount createAccount = new CreateAccount();
		createAccount.createAccount();
	}
	
	public void loadExistingAccountsData()//Load all the account data from the file.
	{
		accountsData = fh.splitStringFromFile();
		//System.out.println(accountsData.keySet());
	}
	
	public AccountHolder accountLogin()//if username and passwords match then return the AccountHolder obj for withdrawal and deposit purposes.
	{
		System.out.print("Enter your UserName: ");
		String username = scanner.next();
		if(accountHolderObjs.containsKey(username))
		{
			AccountHolder ac = accountHolderObjs.get(username);
			System.out.print("Enter your Password: ");
			String password = scanner.next();
			if(password.equals(ac.getPassword()))
			{
				System.out.println("Account Login Successfull !!! ");
			}
			else 
			{
				System.out.println("Your Username (or) Password is Incorrect ");
			}
		}
		return null;
	}
	
	public boolean isAccountAlreadyExists(String username)//check if the account already exists. HINT add some parameters.
	{
		
		
		return accountHolderObjs.containsKey(username);
	}
	
	public void deleteAccount()//Delete the account I guess hmmm.
	{
		
	}
	
	public void dumpToFile() //dump all info from the currently loaded account details to disk.
	{
		
	}
	
	public HashMap<String,AccountHolder> getLoadedAccounts() //instantiate AccountHolder objs.
	{
		HashMap<String,AccountHolder> accountData = new HashMap<>();
		for(String s : accountsData.keySet())
		{
			AccountHolder ac = new AccountHolder(accountsData.get(s),false);
			//ac.setAccBalance(5000);
			//ac.saveToFile();
			accountData.put(s, ac);
			ac = null;
		}
			
		return accountData;
	}
	
	public static void main(String[] args) 
	{
		AccountManager am = new AccountManager();
		//am.getLoadedAccounts();
	
	}
}
