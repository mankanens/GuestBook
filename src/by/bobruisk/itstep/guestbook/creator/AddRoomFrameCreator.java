package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import by.bobruisk.itstep.guestbook.gui.GUI;
import by.bobruisk.itstep.guestbook.hotel.Room;
import by.bobruisk.itstep.guestbook.hotel.Room.RoomType;

public class AddRoomFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		EmptyFrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		Font font = new Font("Bold", Font.BOLD, 24);
		JCheckBox single = ComponentCreator.create("Одноместный");
		JCheckBox doubleRoom = ComponentCreator.create("Двухместный");
		JCheckBox lux = ComponentCreator.create("Люкс");
		JLabel title = ComponentCreator.create("Добавление номера", new Font("Bold", Font.BOLD, 24));
		JLabel numberLabel = ComponentCreator.create("Номер комнаты: ", font);
		JTextField numberText = ComponentCreator.create("", 25);
		JButton addButton = ComponentCreator.create("Добавить", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Room room = new Room();
				try {
					room.setNumber(Integer.parseInt(numberText.getText()));
				} catch (NumberFormatException f) {
					JOptionPane.showMessageDialog(frame, "Проверьте правильность номера комнаты.", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (single.isSelected()) {
					room.setType(RoomType.SINGLE);
					single.setSelected(false);
				} else if (doubleRoom.isSelected()) {
					room.setType(RoomType.DOUBLE);
					doubleRoom.setSelected(false);
				} else if (lux.isSelected()) {
					room.setType(RoomType.LUX);
					lux.setSelected(false);
				} else {
					JOptionPane.showMessageDialog(frame, "Выберите тип комнаты.", "Ошибка", JOptionPane.ERROR_MESSAGE);
					return;
				}
				Object[] answers = gui.guestBook.addRoom(room);
				if (!(Boolean) answers[0]) {
					JOptionPane.showMessageDialog(frame, (String) answers[1], "Ошибка.", JOptionPane.ERROR_MESSAGE);
					frame.setVisible(false);
					gui.getMainFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, (String) answers[1], "Успех", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					gui.getMainFrame().setVisible(true);
				}
				numberText.setText("");
			}
		});
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		panel.add(single);
		panel.add(doubleRoom);
		panel.add(lux);
		panel.add(title);
		panel.add(numberLabel);
		panel.add(numberText);
		panel.add(addButton);
		layout.putConstraint(SpringLayout.WEST, title, 225, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, numberLabel, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, numberLabel, 75, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, numberText, 25, SpringLayout.EAST, numberLabel);
		layout.putConstraint(SpringLayout.NORTH, numberText, 10, SpringLayout.NORTH, numberLabel);
		layout.putConstraint(SpringLayout.WEST, single, 150, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, single, 150, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, doubleRoom, 300, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, doubleRoom, 0, SpringLayout.NORTH, single);
		layout.putConstraint(SpringLayout.WEST, lux, 450, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, lux, 0, SpringLayout.NORTH, single);
		layout.putConstraint(SpringLayout.WEST, addButton, 260, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addButton, 300, SpringLayout.NORTH, panel);
		frame.add(panel);
		return frame;
	}

}
