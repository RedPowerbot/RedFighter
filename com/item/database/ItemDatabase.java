package com.item.database;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.io.FileSystem;
import com.item.RItem;
import com.item.ItemList;
import com.util.DialogUtil;

public class ItemDatabase {
	
	public static final String ITEM_DATABASE_URL = "http://cloud.github.com/downloads/RedDevil505/Fighter/Item%20Cache.txt";

	private static boolean cached = false;
	private static final boolean LOAD_CACHE = true;

	protected static int length;
	protected static int[] ids;
	protected static String[] names;
	protected static boolean[] stackable;
	protected static boolean[] noted;

	public static ItemList get(String name, boolean allowStackable,
			boolean allowNoted, int maxSize) {
		if (length < 1 || name == null || name.length() < 1) {
			return new ItemList();
		}
		name = name.toLowerCase();
		ItemList itemList = new ItemList();
		if (cached) {
			for (int i = 0; i < length; i++) {
				if (names[i] != null && names[i].contains(name)) {
					RItem item = getAt(i);
					if ((!item.isStackable() || allowStackable)
							&& (!item.isNoted() || allowNoted)) {
						itemList.add(item);
						if (itemList.size() >= maxSize) {
							break;
						}
					}
				}
			}
			return itemList;
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(
						FileSystem.getItemDatabaseFile())));
				String[] array;
				String temp;
				while ((temp = br.readLine()) != null) {
					array = temp.split(";");
					if (array.length == 4) {
						if (array[0].contains(name)) {
							RItem i = new RItem(Integer.parseInt(array[0]),
									array[1], array[2].equals("t"),
									array[3].equals("t"));
							if (!i.isStackable() || allowStackable
									&& !i.isNoted() || allowNoted) {
								itemList.add(i);
							}
						}
					}
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

		}
		return new ItemList();
	}

	public static RItem get(int id) {
		if (length < 1 || id < 0 || id >= ids[length - 1]) {
			return new RItem();
		}
		if (cached) {
			for (int i = id - 10; i < length; i++) {
				if (ids[i] == id) {
					return getAt(i);
				}
			}
		} else {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(
						FileSystem.getItemDatabaseFile())));
				String[] array;
				String temp;
				while ((temp = br.readLine()) != null) {
					array = temp.split(";");
					if (array.length == 4) {
						int tempID = Integer.parseInt(array[0]);
						if (id == tempID) {
							return new RItem(tempID, array[1],
									array[2].equals("t"), array[3].equals("t"));
						}
					}
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		return new RItem();
	}

	private static RItem getAt(int index) {
		if (index < 0 || index >= length ) {
			return new RItem();
		}
		return new RItem(ids[index], names[index], stackable[index],
				noted[index]);
	}

	public void clearCache() {
		ids = null;
		names = null;
		stackable = null;
		noted = null;
	}
	
	public static boolean loadInBackground() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				loadData();
			}
		}).start();
		return true;
	}

	public static boolean loadData() {
		return loadData(false);
	}

	public static boolean loadData(boolean forceRead) {
		try {
			if (LOAD_CACHE && !cached || forceRead) {
				File f = new File(FileSystem.getItemDatabaseFile());
				if (f.length() == 0) {
					length = -1;
					ids = new int[0];
					names = new String[0];
					stackable = new boolean[0];
					noted = new boolean[0];
					return false;
				}
				BufferedReader br = new BufferedReader(new FileReader(f));
				length = Integer.parseInt(br.readLine());
				ids = new int[length];
				names = new String[length];
				stackable = new boolean[length];
				noted = new boolean[length];
				int index = 0;
				String[] array;
				String temp;
				while ((temp = br.readLine()) != null) {
					array = temp.split(";");
					if (array.length == 2) {
						ids[index] = Integer.parseInt(array[0]);
						names[index] = array[1].toLowerCase();
					} else if (array.length == 4) {
						ids[index] = Integer.parseInt(array[0]);
						names[index] = array[1].toLowerCase();
						stackable[index] = array[2].equals("t");
						noted[index] = array[3].equals("t");
						;
					}
					index++;
				}
				cached = true;
				System.out.println("Item Cache Loaded. Number of Items Loaded:"
						+ (index + 1));
			}
			return true;
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void manageUpdateAvailableButton(final Component parent, final JPanel panel, final String north) {
		new Thread(new Runnable() {

			public void run() {
				try {
					URL url = new URL(ITEM_DATABASE_URL);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
					int len = Integer.parseInt(br.readLine());
					if (len > length) {
						final JButton but = new JButton("CLICK TO UPDATE");
						but.addActionListener(new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								updateItemDatabase(parent);
								panel.remove(but);
							}
							
						});
						panel.add(but, north);
					}
					br.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	
	public static void updateItemDatabase(final Component c) {
		new Thread(new Runnable() {

			public void run() {
				try {
					URL url = new URL(ITEM_DATABASE_URL);
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
					int len = Integer.parseInt(br.readLine());
					if (len > length) {
						if (DialogUtil.askPermission("A new Item Database is available.\nWould you like to update yours?", c)) {
							BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FileSystem.getItemDatabaseFile())));
							bw.write("" + len);
							bw.newLine();
							String temp;
							while ((temp = br.readLine()) != null) {
								bw.write(temp);
								bw.newLine();
							}
							bw.close();
							br.close();
							loadData(true);
							DialogUtil.yell("Your Item Database has been updated and refreshed.", c);
						}
					}
					br.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}
	

}
