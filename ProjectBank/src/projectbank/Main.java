package projectbank;

import java.util.ArrayList;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import imgui.type.ImString;


public class Main extends Application
{
	PasswordGenerator pass;
	AccountManager am;
    ImInt num = new ImInt(6);
    ImInt money = new ImInt();
    ImString name =  new ImString();
    ImString generatedPass = new ImString();
    ImString password = new ImString();
    ImBoolean flag = new ImBoolean();
    private float[] color = new float[3];
	private String success = "";
	private String accountBalance = "";
	private String withdraw = "";
    private ArrayList<ImString> userdata;
    private ArrayList<String> printdata;
    private String accountNO = "";
	private boolean passGenFlag = false; 
	
	
	public Main()
	{
		pass = new PasswordGenerator();
		am = new AccountManager();
		flag.set(false);
		userdata = new ArrayList<ImString>();
		printdata = am.getPrintData();
		genImString();
		color[0] = 1;
		color[1] = 1;
		color[2] = 1;
	}
	
	protected void configure(final Configuration config) 
    {
    	config.setTitle("Katinji Bank of India");
    	config.setFullScreen(false);
    }
	
	public static void main(String[] args) {
		Main main = new Main();
		launch(main);
	}

	@Override
	public void process() 
	{
		if(!am.getLoginStatus())
		{
			login();
		}
		if(flag.get())
		{
			createAccount();
		}
		if(passGenFlag)
		{
			passwordGenerator();
		}
		if(am.getLoginStatus())
		{
			Log.init();
			afterLogin(); 
		}
	}
	
	public void passwordGenerator()
	{
		ImGui.begin("PasswordGenerator");
		ImGui.textColored(1.0f, 0.0f, 0.0f, 1, "hello");
		ImGui.inputInt("Password Length", num);
		if(ImGui.button("Generate password"))
		{
			pass.process(num.get());
			
		}
		ImGui.pushTextWrapPos();
		ImGui.textColored(color[0], color[1], color[2], 1.0f, pass.getPassword());
		ImGui.newLine();
		generatedPass.set(pass.getPassword());
		Log.warn(pass.getPassword());
		ImGui.inputText("Your Password", generatedPass);
		ImGui.popTextWrapPos();
		ImGui.newLine();
		
		ImGui.colorEdit3("Pick A Color", color);
		ImGui.end();
	}
	
	public void login()
	{
		ImGui.begin("Account Login");
		ImGui.inputText("Username", name);
		ImGui.inputText("Password", password);
		if(ImGui.button("Login"))
		{
			success = am.accountLogin(name.get(),password.get());
		}
		ImGui.text(success);

		ImGui.newLine();
		if(ImGui.button("Create Account"))
		{
			flag.set(true);
		}
		
		if(ImGui.button("Password gen"))
		{
			passGenFlag = true;
		}
		
		if(ImGui.button("Close Windows"))
		{
			flag.set(false);
			passGenFlag = false;
		}
		
		ImGui.end();
	}
	
	public void createAccount()
	{
		ImGui.begin("Create Account");
		for(int i = 0; i < printdata.size(); i++)
		{
			ImGui.pushTextWrapPos();
			ImGui.inputText(printdata.get(i), userdata.get(i));
			ImGui.popTextWrapPos();
		}
		
		if(ImGui.button("Create Account"))
		{
			 am.createAnAccount(userdata);
			 accountNO = am.getAccountNO();
		}
		ImGui.text(accountNO);
		ImGui.text(am.getCreateError());
		ImGui.end();
	}
	
	public void afterLogin()
	{
		ImGui.begin("Your Account");
		ImGui.inputInt("Money",money);
		if(ImGui.button("deleteAccount"))
		{
			am.deleteAccount(name.get());
		}
		if(ImGui.button("Deposit"))
		{
			am.deposit(money.get());
		}
		if(ImGui.button("checkAccountBalance"))
		{
			accountBalance = am.checkAccountBalance();
		}
		Log.warn("Account Balance: " + accountBalance);
		if(ImGui.button("Withdrawal"))
		{
			withdraw = am.withdrawal(money.get());
		}
		Log.error(withdraw);
		if(ImGui.button("back"))
		{
			am.setLoginStatus(false);
		}
		ImGui.end();
	}
	
	public void genImString()
	{
		userdata.clear();
		for(int i=0; i < printdata.size(); i++)
		{
			ImString s = new ImString();
			userdata.add(s);
			s = null;
		}
	}
}
