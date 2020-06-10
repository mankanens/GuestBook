package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import by.bobruisk.itstep.guestbook.gui.GUI;

public class OccupancyFrameCreator implements FrameCreator {

	private static OccupancyFrameCreator singleton = new OccupancyFrameCreator();

	private OccupancyFrameCreator() {
	}

	@Override
	public JFrame create(String name, GUI gui) {
		EmptyFrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		Font font = new Font("Bold", Font.BOLD, 24);
		JLabel singleLabel = ComponentCreator
				.create("Одиночных номеров свободно: " + gui.guestBook.getHotel().getSingleEmptyRoom(), font);
		JLabel doubleLabel = ComponentCreator
				.create("Двухместных номеров свободно: " + gui.guestBook.getHotel().getDoubleEmptyRoom(), font);
		JLabel luxLabel = ComponentCreator.create("Номеров люкс свободно: " + gui.guestBook.getHotel().getLuxEmpty(),
				font);
		JButton backButton = ComponentCreator.create("Назад", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.removeAll();
				gui.getMainFrame().setVisible(true);
			}
		});
		JPanel panel = new JPanel();
		panel.add(singleLabel);
		panel.add(doubleLabel);
		panel.add(luxLabel);
		panel.add(backButton);
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		layout.putConstraint(SpringLayout.WEST, singleLabel, 100, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, singleLabel, 25, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, doubleLabel, 0, SpringLayout.WEST, singleLabel);
		layout.putConstraint(SpringLayout.NORTH, doubleLabel, 25, SpringLayout.SOUTH, singleLabel);
		layout.putConstraint(SpringLayout.WEST, luxLabel, 0, SpringLayout.WEST, singleLabel);
		layout.putConstraint(SpringLayout.NORTH, luxLabel, 25, SpringLayout.SOUTH, doubleLabel);
		layout.putConstraint(SpringLayout.WEST, backButton, 250, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, backButton, 40, SpringLayout.NORTH, luxLabel);
		frame.add(panel);
		return frame;
	}

	public static OccupancyFrameCreator getOccupancyFrameCreator() {
		return singleton;
	}

}
