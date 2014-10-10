package remedy.export;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * {@code MainPanel} is just the main content pane of the Remedy export app.
 * 
 * @author Olivier Stroesser
 * 
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = -5443681405012291300L;

	protected JLabel connStatus;
	private JEditorPane console;
	protected ArrayList<GuiReport> reports;
	protected Gui owner;
	private JTabbedPane tabbedPane;

	/**
	 * Constructor
	 * 
	 * @param owner
	 *            Top-level graphic interface
	 */
	public MainPanel(Gui owner) {
		// create gui reportlist
		this.owner = owner;
		reports = new ArrayList<GuiReport>();

		this.setLayout(new BorderLayout(0, 2));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.9);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);

		JPanel StartPage = new JPanel();

		tabbedPane.addTab("Start Page", null, StartPage, null);

		JLabel lblCheckChildTickets = new JLabel("Check Child tickets");
		StartPage.add(lblCheckChildTickets, "cell 1 1");

		JButton btnCheckChild = new JButton("Go!");
		btnCheckChild.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createCheckChild();
			}
		});
		StartPage.add(btnCheckChild, "cell 2 1");

		JPanel bottomStatus = new JPanel();
		splitPane.setRightComponent(bottomStatus);
		bottomStatus.setLayout(new BorderLayout(0, 0));

		console = new JEditorPane("text", "Remedy reports");
		console.setEditable(false);
		// bottomStatus.add(console, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(console,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bottomStatus.add(scrollPane, BorderLayout.CENTER);

		JPanel bottomLine = new JPanel();
		bottomStatus.add(bottomLine, BorderLayout.SOUTH);
		bottomLine.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("New label");
		bottomLine.add(lblNewLabel_3, BorderLayout.EAST);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);

		connStatus = new JLabel("Connecting to database...");
		bottomLine.add(connStatus, BorderLayout.WEST);
		connStatus.setHorizontalAlignment(SwingConstants.LEFT);

		// TODO Auto-generated constructor stub
	}

	/**
	 * Create a report : Checking if some child tickets are waiting for
	 * information or action.
	 */
	protected void createCheckChild() {
		reports.add(new GuiCheckChildTickets(this));
		tabbedPane.addTab("Check child ticket updates", null,
				reports.get(reports.size() - 1), null);

	}

	public ArrayList<GuiReport> getReports() {
		return reports;
	}

	public void setReports(ArrayList<GuiReport> reports) {
		this.reports = reports;
	}

	public Gui getOwner() {
		return owner;
	}

	public void setOwner(Gui owner) {
		this.owner = owner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setConsole(JEditorPane console) {
		this.console = console;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JLabel getConnStatus() {
		return connStatus;
	}

	public void setConnStatus(JLabel connStatus) {
		this.connStatus = connStatus;
	}

	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source arg0 console
	 */
	public static JScrollPane createJScrollPane(Component arg0) {
		JScrollPane scrollPane = new JScrollPane(arg0,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scrollPane;
	}

	public JEditorPane getConsole() {
		return console;
	}

	protected JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
