
package projectbank;

import java.util.HashMap;
import java.util.Scanner;

import org.w3c.dom.UserDataHandler;

import java.util.Date;
import java.util.ArrayList;

public class AccountHolder {
	
	private String name;
	private Date createdDate;
	private String aadhar;
	private String panNum;
	private String fatherName;
	private ArrayList<String> userInputs;
	
	
	public AccountHolder(ArrayList<String>userInputs)
	{
		this.userInputs = userInputs;
		setUserInputs();
	}
	
	public void setUserInputs()
	{
		this.name = userInputs.get(0);
		this.aadhar = userInputs.get(1);
		this.panNum = userInputs.get(2);
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	
}