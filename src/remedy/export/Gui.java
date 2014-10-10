package remedy.export;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8076818057496013754L;

	protected RExport rExport;
	protected MainPanel contPane;
	protected JMenuBar menuBar;

	public Gui(String name) {
		super(name);

		// Create and set up the content pane.
		contPane = new MainPanel(this);
		contPane.setOpaque(true); // content panes must be opaque
		this.setContentPane(contPane);

		// Create menubar
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem eMenuItem = new JMenuItem("Exit");
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		file.add(eMenuItem);
		menuBar = new JMenuBar();
		menuBar.add(file);
		setJMenuBar(menuBar);

		try {
			rExport = new RExport(this) {
				public void broadcast(String s) {
					DateFormat df = new SimpleDateFormat("HH:mm:ss");
					Date dateobj = new Date();
					System.out.println(df.format(dateobj) + " - INFO : " + s);
					JEditorPane console = contPane.getConsole();
					console.setText(console.getText() + "\n"
							+ df.format(dateobj) + " - INFO : " + s);

				}

				public void broadcast(Exception e) {
					DateFormat df = new SimpleDateFormat("HH:mm:ss");
					Date dateobj = new Date();

					System.out.println(df.format(dateobj) + " - ERROR : "
							+ e.getMessage());
					JEditorPane console = contPane.getConsole();
					console.setText(console.getText() + "\n"
							+ df.format(dateobj) + " - ERROR : "
							+ e.getMessage());
				}
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rExport.broadcast(e);
		}

		// Display the window.
		pack();
		setVisible(true);

	}

	public RExport getrExport() {
		return rExport;
	}

	public void setrExport(RExport rExport) {
		this.rExport = rExport;
	}

	public MainPanel getContPane() {
		return contPane;
	}

	public void setContPane(MainPanel contentPane) {
		this.contPane = contentPane;
	}

	public JMenuBar getMenuBarr() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

}
