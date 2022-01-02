package projectbank;
import imgui.ImGui;

public class Log
{

	public Log()
	{
	}
	
	public static void init()
	{
		ImGui.begin("LOG");
		ImGui.end();
	}
	
	public static void info(String s)
	{
		ImGui.begin("LOG");
		ImGui.pushTextWrapPos();
		ImGui.text("[INFO] : " + s);
		ImGui.popTextWrapPos();
		ImGui.end();
	}
	
	public static void error(String s)
	{
		ImGui.begin("LOG");
		ImGui.pushTextWrapPos();
		ImGui.textColored(1.0f, 0.0f, 0.0f, 1.0f, "[ERROR] : " + s);
		ImGui.popTextWrapPos();
		ImGui.end();
	}
	
	public static void warn(String s)
	{
		ImGui.begin("LOG");
		ImGui.pushTextWrapPos();
		ImGui.textColored(0.25f, 1.0f, 0.0f, 1.0f, "[Warning] : " + s);
		ImGui.popTextWrapPos();
		ImGui.end();
	}

}
