package remedy.export;

/**
 * Generic top-level class for reports
 * 
 * @author livo
 *
 */
public class Report {

	protected RExport owner;
	protected boolean isQuerying;
	protected Exception reportException;

	public RExport getOwner() {
		return owner;
	}

	public boolean isQuerying() {
		return isQuerying;
	}

	public Report(RExport owner) {
		this.owner = owner;
		isQuerying = false;
		reportException = null;
		// TODO Auto-generated constructor stub
	}

}
