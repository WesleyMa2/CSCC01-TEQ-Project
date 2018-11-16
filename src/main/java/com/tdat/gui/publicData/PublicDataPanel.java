package com.tdat.gui.publicData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.gui.GenericPanel;

/*
 * A view for the reports panel in MainWindow.
 */
@SuppressWarnings("serial")
public class PublicDataPanel extends GenericPanel {
	
	protected static DefaultTableModel tableModel = new DefaultTableModel() {
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public PublicDataPanel() {
		// Panel Title
		panelTitle = "Public Data";
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		// Header section of the panel
		JLabel headerHTML = new JLabel("<html><h2 style='margin:0'>Public Data</h2>"
				+ "<small>Attach public data where applicable to your charts.</small></html>");
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		layoutConstraints.weightx = 1;
		layoutConstraints.weighty = 1;
		layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
		this.add(headerHTML, layoutConstraints);

		String th[] = {"Id", "Title"}; 
		JTable currentPublicData = new JTable();
		tableModel.setDataVector(null, th);
		currentPublicData.setModel(tableModel);
		JScrollPane scrollPane = new JScrollPane(currentPublicData);
		scrollPane.setPreferredSize(new Dimension(600, 100));
		layoutConstraints.gridy = 1;
		layoutConstraints.ipadx = 0;
		layoutConstraints.ipady = 0;
		this.add(scrollPane, layoutConstraints);

		Collection<String> publicDataFound = PublicDataCache.CachedPublicData.keySet();
		JComboBox publicDataDropdown = new JComboBox(publicDataFound.toArray());
		layoutConstraints.gridy = 2;
		layoutConstraints.ipadx = 0;
		layoutConstraints.ipady = 0;
		this.add(publicDataDropdown, layoutConstraints);

		publicDataDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) currentPublicData.getModel();

				List<String> keys = new ArrayList(PublicDataCache.CachedPublicData.keySet());
				String key = keys.get(publicDataDropdown.getSelectedIndex());

				String tr[] = new String[] {
					Integer.toString(MasterData.publicDataId.incrementAndGet()),
					key
				};

				model.addRow(tr);
				App.reportsList.add(PublicDataCache.CachedPublicData.get(key));
			}
		});
	}
	
}
