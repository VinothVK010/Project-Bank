package projectbank;

import imgui.ImGui;
import imgui.app.Application;

public class Main extends Application
{
	public static void main(String[] args) {
		Main main = new Main();
		launch(main);
	}

	@Override
	public void process() {
		ImGui.begin("Helloworld");
		ImGui.textColored(1.0f, 0.0f, 0.0f, 1, "hell");
		if(ImGui.button("click me !"))
			System.out.println("clicked");
		ImGui.end();
	}
}
