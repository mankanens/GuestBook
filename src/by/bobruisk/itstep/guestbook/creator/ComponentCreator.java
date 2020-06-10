package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class ComponentCreator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3307541539774227527L;

	public static JButton create(String name, Dimension dimension, ActionListener listener) {
		JButton button = new JButton(name);
		button.setPreferredSize(dimension);
		button.addActionListener(listener);
		return button;
	}

	public static JLabel create(String name, Font font) {
		JLabel label = new JLabel(name);
		label.setFont(font);
		return label;
	}

	public static JTextField create(String initialText, int columns) {
		JTextField textField = new JTextField(initialText, columns);
		return textField;
	}

	public static JTextArea create(String initialText, int rows, int columns) {
		JTextArea textArea = new JTextArea(initialText, rows, columns);
		return textArea;
	}

	public static JCheckBox create(String name) {
		JCheckBox checkBox = new JCheckBox(name);
		return checkBox;
	}

}
