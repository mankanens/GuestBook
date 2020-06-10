package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import by.bobruisk.itstep.guestbook.gui.GUI;

public class DepartureFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		EmptyFrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		Font font = new Font("Bold", Font.BOLD, 24);
		JLabel roomNumberLabel = ComponentCreator.create("Номер комнаты: ", font);
		JTextField roomNumberText = ComponentCreator.create("", 10);
		JButton departureButton = ComponentCreator.create("Отметить отъезд", new Dimension(250, 75),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Object[] answers = null;
						try {
							answers = gui.guestBook.markDeparture(Integer.parseInt(roomNumberText.getText()));
						} catch (NumberFormatException f) {
							JOptionPane.showMessageDialog(frame, "Проверьте правильность номера комнаты.", "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (!(Boolean) answers[0]) {
							JOptionPane.showMessageDialog(frame, (String) answers[1], "Ошибка",
									JOptionPane.ERROR_MESSAGE);
							roomNumberText.setText("");
							frame.setVisible(false);
							gui.getMainFrame().setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, (String) answers[1], "Успех",
									JOptionPane.INFORMATION_MESSAGE);
							roomNumberText.setText("");
							frame.setVisible(false);
							gui.getMainFrame().setVisible(true);
						}
					}
				});
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		panel.add(roomNumberText);
		panel.add(roomNumberLabel);
		panel.add(departureButton);
		layout.putConstraint(SpringLayout.WEST, roomNumberLabel, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, roomNumberLabel, 50, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, roomNumberText, 10, SpringLayout.EAST, roomNumberLabel);
		layout.putConstraint(SpringLayout.NORTH, roomNumberText, 8, SpringLayout.NORTH, roomNumberLabel);
		layout.putConstraint(SpringLayout.WEST, departureButton, 235, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, departureButton, 150, SpringLayout.NORTH, panel);
		frame.add(panel);
		return frame;
	}

}
