package com.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.gui.settings.Profile;
import com.gui.settings.SettingsCache;
import com.util.DialogUtil;

public class SettingManager extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2026143696924488289L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private SettingsCache cache;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SettingManager dialog = new SettingManager();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SettingManager() {
		this(new SettingsCache());
	}

	public SettingManager(SettingsCache cache) {
		super((Frame) cache.getGui(), true);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setLocationRelativeTo(cache.getGui());
		this.cache = cache;
		initialize();
		setLocationRelativeTo(cache.getGui());
	}

	/**
	 * Create the dialog.
	 */
	public void initialize() {
		setTitle("Configuration Manager");
		setBounds(100, 100, 531, 431);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setModel(cache);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// Enumeration<TableColumn> e =
				// table.getColumnModel().getColumns();
				// while (e.hasMoreElements()) {
				// e.nextElement().setCellRenderer(new TableCellRenderer() {
				//
				// @Override
				// public Component getTableCellRendererComponent(JTable arg0,
				// Object arg1,
				// boolean arg2, boolean arg3, int arg4, int arg5) {
				// JLabel l = new JLabel(arg1.toString());
				// l.setToolTipText(l.getText());
				// return l;
				// }
				//
				// });
				// }
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnUsed = new JButton("Used");
				btnUsed.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cache.sortByDateUsed();
					}
				});
				{
					JLabel lblNewLabel = new JLabel("Sort By");
					lblNewLabel.setForeground(Color.WHITE);
					buttonPane.add(lblNewLabel);
				}
				btnUsed.setOpaque(false);
				buttonPane.add(btnUsed);
			}
			{
				JButton btnName = new JButton("Name");
				btnName.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cache.sortByName();
					}
				});
				btnName.setOpaque(false);
				buttonPane.add(btnName);
			}
			{
				JButton btnMade = new JButton("Made");
				btnMade.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						cache.sortByDateMade();
					}
				});
				btnMade.setOpaque(false);
				buttonPane.add(btnMade);
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				horizontalBox.setOpaque(true);
				horizontalBox.setBackground(Color.RED);
				horizontalBox.setPreferredSize(new Dimension(75, 10));
				horizontalBox.setSize(new Dimension(15, 0));
				buttonPane.add(horizontalBox);
			}
			{
				JButton okButton = new JButton("Load");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (table.getSelectedRow() == -1) {
							return;
						}
						if (DialogUtil
								.askPermission(
										"Are you sure you want to load this?\nIt will overwrite all the current settings!",
										SettingManager.this)) {
							cache.init(table.getSelectedRow());
							DialogUtil.yell("Profile loaded.",
									SettingManager.this);
						}
					}
				});
				okButton.setOpaque(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (table.getSelectedRow() == -1) {
							return;
						}
						if (DialogUtil
								.askPermission(
										"Are you sure you want to delete this?\nYou can't restore it!",
										SettingManager.this)) {
							cache.delete(table.getSelectedRow());
							DialogUtil.yell("Profile removed.",
									SettingManager.this);
						}
					}
				});
				btnDelete.setOpaque(false);
				buttonPane.add(btnDelete);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setOpaque(false);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private Profile getSelectedProfile() {
		int i = table.getSelectedRow();
		if (i != -1) {
			cache.get(i);
		}
		return new Profile("null");
	}

}
