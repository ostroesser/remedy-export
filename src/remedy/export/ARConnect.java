package remedy.export;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingWorker;

public class ARConnect extends SwingWorker<Boolean, Boolean> {

	protected RExport owner;
	protected Exception connectionError;
	protected String login = null;
	protected String pwd = null;
	protected Connection conn;

	public ARConnect(RExport rExport, String login, String pwd) {
		super();
		this.owner = rExport;
		this.login = login;
		this.pwd = pwd;
	}

	@Override
	protected Boolean doInBackground() throws Exception {

		try {
			// Load the database driver
			Class.forName("com.bmc.arsys.jdbc.core.Driver");

			// Get connection to the AR server
			if (login != null && pwd != null) {
				conn = DriverManager
						.getConnection("jdbc:arserver://spsms1.muc:2131;user="
								+ login + ";password=" + pwd);
			} else {
				conn = DriverManager
						.getConnection("jdbc:arserver://spsms1.muc:2131;user=qxi9560;password=34erdfcv");
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			connectionError = e;
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
			connectionError = e;
		}

		return true;
	}

	@Override
	protected void done() {
	}
}
