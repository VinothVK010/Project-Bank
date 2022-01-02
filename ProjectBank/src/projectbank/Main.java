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
    ImString name =  new ImString();
    ImString generatedPass = new ImString();
    ImString password = new ImString();
    ImBoolean flag = new ImBoolean();
    private float[] color = new float[3];
	private String sucess = "";
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
    	config.setTitle("Project Bank");
    	config.setFullScreen(false);
    }
	
	public static void main(String[] args) {
		Main main = new Main();
		launch(main);
	}

	@Override
	public void process() 
	{
		login();
		if(flag.get())
		{
			createAccount();
		}
		Log.init();
		if(passGenFlag)
		{
			passwordGenerator();
		}
		afterLogin();
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
		//ImGui.text(pass.getPassword());
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
			sucess = am.accountLogin(name.get(),password.get());
		}
		ImGui.text(sucess);

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
			//ImGui.sameLine();
			ImGui.pushTextWrapPos();
			if(ImGui.inputText(printdata.get(i), userdata.get(i)))
			{
			}
			ImGui.popTextWrapPos();
		}
		
		if(ImGui.button("Create Account"))
		{
			am.createAnAccount(userdata);
			accountNO = am.getAccountNO();
		}
		ImGui.text(accountNO);
		ImGui.end();
	}
	
	public void afterLogin()
	{
		ImGui.begin("Your Account");
		if(ImGui.button("deleteAccount"))
		{
			am.deleteAccount("kar");
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
