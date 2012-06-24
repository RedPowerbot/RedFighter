package com.gui.settings;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

import com.gui.swing.JRangeSlider;
import com.util.Documentable;
import com.util.PropertiesEx;
import com.util.StringUtil;

public class ComponentLoader {

	public static void apply(PropertiesEx p, Component c) {
		scan(p, c);
	}

	private static void scan(PropertiesEx p, Component[] array) {
		for (Component c : array) {
			scan(p, c);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void scan(PropertiesEx p, Component c) {
		if (c == null) {
			return;
		}
		switch (c.getClass().getSimpleName()) {
		case "LFGui":
		case "LFMenuBar":
		case "JViewport":
		case "JFrame":
		case "JRootPane":
		case "JLayeredPane":
		case "JPanel":
		case "JConentPane":
		case "JTabbedPane":
		case "JMenuBar":
		case "JMenu":
		case "JToolBar":
		case "JSplitPane":
		case "JScrollPane":
			scan(p, ((Container) c).getComponents());
			break;
		case "JRadioButton":
		case "JCheckBox":
			if (hasName(c, p)) {
				((JToggleButton) c).setSelected(p.Boolean(c.getName()));
			}
			break;
		case "JRadioButtonMenuItemm":
			if (hasName(c, p)) {
				((JRadioButtonMenuItem) c).setSelected(p.Boolean(c.getName()));
			}
			break;
		case "JCheckBoxMenuItem":
			if (hasName(c, p)) {
				((JCheckBoxMenuItem) c).setSelected(p.Boolean(c.getName()));
			}
			break;
		case "JComboBox":
			if (hasName(c, p)) {
				((JComboBox) c).setSelectedIndex(p.Int(c.getName()));
			}
			break;
		case "JSlider":
			if (hasName(c, p)) {
				((JSlider) c).setValue(p.Int(c.getName()));
			}
			break;
		case "JSpinner":
			if (hasName(c, p)) {
				((JSpinner) c).setValue(p.Int(c.getName()));
			}
			break;
		case "JTextField":
		case "JTextArea":
		case "JEditorPane":
			JTextComponent jtc = (JTextComponent) c;
			if (jtc.isEditable() && hasName(c, p)) {
				jtc.setText(p.String(c.getName()).replaceAll(
						StringUtil.BREAK_REPLACEMENT, StringUtil.LINE_BREAK));
			}
			break;
		case "JRangeSlider":
			if (p.contains(c.getName())) {
				JRangeSlider s = (JRangeSlider) c;
				String[] args = p.String(c.getName()).split(",");
				s.getModel().setValue(Integer.parseInt(args[0]));
				s.getModel().setExtent(Integer.parseInt(args[1]));
			}
			break;
		case "JList":
			if (hasName(c, p)) {
				ListModel model = ((JList) c).getModel();
				parseList(model, p.String(c.getName()));
			}
			break;
		case "JTable":
			if (hasName(c, p)) {
				TableModel model = ((JTable) c).getModel();
				parseTable(model, p.String(c.getName()));
			}
			break;
		default:
			System.out.println("ComponentLoader:Unsupportted Class Type:"
					+ c.getClass().getSimpleName());
		}
	}

	private static boolean hasName(Component c, PropertiesEx p) {
		if (c.getName() == null) {
			return false;
		}
		boolean b = p.containsKey(c.getName());
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	private static void parseList(ListModel<?> model, String value) {
		if (model instanceof Documentable) {
			((Documentable) model).initFromDataString(value);
		}
	}

	private static void parseTable(TableModel model, String value) {
		if (model instanceof Documentable) {
			((Documentable) model).initFromDataString(value);
		}
	}

}
