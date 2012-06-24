package com.gui.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.gui.settings.SettingsCache;
import com.io.Cache;

public class CacheElementCreationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7179562879962162212L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CacheElementCreationDialog dialog = new CacheElementCreationDialog(
					new SettingsCache());
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Cache cache;
	protected JTextField textField;
	protected JScrollPane scrollPane;
	protected JTextArea errorsTextArea;

	/**
	 * Create the dialog.
	 */
	public CacheElementCreationDialog(Cache cache) {
		super((Frame) cache.getGui(), true);
		this.cache = cache;
		setModalityType(ModalityType.DOCUMENT_MODAL);
		initialize();
		setLocationRelativeTo(cache.getGui());
	}

	private void initialize() {
		setTitle("Cache Element Creator");
		setBounds(100, 100, 324, 192);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 11, 288, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 288, 69);
		contentPanel.add(scrollPane);

		errorsTextArea = new JTextArea();
		errorsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
		errorsTextArea.setText("No Errors yet.");
		errorsTextArea.setLineWrap(true);
		errorsTextArea.setWrapStyleWord(true);
		scrollPane.setViewportView(errorsTextArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String name = textField.getText();
						String r = cache.acceptNewName(name);
						if (r.equals("")) {
							cache.createNewElement(name);
							setVisible(false);
						} else {
							errorsTextArea.setText(r);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
