
package projectbank;

import java.util.Date;
import java.util.ArrayList;

public class AccountHolder {
	
	
	private String name;
	private String fatherName;
	private String createdDate;
	private String aadhar;
	private String panNum;
	private String accountNo;
	private String password;
	private ArrayList<String> userInputs;
	
	
	public AccountHolder(ArrayList<String>userInputs)
	{
		this.userInputs = userInputs;
		Date date = new Date();
		createdDate = date.toString();
		setUserInputs();
	}
	
	public void setUserInputs()
	{
		name = userInputs.get(0);
		fatherName = userInputs.get(1);
		aadhar = userInputs.get(2);
		panNum = userInputs.get(3);
		accountNo = userInputs.get(4);
		password = userInputs.get(5);
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
		return name + " " + fatherName;
	}
	
	
}