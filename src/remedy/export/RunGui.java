package remedy.export;

import gui.remedy.export.Gui;

import javax.swing.JFrame;

public class RunGui implements Runnable {

	public RunGui() {

	}

	
	@Override
	public void run() {
		// Create and set up the window.

		Gui gui = new Gui("Export Remedy data to Excel");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
