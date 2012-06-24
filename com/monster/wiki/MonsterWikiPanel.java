package com.monster.wiki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.data.Configuration;
import com.gui.swing.MultilineTableCellHandler;
import com.gui.swing.popup.ItemSelectionPopup;
import com.item.database.ItemDatabase;

public class MonsterWikiPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5615762895890713658L;

	public static void main(String[] args) {
		ItemDatabase.loadData();
		JFrame f = new JFrame();
		f.setSize(450, 700);
		f.setLayout(null);
		MonsterWikiParser p = new MonsterWikiParser("Moss giant", System.out);
		MonsterWikiPanel panel = new MonsterWikiPanel("Moss giant", p,
				new Configuration(null));
		panel.setSize(400, 650);
		f.add(panel);
		f.setVisible(true);
	}

	private MonsterWikiParser source;
	private JFrame parentFrame;
	private Configuration con = new Configuration(null);

	private ItemSelectionPopup popup;

	private JLabel title;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane parentScrollPane;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel npcDetails;
	private JLabel droppedItems;
	private JLabel rareDropList;
	private JTable detailsTable;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public MonsterWikiPanel() {
		this("Title", null, new Configuration());
	}

	public MonsterWikiPanel(String name, MonsterWikiParser source,
			Configuration con) {
		super();
		this.source = source;
		this.con = con;
		initialize(name);
		if (source != null) {
			initiateModels();
			initiateCellRenderers();
		}

	}

	private void initialize(String name) {
		setLayout(new BorderLayout(0, 0));

		title = new JLabel(name);
		title.setBackground(Color.DARK_GRAY);
		title.setOpaque(true);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Cambria", Font.PLAIN, 20));
		add(title, BorderLayout.NORTH);

		parentScrollPane = new JScrollPane();
		add(parentScrollPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 605));
		parentScrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 360, 183);
		panel_1.add(scrollPane_1);

		npcDetails = new JLabel("NPC Details");
		npcDetails.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		npcDetails.setOpaque(true);
		npcDetails.setForeground(Color.BLACK);
		npcDetails.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(npcDetails);

		detailsTable = new JTable();
		scrollPane_1.setViewportView(detailsTable);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 204, 360, 183);
		panel_1.add(scrollPane_2);

		droppedItems = new JLabel("Dropped Items");
		droppedItems.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		droppedItems.setOpaque(true);
		droppedItems.setForeground(Color.BLACK);
		droppedItems.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(droppedItems);

		table = new JTable();
		table.addMouseListener(new TableMouseListener(table));
		scrollPane_2.setViewportView(table);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 398, 360, 183);
		panel_1.add(scrollPane_3);

		rareDropList = new JLabel("Rare Drop List");
		rareDropList.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		rareDropList.setOpaque(true);
		rareDropList.setForeground(Color.BLACK);
		rareDropList.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setColumnHeaderView(rareDropList);

		table_1 = new JTable();
		table_1.addMouseListener(new TableMouseListener(table_1));
		scrollPane_3.setViewportView(table_1);
	}

	private void initiateModels() {
		DefaultModel model = new DefaultModel();
		model.setColumnIdentifiers(new Object[] { "Property", "Value" });
		source.list(model);
		detailsTable.setModel(model);
		DefaultModel model1 = new DefaultModel();
		source.listItemProperties(model1);
		table.setModel(model1);
	}

	private void initiateCellRenderers() {
		new MultilineTableCellHandler(detailsTable.getColumnModel());
		new MultilineTableCellHandler(table.getColumnModel());
	}
	
	private class DefaultModel extends DefaultTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 5586125089073500383L;

 
		
	}

	private class TableMouseListener extends MouseAdapter {

		private JTable table;

		public TableMouseListener(JTable table) {
			super();
			this.table = table;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (table.getRowCount() < 1 || table.getSelectedRows().length < 1) {
				return;
			}
			if (e.isPopupTrigger()) {
				createPopupMenu();
				popup.show(e.getComponent(), e.getX() - 10, e.getY() - 10);
			}
		}

		private void createPopupMenu() {
			if (popup != null && popup.isVisible()) {
				popup.setVisible(false);
			}
			int[] idxs = table.getSelectedRows();
			String[] selectedNames = new String[idxs.length];
			for (int i = 0; i < idxs.length; i++) {
				selectedNames[i] = table.getModel().getValueAt(idxs[i], 0)
						.toString();
			}
			popup = new ItemSelectionPopup(selectedNames, con.lootList,
					parentFrame);
		}

	}

}
