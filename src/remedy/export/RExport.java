package remedy.export;

import gui.remedy.export.Gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RExport implements Connectable {

	protected Connection conn;
	protected Gui gui;
	protected ArrayList<Report> reports;
	protected ArrayList<String> agents;

	// Flags
	protected Boolean isConnected;

	public RExport(Gui gui) throws IOException {

		this.gui = gui;
		reports = new ArrayList<Report>();
		agents = new ArrayList<String>();

		// Get connection to AR System in background
		broadcast("Establishing connection to AR system");
		new ARConnect(this, "qxi9560", "34erdfcv") {
			public void done() {
				if (connectionError == null) {
					owner.setConn(conn);
					owner.setIsConnected(true);
					broadcast("Connection established");
					//TODO display connection label
				} else {

					broadcast("Connection Failed");
					//TODO display connection label
					owner.broadcast(connectionError);
				}
			}
		}.execute();

		// Get the agents from file
		BufferedReader inputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("agents.txt"));

			String l;
			while ((l = inputStream.readLine()) != null) {
				agents.add(l);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	public void broadcast(String s){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj) + " - INFO : " + s);
	}
	
	
	public void broadcast(Exception e){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj) + " - ERROR : " + e.getMessage());
	}

	// GETTERS - SETTERS

	public Boolean getIsConnected() {
		return isConnected;
	}

	public void setIsConnected(Boolean isConnected) {
		this.isConnected = isConnected;
	}

	public ArrayList<String> getAgents() {
		return agents;
	}

	public ArrayList<Report> getReports() {
		return reports;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}
}
