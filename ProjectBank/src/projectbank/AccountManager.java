package projectbank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import imgui.type.ImString;

//TODO Someone has to do this stuff.

public class AccountManager 
{
	private FileHandler fh;
	private HashMap<String,ArrayList<String>> accountsData;
	private Scanner scanner;
	private HashMap<String,AccountHolder> accountHolderObjs;
	private ArrayList<String> printdata;
	private CreateAccount ca;
	private boolean loginFlag = false;
	private AccountHolder ah;
	private String createError = "";
	
	public AccountManager()
	{
		fh = new FileHandler("printdata.txt");
		printdata = fh.getPrintData();
		fh.closeFile();
		accountsData = new HashMap<>();
		scanner = new Scanner(System.in);
		ca = new CreateAccount();
	}
	
	public void createAnAccount()
	{
		CreateAccount createAccount = new CreateAccount();
		createAccount.createAccount();
		
	}
	
	public void loadExistingAccountsData()//Load all the account data from the file.
	{
		FileHandler fh = new FileHandler("data.csv");
		accountsData = null;
		accountsData = fh.splitStringFromFile();
		accountHolderObjs = getLoadedAccounts();
		fh.closeFile();
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
	
	public void deleteAccount(String accountName)//Delete the account I guess hmmm.
	{
		loadExistingAccountsData();
		if(isAccountAlreadyExists(accountName))
		{
			accountHolderObjs.remove(accountName);
			this.dumpToFile();
		}
	}
	
	public void dumpToFile() //dump all info from the currently loaded account details to disk.
	{
		FileHandler fh = new FileHandler("data.csv");
		String dump = "";
		for(String s : accountHolderObjs.keySet())
		{
			dump += accountHolderObjs.get(s).getOutputString() +"\n"; 
		}
		fh.dumString(dump);
	}
	
	public HashMap<String,AccountHolder> getLoadedAccounts() //instantiate AccountHolder objs.
	{
		HashMap<String,AccountHolder> accountData = new HashMap<>();
		for(String s : accountsData.keySet())
		{
			AccountHolder ac = new AccountHolder(accountsData.get(s),false);
			accountData.put(s, ac);
			ac = null;
		}
		return accountData;
	}
	
	public String accountLogin(String userName, String password) 
	{
		loadExistingAccountsData();
		String success ="Your Username (or) Password is Incorrect ";
		if(accountHolderObjs.containsKey(userName))
		{
			AccountHolder ac = accountHolderObjs.get(userName);
			if(password.equals(ac.getPassword()))
			{
				success = "Account Login Successfull !!! ";
				loginFlag = true;
				ah = ac;
			}
			else 
			{
				success = "Your Username (or) Password is Incorrect ";
				loginFlag = false;
				ah = null;
			}
		}
		return success;
	}
	
	public void deposit(int amount)
	{
		if(loginFlag)
		{
			if(amount > 0)
			{
				ah.setAccBalance(ah.getAccBalance() + amount);
				this.dumpToFile();
			}
		}
	}
	public String checkAccountBalance()
	{
		String accountBal = "";
		if(loginFlag)
		{
			accountBal = ah.getAccBalance() +"";
		}
		return accountBal;
	}
	public String withdrawal(int amount)
	{
		String success = ""; 
		if(loginFlag)
		{
			int currBalance = ah.getAccBalance();
			if(currBalance < amount || amount == 0)
			{
				success = String.format("your Current balance is $%d how can you take such Amount $%d first "
						+ "deposit some amount after that I'm sure you can withdraw ",currBalance,amount);
				return success;
			}
			else
			{	if(currBalance > 0)
				{
					int newBalance = currBalance - amount;
					if(newBalance >= 0)
					{
						ah.setAccBalance(newBalance);
						this.dumpToFile();	
					}
				}
			}
		}
		return success;
	}
	
	public void createAnAccount(ArrayList<ImString> userdata) 
	{
		loadExistingAccountsData();
		if(!accountsData.containsKey(userdata.get(0).get()))
		{
			String userName = userdata.get(0).get();
			String password = userdata.get(1).get();
			if(userName.equals(password))
			{
				this.createError = "UserName and Password can't be the same";
				return;
			}
			if(password.length() <= 6)
			{
				this.createError = "Password must be more than \"6\" characters";
				return;
			}
			ca.createAccount(userdata);
		}
		else
		{
			this.createError = "Account Already exists !! Contact Katinji tech "
					+ "Support they are good guy :)";
		}
	}
	
	public ArrayList<String> getPrintData()
	{
		return this.printdata;
	}

	public String getAccountNO() 
	{	
		return ca.getAccountNO();
	}

	public boolean getLoginStatus() 
	{
		return loginFlag;
	}

	public void setLoginStatus(boolean b) {

		this.loginFlag = b;
	}

	public String getCreateError() {
		return this.createError;
	}
}
