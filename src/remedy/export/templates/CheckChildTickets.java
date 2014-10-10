package remedy.export.templates;

import java.sql.SQLException;

import remedy.export.BackgrdSQL;
import remedy.export.RExport;
import remedy.export.Report;

public class CheckChildTickets extends Report {

	public CheckChildTickets(RExport owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	public String[] CheckChildUpdates(int agentIndex) {

//		String query = "select Description from SMS_SRT_Service_Request where Ticket_id = 'B00000022640820'";
		String query = "select * from  SMS_SRT_Service_Request";
		isQuerying = true;
		reportException = null;
		owner.broadcast("Querying the database. This may take some time...");
		new BackgrdSQL(owner, query) {
			public void done() {
				owner.broadcast("...done");
				if (sqlError == null) {
					isQuerying = false;

					try {
						
						while (rs.next()) {
							owner.broadcast(rs.getString("Description"));
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						reportException = e;
						owner.broadcast(reportException);
					}
					// TODO This should be in the gui
					// owner.getGui().getContPane().consoleOut(sqlError);
				} else {
					reportException = sqlError;
					owner.broadcast(reportException);
				}
			}
		}.execute();


		String[] ret = null;
		return ret;
	}

}
