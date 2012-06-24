package com.item.suggest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.item.RItem;
import com.item.ItemList;
import com.item.database.ItemDatabase;

public class ItemSelectionDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1636998300635469560L;

	private ItemList selectedItemList = new ItemList();

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JLabel itemName;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JList<RItem> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemDatabase.loadData();
			ItemSelectionDialog dialog = new ItemSelectionDialog(
					"Attack potion", null);
			dialog.setModalityType(ModalityType.DOCUMENT_MODAL);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			System.out.println("SelectedItems:"
					+ dialog.getSelectedItems().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ItemList showItemSelectionDialog(Frame owner, String itemName) {
		ItemList itemList = ItemDatabase.get(itemName, true, true, 100);
		if (itemList.size() <= 1) {
			return itemList;
		}
		ItemSelectionDialog dialog = new ItemSelectionDialog(owner, itemName,
				itemList);
		dialog.setVisible(true);
		return dialog.getSelectedItems();
	}

	public static ItemList showItemSelectionDialog(Frame owner,
			String itemName, boolean acceptStackable, boolean acceptNoted) {
		ItemList itemList = ItemDatabase.get(itemName, true, true, 100);
		if (itemList.size() <= 1) {
			return itemList;
		}
		ItemSelectionDialog dialog = new ItemSelectionDialog(itemName,
				acceptStackable, acceptNoted, owner);
		dialog.setVisible(true);
		return dialog.getSelectedItems();
	}

	private ItemSelectionDialog(Frame owner, String itemName, ItemList itemList) {
		super(owner);
		setLocationRelativeTo(owner);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		initialize();
		list.setModel(itemList);
		list.setSelectedIndex(0);
		this.textField.setText(itemName);
	}

	private ItemSelectionDialog(String itemName, Frame owner) {
		this(itemName, true, true, owner);
	}

	private ItemSelectionDialog(String itemName, boolean acceptStackable,
			boolean acceptNoted, Frame owner) {
		this(owner, itemName, ItemDatabase.get(itemName, acceptStackable,
				acceptNoted, 100));
	}

	/**
	 * Create the dialog.
	 */
	public ItemSelectionDialog() {
		initialize();
	}

	public ItemList getSelectedItems() {
		return selectedItemList;
	}

	private void initialize() {
		setTitle("Item Selection Dialog");
		setBounds(100, 100, 278, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 11, 239, 44);
		contentPanel.add(panel);
		panel.setLayout(null);

		itemName = new JLabel("Item Name :");
		itemName.setBounds(10, 11, 77, 24);
		panel.add(itemName);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(97, 13, 132, 20);
		panel.add(textField);
		textField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 242, 219);
		contentPanel.add(scrollPane);

		list = new JList<RItem>();
		scrollPane.setViewportView(list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add Selected Items");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						selectedItemList.addAll(list.getSelectedValuesList());
						setVisible(false);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
