package com.item.TOOLS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.item.RItem;
import com.item.ItemList;
import com.item.database.ItemDatabase;
import com.util.SortUtil;

public class ItemIDFinder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358528172288248808L;
	private boolean allowStackable = true;
	private boolean allowNoted = false;
	private int maxSize = 100;
	private ItemList selectedItemList = new ItemList();
	private SortUtil<RItem> sortUtil = new SortUtil<>();

	private JPanel contentPane;
	private ItemPrintFormat printer;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList<RItem> list;
	private JTextField textField;
	private JButton btnPrintOut;
	private JList<RItem> list_1;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public static void main(String[] args) {
		ItemDatabase.loadData();
		ItemPrintFormat f = new ItemPrintFormat() {
			
			@Override
			public String print(ItemList items) {
				StringBuilder b = new StringBuilder();
				for (RItem i : items) {
					b.append(i.getId());
					b.append(",");
				}
				return b.toString();
			}
		};
//		ItemPrintFormat f = new ItemPrintFormat() {
//			
//			@Override
//			public String print(ItemList items) {
//				StringBuilder b = new StringBuilder();
//				for (Item i : items) {
//					StringBuilder tb = new StringBuilder();
//					tb.append(i.getName().toUpperCase().replace(" ", "_"));
//					tb.append("(");
//					tb.append("\"" + i.getName() + "\",");
//					tb.append(i.getId());
//					tb.append(",");
//					b.append(tb);
//					b.append("\n");
//				}
//				return b.toString();
//			}
//		};
		new ItemIDFinder(f).setVisible(true);
	}
	
	/**
	 * SuggestionEngine
	 */
//	public static void main(String[] args) {
//		ItemDatabase.loadData();
//		ItemPrintFormat format = new ItemPrintFormat() {
//
//			@Override
//			public String print(ItemList items) {
//				StringBuilder b = new StringBuilder();
//				for (Item i : items) {
//					b.append("groupNode.add(new ItemNode(ItemDatabase.get(" + i.getId() + ");");
//					b.append("\n");
//				}
//				return b.toString();
//			}
//
//			
//			
//
//		};
//		new ItemIDFinder(format).setVisible(true);
//	}

	/**
	 * Launch the application. Potions 
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ItemPrintFormat format = new ItemPrintFormat() {
//
//						@Override
//						public String print(ItemList items) {
//							HashMap<String, ArrayList<Item>> map = new HashMap<>();
//							for (Item i : items) {
//								String name = i.getName();
//								name = name.substring(0, name.lastIndexOf("("));
//								System.out.println(name);
//								if (!map.containsKey(name)) {
//									map.put(name, new ArrayList<Item>());
//								}
//								map.get(name).add(i);
//							}
//							StringBuilder b = new StringBuilder();
//							for (ArrayList<Item> list : map.values()) {
//								StringBuilder tb = new StringBuilder();
//								Item ti = list.get(0);
//								tb.append(ti.getName().substring(0, ti.getName().indexOf("(") -
//										2).replace(" ", "_").toUpperCase());
//								tb.append("(new int[] {");
//								TODO Flip array
//								Collections.reverse(list);
//								for (Item i : list) {
//									tb.append(i.getId());
//									tb.append(",");
//								}
//								tb = tb.delete(tb.length() - 1, tb.length());
//								tb.append("}),\n");
//								b.append(tb.toString());
//							}
//							return b.toString();
//						}
//
//					};
//					ItemDatabase.loadInBackground();
//					ItemIDFinder frame = new ItemIDFinder(format);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ItemIDFinder(ItemPrintFormat printer) {
		this();
		this.printer = printer;
	}

	/**
	 * Create the frame.
	 */
	public ItemIDFinder() {
		initialize();
	}

	private void initialize() {
		setTitle("Item ID Printer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 290, 283);
		contentPane.add(scrollPane);

		list = new JList<RItem>();
		list.setDropMode(DropMode.ON);
		scrollPane.setViewportView(list);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(310, 11, 296, 283);
		contentPane.add(scrollPane_1);

		list_1 = new JList<RItem>();
		list_1.setDropMode(DropMode.ON);
		list_1.setModel(selectedItemList);
		scrollPane_1.setViewportView(list_1);

		textField = new JTextField();
		textField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				if (textField.getText().length() > 2) {
					ItemList newList = ItemDatabase.get(textField.getText(),
							allowStackable, allowNoted, maxSize);
					System.out.println("List Size:=" + newList.size());
					sortUtil.bubbleSort(newList, true);
					list.setModel(newList);
				}
			}
		});
		textField.setBounds(10, 305, 191, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		btnPrintOut = new JButton("Print Out");
		btnPrintOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (printer == null) {
					return;
				}
				System.out.println("Printing");
				JFrame frame = new JFrame();
				frame.setTitle("Print Out");
				frame.setLocationRelativeTo(btnPrintOut);
				frame.setSize(250, 250);
				JScrollPane scrollPane = new JScrollPane();
				JTextArea textArea = new JTextArea(printer
						.print(selectedItemList));
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				scrollPane.setViewportView(textArea);
				frame.getContentPane().add(scrollPane);
				frame.setVisible(true);
			}
		});
		btnPrintOut.setBounds(415, 305, 191, 29);
		contentPane.add(btnPrintOut);

		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItemList.addAll(list.getSelectedValuesList());
				sortUtil.bubbleSort(selectedItemList, true);
			}
		});
		btnNewButton.setBounds(211, 305, 89, 29);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedItemList.removeAll(list_1.getSelectedValuesList());
				sortUtil.bubbleSort(selectedItemList, true);
			}
		});
		btnNewButton_1.setBounds(310, 305, 95, 29);
		contentPane.add(btnNewButton_1);
	}
}
