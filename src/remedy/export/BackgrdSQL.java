package remedy.export;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingWorker;

public class BackgrdSQL extends SwingWorker<Void, Boolean> {

	protected String query;

	protected Exception sqlError;
	protected RExport owner;
	protected ResultSet rs;


	public BackgrdSQL(RExport App, String Query) {
		super();
		query = Query;
		owner = App;
	}
	

	@Override
	protected Void doInBackground() throws Exception {
		try {
			Statement st;
			st = owner.getConn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			sqlError = e;
		}
		return null;
	}

}
