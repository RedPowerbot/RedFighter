package com.gui.settings;

import java.awt.Component;
import java.awt.Container;

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

/**
 * This class is to be used to save a configuration of any Component. In order
 * for the value of a inner component to be saved, the component must have a
 * name via Component.setName(String). Be careful not to have components share
 * names. It will result in overwriting and possibly exception during loading
 * via GUILoader. AWT components are NOT supported.
 * 
 * @author RedDevil
 * 
 */
public class ComponentReader {

	public static void store(PropertiesEx p, Component c) {
		scan(p, c);
	}

	private static void scan(PropertiesEx p, Component[] array) {
		for (Component c : array) {
			scan(p, c);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void scan(PropertiesEx p, Component c) {
		switch (c.getClass().getSimpleName()) {
		case "LFGui": // Custom classes are here too.
		case "LFMenuBar":
		case "JGlassPane":
		case "JConentPane":
		case "JFrame":
		case "JRootPane":
		case "JPanel":
		case "JLayeredPane":
		case "JTabbedPane":
		case "JMenuBar":
		case "JMenu":
		case "JToolBar":
		case "JSplitPane":
		case "JScrollPane":
		case "JViewport":
			scan(p, ((Container) c).getComponents());
			break;
		case "JRadioButton":
		case "JCheckBox":
			put(p, c.getName(),
					Boolean.toString(((JToggleButton) c).isSelected()));
			break;
		case "JRadioButtonMenuItem":
			put(p, c.getName(),
					Boolean.toString(((JRadioButtonMenuItem) c).isSelected()));
			break;
		case "JCheckBoxMenuItem":
			put(p, c.getName(),
					Boolean.toString(((JRadioButtonMenuItem) c).isSelected()));
			break;
		case "JComboBox":
			put(p, c.getName(),
					Integer.toString(((JComboBox) c).getSelectedIndex()));
			break;
		case "JSlider":
			put(p, c.getName(), Integer.toString(((JSlider) c).getValue()));
			break;
		case "JSpinner":
			put(p, c.getName(), ((JSpinner) c).getValue().toString());
			break;
		case "JTextField":
		case "JTextArea":
		case "JEditorPane":
			put(p,
					c.getName(),
					((JTextComponent) c).getText()
							.replaceAll(StringUtil.LINE_BREAK,
									StringUtil.BREAK_REPLACEMENT));
			break;
		case "JRangeSlider":
			JRangeSlider s = (JRangeSlider) c;
			put(p, c.getName(), s.getModel().getValue() + ","
					+ s.getModel().getExtent());
			break;
		case "JList":
			ListModel listModel = ((JList) c).getModel();
			put(p, c.getName(), parseList(listModel));
			break;
		case "JTable":
			TableModel tableModel = ((JTable) c).getModel();
			put(p, c.getName(), parseTable(tableModel));
			break;
		default:
			System.out.println("ComponentReader:Unsupportted Class Type:"
					+ c.getClass().getSimpleName());
		}
	}

	private static void put(PropertiesEx p, String name, String value) {
		if (name != null && !name.equals("")) {
			p.setProperty(name, value);
		}
	}

	private static String parseList(ListModel<?> model) {
		if (model instanceof Documentable) {
			return ((Documentable) model).toDataString();
		}
		return "{Unsuported List Model Type" + model.getClass().getSimpleName()
				+ "}";
	}

	private static String parseTable(TableModel model) {
		if (model instanceof Documentable) {
			return ((Documentable) model).toDataString();
		}
		return "{Unsuported Table Model Type:"
				+ model.getClass().getSimpleName() + "}";
	}

}
