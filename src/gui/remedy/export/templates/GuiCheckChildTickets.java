package gui.remedy.export.templates;

import gui.remedy.export.GuiReport;
import gui.remedy.export.MainPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import remedy.export.Report;
import remedy.export.templates.CheckChildTickets;

public class GuiCheckChildTickets extends GuiReport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1984860306505525719L;
	private JComboBox<Object> comboBoxAgentSelect;

	public GuiCheckChildTickets(MainPanel gui) {
		super(gui);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
	

		comboBoxAgentSelect = new JComboBox<Object>(owner.getOwner().getrExport().getAgents().toArray());
		panel.add(comboBoxAgentSelect);

		JButton btnCheckAgentChildTickets = new JButton("Go!");
		btnCheckAgentChildTickets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkAgentChild(getComboBoxAgentSelect().getSelectedIndex());
			}
		});
		panel.add(btnCheckAgentChildTickets);
		// TODO Auto-generated constructor stub
	}

	public void checkAgentChild(int agentIndex) {
		
		ArrayList<Report> reports = owner.getOwner().getrExport().getReports();
		reports.add(new CheckChildTickets(owner.getOwner().getrExport()));
		CheckChildTickets checkChild = (CheckChildTickets) reports.get(reports.size()-1);
		String[] ticket_updates = checkChild.CheckChildUpdates(agentIndex);
		return;
	}

	public JComboBox<Object> getComboBoxAgentSelect() {
		return comboBoxAgentSelect;
	}
}
