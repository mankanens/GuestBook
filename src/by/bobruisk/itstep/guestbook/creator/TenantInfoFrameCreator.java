package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import by.bobruisk.itstep.guestbook.Tenant;
import by.bobruisk.itstep.guestbook.gui.GUI;

public class TenantInfoFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		EmptyFrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		Font font = new Font("Bold", Font.BOLD, 16);
		JLabel title = ComponentCreator.create("Введите ФИО: ", font);
		JLabel tenantInfoLabel = ComponentCreator.create("Информация о жильце", font);
		JTextArea tenantInfoArea = ComponentCreator.create("", 10, 55);
		JLabel nameLabel = ComponentCreator.create("Имя:", font);
		JTextField nameText = ComponentCreator.create("", 10);
		JLabel surnameLabel = ComponentCreator.create("Фамилия:", font);
		JTextField surnameText = ComponentCreator.create("", 10);
		JLabel patronymicLabel = ComponentCreator.create("Отчество:", font);
		JTextField patronymicText = ComponentCreator.create("", 10);
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		JButton backButton = ComponentCreator.create("Назад", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				tenantInfoArea.setText("");
				nameText.setText("");
				surnameText.setText("");
				patronymicText.setText("");
				gui.getMainFrame().setVisible(true);
			}
		});
		JButton getButton = ComponentCreator.create("Получить информацию", new Dimension(250, 75),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Tenant tenant = gui.guestBook.getTenantInfo(nameText.getText(), surnameText.getText(),
								patronymicText.getText());
						if (tenant != null) {
							tenantInfoArea.setText(new StringBuilder().append("Номер паспорта: ")
									.append(tenant.getPassportID()).append("\n").append("Дата заезда: ")
									.append(tenant.getArrivalDate().toString()).append("\n").append("Номер комнаты: ")
									.append(tenant.getRoomNumber()).toString());
						} else {
							tenantInfoArea.setText("Постоялец с такими данными не найден.");
						}
					}
				});
		panel.setLayout(layout);
		panel.add(backButton);
		panel.add(patronymicText);
		panel.add(patronymicLabel);
		panel.add(surnameText);
		panel.add(surnameLabel);
		panel.add(nameText);
		panel.add(nameLabel);
		panel.add(tenantInfoArea);
		panel.add(tenantInfoLabel);
		panel.add(title);
		panel.add(getButton);
		layout.putConstraint(SpringLayout.WEST, title, 300, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, nameLabel, 25, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 75, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nameText, 10, SpringLayout.EAST, nameLabel);
		layout.putConstraint(SpringLayout.NORTH, nameText, 0, SpringLayout.NORTH, nameLabel);
		layout.putConstraint(SpringLayout.WEST, surnameLabel, 50, SpringLayout.EAST, nameText);
		layout.putConstraint(SpringLayout.NORTH, surnameLabel, 0, SpringLayout.NORTH, nameLabel);
		layout.putConstraint(SpringLayout.WEST, surnameText, 10, SpringLayout.EAST, surnameLabel);
		layout.putConstraint(SpringLayout.NORTH, surnameText, 0, SpringLayout.NORTH, surnameLabel);
		layout.putConstraint(SpringLayout.WEST, patronymicLabel, 50, SpringLayout.EAST, surnameText);
		layout.putConstraint(SpringLayout.NORTH, patronymicLabel, 0, SpringLayout.NORTH, surnameText);
		layout.putConstraint(SpringLayout.WEST, patronymicText, 10, SpringLayout.EAST, patronymicLabel);
		layout.putConstraint(SpringLayout.NORTH, patronymicText, 0, SpringLayout.NORTH, patronymicLabel);
		layout.putConstraint(SpringLayout.WEST, tenantInfoLabel, 275, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, tenantInfoLabel, 25, SpringLayout.SOUTH, surnameText);
		layout.putConstraint(SpringLayout.WEST, tenantInfoArea, 50, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, tenantInfoArea, 25, SpringLayout.NORTH, tenantInfoLabel);
		layout.putConstraint(SpringLayout.WEST, backButton, 73, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, backButton, 25, SpringLayout.SOUTH, tenantInfoArea);
		layout.putConstraint(SpringLayout.WEST, getButton, 73, SpringLayout.EAST, backButton);
		layout.putConstraint(SpringLayout.NORTH, getButton, 25, SpringLayout.SOUTH, tenantInfoArea);
		frame.add(panel);
		return frame;
	}

}
