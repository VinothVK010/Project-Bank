
package projectbank;

import java.util.ArrayList;

public class AccountHolder {
	
	
	private String name;
	private String fatherName;
	private String createdDate;
	private String aadhar;
	private String panNum;
	private String accountNo;
	private String password;
	private String accBalance;
	private ArrayList<String> userInputs;
	
	
	public AccountHolder(ArrayList<String>userInputs,boolean saveToFile)
	{
		this.userInputs = userInputs;
		setUserInputs();
		if(saveToFile)
			saveToFile();
	}
	
	public void setUserInputs()
	{
		name = userInputs.get(0);
		setPassword(userInputs.get(1));
		fatherName = userInputs.get(2);
		setAadhar(userInputs.get(3));
		setPanNum(userInputs.get(4));
		accountNo = userInputs.get(5);
		accBalance = userInputs.get(6);
		createdDate = userInputs.get(7);
	}
	
	public String getOutputString()
	{
		String output = String.format("%s,%s,%s,%s,%s,%s,%s,%s",name,password,fatherName,aadhar,panNum,accountNo,accBalance,createdDate);
		return output;
	}
	
	public void saveToFile() 
	{
		FileHandler fh = new FileHandler("data.csv");
		fh.writeString(getOutputString());
		fh.closeFile();
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

	public int getAccBalance() {
		return Integer.parseInt(accBalance);
	}

	public void setAccBalance(int accBalance) {
		this.accBalance = ""+accBalance;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPanNum() {
		return panNum;
	}

	public void setPanNum(String panNum) {
		this.panNum = panNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}