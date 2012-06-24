package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.eclipse.FocusTraversalOnArray;

import com.data.Configuration;
import com.data.enums.FightMode;
import com.data.enums.PrayerMethod;
import com.gui.action.GUIActionHandler;
import com.gui.action.GuiIntiater;
import com.gui.settings.ComponentReader;
import com.gui.swing.CacheElementCreationDialog;
import com.gui.swing.Divider;
import com.gui.swing.JRangeSlider;
import com.gui.swing.LFMenuBar;
import com.gui.swing.popup.LootTablePopup;
import com.gui.swing.popup.MonsterSelectionPopup;
import com.item.RItem;
import com.item.ItemGroup;
import com.item.ItemIO;
import com.item.ItemList;
import com.item.ItemListMode;
import com.item.ItemPriority;
import com.item.ItemSaveCache;
import com.item.database.ItemDatabase;
import com.item.special.WeaponSpecial;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.loop.RedFighter;
import com.monster.Monster;
import com.monster.MonsterList;
import com.monster.SpecialType;
import com.util.DialogUtil;
import com.util.MultiListener;
import com.util.PropertiesEx;
import com.item.suggest.SugTree;
import com.data.enums.SlayerTask;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;

public class RFGui extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				try {
					RedFighter.initialize(false);
					RFGui frame = new RFGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private boolean completed = false;
	
	private Configuration con;
	
	private GUIActionHandler actionListener;
	private ItemSaveCache itemSaveCache;
	private LootTablePopup lootTablePopup;

	private MonsterSelectionPopup monsterPopup;
	
	private static final long serialVersionUID = -3658211789443348974L;
	private JPanel contentPane;
	private LFMenuBar menuBar;
	private JLabel lblLegendaryFighterV;
	private JButton btnStartScript;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JScrollPane scrollPane;
	private JComboBox<?> specialCombat;
	private JScrollPane scrollPane_1;
	private JList<Monster> npcAvailableList;
	private JList<Monster> npcSelectedList;
	private JLabel lblAvailableMonsters;
	private JLabel lblSelectedMonsters;
	private JLabel lblSpecialCombatMode;
	private JTabbedPane tabbedPane_1;
	private JPanel panel_6;
	private JComboBox<FightMode> combatTypeComboBox;
	private JLabel lblCombatType;
	private Divider divider;
	private JCheckBox enableMulticombatSupport;
	private JCheckBox attackAlreadyBusyMonsters;
	private JSpinner maxCompetingCombatantsBox;
	private JLabel lblMaxCompetingCombatants;
	private Divider divider_1;
	private JTabbedPane tabbedPane_2;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JCheckBox enablePickupAmmo;
	private JCheckBox enableAutoEquipAmmo;
	private JLabel lblNoMeleeSpecific;
	private JLabel lblNoMagicSpecific;
	private JPanel panel_10;
	private JCheckBox enableWeaponSpecial;
	private Divider divider_2;
	private JLabel lblSelectedWeaponSpecial;
	private JComboBox<WeaponSpecial> weaponSpecialCombobox;
	private JTabbedPane tabbedPane_3;
	private JPanel panel_11;
	private JCheckBox eatFoodCheckBox;
	private Divider divider_3;
	private JLabel eatingLabel1;
	private JRangeSlider eatingRangeSlider;
	private JLabel eatingLabel2;
	private Divider divider_4;
	private JCheckBox smartEatingCheckBox;
	private JLabel minimumHPLabel;
	private JSlider minimumHPSlider;
	private JPanel panel_12;
	private JCheckBox enableB2P;
	private Divider divider_5;
	private JRangeSlider boneLimitRangeSlider;
	private JLabel lblBoneLimits;
	private JScrollPane scrollPane_2;
	private JTable itemTable;
	private JPanel panel_13;
	private JButton btnAddSelected;
	private JTabbedPane tabbedPane_4;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JComboBox<ItemPriority> comboBox_3;
	private JLabel lblItemId;
	private JTextField itemName;
	private JSpinner itemID;
	private JLabel lblItemName;
	private JCheckBox itemStackable;
	private JCheckBox itemNoted;
	private JScrollPane scrollPane_3;
	private JList<RItem> autoLootList;
	private JTextField textField_1;
	private JLabel lblEachContainsMany;
	private JScrollPane scrollPane_4;
	private Divider divider_6;
	private JList<File> itemIOList;
	private JLabel lblSavedItemFiles;
	private JPanel panel_18;
	private JCheckBox enableAttackPotions;
	private JCheckBox enableStrengthPotions;
	private JCheckBox enableDefensePotions;
	private JCheckBox enableRangePotions;
	private JCheckBox enableMagicPotions;
	private Divider divider_7;
	private JCheckBox enableAntipoison;
	private Divider divider_8;
	private JCheckBox enableAntifireControl;
	private JLabel mininumBoostLimit;
	private JSlider minimumBoostSlider;
	private JTabbedPane tabbedPane_5;
	private JPanel panel_19;
	private JLabel moreToCome;
	private JTabbedPane tabbedPane_6;
	private JPanel panel_20;
	private JLabel newLabel;
	private Divider divider_9;
	private JLabel selectA;
	private JLabel rightClick;
	private JLabel selectWhether;
	private JLabel yourWikiPages;
	private JLabel youWill;
	private JLabel goTo;
	private JLabel newLabel_1;
	private JScrollPane scrollPane_5;
	private SugTree sugTree;
	private JPanel panel_21;
	private final MultiListener fighting_main_multicombatAL = new MultiListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean b1 = enableMulticombatSupport.isSelected();
			attackAlreadyBusyMonsters.setEnabled(b1);
			b1 = b1 && attackAlreadyBusyMonsters.isSelected();
			lblMaxCompetingCombatants.setEnabled(b1);
			maxCompetingCombatantsBox.setEnabled(b1);
		}
	};

	private final MultiListener eating_food_AL = new MultiListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean b1 = eatFoodCheckBox.isSelected();
			eatingLabel1.setEnabled(b1);
			eatingLabel2.setEnabled(b1);
			eatingRangeSlider.setEnabled(b1);
			smartEatingCheckBox.setEnabled(b1);
			b1 = b1 && smartEatingCheckBox.isSelected();
			if (minimumHPLabel != null) {
				minimumHPLabel.setEnabled(b1);
			}
			if (minimumHPSlider != null) {
				minimumHPSlider.setEnabled(b1);
			}
		}
	};

	private JCheckBox prayerSupportCheckBox;

	private Divider divider_10;

	private JTabbedPane tabbedPane_7;

	private JPanel panel_22;
	
	private JPanel panel_23;

	private JCheckBox enableSlayerSupport;
	private Divider divider_11;
	private JLabel selectedTask;
	private JComboBox<SlayerTask> slayerTaskComboBox;
	private JLabel newLabel_2;
	private JPanel panel_24;
	private JLabel prayerRegenerationMethod;
	private JComboBox<PrayerMethod> prayerMethodComboBox;
	private JTabbedPane tabbedPane_8;
	private JPanel panel_25;
	private JLabel noPrayerPotion;
	private JPanel panel_26;
	private JLabel newLabel_3;
	private JSpinner prayerAltarIdSpinner;
	private JCheckBox useSecondaryWeapons;
	private Divider divider_12;
	private JTextArea unlessYouSelect;
	private JTextArea pleaseAddClues;
	public RFGui() {
		this(new Configuration(new RedFighter()));

	}
	/**
	 * Create the frame.
	 */
	public RFGui(Configuration c) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.getDefaults().put("Panel.background", Color.black);
			// UIManager.getDefaults().put("Label.foreground", Color.white);
			// UIManager.getDefaults().put("Slider.tickColor", Color.red);
			// UIManager.getDefaults().put("OptionPane.questionDialog.titlePane.foreground",
			// Color.white);
			// ItemDatabase.loadData();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		con = c;
		itemSaveCache = new ItemSaveCache(this, con.lootList);
		actionListener = new GUIActionHandler(con);
		con.lootList.setMode(ItemListMode.SELECTION);
		initialize();
		new GuiIntiater(con).init(this);
		con.monsterList.add(new Monster(0, 0, "Moss Giant"));
	}
	private void checkExpansion(ItemList target, List<RItem> newList) {
		for (RItem i : newList) {
			if (i instanceof ItemGroup) {
				ItemGroup g = (ItemGroup) i;
				checkExpansion(target, g.list);
			} else {
				target.add(i);
			}
		}
	}
	public MonsterList getAvailableMonsterModel() {
		return (MonsterList) npcAvailableList.getModel();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Legendary Fighter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 470);

		menuBar = new LFMenuBar(this);
		menuBar.setPreferredSize(new Dimension(0, 20));
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		lblLegendaryFighterV = DefaultComponentFactory.getInstance()
				.createTitle("Legendary Fighter");
		lblLegendaryFighterV.setFont(new Font("Calibri", Font.BOLD, 30));
		lblLegendaryFighterV.setHorizontalAlignment(SwingConstants.CENTER);
		lblLegendaryFighterV.setLabelFor(this);
		lblLegendaryFighterV.setForeground(Color.RED);
		contentPane.add(lblLegendaryFighterV, BorderLayout.NORTH);

		btnStartScript = new JButton("Start Script");
		btnStartScript.setHorizontalTextPosition(SwingConstants.CENTER);
		btnStartScript.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				completed = true;
				setVisible(false);
			}
		});
		contentPane.add(btnStartScript, BorderLayout.SOUTH);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.BLACK);
		tabbedPane.addTab("Npcs", null, panel, "Select which Npcs to fight.");
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 237, 264);
		panel.add(scrollPane);

		npcAvailableList = new JList<Monster>(new MonsterList());
		npcAvailableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					List<Monster> list = npcAvailableList
							.getSelectedValuesList();
					if (list.size() > 0) {
						con.monsterList.addAll(list);
					}
				}
			}
		});
		scrollPane.setViewportView(npcAvailableList);

		lblAvailableMonsters = new JLabel("Available Monsters");
		lblAvailableMonsters.setForeground(Color.RED);
		lblAvailableMonsters.setBackground(Color.DARK_GRAY);
		lblAvailableMonsters.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblAvailableMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAvailableMonsters);

		specialCombat = new JComboBox<SpecialType>();
		specialCombat.addItemListener(actionListener);
		specialCombat.setName("specialFightMode");
		specialCombat.setModel(new DefaultComboBoxModel(SpecialType.values()));
		specialCombat.setBounds(272, 286, 237, 20);
		panel.add(specialCombat);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(272, 11, 237, 264);
		panel.add(scrollPane_1);

		npcSelectedList = new JList<Monster>(con.monsterList);
		npcSelectedList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (!npcSelectedList.isSelectionEmpty()) {
						monsterPopup = new MonsterSelectionPopup(tabbedPane_6,
								npcSelectedList.getSelectedValuesList(), con);
						monsterPopup.setLocation(e.getLocationOnScreen());
						monsterPopup.setVisible(true);
					}
				} else if (e.getClickCount() > 1) {
					List<Monster> list = npcSelectedList
							.getSelectedValuesList();
					con.monsterList.removeAll(list);
				}
			}
		});
		npcSelectedList.setName("SelectedMonstersList");
		scrollPane_1.setViewportView(npcSelectedList);

		lblSelectedMonsters = new JLabel("Selected Monsters");
		lblSelectedMonsters.setForeground(Color.RED);
		lblSelectedMonsters.setBackground(Color.DARK_GRAY);
		lblSelectedMonsters.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblSelectedMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblSelectedMonsters);

		lblSpecialCombatMode = new JLabel("Special Combat Mode :");
		lblSpecialCombatMode.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpecialCombatMode.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSpecialCombatMode.setForeground(Color.WHITE);
		lblSpecialCombatMode.setBounds(10, 286, 237, 16);
		panel.add(lblSpecialCombatMode);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		tabbedPane.addTab("Fighting", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));

		tabbedPane_1 = new JTabbedPane(SwingConstants.LEFT);
		panel_2.add(tabbedPane_1, BorderLayout.CENTER);

		panel_6 = new JPanel();
		panel_6.setBackground(Color.BLACK);
		tabbedPane_1.addTab("Main", null, panel_6, null);
		panel_6.setLayout(null);

		combatTypeComboBox = new JComboBox<FightMode>();
		combatTypeComboBox.setName("fightMode");
		combatTypeComboBox.addItemListener(actionListener);
		combatTypeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane_2.setSelectedIndex(combatTypeComboBox
						.getSelectedIndex());
			}
		});
		combatTypeComboBox
				.setModel(new DefaultComboBoxModel(FightMode.values()));
		combatTypeComboBox.setBounds(167, 15, 291, 22);
		panel_6.add(combatTypeComboBox);

		lblCombatType = new JLabel("Combat Type");
		lblCombatType.setForeground(Color.WHITE);
		lblCombatType.setBounds(10, 6, 149, 41);
		panel_6.add(lblCombatType);

		divider = new Divider();
		divider.setBounds(10, 58, 448, 20);
		panel_6.add(divider);

		enableMulticombatSupport = new JCheckBox("Enable Multicombat Support");
		enableMulticombatSupport.addActionListener(fighting_main_multicombatAL);
		enableMulticombatSupport.addChangeListener(fighting_main_multicombatAL);
		enableMulticombatSupport.addActionListener(actionListener);
		enableMulticombatSupport.setName("multiCombat_enable");
		enableMulticombatSupport.setForeground(Color.WHITE);
		enableMulticombatSupport.setOpaque(false);
		enableMulticombatSupport.setHorizontalAlignment(SwingConstants.LEFT);
		enableMulticombatSupport.setBounds(6, 85, 452, 20);
		panel_6.add(enableMulticombatSupport);

		attackAlreadyBusyMonsters = new JCheckBox(
				"Attack Already Busy Monsters");
		attackAlreadyBusyMonsters.setEnabled(false);
		attackAlreadyBusyMonsters
				.addActionListener(fighting_main_multicombatAL);
		attackAlreadyBusyMonsters
				.addChangeListener(fighting_main_multicombatAL);
		attackAlreadyBusyMonsters.addActionListener(actionListener);
		attackAlreadyBusyMonsters.setName("attackBusyMobs_enable");
		attackAlreadyBusyMonsters.setForeground(Color.WHITE);
		attackAlreadyBusyMonsters.setOpaque(false);
		attackAlreadyBusyMonsters.setBounds(47, 117, 411, 20);
		panel_6.add(attackAlreadyBusyMonsters);

		maxCompetingCombatantsBox = new JSpinner();
		maxCompetingCombatantsBox.addChangeListener(actionListener);
		maxCompetingCombatantsBox.setEnabled(false);
		maxCompetingCombatantsBox.setName("attackBusyMobs_maxPlayerAmount");
		maxCompetingCombatantsBox.setBounds(278, 146, 180, 20);
		panel_6.add(maxCompetingCombatantsBox);

		lblMaxCompetingCombatants = new JLabel("Max Competing Combatants");
		lblMaxCompetingCombatants.setEnabled(false);
		lblMaxCompetingCombatants.setLabelFor(maxCompetingCombatantsBox);
		lblMaxCompetingCombatants.setForeground(Color.WHITE);
		lblMaxCompetingCombatants.setBounds(97, 146, 169, 20);
		panel_6.add(lblMaxCompetingCombatants);

		divider_1 = new Divider();
		divider_1.setBounds(12, 173, 440, 20);
		panel_6.add(divider_1);

		tabbedPane_2 = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane_2.setBounds(10, 206, 442, 96);
		panel_6.add(tabbedPane_2);

		panel_9 = new JPanel();
		panel_9.setBackground(Color.BLACK);
		tabbedPane_2.addTab("Melee", null, panel_9, null);

		lblNoMeleeSpecific = new JLabel(
				"No Melee Specific Options are Available Yet");
		lblNoMeleeSpecific.setForeground(Color.WHITE);
		panel_9.add(lblNoMeleeSpecific);

		panel_7 = new JPanel();
		panel_7.setBackground(Color.BLACK);
		tabbedPane_2.addTab("Range", null, panel_7, null);
		panel_7.setLayout(null);

		enablePickupAmmo = new JCheckBox("Automatically Pickup Equiped Ammo");
		enablePickupAmmo.addActionListener(actionListener);
		enablePickupAmmo.setName("range_pickupAmmo");
		enablePickupAmmo.setOpaque(false);
		enablePickupAmmo.setForeground(Color.WHITE);
		enablePickupAmmo.setBounds(8, 9, 372, 25);
		panel_7.add(enablePickupAmmo);

		enableAutoEquipAmmo = new JCheckBox(
				"Equip Any Equivialent Ammo From Inventory");
		enableAutoEquipAmmo.addActionListener(actionListener);
		enableAutoEquipAmmo.setName("range_equipAmmo");
		enableAutoEquipAmmo.setOpaque(false);
		enableAutoEquipAmmo.setForeground(Color.WHITE);
		enableAutoEquipAmmo.setBounds(8, 39, 372, 25);
		panel_7.add(enableAutoEquipAmmo);

		panel_8 = new JPanel();
		panel_8.setBackground(Color.BLACK);
		tabbedPane_2.addTab("Magic", null, panel_8, null);

		lblNoMagicSpecific = new JLabel(
				"No Magic Specific Options are Available Yet");
		lblNoMagicSpecific.setForeground(Color.WHITE);
		panel_8.add(lblNoMagicSpecific);

		panel_10 = new JPanel();
		panel_10.setBackground(Color.BLACK);
		tabbedPane_1.addTab("Wep Spec", null, panel_10, null);
		panel_10.setLayout(null);

		enableWeaponSpecial = new JCheckBox("Enable Weapon Special");
		enableWeaponSpecial.addActionListener(actionListener);
		enableWeaponSpecial.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean b = enableWeaponSpecial.isSelected();
				lblSelectedWeaponSpecial.setEnabled(b);
				weaponSpecialCombobox.setEnabled(b);
			}
		});
		enableWeaponSpecial.setName("weaponSpecial_enable");
		enableWeaponSpecial.setOpaque(false);
		enableWeaponSpecial.setForeground(Color.WHITE);
		enableWeaponSpecial.setBounds(6, 6, 448, 51);
		panel_10.add(enableWeaponSpecial);

		divider_2 = new Divider();
		divider_2.setBounds(6, 64, 448, 30);
		panel_10.add(divider_2);

		lblSelectedWeaponSpecial = new JLabel("Selected Weapon Special");
		lblSelectedWeaponSpecial.setEnabled(false);
		lblSelectedWeaponSpecial.setForeground(Color.WHITE);
		lblSelectedWeaponSpecial.setBounds(38, 128, 136, 30);
		panel_10.add(lblSelectedWeaponSpecial);

		weaponSpecialCombobox = new JComboBox<WeaponSpecial>(
				new DefaultComboBoxModel<WeaponSpecial>(WeaponSpecial.values()));
		weaponSpecialCombobox.setEnabled(false);
		weaponSpecialCombobox.addItemListener(actionListener);
		weaponSpecialCombobox.setName("weaponSpecial_weapon");
		weaponSpecialCombobox.setBounds(180, 133, 274, 20);
		panel_10.add(weaponSpecialCombobox);
		
		useSecondaryWeapons = new JCheckBox("Use Secondary Weapon's Special");
		useSecondaryWeapons.setEnabled(false);
		useSecondaryWeapons.setName("weaponSpecial_useSecondaryWeapon");
		useSecondaryWeapons.setOpaque(false);
		useSecondaryWeapons.setForeground(Color.WHITE);
		useSecondaryWeapons.setBounds(6, 101, 448, 20);
		panel_10.add(useSecondaryWeapons);
		
		divider_12 = new Divider();
		divider_12.setBounds(6, 157, 444, 30);
		panel_10.add(divider_12);
		
		unlessYouSelect = new JTextArea();
		unlessYouSelect.setText("Unless you select 'Use Secondary Weapon's Special', the weapon special to be used will be autmatically determined. ");
		unlessYouSelect.setEditable(false);
		unlessYouSelect.setLineWrap(true);
		unlessYouSelect.setWrapStyleWord(true);
		unlessYouSelect.setForeground(Color.WHITE);
		unlessYouSelect.setOpaque(false);
		unlessYouSelect.setBounds(16, 183, 419, 92);
		panel_10.add(unlessYouSelect);
		
		panel_23 = new JPanel();
		panel_23.setBackground(Color.BLACK);
		tabbedPane_1.addTab("Slayer", null, panel_23, null);
		panel_23.setLayout(null);
		
		enableSlayerSupport = new JCheckBox("Enable Slayer Support");
		enableSlayerSupport.addChangeListener(actionListener);
		enableSlayerSupport.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				boolean b = enableSlayerSupport.isSelected();
				selectedTask.setEnabled(b);
				slayerTaskComboBox.setEnabled(b);
			}
		});
		enableSlayerSupport.setOpaque(false);
		enableSlayerSupport.setName("slayer_enable");
		enableSlayerSupport.setForeground(Color.WHITE);
		enableSlayerSupport.setBounds(6, 7, 448, 51);
		panel_23.add(enableSlayerSupport);
		
		divider_11 = new Divider();
		divider_11.setBounds(6, 57, 448, 21);
		panel_23.add(divider_11);
		
		selectedTask = new JLabel("Selected Task");
		selectedTask.setEnabled(false);
		selectedTask.setForeground(Color.WHITE);
		selectedTask.setBounds(6, 81, 116, 14);
		panel_23.add(selectedTask);
		
		slayerTaskComboBox = new JComboBox<SlayerTask>();
		slayerTaskComboBox.setModel(new DefaultComboBoxModel(SlayerTask.values()));
		slayerTaskComboBox.setEnabled(false);
		slayerTaskComboBox.addItemListener(actionListener);
		slayerTaskComboBox.setName("slayer_task");
		slayerTaskComboBox.setBounds(191, 78, 263, 21);
		panel_23.add(slayerTaskComboBox);
		panel_2.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { tabbedPane_1, panel_6 }));

		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		tabbedPane.addTab("Health", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		tabbedPane_3 = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane_3.setBackground(Color.BLACK);
		panel_1.add(tabbedPane_3, BorderLayout.CENTER);

		panel_11 = new JPanel();
		panel_11.setBackground(Color.BLACK);
		tabbedPane_3.addTab("Eating", null, panel_11, null);
		panel_11.setLayout(null);

		eatFoodCheckBox = new JCheckBox("Enable Eating of Food");
		eatFoodCheckBox.addActionListener(actionListener);
		eatFoodCheckBox.addActionListener(eating_food_AL);
		eatFoodCheckBox.addChangeListener(eating_food_AL);
		eatFoodCheckBox.setName("eating_enable");
		eatFoodCheckBox.setForeground(Color.WHITE);
		eatFoodCheckBox.setOpaque(false);
		eatFoodCheckBox.setBounds(6, 6, 466, 44);
		panel_11.add(eatFoodCheckBox);

		divider_3 = new Divider();
		divider_3.setBounds(6, 57, 462, 23);
		panel_11.add(divider_3);

		eatingLabel1 = new JLabel("Eating Ranges (%)");
		eatingLabel1.setEnabled(false);
		eatingLabel1.setForeground(Color.WHITE);
		eatingLabel1.setBounds(16, 91, 103, 14);
		panel_11.add(eatingLabel1);

		BoundedRangeModel m = new DefaultBoundedRangeModel(
				con.eating_ranges[0], con.eating_ranges[1]
						- con.eating_ranges[0], 20, 95);
		eatingRangeSlider = new JRangeSlider(m, JRangeSlider.HORIZONTAL,
				JRangeSlider.LEFTRIGHT_TOPBOTTOM);
		eatingRangeSlider.addChangeListener(actionListener);
		eatingRangeSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int min = eatingRangeSlider.getModel().getValue();
				eatingLabel2.setText(min + "%-"
						+ (eatingRangeSlider.getModel().getExtent() + min)
						+ "%");
			}
		});
		eatingRangeSlider.setEnabled(false);
		eatingRangeSlider.setName("eating_ranges");
		eatingRangeSlider.setBorder(new LineBorder(Color.RED, 1, true));
		eatingRangeSlider.drawLabels = true;
		eatingRangeSlider.drawTicks = true;
		eatingRangeSlider.tickIncrement = 5;
		eatingRangeSlider.labelIncrement = 10;
		eatingRangeSlider.setBounds(160, 91, 283, 44);
		panel_11.add(eatingRangeSlider);

		eatingLabel2 = new JLabel("65%-80%");
		eatingLabel2.setEnabled(false);
		eatingLabel2.setForeground(Color.WHITE);
		eatingLabel2.setBounds(56, 116, 63, 14);
		panel_11.add(eatingLabel2);

		divider_4 = new Divider();
		divider_4.setBounds(6, 141, 466, 23);
		panel_11.add(divider_4);

		smartEatingCheckBox = new JCheckBox("Enable Smart Eating");
		smartEatingCheckBox.addActionListener(actionListener);
		smartEatingCheckBox.addActionListener(eating_food_AL);
		smartEatingCheckBox.addChangeListener(eating_food_AL);
		smartEatingCheckBox.setEnabled(false);
		smartEatingCheckBox.setName("eating_smartEating_enable");
		smartEatingCheckBox.setForeground(Color.WHITE);
		smartEatingCheckBox.setOpaque(false);
		smartEatingCheckBox.setBounds(6, 171, 466, 23);
		panel_11.add(smartEatingCheckBox);

		minimumHPLabel = new JLabel("Minimum HP %");
		minimumHPLabel.setEnabled(false);
		minimumHPLabel.setForeground(Color.WHITE);
		minimumHPLabel.setBounds(56, 216, 103, 23);
		panel_11.add(minimumHPLabel);

		minimumHPSlider = new JSlider();
		minimumHPSlider.addChangeListener(actionListener);
		minimumHPSlider.setEnabled(false);
		minimumHPSlider.setBorder(new LineBorder(Color.RED, 1, true));
		minimumHPSlider.setName("eating_smartEating_limit");
		minimumHPSlider.setForeground(Color.WHITE);
		minimumHPSlider.setMajorTickSpacing(5);
		minimumHPSlider.setMinorTickSpacing(5);
		minimumHPSlider.setMaximum(64);
		minimumHPSlider.setMinimum(20);
		minimumHPSlider.setPaintTicks(true);
		minimumHPSlider.setPaintLabels(true);
		minimumHPSlider.setOpaque(false);
		minimumHPSlider.setBounds(160, 216, 283, 44);
		panel_11.add(minimumHPSlider);

		panel_12 = new JPanel();
		panel_12.setBackground(Color.BLACK);
		tabbedPane_3.addTab("B2P", null, panel_12, null);
		panel_12.setLayout(null);

		enableB2P = new JCheckBox("Enable B2P");
		enableB2P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean b = enableB2P.isSelected();
				boneLimitRangeSlider.setEnabled(b);
				lblBoneLimits.setEnabled(b);
			}			
		});
		enableB2P.addActionListener(actionListener);
		enableB2P.setName("b2p_enable");
		enableB2P.setForeground(Color.WHITE);
		enableB2P.setOpaque(false);
		enableB2P.setBounds(6, 6, 468, 44);
		panel_12.add(enableB2P);

		divider_5 = new Divider();
		divider_5.setBounds(6, 56, 452, 23);
		panel_12.add(divider_5);

		boneLimitRangeSlider = new JRangeSlider(new DefaultBoundedRangeModel(
				con.b2p_minBoneLimit, con.b2p_maxBoneLimit
						- con.b2p_minBoneLimit, 0, 28), 1, 0);
		boneLimitRangeSlider.setEnabled(false);
		boneLimitRangeSlider.addChangeListener(actionListener);
		boneLimitRangeSlider.setBorder(new LineBorder(Color.RED, 1, true));
		boneLimitRangeSlider.setName("b2p_boneLimits");
		boneLimitRangeSlider.labelIncrement = 7;
		boneLimitRangeSlider.drawTicks = true;
		boneLimitRangeSlider.drawLabels = true;
		boneLimitRangeSlider.setBounds(175, 90, 283, 44);
		panel_12.add(boneLimitRangeSlider);

		lblBoneLimits = new JLabel("Bone Limits");
		lblBoneLimits.setEnabled(false);
		lblBoneLimits.setForeground(Color.WHITE);
		lblBoneLimits.setBounds(16, 88, 134, 44);
		panel_12.add(lblBoneLimits);

		panel_18 = new JPanel();
		panel_18.setBackground(Color.BLACK);
		tabbedPane_3.addTab("Potions", null, panel_18, null);
		panel_18.setLayout(null);

		enableAttackPotions = new JCheckBox("Enable Attack Potions");
		enableAttackPotions.addActionListener(actionListener);
		enableAttackPotions.setName("enableAttackPotions");
		enableAttackPotions.setOpaque(false);
		enableAttackPotions.setForeground(Color.WHITE);
		enableAttackPotions.setBounds(6, 7, 456, 23);
		panel_18.add(enableAttackPotions);

		enableStrengthPotions = new JCheckBox("Enable Strength Potions");
		enableStrengthPotions.addActionListener(actionListener);
		enableStrengthPotions.setName("enableStrengthPotions");
		enableStrengthPotions.setOpaque(false);
		enableStrengthPotions.setForeground(Color.WHITE);
		enableStrengthPotions.setBounds(6, 33, 456, 23);
		panel_18.add(enableStrengthPotions);

		enableDefensePotions = new JCheckBox("Enable Defense Potions");
		enableDefensePotions.addActionListener(actionListener);
		enableDefensePotions.setName("enableDefensePotions");
		enableDefensePotions.setOpaque(false);
		enableDefensePotions.setForeground(Color.WHITE);
		enableDefensePotions.setBounds(6, 59, 456, 23);
		panel_18.add(enableDefensePotions);

		enableRangePotions = new JCheckBox("Enable Range Potions");
		enableRangePotions.addActionListener(actionListener);
		enableRangePotions.setName("enableRangePotions");
		enableRangePotions.setOpaque(false);
		enableRangePotions.setForeground(Color.WHITE);
		enableRangePotions.setBounds(6, 85, 456, 23);
		panel_18.add(enableRangePotions);

		enableMagicPotions = new JCheckBox("Enable Magic Potions");
		enableMagicPotions.addActionListener(actionListener);
		enableMagicPotions.setName("enableMagicPotions");
		enableMagicPotions.setOpaque(false);
		enableMagicPotions.setForeground(Color.WHITE);
		enableMagicPotions.setBounds(6, 111, 456, 23);
		panel_18.add(enableMagicPotions);

		divider_7 = new Divider();
		divider_7.setBounds(6, 199, 452, 23);
		panel_18.add(divider_7);

		enableAntipoison = new JCheckBox("Enable Antipoison Control");
		enableAntipoison.addActionListener(actionListener);
		enableAntipoison.setName("enableAntipoisons");
		enableAntipoison.setOpaque(false);
		enableAntipoison.setForeground(Color.WHITE);
		enableAntipoison.setBounds(6, 229, 456, 23);
		panel_18.add(enableAntipoison);

		divider_8 = new Divider();
		divider_8.setBounds(6, 259, 452, 23);
		panel_18.add(divider_8);

		enableAntifireControl = new JCheckBox("Enable Antifire Control");
		enableAntifireControl.addActionListener(actionListener);
		enableAntifireControl.setName("enableAntifires");
		enableAntifireControl.setOpaque(false);
		enableAntifireControl.setForeground(Color.WHITE);
		enableAntifireControl.setBounds(6, 289, 456, 23);
		panel_18.add(enableAntifireControl);

		mininumBoostLimit = new JLabel("Mininum Boost Limit");
		mininumBoostLimit.setForeground(Color.WHITE);
		mininumBoostLimit.setBounds(6, 141, 111, 52);
		panel_18.add(mininumBoostLimit);

		minimumBoostSlider = new JSlider();
		minimumBoostSlider.addChangeListener(actionListener);
		minimumBoostSlider.setBorder(new LineBorder(Color.RED, 1, true));
		minimumBoostSlider.setName("potions_minBoost");
		minimumBoostSlider.setMajorTickSpacing(1);
		minimumBoostSlider.setForeground(Color.WHITE);
		minimumBoostSlider.setMinorTickSpacing(1);
		minimumBoostSlider.setOpaque(false);
		minimumBoostSlider.setPaintTicks(true);
		minimumBoostSlider.setPaintLabels(true);
		minimumBoostSlider.setMaximum(5);
		minimumBoostSlider.setMinimum(1);
		minimumBoostSlider.setBounds(127, 141, 331, 52);
		panel_18.add(minimumBoostSlider);
		
		panel_21 = new JPanel();
		panel_21.setBackground(Color.BLACK);
		tabbedPane_3.addTab("Prayer", null, panel_21, null);
		panel_21.setLayout(null);
		
		prayerSupportCheckBox = new JCheckBox("Enable Prayer Support");
		prayerSupportCheckBox.addChangeListener(actionListener);
		prayerSupportCheckBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				tabbedPane_7.setEnabled(prayerSupportCheckBox.isSelected());
			}
		});
		prayerSupportCheckBox.setName("prayer_enable");
		prayerSupportCheckBox.setForeground(Color.WHITE);
		prayerSupportCheckBox.setOpaque(false);
		prayerSupportCheckBox.setBounds(6, 7, 461, 44);
		panel_21.add(prayerSupportCheckBox);
		
		divider_10 = new Divider();
		divider_10.setBounds(6, 58, 457, 25);
		panel_21.add(divider_10);
		
		tabbedPane_7 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_7.setEnabled(false);
		tabbedPane_7.setBounds(6, 94, 457, 214);
		panel_21.add(tabbedPane_7);
		
		panel_22 = new JPanel();
		panel_22.setBackground(Color.BLACK);
		tabbedPane_7.addTab("Prayer Selections", null, panel_22, null);
		panel_22.setLayout(null);
		
		newLabel_2 = new JLabel("For now, only quick prayer support is available. ");
		newLabel_2.setForeground(Color.WHITE);
		newLabel_2.setBounds(10, 11, 432, 48);
		panel_22.add(newLabel_2);
		
		panel_24 = new JPanel();
		panel_24.setBackground(Color.BLACK);
		tabbedPane_7.addTab("Prayer Points", null, panel_24, null);
		panel_24.setLayout(null);
		
		prayerRegenerationMethod = new JLabel("Prayer Regeneration Method");
		prayerRegenerationMethod.setForeground(Color.WHITE);
		prayerRegenerationMethod.setBounds(10, 11, 154, 14);
		panel_24.add(prayerRegenerationMethod);
		
		prayerMethodComboBox = new JComboBox<PrayerMethod>();
		prayerMethodComboBox.setModel(new DefaultComboBoxModel(PrayerMethod.values()));
		prayerMethodComboBox.setName("prayer_method");
		prayerMethodComboBox.setOpaque(false);
		prayerMethodComboBox.addItemListener(actionListener);
		prayerMethodComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				tabbedPane_8.setSelectedIndex(prayerMethodComboBox.getSelectedIndex());
			}
		});
		prayerMethodComboBox.setBounds(174, 8, 268, 20);
		panel_24.add(prayerMethodComboBox);
		
		tabbedPane_8 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_8.setBounds(10, 36, 432, 139);
		panel_24.add(tabbedPane_8);
		
		panel_25 = new JPanel();
		panel_25.setBackground(Color.BLACK);
		tabbedPane_8.addTab("Prayer Potion", null, panel_25, null);
		panel_25.setLayout(null);
		
		noPrayerPotion = new JLabel("No prayer potion options are available yet.");
		noPrayerPotion.setForeground(Color.WHITE);
		noPrayerPotion.setBounds(10, 11, 407, 14);
		panel_25.add(noPrayerPotion);
		
		panel_26 = new JPanel();
		panel_26.setBackground(Color.BLACK);
		tabbedPane_8.addTab("Prayer Altar", null, panel_26, null);
		panel_26.setLayout(null);
		
		newLabel_3 = new JLabel("Prayer Altar Object ID");
		newLabel_3.setForeground(Color.WHITE);
		newLabel_3.setBounds(10, 11, 144, 14);
		panel_26.add(newLabel_3);
		
		prayerAltarIdSpinner = new JSpinner();
		prayerAltarIdSpinner.setName("prayer_altarID");
		prayerAltarIdSpinner.addChangeListener(actionListener);
		prayerAltarIdSpinner.setBounds(162, 8, 255, 20);
		panel_26.add(prayerAltarIdSpinner);

		panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		tabbedPane.addTab("Loot", null, panel_3, null);
		panel_3.setLayout(null);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setName("LootTable");
		scrollPane_2.setBounds(200, 6, 323, 315);
		panel_3.add(scrollPane_2);

		itemTable = new JTable(con.lootList);
		itemTable.addMouseListener(new MouseAdapter() {
			
			public void mouseReleased(MouseEvent e) {
				if (itemTable.getSelectedRow() != -1 && e.isPopupTrigger()) {
					lootTablePopup = new LootTablePopup(con.lootList, itemTable);
					lootTablePopup.setLocation(e.getLocationOnScreen());
					lootTablePopup.setVisible(true);
					System.out.println("Showing popup.");
				}
			}
			
		});
		itemTable.setName("LootTable");
		scrollPane_2.setViewportView(itemTable);

		panel_13 = new JPanel();
		panel_13.setBackground(Color.BLACK);
		panel_13.setBounds(6, 6, 182, 315);
		panel_3.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		btnAddSelected = new JButton("Add Selected");
		btnAddSelected.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int tab = tabbedPane_4.getSelectedIndex();
				int pri = comboBox_3.getSelectedIndex();
				RItem item;
				List<RItem> list;
				switch (tab) {
				case 0:
					int id = ((Number) itemID.getValue()).intValue();
					String name = itemName.getText();
					boolean stack = itemStackable.isSelected();
					boolean noted = stack && itemNoted.isSelected();
					item = new RItem(id, name, stack, noted);
					item.setPriority(pri);
					con.lootList.add(item);
					break;
				case 1:
					list = autoLootList.getSelectedValuesList();
					for (RItem i : list) {
						i.setPriority(pri);
					}
					if (list.size() > 0) {
						con.lootList.addAll(list);
					}
					break;
				case 2:
					list = sugTree.getSelectedItems();
					if (list.size() > 0) {
						ItemList newList = new ItemList();
						checkExpansion(newList, list);
						for (RItem i : list) {
							if (i != null) {
								i.setPriority(pri);
							}
						}
						con.lootList.addAll(newList);
					}
					break;
				case 3:
					List<File> files = itemIOList.getSelectedValuesList();
					if (files.size() > 0) {
						for (File f : files) {
							try {
								RItem[] itemArray = ItemIO.readItemFile(f.getPath());
								for (RItem i : itemArray) {
									i.setPriority(pri);
								}
								if (itemArray.length > 0) {
									con.lootList.addAll(itemArray);
								}
							} catch (Exception e1) {
								e1.printStackTrace();
								DialogUtil.yell(
										"Couldn't Load this item file : \n"
												+ f.getPath(), RFGui.this);
							}

						}
					}
				}
			}
		});
		panel_13.add(btnAddSelected, BorderLayout.SOUTH);

		tabbedPane_4 = new JTabbedPane(SwingConstants.TOP);
		panel_13.add(tabbedPane_4, BorderLayout.CENTER);

		panel_14 = new JPanel();
		panel_14.setBackground(Color.BLACK);
		tabbedPane_4.addTab("Man", null, panel_14, null);
		panel_14.setLayout(null);

		lblItemId = new JLabel("Item ID");
		lblItemId.setForeground(Color.WHITE);
		lblItemId.setBounds(6, 24, 46, 14);
		panel_14.add(lblItemId);

		itemName = new JTextField();
		itemName.setBounds(85, 60, 86, 20);
		panel_14.add(itemName);
		itemName.setColumns(10);

		itemID = new JSpinner();
		itemID.setBounds(85, 22, 86, 18);
		panel_14.add(itemID);

		lblItemName = new JLabel("Item Name");
		lblItemName.setForeground(Color.WHITE);
		lblItemName.setBounds(6, 63, 67, 14);
		panel_14.add(lblItemName);

		itemStackable = new JCheckBox("Stackable?");
		itemStackable.setName("ItemCreation_Stackable");
		itemStackable.setForeground(Color.WHITE);
		itemStackable.setOpaque(false);
		itemStackable.setBounds(6, 92, 164, 23);
		panel_14.add(itemStackable);

		itemNoted = new JCheckBox("Noted?");
		itemNoted.setName("ItemCreation_Noted");
		itemNoted.setForeground(Color.WHITE);
		itemNoted.setOpaque(false);
		itemNoted.setBounds(6, 121, 97, 23);
		panel_14.add(itemNoted);

		panel_15 = new JPanel();
		tabbedPane_4.addTab("Auto", null, panel_15, null);
		panel_15.setLayout(new BorderLayout(0, 0));
		ItemDatabase.manageUpdateAvailableButton(this, panel_15, BorderLayout.NORTH);

		scrollPane_3 = new JScrollPane();
		panel_15.add(scrollPane_3, BorderLayout.CENTER);

		autoLootList = new JList<RItem>();
		autoLootList.setSelectionBackground(Color.RED);
		autoLootList.setForeground(Color.WHITE);
		autoLootList.setBackground(Color.BLACK);
		scrollPane_3.setViewportView(autoLootList);

		textField_1 = new JTextField();
		textField_1.setName("ItemCreation_SearchText");
		textField_1.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				if (textField_1.getText().length() > 1) {
					ItemList list = ItemDatabase.get(textField_1.getText(),
							true, true, 100);
					autoLootList.setModel(list);
				}
			}
		});
		panel_15.add(textField_1, BorderLayout.SOUTH);
		textField_1.setColumns(10);

		panel_16 = new JPanel();
		tabbedPane_4.addTab("Groups", null, panel_16, null);
		panel_16.setLayout(new BorderLayout(0, 0));

		lblEachContainsMany = new JLabel("Each Contains Many");
		lblEachContainsMany.setHorizontalAlignment(SwingConstants.CENTER);
		panel_16.add(lblEachContainsMany, BorderLayout.NORTH);
		
		scrollPane_5 = new JScrollPane();
		panel_16.add(scrollPane_5, BorderLayout.CENTER);
		
		sugTree = new SugTree();
		sugTree.setForeground(Color.WHITE);
		sugTree.setBackground(Color.BLACK);
		scrollPane_5.setViewportView(sugTree);
		
		pleaseAddClues = new JTextArea();
		pleaseAddClues.setBackground(Color.BLACK);
		pleaseAddClues.setForeground(Color.WHITE);
		pleaseAddClues.setLineWrap(true);
		pleaseAddClues.setWrapStyleWord(true);
		pleaseAddClues.setFont(new Font("Monospaced", Font.PLAIN, 10));
		pleaseAddClues.setText("Please add clues last. They take up a lot of space.");
		panel_16.add(pleaseAddClues, BorderLayout.SOUTH);

		panel_17 = new JPanel();
		panel_17.setBackground(Color.BLACK);
		tabbedPane_4.addTab("IO", null, panel_17, null);
		panel_17.setLayout(null);

		JButton btnSaveCurrent = new JButton("Save Current");
		btnSaveCurrent.setBounds(6, 6, 165, 23);
		btnSaveCurrent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CacheElementCreationDialog(itemSaveCache).setVisible(true);
			}
		});
		panel_17.add(btnSaveCurrent);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(6, 56, 165, 183);
		panel_17.add(scrollPane_4);

		// itemIOList = new JList<File>(new
		// File(FileSystem.getItemSavesDirectory()).listFiles());
		itemIOList = new JList<File>(itemSaveCache);
		itemIOList.setCellRenderer(new ListCellRenderer<File>() {

			@Override
			public Component getListCellRendererComponent(
					JList<? extends File> list, File value, int index,
					boolean isSelected, boolean cellHasFocus) {
				String n = value.getName();
				JLabel l = new JLabel(n.substring(0, n.lastIndexOf(".")));
				l.setForeground(list.getForeground());
				if (isSelected) {
					l.setOpaque(true);
					l.setBackground(list.getSelectionBackground());
				}
				return l;
			};

		});
		itemIOList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemIOList.setSelectionBackground(Color.RED);
		itemIOList.setForeground(Color.WHITE);
		itemIOList.setBackground(Color.BLACK);
		scrollPane_4.setViewportView(itemIOList);

		lblSavedItemFiles = new JLabel("Saved Item Files");
		lblSavedItemFiles.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_4.setColumnHeaderView(lblSavedItemFiles);

		divider_6 = new Divider();
		divider_6.setBounds(6, 33, 165, 23);
		panel_17.add(divider_6);

		comboBox_3 = new JComboBox<ItemPriority>(
				new DefaultComboBoxModel<ItemPriority>(ItemPriority.values()));
		comboBox_3.setName("ItemCreation_Priority");
		comboBox_3.setSelectedIndex(1);
		panel_13.add(comboBox_3, BorderLayout.NORTH);

		panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
		tabbedPane.addTab("Extras", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));

		tabbedPane_5 = new JTabbedPane(SwingConstants.LEFT);
		tabbedPane_5.setBackground(Color.BLACK);
		panel_5.add(tabbedPane_5, BorderLayout.CENTER);

		panel_19 = new JPanel();
		panel_19.setBackground(Color.BLACK);
		tabbedPane_5.addTab("Extras", null, panel_19, null);

		moreToCome = new JLabel("More to come!!!!");
		moreToCome.setForeground(Color.WHITE);
		panel_19.add(moreToCome);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.BLACK);
		tabbedPane.addTab("Wiki Pages", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));

		tabbedPane_6 = new JTabbedPane(SwingConstants.TOP);
		tabbedPane_6.setBackground(Color.BLACK);
		panel_4.add(tabbedPane_6, BorderLayout.CENTER);

		panel_20 = new JPanel();
		panel_20.setBackground(Color.BLACK);
		tabbedPane_6.addTab("Instructions", null, panel_20, null);
		panel_20.setLayout(null);

		newLabel = new JLabel("Wiki Pages Instructions Page");
		newLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newLabel.setBounds(10, 5, 504, 30);
		newLabel.setForeground(Color.WHITE);
		panel_20.add(newLabel);

		divider_9 = new Divider();
		divider_9.setBounds(10, 46, 504, 19);
		panel_20.add(divider_9);

		selectA = new JLabel(
				"1) Select a monster(s) from the 'Selected Monsters' List");
		selectA.setForeground(Color.WHITE);
		selectA.setBounds(10, 76, 504, 14);
		panel_20.add(selectA);

		rightClick = new JLabel(
				"2) Right click the 'Selected Monsters' list to open the popup menu.");
		rightClick.setForeground(Color.WHITE);
		rightClick.setBounds(10, 101, 504, 14);
		panel_20.add(rightClick);

		selectWhether = new JLabel(
				"3) Select whether to look up one or alll of the selected monsters.");
		selectWhether.setForeground(Color.WHITE);
		selectWhether.setBounds(10, 126, 504, 14);
		panel_20.add(selectWhether);

		yourWikiPages = new JLabel(
				"4) Your wiki pages will be loaded in the background.");
		yourWikiPages.setForeground(Color.WHITE);
		yourWikiPages.setBounds(10, 151, 504, 14);
		panel_20.add(yourWikiPages);

		youWill = new JLabel(
				"5) You will recienve a dialog message when your wiki page is done loading.");
		youWill.setForeground(Color.WHITE);
		youWill.setBounds(10, 176, 504, 14);
		panel_20.add(youWill);

		goTo = new JLabel(
				"6) Go to the 'Wiki Pages' tab and view your wiki page(s).");
		goTo.setForeground(Color.WHITE);
		goTo.setBounds(10, 201, 504, 14);
		panel_20.add(goTo);

		newLabel_1 = new JLabel(
				"7) \r\nRepeat Steps 1-3 on the loots tables on each wiki page to add their drops.");
		newLabel_1.setBorder(new LineBorder(Color.RED, 1, true));
		newLabel_1.setForeground(Color.WHITE);
		newLabel_1.setBounds(10, 226, 381, 59);
		panel_20.add(newLabel_1);
	}
	public boolean isCompleted() {
		return completed;
	}
}
