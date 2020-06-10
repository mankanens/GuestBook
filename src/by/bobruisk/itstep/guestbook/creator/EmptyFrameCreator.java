package by.bobruisk.itstep.guestbook.creator;

import javax.swing.JFrame;

import by.bobruisk.itstep.guestbook.gui.GUI;

public class EmptyFrameCreator implements FrameCreator {

	@Override
	public JFrame create(String name, GUI gui) {
		JFrame frame = new JFrame(name);
		frame.setSize(720, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
