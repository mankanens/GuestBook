package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import by.bobruisk.itstep.guestbook.Tenant;
import by.bobruisk.itstep.guestbook.gui.GUI;
import by.bobruisk.itstep.guestbook.hotel.Room.RoomType;

public class AddTenantFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		EmptyFrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		Font font = new Font("Bold", Font.BOLD, 24);
		String[] labelsText = { "Имя:", "Фамилия:", "Отчество:", "Номер паспорта:" };
		JCheckBox single = ComponentCreator.create("Одноместный");
		JCheckBox doubleRoom = ComponentCreator.create("Двухместный");
		JCheckBox lux = ComponentCreator.create("Люкс");
		ArrayList<JLabel> labels = new ArrayList<>();
		ArrayList<JTextField> textFields = new ArrayList<>();
		for (int i = 0; i < labelsText.length; i++) {
			labels.add(ComponentCreator.create(labelsText[i], font));
			textFields.add(ComponentCreator.create("", 18));
		}
		JButton addButton = ComponentCreator.create("Добавить", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (JTextField jTextField : textFields) {
					if (jTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(frame, "Пожалуйста заполните все поля.", "Ошибка",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				Tenant tenant = new Tenant();
				tenant.setName(textFields.get(0).getText());
				tenant.setSurname(textFields.get(1).getText());
				tenant.setPatronymic(textFields.get(2).getText());
				tenant.setPassportID(textFields.get(3).getText());
				tenant.setArrivalDate(new Date());
				RoomType temp = null;
				if (single.isSelected()) {
					temp = RoomType.SINGLE;
					single.setSelected(false);
				} else if (doubleRoom.isSelected()) {
					temp = RoomType.DOUBLE;
					doubleRoom.setSelected(false);
				} else if (lux.isSelected()) {
					temp = RoomType.LUX;
					lux.setSelected(false);
				} else {
					JOptionPane.showMessageDialog(frame, "Выберите необходимый тип комнаты.", "Ошибка",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Object[] answers = gui.guestBook.addTenant(tenant, temp);
				if (!(Boolean) answers[0]) {
					JOptionPane.showMessageDialog(frame, (String) answers[1], "Ошибка", JOptionPane.ERROR_MESSAGE);
					frame.setVisible(false);
					gui.getMainFrame().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, (String) answers[1], "Успех", JOptionPane.INFORMATION_MESSAGE);
					frame.setVisible(false);
					gui.getMainFrame().setVisible(true);
				}
				for (JTextField jTextField : textFields) {
					jTextField.setText("");
				}
			}
		});
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		JLabel roomTypeLabel = ComponentCreator.create("Необходимый нормер:", font);
		panel.setLayout(layout);
		labels.forEach(panel::add);
		textFields.forEach(panel::add);
		panel.add(addButton);
		panel.add(single);
		panel.add(doubleRoom);
		panel.add(lux);
		panel.add(roomTypeLabel);
		layout.putConstraint(SpringLayout.WEST, labels.get(0), 50, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labels.get(0), 25, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, textFields.get(0), 0, SpringLayout.WEST, labels.get(0));
		layout.putConstraint(SpringLayout.NORTH, textFields.get(0), 10, SpringLayout.SOUTH, labels.get(0));
		layout.putConstraint(SpringLayout.WEST, labels.get(1), 0, SpringLayout.WEST, labels.get(0));
		layout.putConstraint(SpringLayout.NORTH, labels.get(1), 75, SpringLayout.SOUTH, labels.get(0));
		layout.putConstraint(SpringLayout.WEST, textFields.get(1), 0, SpringLayout.WEST, labels.get(1));
		layout.putConstraint(SpringLayout.NORTH, textFields.get(1), 10, SpringLayout.SOUTH, labels.get(1));
		layout.putConstraint(SpringLayout.WEST, labels.get(2), 0, SpringLayout.WEST, labels.get(0));
		layout.putConstraint(SpringLayout.NORTH, labels.get(2), 75, SpringLayout.SOUTH, labels.get(1));
		layout.putConstraint(SpringLayout.WEST, textFields.get(2), 0, SpringLayout.WEST, labels.get(2));
		layout.putConstraint(SpringLayout.NORTH, textFields.get(2), 10, SpringLayout.SOUTH, labels.get(2));
		layout.putConstraint(SpringLayout.WEST, labels.get(3), 300, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, labels.get(3), 0, SpringLayout.NORTH, labels.get(0));
		layout.putConstraint(SpringLayout.WEST, textFields.get(3), 0, SpringLayout.WEST, labels.get(3));
		layout.putConstraint(SpringLayout.NORTH, textFields.get(3), 10, SpringLayout.SOUTH, labels.get(3));
		layout.putConstraint(SpringLayout.WEST, roomTypeLabel, 0, SpringLayout.WEST, textFields.get(3));
		layout.putConstraint(SpringLayout.NORTH, roomTypeLabel, 15, SpringLayout.SOUTH, textFields.get(3));
		layout.putConstraint(SpringLayout.WEST, single, 0, SpringLayout.WEST, textFields.get(3));
		layout.putConstraint(SpringLayout.NORTH, single, 15, SpringLayout.SOUTH, roomTypeLabel);
		layout.putConstraint(SpringLayout.WEST, doubleRoom, 0, SpringLayout.WEST, textFields.get(3));
		layout.putConstraint(SpringLayout.NORTH, doubleRoom, 15, SpringLayout.SOUTH, single);
		layout.putConstraint(SpringLayout.WEST, lux, 0, SpringLayout.WEST, textFields.get(3));
		layout.putConstraint(SpringLayout.NORTH, lux, 15, SpringLayout.SOUTH, doubleRoom);
		layout.putConstraint(SpringLayout.WEST, addButton, 235, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addButton, 345, SpringLayout.NORTH, panel);
		frame.add(panel);
		return frame;
	}

}
