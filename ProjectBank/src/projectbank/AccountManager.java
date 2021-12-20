package projectbank;

import java.util.ArrayList;
import java.util.HashMap;

//TODO Some has to do this stuff.

public class AccountManager 
{
	
	public AccountManager()
	{
		
	}
	
	public void createAnAccount()
	{
		CreateAccount createAccount = new CreateAccount();
		createAccount.createAccount();
	}
	
	private void loadExistingAccounts()//Load all the account data from the file.
	{
		
	}
	
	public AccountHolder accountLogin()//if username and passwords match then return the AccountHolder obj for withdrawal and deposit purposes.
	{
		return null;
	}
	
	public boolean isAccountAlreadyExists()//check if the account already exists. HINT add some parameters.
	{
		return true;
	}
	
	public void deleteAccount()//Delete the account I guess hmmm.
	{
		
	}
	
	public void dumpToFile() //dump all info from the currently loaded account details to disk.
	{
		
	}
	
	public HashMap<String,ArrayList<AccountHolder>> getLoadedAccounts() //instantiate AccountHolder objs.
	{
		return null;
	}
	
	public static void main(String[] args) 
	{
		
	}

}
