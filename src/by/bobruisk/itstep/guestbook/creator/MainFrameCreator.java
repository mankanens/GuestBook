package by.bobruisk.itstep.guestbook.creator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import by.bobruisk.itstep.guestbook.gui.GUI;

public class MainFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		FrameCreator creator = new EmptyFrameCreator();
		JFrame frame = creator.create(name, gui);
		ArrayList<JButton> mainFrameButtons = new ArrayList<>();
		JPanel panel = new JPanel();
		SpringLayout layout = new SpringLayout();
		panel.setLayout(layout);
		mainFrameButtons.add(ComponentCreator.create("Добавить номер", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transitionAction(gui.getMainFrame(), gui.getAddRoomFrame());
			}
		}));
		mainFrameButtons
				.add(ComponentCreator.create("Заполненность отеля", new Dimension(250, 75), new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						FrameCreator creator = OccupancyFrameCreator.getOccupancyFrameCreator();
						JFrame occupancyFrame = creator.create("Заполненность отеля", gui);
						transitionAction(gui.getMainFrame(), occupancyFrame);
					}
				}));
		mainFrameButtons.add(ComponentCreator.create("Новый постоятлец", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transitionAction(gui.getMainFrame(), gui.getAddTenantFrame());
			}
		}));
		mainFrameButtons.add(ComponentCreator.create("Отметить отъезд", new Dimension(250, 75), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				transitionAction(gui.getMainFrame(), gui.getDepartureFrame());
			}
		}));
		mainFrameButtons
				.add(ComponentCreator.create("Информация о постояльце", new Dimension(250, 75), new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						transitionAction(gui.getMainFrame(), gui.getTenantInfoFrame());
					}
				}));
		mainFrameButtons.forEach(panel::add);
		layout.putConstraint(SpringLayout.WEST, mainFrameButtons.get(0), 60, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, mainFrameButtons.get(0), 45, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, mainFrameButtons.get(1), 60, SpringLayout.EAST,
				mainFrameButtons.get(0));
		layout.putConstraint(SpringLayout.NORTH, mainFrameButtons.get(1), 0, SpringLayout.NORTH,
				mainFrameButtons.get(0));
		layout.putConstraint(SpringLayout.WEST, mainFrameButtons.get(2), 0, SpringLayout.WEST, mainFrameButtons.get(0));
		layout.putConstraint(SpringLayout.NORTH, mainFrameButtons.get(2), 105, SpringLayout.NORTH,
				mainFrameButtons.get(0));
		layout.putConstraint(SpringLayout.WEST, mainFrameButtons.get(3), 0, SpringLayout.WEST, mainFrameButtons.get(1));
		layout.putConstraint(SpringLayout.NORTH, mainFrameButtons.get(3), 0, SpringLayout.NORTH,
				mainFrameButtons.get(2));
		layout.putConstraint(SpringLayout.WEST, mainFrameButtons.get(4), 0, SpringLayout.WEST, mainFrameButtons.get(0));
		layout.putConstraint(SpringLayout.NORTH, mainFrameButtons.get(4), 105, SpringLayout.NORTH,
				mainFrameButtons.get(2));
		frame.add(panel);
		return frame;
	}

	private void transitionAction(JFrame invisibleFrame, JFrame visibleFrame) {
		invisibleFrame.setVisible(false);
		visibleFrame.setVisible(true);
	}

}
